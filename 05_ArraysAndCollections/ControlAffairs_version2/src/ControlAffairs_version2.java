import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;


public class ControlAffairs_version2 {

  public static void main(String[] args) throws IOException {

    final String COMMAND_ADD_TO_INDEX = "(ADD\\s+)(\\d+)\\s+(.+)";
    final String COMMAND_TEXT = "(ADD)\\s+(.+)";
    final String COMMAND_EDIT = "(EDIT\\s+)(\\d+)\\s+(.+)";
    final String COMMAND_DELETE = "(DELETE\\s+)(\\d+)";
    final String COMMAND_LIST = "LIST";
    final String COMMAND_END = "END";
    String command = null;

    TodoList todoList = new TodoList();

    todoList.manageList.add("Дело №2");
    todoList.manageList.add(0, "Дело №1");
    todoList.manageList.add("Дело №3");
    todoList.manageList.add("Дело №4");
    todoList.manageList.add("Дело №5");
    todoList.manageList.add("Дело №6");
    todoList.manageList.add("Дело №7");
    todoList.manageList.add("Дело №8");
    todoList.manageList.add("Дело №9");
    todoList.manageList.add("Дело №10");

    printUsage();

    while (true) {

      Scanner scanner = new Scanner(System.in);
      command = scanner.nextLine();

      if (command.matches(COMMAND_LIST)) {
        todoList.printList();
      } else if (command.matches(COMMAND_ADD_TO_INDEX)) {

        int indexOfCommand = Integer.parseInt(command.replaceAll(COMMAND_ADD_TO_INDEX, "$2"));
        String todoText = command.replaceAll(COMMAND_ADD_TO_INDEX, "$3");

        if (todoList.add(indexOfCommand - 1, todoText)) {
          System.out.println(todoList.manageList);
        } else {
          System.out.println("Параметр команды ADD введен неправильно. Введите еще раз");
        }
      } else if (command.matches(COMMAND_TEXT)) {
        String todoText = command.replaceAll(COMMAND_TEXT, "$2");
        todoList.add(todoText);

      } else if (command.matches(COMMAND_EDIT)) {

        int indexOfCommand = Integer.parseInt(command.replaceAll(COMMAND_EDIT, "$2"));
        String todoText = command.replaceAll(COMMAND_EDIT, "$3");
        if (todoList.editList(indexOfCommand - 1, todoText)) {
          System.out.println(todoList.manageList);
        } else {
          System.out.println("Параметр команды EDIT указан неправильно. Введите еще раз");
        }
      } else if (command.matches(COMMAND_DELETE)) {
        int indexOfCommand = Integer.parseInt(command.replaceAll(COMMAND_DELETE, "$2"));
        try {
          System.out.println("Удаленный элемент списка:  " + todoList.delete(indexOfCommand - 1));
        } catch (IndexOutOfBoundsException e) {
          e.printStackTrace();
          String fault = "Ошибка ввода команды DELETE. Удаление элемента массива не было осуществлено";
          System.out.println(fault);
        }
        System.out.println("Полный список дел" + todoList.manageList);
      } else if (command.matches(COMMAND_END)) {
        System.out.println("Работа программы окончена");
        return;
      } else {
        System.out.println("Команда не распознана. Введите еще раз.");
      }
    }
  }

  public static void printUsage() {
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
  }
}






