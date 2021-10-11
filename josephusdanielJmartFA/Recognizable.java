package josephusdanielJmartFA;


/**
 * Write a description of class Recognizeable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Recognizable implements Comparable<Recognizable>
{
    // instance variables - replace the example below with your own
    public final int id;
    
    protected Recognizable() {
        this.id = 5;
    }
    
    public static <T extends Recognizable> int setClosingId(Class<T> clasz, int i) {
//    	if (clasz.getSuperclass() == Recognizable.class) {
//    		return 0;
//    	}
		return 0;
    }
    
    public static <T extends Recognizable> int getClosingId(Class<T> clasz) {
//    	if (clasz.getSuperclass() == Recognizable.class) {
//    		return 0;
//    	}
		return 0;
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

	@Override
	public int compareTo(Recognizable o) {
		return Integer.compare(this.id, o.id);
	}
}
