package com.josephusdanielJmartFA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
//import com.josephusdanielJmartFA.Payment.Record;
import com.josephusdanielJmartFA.dbjson.JsonDBEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jmart {
	
	class Country {
		public String name;
		public int population;
		public List<String> listOfStates;
	}
	
    public static void main(String[] args) {
    	JsonDBEngine.Run(Jmart.class);
    	SpringApplication.run(Jmart.class, args);
    	Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()) );
//    	try {
//    		JsonTable<Payment> table = new JsonTable<>(Payment.class, "C:\\Users\\Daniel\\Documents\\Daniel\\Semester 3\\OOP\\Praktikum\\Modul 1\\jmart\\assets\\randomPaymentList.json");
//    		ObjectPoolThread<Payment> paymentPool = new ObjectPoolThread<Payment>("Thread-PP", Jmart::paymentTimekeeper);
//    		paymentPool.start();
//    		table.forEach(payment -> paymentPool.add(payment));
//    		while (paymentPool.size() != 0);
//    		paymentPool.exit();
//    		while (paymentPool.isAlive());
//    		System.out.println("Thread exited successfully");
//    		Gson gson = new Gson();
//    		table.forEach(payment -> {
//    			String history = gson.toJson(payment.history);
//    			System.out.println(history);
//    		});
//    	} catch (Throwable t) {
//    		t.printStackTrace();
//    	}
    }
    
//    public static boolean paymentTimekeeper(Payment payment) {
//    	long startTime = System.currentTimeMillis();
//    	
//    	Record record = payment.history.get(payment.history.size() - 1);
//		long time_elapsed = System.currentTimeMillis() - startTime;
//		if (record.status == Invoice.Status.WAITING_CONFIRMATION && time_elapsed > WAITING_CONF_LIMIT_MS) {
//			payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
//		} else if (record.status == Invoice.Status.ON_PROGRESS && time_elapsed > ON_PROGRESS_LIMIT_MS) {
//			payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
//		} else if (record.status == Invoice.Status.ON_DELIVERY && time_elapsed > ON_DELIVERY_LIMIT_MS) {
//			payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Berhasil"));
//		} else if (record.status == Invoice.Status.DELIVERED && time_elapsed > DELIVERED_LIMIT_MS) {
//			payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Berhasil"));
//		}
//		
//		if (record.status == Invoice.Status.FAILED && record.status == Invoice.Status.FINISHED) {
//			return true;
//		}
//		
//		return false;
//    }
    
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
