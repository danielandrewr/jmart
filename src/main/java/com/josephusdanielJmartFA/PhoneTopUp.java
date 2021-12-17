package com.josephusdanielJmartFA;

/**
 * PhoneTopUp Class Model
 * @author Daniel
 *
 */
public class PhoneTopUp extends Invoice {

	public String phoneNumber;
	public Status status;
	
	/**
	 * PhoneTopUp Constructor
	 * @param buyerId
	 * @param productId
	 * @param phoneNumber
	 * @param status
	 */
	public PhoneTopUp(int buyerId, int productId, String phoneNumber, Status status) {
		super(buyerId, productId);
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	/**
     * Finds the total pay of a product
     */
	@Override
	public double getTotalPay(Product product) {
		return product.price - ((product.discount/100) * product.price);
	}
}
