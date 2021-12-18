package com.josephusdanielJmartFA.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.josephusdanielJmartFA.Algorithm;
import com.josephusdanielJmartFA.Coupon;
import com.josephusdanielJmartFA.dbjson.JsonAutowired;
import com.josephusdanielJmartFA.dbjson.JsonTable;

/**
 * Coupon Control Class
 * @author Daniel
 *
 */
@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {

	@JsonAutowired(filepath = "C:\\Users\\Daniel\\Documents\\Daniel\\Semester 3\\OOP\\Praktikum\\Modul 1\\jmart\\src\\main\\java\\com\\assets\\Coupon.json", value = Coupon.class)
	public static JsonTable<Coupon> couponTable;
	
	public JsonTable<Coupon> getJsonTable() {
		return couponTable;
	}
	
	/**
	 * Returns a boolean value if a coupon is valid to be used or not
	 * @param id
	 * @param price
	 * @param discount
	 * @return
	 */
	@GetMapping("/{id}/canApply")
	public boolean canApply(@PathVariable int id, @RequestParam double price, @RequestParam double discount) {
		for (Coupon coupon : getJsonTable()) {
			return coupon.canApply(price, discount);
		}
		return false;
	}
	
	/**
	 * Collects available coupons from Coupon.json
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/getAvailable")
	public List<Coupon> getAvailable(@RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="5") int pageSize) {
		List<Coupon> paginated = Algorithm.<Coupon>paginate(getJsonTable(), page, pageSize, (e) -> e.isUsed() == false);
		return paginated;
	}
	
	/**
	 * Returns a boolean value if a coupon has been used or not
	 * @param id
	 * @return
	 */
	@GetMapping("{id}/isUsed")
	public boolean isUsed(@PathVariable int id) {
		for (Coupon coupon : getJsonTable()) {
			if (coupon.id == id) return coupon.isUsed();
		}
		return false;
	}
}
