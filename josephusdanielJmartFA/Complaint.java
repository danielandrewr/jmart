package josephusdanielJmartFA;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Complaint extends Recognizable
{
    
    public Date date;
    public String desc;
    
    public Complaint(String desc) {
        this.date = new Date();
        this.desc = desc;
    }
    
    public boolean read(String content) {
        return false;
    }
    
    public String toString() {
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return "Complaint{date=" + sdf.format(this.date) + ",desc=" + "'" + this.desc + "'" + "}";
    }
}
