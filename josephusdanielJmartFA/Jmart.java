package josephusdanielJmartFA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class Jmart {
	
	class Country {
		public String name;
		public int population;
		public List<String> listOfStates;
	}
	
    public static void main(String[] args) {
    	String filePath = "C:/Users/Daniel/Documents/Daniel/Semester 3/OOP/Praktikum/Modul 1/jmart/assets/city.json";
    	Gson gson = new Gson();
    	
    	try {
    		BufferedReader br = new BufferedReader(new FileReader(filePath));
    		Country input = gson.fromJson(br, Country.class);
    		System.out.println("name: " + input.name);
    		System.out.println("population: " + input.population);
    		System.out.println("states: ");
    		input.listOfStates.forEach(state -> System.out.println(state));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	System.out.println("account id: " + new Account(null ,null, null, -1).id);
    	System.out.println("account id: " + new Account(null ,null, null, -1).id);
    	System.out.println("account id: " + new Account(null ,null, null, -1).id);
    	
    	System.out.println("payment id: " + new Payment(-1, -1, -1, null).id);
    	System.out.println("payment id: " + new Payment(-1, -1, -1, null).id);
    	System.out.println("payment id: " + new Payment(-1, -1, -1, null).id);
    	
    	try {
    		List<Product> list = read("C:/Users/Daniel/Documents/Daniel/Semester 3/OOP/Praktikum/Modul 1/jmart/assets/randomProductList.json");
    		List<Product> filtered = filterByName(list, "gtx", 1, 5);
    		//List<Product> filtered = filterByAccountId(list, 10, 1, 5);
    		filtered.forEach(product -> System.out.println(filtered));
    	} catch (Throwable t) {
    		t.printStackTrace();
    	}
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
    	if (page <= 0 || pageSize <= 0) {
    		throw new IllegalArgumentException("Invalid Input!");
    	}
    	
    	List<Product> paginated = new ArrayList<>();
    	
    	for (Product product : list) {
    		if (pred.predicate(product) == true) {
    			paginated.add(product);
    		}
    	}
    	
    	int index = (page - 1) * pageSize;
    	if (paginated == null || paginated.size() <= index) {
    		return Collections.emptyList();
    	}
    	
    	int floorPage = Math.min(index + pageSize, paginated.size());
    	return paginated.subList(index, floorPage);
    }
    
    public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize) {
    	List<Product> filtered = new ArrayList<>();
    	
    	for (Product product : list) {
    		if (product.name.toLowerCase() == search.toLowerCase()) {
    			filtered.add(product);
    		}
    	}
    	return paginate(filtered, page, pageSize, (e) -> e.name.toLowerCase() == search.toLowerCase());
    }
    
	public static List<Product> filterByAccountId(List<Product> list, int accountId, int page, int pageSize) {
    	List<Product> filtered = new ArrayList<>();
    	
    	for (Product product : list) {
    		if (product.accountId == accountId) {
    			filtered.add(product);
    		}
    	}
    	
    	return paginate(filtered, page, pageSize, (e) -> e.accountId == accountId);
    }
}
