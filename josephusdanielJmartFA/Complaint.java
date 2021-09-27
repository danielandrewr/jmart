package josephusdanielJmartFA;

public class Complaint extends Recognizeable implements FileParser
{
    public String date = "27-09-2021";
    public String desc;
    
    public Complaint(int id, String desc) {
        super(id);
        this.desc = desc;
    }
    
    public boolean read(String content) {
        return false;
    }
}
