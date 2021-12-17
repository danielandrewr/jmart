package com.josephusdanielJmartFA;

import com.josephusdanielJmartFA.dbjson.Serializable;

/**
 * Product Model Class
 * @author Daniel
 *
 */
public class Product extends Serializable
{
    public String name;
    public int weight;
    public boolean conditionUsed;
    public ProductCategory category;
    public double price;
    public double discount;
    public int accountId;
    public byte shipmentPlans;

    /**
     * Product Constructor
     * @param accountId
     * @param name
     * @param weight
     * @param conditionUsed
     * @param price
     * @param discount
     * @param category
     * @param shipmentPlans
     */
    public Product(int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans)
    {
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.category = category;
        this.discount = discount;
        this.accountId = accountId;
        this.shipmentPlans = shipmentPlans;
    }
    
    /**
     * Convert from Product objects to readable string
     */
    public String toString() {
        return "Name: " + this.name + "\n" + "Weight: " + this.weight + "\n" + "conditionUsed: " + this.conditionUsed + "\n"  + "\n" + "accountId: " + this.accountId + "\n";
    }
}
