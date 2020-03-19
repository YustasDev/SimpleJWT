import java.util.TreeSet;

public class EmailList {

  TreeSet<String> emailList = new TreeSet<>();

  public void printList() {
    System.out.println("Полный список e-mail адресов: " + emailList);
  }

  public void addEmail(String email) {
    emailList.add(email);
    System.out.println("Указанный e-mail адрес добавлен в список адресов");
    System.out.println("Вывод полного списка e-mail адресов: ");
    for (String emails : emailList) {
      System.out.println(emails);
    }
  }
}
