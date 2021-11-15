package josephusdanielJmartFA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import josephusdanielJmartFA.Invoice.Status;
import josephusdanielJmartFA.Payment.Record;

public class Jmart {
	
	public static long DELIVERED_LIMIT_MS = 10;
	public static long ON_DELIVERY_LIMIT_MS = 10;
	public static long ON_PROGRESS_LIMIT_MS = 10;
	public static long WAITING_CONF_LIMIT_MS = 10;
	
	class Country {
		public String name;
		public int population;
		public List<String> listOfStates;
	}
	
    public static void main(String[] args) {
    	try {
    		JsonTable<Payment> table = new JsonTable<>(Payment.class, "randomPaymentList.json");
    		ObjectPoolThread<Payment> paymentPool = new ObjectPoolThread<Payment>("Thread-PP", Jmart::paymentTimekeeper);
    		paymentPool.start();
    		table.forEach(payment -> paymentPool.add(payment));
    		while (paymentPool.size() != 0);
    		paymentPool.exit();
    		while (paymentPool.isAlive());
    		System.out.println("Thread exited successfully");
    		Gson gson = new Gson();
    		table.forEach(payment -> {
    			String history = gson.toJson(payment.history);
    			System.out.println(history);
    		});
    	} catch (Throwable t) {
    		t.printStackTrace();
    	}
    }
    
    public static boolean paymentTimekeeper(Payment payment) {
    	long startTime = System.currentTimeMillis();
    	for (Record record : payment.history) {
    		
    		long time_elapsed = System.currentTimeMillis() - startTime;
    		
    		if (record.status == Status.WAITING_CONFIRMATION && time_elapsed > WAITING_CONF_LIMIT_MS) {
    			record.status = Invoice.Status.FAILED;
    		} else if (record.status == Status.ON_PROGRESS && time_elapsed > ON_PROGRESS_LIMIT_MS) {
    			record.status = Invoice.Status.FAILED;
    		} else if (record.status == Status.ON_DELIVERY && time_elapsed > ON_DELIVERY_LIMIT_MS) {
    			record.status = Invoice.Status.DELIVERED;
    		} else if (record.status == Status.DELIVERED && time_elapsed > DELIVERED_LIMIT_MS) {
    			record.status = Invoice.Status.FINISHED;
    		}
    	}
		return false;
    }
    
    public static List<Product> read(String filepath) throws FileNotFoundException {
    	JsonReader jr = new JsonReader(new FileReader(filepath));
    	Product[] products = new Gson().fromJson(jr, Product[].class);
    	
    	List<Product> list = new ArrayList<Product>();
    	Collections.addAll(list, products);
    	
    	return list;
    }
    
    public static List<Product> filterByCategory(List<Product> product, ProductCategory category) {
    	List<Product> filtered = Algorithm.<Product>collect(product, (e) -> e.category == category);
    	return filtered;
    }
    
    public static List<Product> filterByPrice(List<Product> product, double min, double max) {
    	List<Product> filtered = new ArrayList<Product>();
    	
    	if (min == 0.0) {
    		filtered = Algorithm.<Product>collect(product, (e) -> e.price <= max);
    	} else if (max == 0.0) {
    		filtered = Algorithm.<Product>collect(product, (e) -> e.price >= min);
    	} else {
    		filtered = Algorithm.<Product>collect(product, (e) -> e.price >= min && e.price <= max);
    	}
    	return filtered;
    }
    
    private static List<Product> paginate(List<Product> list, int page, int pageSize, Predicate<Product> pred) {
       List<Product> paginated = new ArrayList<>();
       int index = 0;
       int startingPage = page * pageSize;
       int lastPage = startingPage + pageSize;
       
       for (Product prod : list) {
    	   if (index >= startingPage && index < lastPage && (pred.predicate(prod) == true)) {
    		   paginated.add(prod);
    	   }
    	   index++;
       }
       
       return paginated;
    }
    
    public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize) {
    	List<Product> filtered = Algorithm.<Product>collect(list, (e) -> e.name.toLowerCase().contains(search.toLowerCase()));
    	return paginate(filtered, page, pageSize, (e) -> e.name.toLowerCase().contains(search.toLowerCase()));
    }
    
	public static List<Product> filterByAccountId(List<Product> list, int accountId, int page, int pageSize) {
    	List<Product> filtered = Algorithm.<Product>collect(list, (e) -> e.accountId == accountId);
    	return paginate(filtered, page, pageSize, (e) -> e.accountId == accountId);
    }
}
