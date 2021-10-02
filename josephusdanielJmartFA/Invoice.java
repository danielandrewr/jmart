package josephusdanielJmartFA;

import java.util.Date;

public abstract class Invoice extends Recognizeable implements FileParser
{
    enum Status { WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED };
    enum Rating { NONE, BAD, NEUTRAL, GOOD };
    
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating = Rating.NONE;
    public Status status = Status.WAITING_CONFIRMATION;
    
    protected Invoice(int id, int buyerId, int productId) {
        super(id);
        this.buyerId = buyerId;
        this.productId = productId;
        this.date = new Date(); 
    }
    
    public abstract double getTotalPay();
    
    @Override
    public boolean read(String content) {
        return false;
    }
}
