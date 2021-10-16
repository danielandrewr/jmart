package josephusdanielJmartFA;

public class Treasury {
    private static double price;
    private static double discount;
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_FEE = 1000.0;
    public static final double BOTTOM_PRICE = 20000.0;

    public Treasury(double priceE) 
    {
        price = priceE;
        discount = 0.0;
    }
    
    public Treasury(double priceE, double discountE) {
        price = priceE;
        discount = discountE;
    }
    
    public static double getAdminFee() {
        double hargaDiskon = getDiscountedPrice();
        
        if (hargaDiskon < BOTTOM_PRICE) {
            return BOTTOM_FEE;
        }
        
        return (hargaDiskon * COMMISSION_MULTIPLIER);
    }
    
    public static double getDiscountedPrice() {
        if (discount > 100.0) {
            return 0.0;
        } else if (discount == 100.0) {
            return price;
        }
        
        return price - (price * (discount/100));
    }
    
    public static double getAdjustedPrice() {
        return getDiscountedPrice() + getAdminFee();
    }
}