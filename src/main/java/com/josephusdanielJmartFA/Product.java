package com.josephusdanielJmartFA;

import com.josephusdanielJmartFA.dbjson.Serializable;

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
    
    public String toString() {
        return "Name: " + this.name + "\n" + "Weight: " + this.weight + "\n" + "conditionUsed: " + this.conditionUsed + "\n"  + "\n" + "accountId: " + this.accountId + "\n";
    }
}
