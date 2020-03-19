import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class Loader {

  public static void main(String[] args) throws IOException {

    List emails = new ArrayList();
    emails.add("user@domain.com");
    emails.add("user@domain.co.in");
    emails.add("user.name@domain.com");
    emails.add("user_name@domain.com");
    emails.add("username@yahoo.corporate.in");

//Invalid emails
    emails.add(".username@yahoo.com");
    emails.add("username@yahoo.com.");
    emails.add("username@yahoo..com");
    emails.add("username@yahoo.c");
    emails.add("username@yahoo.corporate");
    emails.add("username@@yahoo.com");
    emails.add("use@rname@yahoo.com");

    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    Pattern pattern = Pattern.compile(regex);

    for (Object email : emails) {
      Matcher matcher = pattern.matcher((CharSequence) email);
      System.out.println(email + " : " + matcher.matches());
    }
  }
}