package com.josephusdanielJmartFA;

public class PhoneTopUp extends Invoice {

	public String phoneNumber;
	public Status status;
	
	public PhoneTopUp(int buyerId, int productId, String phoneNumber, Status status) {
		super(buyerId, productId);
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	@Override
	public double getTotalPay(Product product) {
		return product.price - ((product.discount/100) * product.price);
	}
}
