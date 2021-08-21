import java.lang.reflect.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {
    String str = "addбатон25";
    String pattern = "(add)(\\D+)(\\d+)";

    // Создание Pattern объекта
    Pattern r = Pattern.compile(pattern);

    // Создание matcher объекта
    Matcher m = r.matcher(str);
    if (m.find( )) {
      System.out.println("Найдено значение: " + m.group(0));
      System.out.println("Найдено значение: " + m.group(1));
      System.out.println("Найдено значение: " + m.group(2));
      System.out.println("Найдено значение: " + m.group(3));
    }else {
      System.out.println("НЕ СОВПАДАЕТ");
    }

    String str1 = "setПятерочка";
    String pattern1 = "(set)(\\D+)";

    // Создание Pattern объекта
    Pattern r1 = Pattern.compile(pattern1);

    // Создание matcher объекта
    Matcher m1 = r1.matcher(str1);
    if (m1.find( )) {
      System.out.println("Найдено значение: " + m1.group(0));
      System.out.println("Найдено значение: " + m1.group(1));
      System.out.println("Найдено значение: " + m1.group(2));
    }else {
      System.out.println("НЕ СОВПАДАЕТ");
    }







  }
}
