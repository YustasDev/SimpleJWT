import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;



public class ControlAffairs {

 final static String COMMAND_ADD_TO_INDEX = "(ADD\\s+)(\\d+)(\\s+.+)";
 final static String COMMAND_TEXT = "(ADD)(\\s+.+)";
 final static String COMMAND_EDIT = "EDIT\\s+\\d+\\s+.+";
 final static String COMMAND_DELETE = "DELETE\\s+\\d+";
 final static String COMMAND_LIST = "LIST";
 static String command = null;

 public static ArrayList<String> manageList = new ArrayList<>();

 public static void main(String[] args) throws IOException {


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
    System.out.println(
        "LIST - вводит дело с порядковым номером.\nADD - добавляет дело в конец списка или дело на определённое место, сдвигая остальные дела вперёд, если указать номер.");
    System.out.println(
        "EDIT - заменяет дело с указанным номером.\nDELETE - удаляет дело с указанным номером.");
    System.out.println("Примеры команд:\n"
        + "    LIST\n"
        + "    ADD Какое-то дело\n"
        + "    ADD 4 Какое-то дело на четвёртом месте\n"
        + "    EDIT 3 Новое название дела\n"
        + "    DELETE 7");
    System.out.println("Если Вы хотите прекратить выполнение программы, наберите команду: END ");
    System.out.println("Вызовите команду : ");


    Scanner scanner = new Scanner(System.in);   // Организуем ввод команды с клавиатуры
    command = scanner.nextLine();

    if (command.matches(COMMAND_LIST)) {
    Methods.printList();
    }
    else if (command.matches(COMMAND_ADD_TO_INDEX)) {
    Methods.addToListIndex();
     }
    else if (command.matches(COMMAND_TEXT)) {
     Methods.addText();
    }
    else {

      System.out.println("Команда не распознана. Введите еще раз.");

     }







  }
 }







//    while (true) {
//      Scanner scanner = new Scanner(System.in);   // Организуем ввод команды с клавиатуры
//      String[] subStr;
//      String delimeter = " "; // Разделитель
//      text = scanner.nextLine();
//      subStr = text.split(delimeter, 3);  // по умолчанию делим введенный текст на три части
//      Integer x = null;
//// проверяем, сколько частей содержит введенная команда
//      if (subStr.length == 1) {
//        subStr[0] = text;
//      } else if (subStr.length == 2) {     // проверяем, если в команде две части, является ли вторая часть команды числом
//        try {
//          x = Integer.parseInt(subStr[1]);
//        } catch (NumberFormatException e) {
//          x = null;
//          subStr = text.split(delimeter, 2);   // если нет, делим команду на две части
//        }
//      } else {                              // если в команде три части, является ли вторая часть числом
//        try {
//          x = Integer.parseInt(subStr[1]);
//        } catch (NumberFormatException e) {
//          x = null;
//          subStr = text.split(delimeter, 2);   //  ----
//        }
//      }
//
//      for (int i = 0; i < subStr.length; i++) {   // технический цикл распечатки введенных команд
//        System.out.println(subStr[i]);            // можно в дальнейшем убрать
//      }
//
//      switch (subStr[0]) {
//        case "LIST":
//          System.out.println("Полный список дел: " + manageList);
//          continue;
////
//        case "ADD":
//          if (subStr.length == 1) {
//            System.out.println("Команда ADD должна быть с аргументами. Введите еще раз.");
//            continue;
//          } else if (x == null) {
//            manageList.add(subStr[1]);
//          } else if (subStr.length == 2) {
//            System.out.println("Вы ввели не все аргументы ADD. Введите еще раз.");
//            continue;
//          } else {
//            manageList.add(x - 1, subStr[2]);
//          }
//          System.out.println(manageList);
//          continue;
//
//        case "EDIT":
//          if (subStr.length == 1) {
//            System.out.println("Команда EDIT должна быть с аргументами. Введите еще раз.");
//            continue;
//          } else if (x == null) {
//            System.out.println("Вы ввели не все аргументы EDIT. Введите еще раз.");
//            continue;
//          } else if (subStr.length == 2) {
//            System.out.println("Вы ввели не все аргументы EDIT. Введите еще раз.");
//            continue;
//          } else {
//            manageList.set(x - 1, subStr[2]);
//          }
//          System.out.println(manageList);
//          continue;
//
//        case "DELETE":
//          if (subStr.length == 1) {
//            System.out.println("Команда DELETE должна быть с аргументом. Введите еще раз.");
//            continue;
//          } else if (x == null) {
//            System.out.println("Вы не ввели числовой аргумент EDIT. Введите еще раз.");
//            continue;
//          } else {
//            manageList.remove(x - 1);
//          }
//          System.out.println(manageList);
//          continue;
//
//        case "END":
//          System.out.println("Работа программы окончена");
//          return;
//
//        default:
//          System.out.println("Ошибка при вводе команды. Введите еще раз.");
//          continue;
//      }
//    }



