import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Methods {


  public static void printList() {
    System.out.println("Полный список дел: " + ControlAffairs.manageList);
  }

  public static void addToListIndex() {
    Integer x = null;

    Pattern prototype = Pattern.compile(ControlAffairs.COMMAND_ADD_TO_INDEX);
    Matcher m = prototype.matcher(ControlAffairs.command);

    while (m.find()) {
   for (int i = 0; i <= m.groupCount(); i++) {
 //    System.out.println(i + "  ВСЕ ЭЛЕМЕНТЫ: " + m.group(i));
     // техническая распечетка, потом убрать

   }
      x = Integer.parseInt(m.group(2));
   if (x <= ControlAffairs.manageList.size()) {
     ControlAffairs.manageList.add(x - 1, m.group(3));
     System.out.println(ControlAffairs.manageList);
   }
   else {
     System.out.println("Параметр команды ADD введен неправильно. Введите еще раз");
  }
  }
  }

  public static void addText() {
    Pattern prototype = Pattern.compile(ControlAffairs.COMMAND_TEXT);
    Matcher m = prototype.matcher(ControlAffairs.command);

    while (m.find()) {
      for (int i = 0; i <= m.groupCount(); i++) {
        //    System.out.println(i + "  ВСЕ ЭЛЕМЕНТЫ: " + m.group(i));
        // техническая распечетка, потом убрать
      }
      ControlAffairs.manageList.add(m.group(2));
      System.out.println(ControlAffairs.manageList);
    }
  }



  }

