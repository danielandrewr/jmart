package josephusdanielJmartFA;

import java.util.Date;

public class Complaint extends Recognizeable implements FileParser
{
    
    public Date date;
    public String desc;
    
    public Complaint(int id, String desc) {
        super(id);
        this.date = new Date();
        this.desc = desc;
    }
    
    public boolean read(String content) {
        return false;
    }
}
