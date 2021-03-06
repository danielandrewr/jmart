package com.josephusdanielJmartFA.controller;


import com.josephusdanielJmartFA.Account;
import com.josephusdanielJmartFA.Store;
import com.josephusdanielJmartFA.dbjson.JsonAutowired;
import com.josephusdanielJmartFA.dbjson.JsonTable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.*;

/**
 * Account Control Class
 * @author Daniel
 *
 */
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
	
	/**
	 * Post Method to Login
	 * @param email
	 * @param password
	 * @return
	 */
	@PostMapping("/login")
	public Account login(@RequestParam String email, @RequestParam String password) {
		for (Account account : getJsonTable()) {
			if (account.email.equals(email) && account.password.equals(hashMD5(password))) {
				return account;
			}
		}
		return null;
	}
	
	/**
	 * Post Method to Register
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	@PostMapping("/register")
	public Account register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		if (!name.isBlank()) {
			if (REGEX_PATTERN_EMAIL.matcher(email).matches() && REGEX_PATTERN_PASSWORD.matcher(password).matches()) {
				for (Account account : getJsonTable()) {
					if (account.email.equals(email)) {
						return null;
					}
				}
				password = hashMD5(password);
				Account newAccount = new Account(name, email, password, 0.0);
				accountTable.add(newAccount);
				return newAccount;
			}
		}
		return null;
	}
	
	/**
	 * Post Method to Register Store
	 * @param id
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @return
	 */
	@PostMapping("/{id}/registerStore")
	public Store registerStore(@PathVariable int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber) {
		Store newStore = new Store(name, address, phoneNumber, 0.0);
		for (Account account : accountTable) {
			if (account.id == id && account.store == null) {
				account.store = newStore;
				return newStore;
			}
		}
		return null;
	}
	
	/**
	 * Post Method to Top Balance Up
	 * @param id
	 * @param balance
	 * @return
	 */
	@PostMapping("/{id}/topUp")
	public boolean topUp(@PathVariable int id, @RequestParam double balance) {
		for (Account account : accountTable) {
			if (account.id == id) {
				account.balance += balance;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Hashes a password string to MD5
	 * @param password
	 * @return
	 */
	public String hashMD5(String password) {
		String hashedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; ++i) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			hashedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashedPassword;
	}
}
