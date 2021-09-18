package josephusdanielJmartFA;


/**
 * Write a description of class PriceTag here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PriceTag
{
    // instance variables - replace the example below with your own
    private double price;
    private double discount;
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_FEE = 1000.0;
    public static final double BOTTOM_PRICE = 20000.0;

    /**
     * Constructor for objects of class PriceTag
     */
    public PriceTag(double price) 
    {
        this.price = price;
        this.discount = 0.0;
    }
    
    public PriceTag(double price, double discount) {
        this.price = price;
        this.discount = discount;
    }
    
    public double getAdminFee() {
        double hargaDiskon = getDiscountedPrice();
        
        if (hargaDiskon < BOTTOM_FEE) {
            return BOTTOM_FEE;
        }
        
        return hargaDiskon - (hargaDiskon * (COMMISSION_MULTIPLIER));
    }
    
    private double getDiscountedPrice() {
        double discount = this.discount;
        if (this.discount > 100) {
            discount = 100.0;
        } else if (this.discount == 100.0) {
            return 0.0;
        }  
        
        return this.price - (this.price * (discount/100));
    }
    
    public double getAdjustedPrice() {
        return getDiscountedPrice() + getAdminFee();
    }
}


