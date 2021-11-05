package josephusdanielJmartFA;

public class Treasury {
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_FEE = 1000.0;
    public static final double BOTTOM_PRICE = 20000.0;
    
    public static double getAdminFee(double price, double discount) {
        double hargaDiskon = getDiscountedPrice(price, discount);
        
        if (hargaDiskon < BOTTOM_PRICE) {
            return BOTTOM_FEE;
        }
        
        return (hargaDiskon * COMMISSION_MULTIPLIER);
    }
    
    public static double getDiscountedPrice(double price, double discount) {
        if (discount > 100.0) {
            return 0.0;
        } else if (discount == 100.0) {
            return price;
        }
        
        return price - (price * (discount/100));
    }
    
    public static double getAdjustedPrice(double price, double discount) {
        return getDiscountedPrice(price, discount) + getAdminFee(price, discount);
    }
}