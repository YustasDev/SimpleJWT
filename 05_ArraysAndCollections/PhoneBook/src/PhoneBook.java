import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class PhoneBook {

  public static void main(String[] args) throws IOException {

    final String COMMAND_LIST = "(?i)LIST";
    final String COMMAND_NAME = "[([a-zA-Zа-яА-Я]+)(\\s+)]+";
    final String COMMAND_NUMBER = "[0-9]+";

    TreeMap<String, Integer> phoneBook = new TreeMap<>();
    phoneBook.put("Иванов Иван Иванович", 111111);
    phoneBook.put("Петров Петр Петрович", 222222);
    phoneBook.put("Сидоров Сидор Сидорович", 333333);
    phoneBook.put("Одиссей Лаэртович Зевсов", 444444);
    phoneBook.put("Пенелопа Икарьевна Телегонова", 555555);

    System.out.println("Вы пользуетесь программой <Телефонная книга>");
    System.out.println("Если Вы хотите увидеть все данные <Телефонной книги> ввелите команду LIST");
    System.out.println("Если Вы хотите добавить в <Телефонную книгу> контакт - введите ФИО и номер телефона абонента через пробел");
    System.out.println("Введите команду:");

    Scanner scanner = new Scanner(System.in);

    String phoneRecording;
    while (true) {

      phoneRecording = scanner.nextLine();

      if (phoneRecording.matches(COMMAND_LIST)) {
        printMap(phoneBook);
        continue;
      } else if (phoneRecording.matches(COMMAND_NAME)) {
        if (phoneBook.containsKey(phoneRecording)) {
          System.out.println("Телефон абонента:  " + phoneBook.get(phoneRecording));
        } else {
          System.out.println("Контакта с такими данными не существует");
          System.out.println("Введите номер телефона указанного абонента");

          try {
          Integer phoneNumber = Integer.valueOf(scanner.nextLine());
            phoneBook.put(phoneRecording, phoneNumber);
            System.out.println("Данные абонента добавлены в телефонный справочник");
            printMap(phoneBook);           // техническая распечатка, чтобы убедиться, что данные корректно добавлены
          } catch (NumberFormatException e) {
            e.printStackTrace();
            String fault = "Ошибка ввода номера телефона. Введите информацию еще раз.";
            System.out.println(fault);
          }
        }
      } else if (phoneRecording.matches(COMMAND_NUMBER)) {
        if (phoneBook.containsValue(Integer.valueOf(phoneRecording))) {
          for (Entry<String, Integer> entry : phoneBook.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (Integer.valueOf(phoneRecording).equals(value)) {
              System.out.print("Данные абонента:  " + key);
            }
          }
        } else {
          System.out.println("Контакта с такими данными не существует");
          System.out.println("Введите ФИО указанного абонента");
          String phoneName = scanner.nextLine();
          phoneBook.put(phoneName, Integer.valueOf(phoneRecording));
          System.out.println("Данные абонента добавлены в телефонный справочник");
          printMap(phoneBook);           // техническая распечатка, чтобы убедиться, что данные корректно добавлены
        }
      } else {
        System.out.println("Команда не распознана. Введите еще раз.");
      }
    }
  }

  private static void printMap(TreeMap<String, Integer> phoneBook) {
    for (String key : phoneBook.keySet()) {
      System.out.println("ФИО абонента:  " + key + "  Номер телефона:  " + phoneBook.get(key));
    }
  }
}
