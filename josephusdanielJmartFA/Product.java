package josephusdanielJmartFA;


/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Product
{
    // instance variables - replace the example below with your own
    private int idCounter = 0;
    public int id;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag pricetag;
    public ProductCategory category;
    public ProductRating rating;

    /**
     * Constructor for objects of class Product
     */
    public Product(String name, int weight, boolean conditionUsed, PriceTag pricetag, ProductCategory category)
    {
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.pricetag = pricetag;
        this.category = category;
        this.rating = new ProductRating();
        this.id = this.idCounter;
        this.idCounter++;
    }
}
