package com.josephusdanielJmartFA.controller;


import com.josephusdanielJmartFA.Account;
import com.josephusdanielJmartFA.Store;
import com.josephusdanielJmartFA.dbjson.JsonAutowired;
import com.josephusdanielJmartFA.dbjson.JsonTable;

import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
	
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9_&_*~]+(?:\\.[a-zA-Z0-9_&_*~]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9-]+)*$";
	public static final String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
	@JsonAutowired(filepath = "C:\\Users\\Daniel\\Documents\\Daniel\\Semester 3\\OOP\\Praktikum\\Modul 1\\jmart\\src\\main\\java\\com\\assets\\Account.json", value = Account.class)
	public static JsonTable<Account> accountTable;
	
	public JsonTable<Account> getJsonTable() {
		return accountTable;
	}
	
	@PostMapping("/login")
	public Account login(@RequestParam String email, @RequestParam String password) {
		for (Account account : getJsonTable()) {
			if (account.email.equals(email) && account.password.equals(password)) {
				return account;
			}
		}
		return null;
	}
	
	@PostMapping("/register")
	public Account register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		Account newAccount = new Account(name, email, password, 0.0);
		if ((!name.isBlank())) {
			if (REGEX_PATTERN_EMAIL.matcher(email).matches() && REGEX_PATTERN_PASSWORD.matcher(password).matches()) {
				for (Account account : getJsonTable()) {
					if (account.email.equals(email)) {
						return null;
					}
				}
				accountTable.add(newAccount);
				return newAccount;
			}
		}
		return null;
	}
	
	@PostMapping("/{id}/registerStore")
	public Store registerStore(int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber) {
		Store newStore = new Store(name, address, phoneNumber, 0.0);
		for (Account account : accountTable) {
			if (account.id == id && account.store == null) {
				account.store = newStore;
				return newStore;
			}
		}
		return null;
	}
	
	@PostMapping("/{id}/topUp")
	public boolean topUp(int id, @RequestParam double balance) {
		for (Account account : accountTable) {
			if (account.id == id) {
				account.balance += balance;
				return true;
			}
		}
		return false;
	}
	
//	@GetMapping
//	String index() { return "account page"; }
//	
//	@PostMapping("/register")
//	Account register
//	(
//		@RequestParam String name,
//		@RequestParam String email,
//		@RequestParam String password
//	)
//	{
//		return new Account(name, email, password, 0);
//	}
//	
//	@GetMapping("/{id}")
//	String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}
