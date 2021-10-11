package josephusdanielJmartFA;

public class Product extends Recognizable
{
    public String name;
    public int weight;
    public boolean conditionUsed;
    public Treasury pricetag;
    public ProductCategory category;
    public ProductRating rating;
    public int accountId;
    public byte shipmentPlans;

    public Product(int accountId, String name, int weight, boolean conditionUsed, Treasury pricetag, ProductCategory category, byte shipmentPlans)
    {
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.pricetag = pricetag;
        this.category = category;
        this.rating = new ProductRating();
        this.accountId = accountId;
        this.shipmentPlans = shipmentPlans;
    }
    
    public String toString() {
        return "Name: " + this.name + "\n" + "Weight: " + this.weight + "\n" + "conditionUsed: " + this.conditionUsed + "\n" + "priceTag: " + this.pricetag.getAdjustedPrice() + "\n" + "category: " + this.category + "\n" + "rating: " + this.rating + "\n" + "accountId: " + this.accountId + "\n";
    }
}
