package josephusdanielJmartFA;


/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Product extends Recognizeable implements FileParser
{
    // instance variables - replace the example below with your own
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag pricetag;
    public ProductCategory category;
    public ProductRating rating;
    public int storeId;

    /**
     * Constructor for objects of class Product
     */
    public Product(int id, int storeId, String name, int weight, boolean conditionUsed, PriceTag pricetag, ProductCategory category)
    {
        super(id);
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.pricetag = pricetag;
        this.category = category;
        this.rating = new ProductRating();
        this.storeId = storeId;
    }
    
    public Product(int id, Store store, String name, int weight, boolean conditionUsed, PriceTag pricetag, ProductCategory category) {
        super(id);
        this.storeId = store.id;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.pricetag = pricetag;
        this.category = category;
        this.rating = new ProductRating();
    }
    
    @Override
    public boolean read(String content) {
        return false;
    }
}
