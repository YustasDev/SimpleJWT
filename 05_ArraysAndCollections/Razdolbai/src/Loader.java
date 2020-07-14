import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import java.util.stream.Collectors;

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
    ArrayList<String> list = new ArrayList<String>();
    Collections.addAll(list, "привет", "как", "дела?");

    Set<String> result = list.stream()
        .filter( s -> Character.isUpperCase(s.charAt(0)) )
        .collect( Collectors.toSet() );

    result.forEach(System.out::println);

    System.err.println("#1.in");
    f(); // создаем фрейм, помещаем в стек, передаем в него управление
    System.err.println("#1.out"); // вернулись
  } // выходим из текущего фрейма, кончились инструкции

  public static void f() {
    System.err.println(".   #2.in");
    g(); // создаем фрейм, помещаем в стек, передаем в него управление
    System.err.println(".   #2.out");  //вернулись
  } // выходим из текущего фрейма, кончились инструкции

  public static void g() {
    System.err.println(".   .   #3.in");
    h(); // создаем фрейм, помещаем в стек, передаем в него управление
    System.err.println(".   .   #3.out"); // вернулись
  } // выходим из текущего фрейма, кончились инструкции

  public static void h() {
    System.err.println(".   .   .   #4.in");
    if (true) {
      System.err.println(".   .   .   #4.RETURN");
      return; // выходим из текущего фрейма по 'return'
    }
    System.err.println(".   .   .   #4.out"); // ПРОПУСКАЕМ
  }
}