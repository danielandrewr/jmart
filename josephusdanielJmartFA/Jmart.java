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
    
    public static Product createProduct() {
        PriceTag pt = new PriceTag(100000, 10);
        return new Product(1, 1, "Piring Cantik", 5, false, pt, ProductCategory.KITCHEN);
    }
    
    public static Coupon createCoupon() {
        return new Coupon(1, "TOMY", 69696969, Type.REBATE, 50000, 10000);
    }
    
    public static ShipmentDuration createShipmentDuration() {
        return new ShipmentDuration(ShipmentDuration.INSTANT);
    }  
 }
