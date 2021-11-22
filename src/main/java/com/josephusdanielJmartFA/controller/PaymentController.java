package com.josephusdanielJmartFA.controller;

import org.springframework.web.bind.annotation.*;

import com.josephusdanielJmartFA.Invoice;
import com.josephusdanielJmartFA.ObjectPoolThread;
import com.josephusdanielJmartFA.Payment;
import com.josephusdanielJmartFA.Payment.Record;
import com.josephusdanielJmartFA.dbjson.JsonAutowired;
import com.josephusdanielJmartFA.dbjson.JsonTable;

@RestController
@RequestMapping("/payment")
public abstract class PaymentController implements BasicGetController<Payment> {

	public static final long DELIVERED_LIMIT_MS = 10;
	public static final long ON_DELIVERY_LIMIT_MS = 10;
	public static final long ON_PROGRESS_LIMIT_MS = 10;
	public static final long WAITING_CONF_LIMIT_MS = 10;
	@JsonAutowired(filepath = "C:\\Users\\Daniel\\Documents\\Daniel\\Semester 3\\OOP\\Praktikum\\Modul 1\\jmart\\src\\main\\java\\com\\assets\\Payment.json", value = Payment.class)
	public static JsonTable<Payment> paymentTable;
	public static ObjectPoolThread<Payment> poolThread; 
	static {
		new ObjectPoolThread<Payment>("Payment-Thread", PaymentController::timekeeper);
		poolThread.start();
	}
	
	@PostMapping("/{id}/accept")
	public boolean accept(@PathVariable int id) {
		return false;
	}
	
	@PostMapping("/{id}/cancel")
	public boolean cancel(@PathVariable int id) {
		return false;
	}
	
	@PostMapping("/create")
	public Payment create(@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan) {
		return null;
	}
	
	public JsonTable<Payment> getJsonTable() {
		return paymentTable;
	}
	
	@PostMapping("/{id}/submit")
	public boolean submit(@PathVariable int id, @RequestParam String receipt) {
		return false;
	}
	
	private static boolean timekeeper(Payment payment) {
		long startTime = System.currentTimeMillis();
    	
    	Record record = payment.history.get(payment.history.size() - 1);
		long time_elapsed = System.currentTimeMillis() - startTime;
		if (record.status == Invoice.Status.WAITING_CONFIRMATION && time_elapsed > WAITING_CONF_LIMIT_MS) {
			payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
		} else if (record.status == Invoice.Status.ON_PROGRESS && time_elapsed > ON_PROGRESS_LIMIT_MS) {
			payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
		} else if (record.status == Invoice.Status.ON_DELIVERY && time_elapsed > ON_DELIVERY_LIMIT_MS) {
			payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Berhasil"));
		} else if (record.status == Invoice.Status.DELIVERED && time_elapsed > DELIVERED_LIMIT_MS) {
			payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Berhasil"));
		}
		if (record.status == Invoice.Status.FAILED && record.status == Invoice.Status.FINISHED) {
			return true;
		}
		return false;
	}
}