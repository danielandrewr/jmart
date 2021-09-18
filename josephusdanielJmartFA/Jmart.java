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
        System.out.println(getOriginalPrice(900, 10.0f));
    }
    
    public static int getPromo() {
        return 0;
    }
    
    public static String getCustomer() {
        return "oop";
    }
    
    public static float getDiscountPercentage(int before, int after) {
        float discountPercentage;
        if (before <= after) {
            discountPercentage = 0.0f;
        } else {
            discountPercentage = (float)(before - after)/100;
        }
        
        return discountPercentage;
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage) {
        int discountedPrice;
        double discountPrice;
        
        if (discountPercentage == 100.0f) {
            discountPrice = price;
        } else {
            discountPrice = ((discountPercentage/100) * price);
        }
        
        return discountedPrice = price - (int) discountPrice;
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage) {
        int originalPrice = (int)((discountedPrice * 100) / (100 - discountPercentage));
        
        return originalPrice;   
    }
    
    public static float getCommissionMultiplier() {
        return 0.05f;
    }
    
    public static int getAdjustedPrice(int price) {
        return (price * (int) (getCommissionMultiplier())) + price;
    }
    
    public static int getAdminFee(int price) {
        return (int)(price * getCommissionMultiplier());
    }
}
