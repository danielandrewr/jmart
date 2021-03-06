package com.josephusdanielJmartFA.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.josephusdanielJmartFA.Account;
import com.josephusdanielJmartFA.Algorithm;
import com.josephusdanielJmartFA.Invoice;
import com.josephusdanielJmartFA.ObjectPoolThread;
import com.josephusdanielJmartFA.Payment;
import com.josephusdanielJmartFA.Payment.Record;
import com.josephusdanielJmartFA.Product;
import com.josephusdanielJmartFA.Shipment;
import com.josephusdanielJmartFA.dbjson.JsonAutowired;
import com.josephusdanielJmartFA.dbjson.JsonTable;

/**
 * Payment Control Class
 * @author Daniel
 *
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
	
	public static final long DELIVERED_LIMIT_MS = 1;
	public static final long ON_DELIVERY_LIMIT_MS = 1;
	public static final long ON_PROGRESS_LIMIT_MS = 1;
	public static final long WAITING_CONF_LIMIT_MS = 1;
	@JsonAutowired(filepath = "C:\\Users\\Daniel\\Documents\\Daniel\\Semester 3\\OOP\\Praktikum\\Modul 1\\jmart\\src\\main\\java\\com\\assets\\Payment.json", value = Payment.class)
	public static JsonTable<Payment> paymentTable;
	public static ObjectPoolThread<Payment> poolThread; 
//	static {
//		new ObjectPoolThread<Payment>("Payment-Thread", PaymentController::timekeeper);
//		poolThread.start();
//	}
	
	/**
	 * Accepts a payment, Used by Store Owner
	 * @param id
	 * @return
	 */
	@PostMapping("/{id}/accept")
	public boolean accept(@PathVariable int id) {
		for (Payment payment : getJsonTable()) {
			if (payment.id == id && (payment.history.get(payment.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION)) {
				payment.history.add(new Record(Invoice.Status.ON_PROGRESS, "Dalam Proses"));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Cancels a payment, Used by Store Owner or Buyer
	 * @param id
	 * @return
	 */
	@PostMapping("/{id}/cancel")
	public boolean cancel(@PathVariable int id) {
		for (Payment payment : getJsonTable()) {
			if (payment.id == id && (payment.history.get(payment.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION)) {
				payment.history.add(new Record(Invoice.Status.CANCELLED, "DIBATALKAN"));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates a new payment
	 * @param buyerId
	 * @param productId
	 * @param productCount
	 * @param shipmentAddress
	 * @param shipmentPlan
	 * @return
	 */
	@PostMapping("/create")
	public Payment create(@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan) {
		Account account = Algorithm.<Account>find(AccountController.accountTable, (e) -> e.id == buyerId);
		Product product = Algorithm.<Product>find(ProductController.productTable, (e) -> e.id == productId);
		
		if ((account != null) && (product != null)) {
			Payment payment = new Payment(buyerId, productId, productCount, null);
			double totalPriceToPay = payment.getTotalPay(product) * productId;
			
			if (account.balance >= totalPriceToPay ) {
				Shipment shipmentDetail = new Shipment(shipmentAddress, 0, shipmentPlan, null);
				account.balance -= totalPriceToPay; // Cek lagi nanti bagian ini
				payment = new Payment(buyerId, productId, productCount, shipmentDetail);
				payment.history.add(new Record(Invoice.Status.WAITING_CONFIRMATION, "Menunggu Konfirmasi"));
				paymentTable.add(payment);
//				poolThread.add(payment);
				return payment;
			}
		}
		return null;
	}
	
	/**
	 * Get jsonTable
	 */
	public JsonTable<Payment> getJsonTable() {
		return paymentTable;
	}
	
	/**
	 * Submits a payment for delivery, Used by Store Owner
	 * @param id
	 * @param receipt
	 * @return
	 */
	@PostMapping("/{id}/submit")
	public boolean submit(@PathVariable int id, @RequestParam String receipt) {
		for (Payment payment : getJsonTable()) {
			if (payment.id == id && (payment.history.get(payment.history.size()-1).status == Invoice.Status.ON_PROGRESS)) {
				if (!receipt.isBlank()) {
					payment.shipment.receipt = receipt;
					payment.history.add(new Record(Invoice.Status.ON_DELIVERY, "Dalam Pengiriman"));
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Get Payment with matching accountId to a List of Payment
	 * @param accountId
	 * @return
	 */
	@GetMapping("/getPayments")
	public List<Payment> getPayment(@RequestParam int accountId) {
		List<Payment> payments = Algorithm.<Payment>collect(getJsonTable(), (e) -> e.buyerId == accountId);
		return payments;
	}
	
	/**
	 * Get Payment with matching accountId to a List of Payment for Store Owner
	 * @param accountId
	 * @return
	 */
	@GetMapping("/getStorePayments")
	public List<Payment> getStorePayments(@RequestParam int accountId) {
		List<Payment> filtered = new ArrayList<>();
		int tempId = 0;
		for (Product product : ProductController.productTable) {
			if (product.accountId == accountId) {
				tempId = product.id;
				for (Payment payment : paymentTable) {
					if (payment.productId == tempId) {
						filtered.add(payment);
					}
				}
			}
		}
		return filtered;
	}
	
//	private static boolean timekeeper(Payment payment) {
//		long startTime = System.currentTimeMillis();
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
//		if (record.status == Invoice.Status.FAILED && record.status == Invoice.Status.FINISHED) {
//			return true;
//		}
//		return false;
//	}
}