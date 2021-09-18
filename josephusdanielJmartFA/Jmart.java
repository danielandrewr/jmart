package josephusdanielJmartFA;


/**
 * Write a description of class Jmart here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jmart
{
    // instance variables - replace the example below with your own
    public static void main(String[] args) {
    }
    
    public static Product create() {
        PriceTag pt = new PriceTag(10000, 10);
        return new Product("Piring Cantik", 5, false, pt, ProductCategory.KITCHEN);
    }
}
