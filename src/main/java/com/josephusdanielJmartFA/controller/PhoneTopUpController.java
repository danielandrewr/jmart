package com.josephusdanielJmartFA.controller;

import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josephusdanielJmartFA.Account;
import com.josephusdanielJmartFA.Algorithm;
import com.josephusdanielJmartFA.Invoice;
import com.josephusdanielJmartFA.PhoneTopUp;
import com.josephusdanielJmartFA.Product;
import com.josephusdanielJmartFA.dbjson.JsonAutowired;
import com.josephusdanielJmartFA.dbjson.JsonTable;

@RestController
@RequestMapping("/phoneTopUp")
public class PhoneTopUpController implements BasicGetController<PhoneTopUp> {
	
	private static final String REGEX_PHONE = "^(\\+62|62|0)8[1-9][0-9]{6,9}$";
	private static final Pattern REGEX_PHONE_PATTERN = Pattern.compile(REGEX_PHONE);

	@JsonAutowired(filepath = "\\assets\\PhoneTopUp.json", value = PhoneTopUp.class)
	public static JsonTable<PhoneTopUp> phoneTopUpTable;
	
	@PostMapping("/create")
	public PhoneTopUp create(@RequestParam int buyerId, @RequestParam int productId, @RequestParam String phoneNumber) {
		Account account = Algorithm.<Account>find(AccountController.accountTable, (e) -> e.id == buyerId);
		Product product = Algorithm.<Product>find(ProductController.productTable, (e) -> e.id == productId);
		
		PhoneTopUp phoneTopUp;
		if (REGEX_PHONE_PATTERN.matcher(phoneNumber).matches()) {
			if (account != null && product != null && product.price <= account.balance) {
				phoneTopUp = new PhoneTopUp(buyerId, productId, phoneNumber, Invoice.Status.FINISHED);
				account.balance -= product.price;
			} else {
				phoneTopUp = new PhoneTopUp(buyerId, productId, phoneNumber, Invoice.Status.FAILED);
			}
			phoneTopUpTable.add(phoneTopUp);
			return phoneTopUp;
		}
		return null;
	}
	
	@Override
	public JsonTable<PhoneTopUp> getJsonTable() {
		return phoneTopUpTable;
	}

}
