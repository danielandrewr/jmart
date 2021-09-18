package josephusdanielJmartFA;


/**
 * Write a description of class ProductRating here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ProductRating
{
    // instance variables - replace the example below with your own
    private long total;
    private long count;

    /**
     * Constructor for objects of class ProductRating
     */
    public ProductRating()
    {
        this.total = 0;
        this.count = 0;
    }
    
    public void insert(int rating) {
        count += 1;
        total = (long)(total + rating);
    }
    
    public double getAverage() {
        if (this.count == 0) {
            return 0.0;
        }
        return this.total / this.count;
    }
    
    public long getCount() {
        return this.count;
    }
    
    public long getTotal() {
        return this.total;
    }
 }
