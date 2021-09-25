package josephusdanielJmartFA;


/**
 * Write a description of class Recognizeable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Recognizeable
{
    // instance variables - replace the example below with your own
    public final int id;
    
    protected Recognizeable(int id) {
        this.id = id;
    }
    
    public boolean equals(Recognizeable recognizeable) {
        if (this.id == recognizeable.id) {
            return true;
        }
        
        return false;
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof Recognizeable) {
            Recognizeable recog = (Recognizeable) obj;
            if (recog.id == this.id) {
                return true;
            }
        }
        return false;
    }
}
