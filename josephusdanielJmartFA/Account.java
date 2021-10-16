package josephusdanielJmartFA;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Account extends Recognizable
{
   public static final String REGEX_EMAIL = "^[a-zA-Z0-9_&_*~]+(?:\\.[a-zA-Z0-9_&_*~]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9-]+)*$";
   public static final String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$";
   public String name;
   public String email;
   public String password;

   public Account(String name, String email, String password) {
       this.name = name;
       this.email = email;
       this.password = password;
       
   }
   
   public String toString() {
        return "name: " + this.name + "\n" + "email: " + this.email + "\n" + "password: " + this.password;
   }
   
   public boolean validate() {
        Pattern p1 = Pattern.compile(REGEX_EMAIL);
        Pattern p2 = Pattern.compile(REGEX_PASSWORD);
        Matcher m1 = p1.matcher(this.email);
        Matcher m2 = p2.matcher(this.password);
        if (m1.find() == true && m2.find() == true) {
            return true;
        }
        return false;
    }
}