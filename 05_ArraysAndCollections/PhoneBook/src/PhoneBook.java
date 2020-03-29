import java.io.IOException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class PhoneBook {

  public static void main(String[] args) throws IOException {

    final String COMMAND_LIST = "(?i)LIST";
    final String COMMAND_NAME = "[([a-zA-Zа-яА-Я]+)(\\s+)]+";
    final String COMMAND_NUMBER = "[0-9]+";

    TreeMap<String, String> phoneBook = new TreeMap<>();
    phoneBook.put("Иванов Иван Иванович", "111111");
    phoneBook.put("Петров Петр Петрович", "222222");
    phoneBook.put("Сидоров Сидор Сидорович", "333333");
    phoneBook.put("Одиссей Лаэртович Зевсов", "444444");
    phoneBook.put("Пенелопа Икарьевна Телегонова", "555555");

    System.out.println("Вы пользуетесь программой <Телефонная книга>");
    System.out.println("Если Вы хотите увидеть все данные <Телефонной книги> ввелите команду LIST");
    System.out.println(
        "Если Вы хотите добавить в <Телефонную книгу> контакт - введите ФИО и номер телефона абонента через пробел");
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
          String phoneNumber = scanner.nextLine();
          phoneBook.put(input, phoneNumber);
          System.out.println("Данные абонента добавлены в телефонный справочник");
          printMap(
              phoneBook);           // техническая распечатка, чтобы убедиться, что данные корректно добавлены
        }
      } else if (input.matches(COMMAND_NUMBER)) {
        if (phoneBook.containsValue(input)) {
          printAbonentByNumber(input);
        } else {
          System.out.println("Контакта с такими данными не существует");
          System.out.println("Введите ФИО указанного абонента");
          String phoneName = scanner.nextLine();
          phoneBook.put(phoneName, input);
          System.out.println("Данные абонента добавлены в телефонный справочник");
          printMap(
              phoneBook);           // техническая распечатка, чтобы убедиться, что данные корректно добавлены
        }
      } else {
        System.out.println("Команда не распознана. Введите еще раз.");
      }
    }
  }


  private static void printAbonentByName(String input) {
    System.out.println("Телефон абонента:  " + phoneBook.get(input));
  }

  private static void printAbonentByNumber(String input) {
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


