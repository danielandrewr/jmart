package com.josephusdanielJmartFA;

import com.josephusdanielJmartFA.dbjson.Serializable;

/**
 * Coupon Model Class
 * @author Daniel
 *
 */
public class Coupon extends Serializable
{
    // instance variables - replace the example below with your own
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public static double minimum;
    private static boolean used;

    /**
     * Coupon Constructor
     * @param name
     * @param code
     * @param type
     * @param cut
     * @param minimum
     */
    public Coupon(String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        Coupon.minimum = minimum;
        used = false;
    }
    
    /**
     * Returns value to check if a coupon has already been used or not
     * @return
     */
    public boolean isUsed() {
        return used;
    }
    
    /**
     * Check whether a coupon can be apllied or not
     * @param price
     * @param discount
     * @return
     */
    public boolean canApply(double price, double discount) {
        
        if ((used == false) && (Treasury.getAdjustedPrice(price, discount) >= Coupon.minimum)) {
            return true;
        }
        return false;
    }
    
    /**
     * Applies coupon to a certain Product's price
     * @param price
     * @param discount
     * @return
     */
    @SuppressWarnings("static-access")
	public double apply(double price, double discount) {
        this.used = true;
        if (this.type == Type.DISCOUNT) {
            return Treasury.getAdjustedPrice(price, discount) - (Treasury.getAdjustedPrice(price, discount) * (this.cut / 100));
        }
        return Treasury.getAdjustedPrice(price, discount) - this.cut;
    }
    
//    @Override
//    public boolean read(String content) {
//        return false;
//    }
}
