package josephusdanielJmartFA;

public class Coupon extends Recognizeable
{
    // instance variables - replace the example below with your own
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;

    public Coupon(int id, String name, int code, Type type, double cut, double minimum)
    {
        super(id);
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
    
    public boolean canApply(PriceTag pricetag) {
        
        if ((used == false) && (pricetag.getAdjustedPrice() >= this.minimum)) {
            return true;
        }
        return false;
    }
    
    public double apply(PriceTag pricetag) {
        this.used = true;
        if (this.type == Type.DISCOUNT) {
            return pricetag.getAdjustedPrice() - (pricetag.getAdjustedPrice() * (this.cut/100));
        }
        return pricetag.getAdjustedPrice() - this.cut;
    }
}
