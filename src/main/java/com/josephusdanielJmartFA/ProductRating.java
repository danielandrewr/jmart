package com.josephusdanielJmartFA;

/**
 * Rates a product
 * *UNUSED
 * @author Daniel
 *
 */
public class ProductRating
{
    private long total;
    private long count;

    /**
     * ProductRating Constructor
     */
    public ProductRating()
    {
        this.total = 0;
        this.count = 0;
    }
    
    /**
     * Add a rating to a product in an int format
     * @param rating
     */
    public void insert(int rating) {
        count += 1;
        total = (long)(total + rating);
    }
    
    /**
     * Counts the average rating from a product
     * @return
     */
    public double getAverage() {
        if (this.count == 0) {
            return 0.0;
        }
        return this.total / this.count;
    }
    
    /**
     * Counts how many rating does a product have
     * @return
     */
    public long getCount() {
        return this.count;
    }
    
    /**
     * Returns the total ratings
     * @return
     */
    public long getTotal() {
        return this.total;
    }
 }
