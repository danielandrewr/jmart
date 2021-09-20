package josephusdanielJmartFA;

import java.util.EnumSet;

enum Shipments { KARGO, SAME_DAY, NEXT_DAY, REGULER, INSTANT };

public class ShipmentDuration
{
    // instance variables - replace the example below with your own
    EnumSet<Shipments> sd = EnumSet.of(Shipments.INSTANT, Shipments.SAME_DAY, 
        Shipments.NEXT_DAY, Shipments.REGULER, Shipments.KARGO);
    private int bit;

    /**
     * Constructor for objects of class ShipmentDuration
     */
    public ShipmentDuration(int bit)
    {
        this.bit = bit; // 0000 1000
    }
    
    public ShipmentDuration(Shipments... args) { // (ShipmentDuration)
        // not done yet
    }
    
    public boolean isDuration(ShipmentDuration reference) {
        // not done yet
        return false;
    }
}
