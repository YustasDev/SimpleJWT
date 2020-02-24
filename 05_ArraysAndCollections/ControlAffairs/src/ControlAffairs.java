import java.io.IOException;
import java.util.ArrayList;
import java.util.*;


public class ControlAffairs {


  private static String value;

  public static void main(String[] args) throws IOException {

    String text = null;

    ArrayList<String> manageList = new ArrayList<>();
    manageList.add("Дело №2");
    manageList.add(0, "Дело №1");
    manageList.add("Дело №3");
    manageList.add("Дело №4");
    manageList.add("Дело №5");
    manageList.add("Дело №6");
    manageList.add("Дело №7");
    manageList.add("Дело №8");
    manageList.add("Дело №9");
    manageList.add("Дело №10");

    System.out.println("Перечень возможных команд: LIST, ADD, EDIT, DELETE ");
    System.out.println("LIST - вводит дело с порядковым номером.\nADD - добавляет дело в конец списка или дело на определённое место, сдвигая остальные дела вперёд, если указать номер.");
    System.out.println("EDIT - заменяет дело с указанным номером.\nDELETE - удаляет дело с указанным номером.");
    System.out.println("Примеры команд:\n"
        + "    LIST\n"
        + "    ADD Какое-то дело\n"
        + "    ADD 4 Какое-то дело на четвёртом месте\n"
        + "    EDIT 3 Новое название дела\n"
        + "    DELETE 7");
    System.out.println("Вызовите команду : ");

//    while (true) {
    Scanner scanner = new Scanner(System.in);
    String[] subStr;
    String delimeter = " "; // Разделитель
    text = scanner.nextLine();
    subStr = text.split(delimeter, 3);
    Integer x = null;

    if (subStr.length == 1) {
      subStr[0] = text;
    }
    else if (subStr.length == 2) {
      try {
        x = Integer.parseInt(subStr[1]);
      } catch (NumberFormatException e) {
        x = null;
        subStr = text.split(delimeter, 2);
      }
    }
    else {
      try {
        x = Integer.parseInt(subStr[1]);
      } catch (NumberFormatException e) {
        x = null;
        subStr = text.split(delimeter, 2);
      }
    }

    for(int i = 0; i < subStr.length; i++) {
      System.out.println(subStr[i]);
    }

    switch (subStr[0]) {
      case "LIST":
        System.out.println("Полный список дел: " + manageList);
        break;
//
      case "ADD":
        if (subStr.length == 1){
          System.out.println("Команда ADD должна быть с аргументами. Введите еще раз.");
          break;
        }
        else if (x == null){
          manageList.add(subStr[1]);}

        else if (subStr.length == 2){
          System.out.println("Вы ввели не все аргументы ADD. Введите еще раз.");
          break;
        }
        else { manageList.add(x - 1, subStr[2]);}
        System.out.println(manageList);
        break;

      case "EDIT":
        if (subStr.length == 1){
          System.out.println("Команда EDIT должна быть с аргументами. Введите еще раз.");
          break;
        }
        else if (x == null) {
          System.out.println("Вы ввели не все аргументы EDIT. Введите еще раз.");
          break;
        }
        else if (subStr.length == 2){
          System.out.println("Вы ввели не все аргументы EDIT. Введите еще раз.");
          break;
        }
        else { manageList.set(x - 1, subStr[2]);}
        System.out.println(manageList);
        break;

      case "DELETE":
        if (subStr.length == 1){
          System.out.println("Команда DELETE должна быть с аргументом. Введите еще раз.");
          break;
        }
        else if (x == null) {
          System.out.println("Вы не ввели числовой аргумент EDIT. Введите еще раз.");
          break;
        }
        else { manageList.remove(x - 1);}
        System.out.println(manageList);
        break;



      default:
        System.out.println("Ошибка при вводе команды. Введите еще раз.");
    }
    scanner.close();
  }
}

