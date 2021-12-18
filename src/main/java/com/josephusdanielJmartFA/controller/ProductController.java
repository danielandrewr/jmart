package com.josephusdanielJmartFA.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.josephusdanielJmartFA.Account;
import com.josephusdanielJmartFA.Algorithm;
import com.josephusdanielJmartFA.Product;
import com.josephusdanielJmartFA.ProductCategory;
import com.josephusdanielJmartFA.dbjson.JsonAutowired;
import com.josephusdanielJmartFA.dbjson.JsonTable;

/**
 * Product Control Class
 * @author Daniel
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {

	@JsonAutowired(filepath = "C:\\Users\\Daniel\\Documents\\Daniel\\Semester 3\\OOP\\Praktikum\\Modul 1\\jmart\\src\\main\\java\\com\\assets\\Product.json", value = Product.class)
	public static JsonTable<Product> productTable;
	
	/**
	 * Create a New Product
	 * @param accountId
	 * @param name
	 * @param weight
	 * @param conditionUsed
	 * @param price
	 * @param discount
	 * @param category
	 * @param shipmentPlans
	 * @return
	 */
	@PostMapping("/create")
	public Product create(@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed, @RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category, @RequestParam byte shipmentPlans) {
		JsonTable<Account> account = AccountController.accountTable;
		for (Account acc : account) {
			if ((acc.id == accountId) && (acc.store != null)) {
				Product newProduct = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
				productTable.add(newProduct);
				return newProduct;
			}
		}
		return null;
	}
	
	/**
	 * Returns product jsontable
	 */
	public JsonTable<Product> getJsonTable() {
		return productTable;
	}
	
	/**
	 * Creates New Account's Store
	 * @param id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/{id}/store")
	public List<Product> getProductByStore(@PathVariable int id, int page, int pageSize) {
		List<Product> paginated = Algorithm.<Product>paginate(getJsonTable(), page, pageSize, (e) -> e.accountId == id);
		return paginated;
	}
	
	/**
	 * Filter Products based on some parameters/conditions
	 * @param page
	 * @param pageSize
	 * @param accountId
	 * @param search
	 * @param minPrice
	 * @param maxPrice
	 * @param category
	 * @param conditionUsed
	 * @return
	 */
	@GetMapping("/getFiltered")
	public List<Product> getProductFiltered(@RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="5") int pageSize, @RequestParam int accountId, @RequestParam String search, @RequestParam int minPrice, @RequestParam int maxPrice, @RequestParam ProductCategory category, @RequestParam boolean conditionUsed) {
		List<Product> filtered = new ArrayList<>();
		
		for (Product product : getJsonTable()) {
            if (product.name.toLowerCase().contains(search.toLowerCase())) {
                if (product.conditionUsed == conditionUsed) {
                    if (product.category == category) {
                        if (maxPrice == 0.0 && minPrice != 0.0) {
                            if (product.price >= minPrice) 
                                filtered.add(product);
                        } else if (product.price >= minPrice && product.price <= maxPrice)
                                filtered.add(product);
                    }
                }
            }
        }
		
		return filtered;
	}
	
	/**
	 * Get a product by productId
	 * @param productId
	 * @return
	 */
	@GetMapping("/getProductById")
	public Product getProductById(@RequestParam int productId) {
		Product foundProduct = Algorithm.<Product>find(getJsonTable(), (e) -> e.id == productId);
		if (foundProduct != null) {
			return foundProduct;
		}
		return null;
	}
	
	/**
	 * Get a product by names
	 * @param search
	 * @return
	 */
	@GetMapping("/getProductByName")
	public Product getProductByName(@RequestParam String search) {
		Product foundProduct = Algorithm.<Product>find(getJsonTable(), (e) -> e.name.toLowerCase().equals(search.toLowerCase()));
		if (foundProduct != null) {
			return foundProduct;
		}
		return null;
	}
}
