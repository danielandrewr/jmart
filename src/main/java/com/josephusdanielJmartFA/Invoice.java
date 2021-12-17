package com.josephusdanielJmartFA;

import java.util.Date;

import com.josephusdanielJmartFA.dbjson.Serializable;

/**
 * Invoice Class Model
 * @author Daniel
 *
 */
public abstract class Invoice extends Serializable
{
    public enum Status { WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED, DELIVERED };
    public enum Rating { NONE, BAD, NEUTRAL, GOOD };
    
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    
    /**
     * Invoice Constructor
     * @param buyerId
     * @param productId
     */
    protected Invoice(int buyerId, int productId) {
        this.buyerId = buyerId;
        this.productId = productId;
        this.date = java.util.Calendar.getInstance().getTime();
        this.rating = Rating.NONE;
        this.complaintId = -1;
    }
    
    /**
     * Abstract method to count Total Pay
     * @param product
     * @return
     */
    public abstract double getTotalPay(Product product);
}
