import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Methods {


  public static void printList() {
    System.out.println("Полный список дел: " + ControlAffairs.manageList);
  }

  public static void addToListIndex() {
    Integer indexOfCommand = null;     // индекс команды

    Pattern prototype = Pattern.compile(ControlAffairs.COMMAND_ADD_TO_INDEX);
    Matcher m = prototype.matcher(ControlAffairs.command);

    while (m.find()) {
      for (int i = 0; i <= m.groupCount(); i++) {
        //    System.out.println(i + "  ВСЕ ЭЛЕМЕНТЫ: " + m.group(i));
        // техническая распечатка, потом убрать

      }
      indexOfCommand = Integer.parseInt(m.group(2));
      if (indexOfCommand <= ControlAffairs.manageList.size()) {
        ControlAffairs.manageList.add(indexOfCommand - 1, m.group(3));
        System.out.println(ControlAffairs.manageList);
      } else {
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
        // техническая распечатка, потом убрать
      }
      ControlAffairs.manageList.add(m.group(2));
      System.out.println(ControlAffairs.manageList);
    }
  }

  public static void editList() {
    Integer indexOfCommand = null;

    Pattern prototype = Pattern.compile(ControlAffairs.COMMAND_EDIT);
    Matcher m = prototype.matcher(ControlAffairs.command);

    while (m.find()) {
      for (int i = 0; i <= m.groupCount(); i++) {
        //    System.out.println(i + "  ВСЕ ЭЛЕМЕНТЫ: " + m.group(i));
        // техническая распечатка, потом убрать

      }
      indexOfCommand = Integer.parseInt(m.group(2));
      if (indexOfCommand <= ControlAffairs.manageList.size()) {
        ControlAffairs.manageList.set(indexOfCommand - 1, m.group(3));
        System.out.println(ControlAffairs.manageList);
      } else {
        System.out.println("Параметр команды EDIT введен неправильно. Введите еще раз");
      }
    }
  }

  public static void deleteList() {
    Integer indexOfCommand = null;

    Pattern prototype = Pattern.compile(ControlAffairs.COMMAND_DELETE);
    Matcher m = prototype.matcher(ControlAffairs.command);

    while (m.find()) {
      for (int i = 0; i <= m.groupCount(); i++) {
        //    System.out.println(i + "  ВСЕ ЭЛЕМЕНТЫ: " + m.group(i));
        // техническая распечатка, потом убрать

      }
      indexOfCommand = Integer.parseInt(m.group(2));
      if (indexOfCommand <= ControlAffairs.manageList.size()) {
        ControlAffairs.manageList.remove(indexOfCommand - 1);
        System.out.println(ControlAffairs.manageList);
      } else {
        System.out.println("Параметр команды DELETE введен неправильно. Введите еще раз");
      }
    }
  }
}



