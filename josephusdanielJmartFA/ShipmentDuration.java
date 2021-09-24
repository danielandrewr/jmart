package josephusdanielJmartFA;

import java.util.EnumSet;

public class ShipmentDuration
{      
    // instance variables
    public static final ShipmentDuration INSTANT = new ShipmentDuration(00000001);
    public static final ShipmentDuration SAME_DAY = new ShipmentDuration(00000010);
    public static final ShipmentDuration NEXT_DAY = new ShipmentDuration(00000100);
    public static final ShipmentDuration REGULER = new ShipmentDuration(00001000);
    public static final ShipmentDuration KARGO = new ShipmentDuration(00010000);
    
    private int bit;

    private ShipmentDuration(int bit)
    {
        this.bit = bit;
    }
    
    public ShipmentDuration(ShipmentDuration... args) {
        
        for (ShipmentDuration sd : args) {
            this.bit = this.bit | sd.bit;    
        }
    }
    
    public boolean isDuration(ShipmentDuration reference) {
        if ((this.bit & (1 << (reference.bit - 1))) >= 0) {
            return true;
        }
        return false;
    }
}
