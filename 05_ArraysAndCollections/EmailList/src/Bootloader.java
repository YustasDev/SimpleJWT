import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Bootloader {

  public static void main(String[] args) throws IOException {

    final String COMMAND_TEXT_EMAIL = "(?i)(ADD)\\s+(.+)";
    final String COMMAND_LIST = "(?i)LIST";
    final String COMMAND_END = "(?i)END";
    final String REGEXEMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    String command = null;

    printUsage();

    EmailList myEmailList = new EmailList();

    myEmailList.emailList.add("111@mail.ru");
    myEmailList.emailList.add("222@mail.ru");
    myEmailList.emailList.add("333@mail.ru");
    myEmailList.emailList.add("444@mail.ru");
    myEmailList.emailList.add("555@mail.ru");
    myEmailList.emailList.add("aaa@mail.ru");
    myEmailList.emailList.add("bbb@mail.ru");
    myEmailList.emailList.add("ccc@mail.ru");
    myEmailList.emailList.add("ddd@mail.ru");
    myEmailList.emailList.add("eee@mail.ru");

    while (true) {
      Scanner scanner = new Scanner(System.in);
      command = scanner.nextLine();

      if (command.matches(COMMAND_LIST)) {
        myEmailList.printList();
      } else if (command.matches(COMMAND_TEXT_EMAIL)) {
        String emailText = command.replaceAll(COMMAND_TEXT_EMAIL, "$2");

        if (emailText.matches(REGEXEMAIL)) {
          myEmailList.addEmail(emailText);
        } else {
          System.out.println("Вы не указали или неправильно указали e-mail адрес. Попробуйте еще раз");
        }

      } else if (command.matches(COMMAND_END)) {
        System.out.println("Работа программы окончена");
        return;
      } else {
        System.out.println("Команда не распознана. Введите еще раз.");
      }
    }
  }

  public static void printUsage() {
    System.out.println("Программа содержит перечень e-mail адресов. Если Вы хотите добавить е-mail адрес, его можно добавить через консоль командой ADD");
    System.out.println("Формат ввода команды:  ADD e-mail");
    System.out.println("Чтобы напечатать весь список - используйте команду LIST");
    System.out.println("Если Вы хотите прекратить выполнение программы, наберите команду: END ");
    System.out.println("Вызовите команду : ");
  }
}
