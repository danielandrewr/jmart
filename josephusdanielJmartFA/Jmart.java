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
    		List<Product> filtered = filterByPrice(list, 0.0, 20000.0);
    		filtered.forEach(product -> System.out.println(product.price));
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
    	
    	for (Product prod : product) {
    		if (min == 0.0 && prod.price < min) {
    			continue;
    		}
    		if (max == 0.0 && prod.price > max) {
				continue;
			}
    		filtered.add(prod);
    	}
    	return filtered;
    }
}
