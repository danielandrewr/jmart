package josephusdanielJmartFA;


/**
 * Write a description of class Recognizeable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Recognizable
{
    // instance variables - replace the example below with your own
    public final int id;
    
    protected Recognizable(int id) {
        this.id = id;
    }
    
    public boolean equals(Recognizable recognizeable) {
        if (this.id == recognizeable.id) {
            return true;
        }
        
        return false;
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof Recognizable) {
            Recognizable recog = (Recognizable) obj;
            if (recog.id == this.id) {
                return true;
            }
        }
        return false;
    }
}
