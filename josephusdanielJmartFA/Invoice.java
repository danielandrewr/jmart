package josephusdanielJmartFA;

import java.util.Date;

public abstract class Invoice extends Serializable
{
    enum Status { WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED };
    enum Rating { NONE, BAD, NEUTRAL, GOOD };
    
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    
    protected Invoice(int buyerId, int productId) {
        this.buyerId = buyerId;
        this.productId = productId;
        this.date = java.util.Calendar.getInstance().getTime();
        this.rating = Rating.NONE;
        this.complaintId = -1;
    }
    
    public abstract double getTotalPay(Product product);
}
