import java.io.IOException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class PhoneBookModule1 {

  TreeMap<String, String> phoneBook = new TreeMap<>();

  public static void main(String[] args) throws IOException {
    PhoneBookModule1 phoneBook = new PhoneBookModule1();
    phoneBook.run();
  }

  void run() {

    final String COMMAND_LIST = "(?i)LIST";
    final String COMMAND_NAME = "[([a-zA-Zа-яА-Я]+)(\\s+)]+";
    final String COMMAND_NUMBER = "[0-9]+";

    phoneBook.put("Иванов Иван Иванович", "111111");
    phoneBook.put("Петров Петр Петрович", "222222");
    phoneBook.put("Сидоров Сидор Сидорович", "333333");
    phoneBook.put("Одиссей Лаэртович Зевсов", "444444");
    phoneBook.put("Пенелопа Икарьевна Телегонова", "555555");

    System.out.println("Вы пользуетесь программой <Телефонная книга>");
    System.out.println("Если Вы хотите увидеть все данные <Телефонной книги> введите команду LIST");
    System.out.println("Если Вы хотите добавить в <Телефонную книгу> контакт:" + "\n"
        + "введите ФИО и если абонента с такими данными не существует - номер телефона");
    System.out.println("Введите команду:");

    Scanner scanner = new Scanner(System.in);
    String input;

    while (true) {

      input = scanner.nextLine();

      if (input.matches(COMMAND_LIST)) {
        printMap(phoneBook);
        continue;

      } else if (input.matches(COMMAND_NAME)) {
        if (phoneBook.containsKey(input)) {
          printAbonentByName(input);
        } else {
          System.out.println("Контакта с такими данными не существует");
          System.out.println("Введите номер телефона указанного абонента");
          String command_input2 = scanner.nextLine();
          if (command_input2.matches(COMMAND_NUMBER)) {
            phoneBook.put(input, getUserInput(command_input2));
            printMap(phoneBook);
          } else {
            System.out.println("Вы не указали правильный номер телефона. Введите команду еще раз.");
          }
        }

      } else if (input.matches(COMMAND_NUMBER)) {
        if (phoneBook.containsValue(input)) {
          printAbonentByNumber(input);
        } else {
          System.out.println("Контакта с таким номером не существует");
          System.out.println("Введите ФИО указанного абонента");
          String command_input2 = scanner.nextLine();
          phoneBook.put(getUserInput(command_input2), input);
          printMap(phoneBook);
        }
      } else {
        System.out.println("Команда не распознана. Введите еще раз.");
      }
    }
  }

  private String getUserInput(String command_input2) {
    System.out.println("Данные абонента добавлены в телефонный справочник");
    return command_input2;
  }

  private void printAbonentByName(String input) {
    System.out.println("Телефон абонента:  " + phoneBook.get(input));
  }

  private void printAbonentByNumber(String input) {
    for (Entry<String, String> entry : phoneBook.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
      if (input.equals(value)) {
        System.out.print("Данные абонента:  " + key);
      }
    }
  }

  private static void printMap(TreeMap<String, String> phoneBook) {
    for (String key : phoneBook.keySet()) {
      System.out.println("ФИО абонента:  " + key + "  Номер телефона:  " + phoneBook.get(key));
    }
  }
}
