import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;


public class ControlAffairs {

  final static String COMMAND_ADD_TO_INDEX = "(ADD\\s+)(\\d+)(\\s+.+)";
  final static String COMMAND_TEXT = "(ADD)(\\s+.+)";
  final static String COMMAND_EDIT = "(EDIT\\s+)(\\d+)(\\s+.+)";
  final static String COMMAND_DELETE = "(DELETE\\s+)(\\d+)";
  final static String COMMAND_LIST = "LIST";
  final static String COMMAND_END = "END";
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

    while (true) {
      Scanner scanner = new Scanner(System.in);   // Организуем ввод команды с клавиатуры
      command = scanner.nextLine();

      if (command.matches(COMMAND_LIST)) {
        Methods.printList();
      } else if (command.matches(COMMAND_ADD_TO_INDEX)) {
        Methods.addToListIndex();
      } else if (command.matches(COMMAND_TEXT)) {
        Methods.addText();
      } else if (command.matches(COMMAND_EDIT)) {
        Methods.editList();
      } else if (command.matches(COMMAND_DELETE)) {
        Methods.deleteList();
      } else if (command.matches(COMMAND_END)) {
        System.out.println("Работа программы окончена");
        return;
      } else {
        System.out.println("Команда не распознана. Введите еще раз.");
      }
    }
  }
}

