package josephusdanielJmartFA;


/**
 * Write a description of interface FileParser here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface FileParser
{
    public boolean read(String str);
    public default Object write() { return null; }
    public static Object newInstance(String str) { return null; }
}
