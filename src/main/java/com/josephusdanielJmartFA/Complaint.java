package com.josephusdanielJmartFA;

import java.util.Date;

import com.josephusdanielJmartFA.dbjson.Serializable;

import java.text.SimpleDateFormat;

/**
 * Complaint Class Model
 * @author Daniel
 *
 */
public class Complaint extends Serializable {
    
    public Date date;
    public String desc;
    
    /**
     * Complaint Constructor
     * @param desc
     */
    public Complaint(String desc) {
        this.date = new Date();
        this.desc = desc;
    }
    
    public boolean read(String content) {
        return false;
    }
    
    /**
     * Convert from date format to readable string
     */
    public String toString() {
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return "Complaint{date=" + sdf.format(this.date) + ",desc=" + "'" + this.desc + "'" + "}";
    }
}
