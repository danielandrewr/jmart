package josephusdanielJmartFA;

public class Coupon extends Serializable
{
    // instance variables - replace the example below with your own
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;

    public Coupon(String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }
    
    public boolean isUsed() {
        return this.used;
    }
    
    public boolean canApply(Treasury pricetag) {
        
        if ((used == false) && (Treasury.getAdjustedPrice(10000, 0.5) >= this.minimum)) {
            return true;
        }
        return false;
    }
    
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
