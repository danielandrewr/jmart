package com.josephusdanielJmartFA;

import com.josephusdanielJmartFA.dbjson.JsonDBEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jmart {
	
    public static void main(String[] args) {
    	/**
    	 * Runs Json Database
    	 */
    	JsonDBEngine.Run(Jmart.class);
    	SpringApplication.run(Jmart.class, args);
    	Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()) );
    }
    
//    public static List<Product> filterByCategory(List<Product> product, ProductCategory category) {
//    	List<Product> filtered = Algorithm.<Product>collect(product, (e) -> e.category == category);
//    	return filtered;
//    }
//    
//    public static List<Product> filterByPrice(List<Product> product, double min, double max) {
//    	List<Product> filtered = new ArrayList<Product>();
//    	
//    	if (min == 0.0) {
//    		filtered = Algorithm.<Product>collect(product, (e) -> e.price <= max);
//    	} else if (max == 0.0) {
//    		filtered = Algorithm.<Product>collect(product, (e) -> e.price >= min);
//    	} else {
//    		filtered = Algorithm.<Product>collect(product, (e) -> e.price >= min && e.price <= max);
//    	}
//    	return filtered;
//    }
//    
//    private static List<Product> paginate(List<Product> list, int page, int pageSize, Predicate<Product> pred) {
//       List<Product> paginated = new ArrayList<>();
//       int index = 0;
//       int startingPage = page * pageSize;
//       int lastPage = startingPage + pageSize;
//       
//       for (Product prod : list) {
//    	   if (index >= startingPage && index < lastPage && (pred.predicate(prod) == true)) {
//    		   paginated.add(prod);
//    	   }
//    	   index++;
//       }
//       
//       return paginated;
//    }
//    
//    public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize) {
//    	List<Product> filtered = Algorithm.<Product>collect(list, (e) -> e.name.toLowerCase().contains(search.toLowerCase()));
//    	return paginate(filtered, page, pageSize, (e) -> e.name.toLowerCase().contains(search.toLowerCase()));
//    }
//    
//	public static List<Product> filterByAccountId(List<Product> list, int accountId, int page, int pageSize) {
//    	List<Product> filtered = Algorithm.<Product>collect(list, (e) -> e.accountId == accountId);
//    	return paginate(filtered, page, pageSize, (e) -> e.accountId == accountId);
//    }
}
