import java.util.TreeSet;
import javax.management.AttributeList;

public class EmailList {

  TreeSet<String> emailList = new TreeSet<>();

  public void printList() {
    System.out.println("Полный список e-mail адресов: " + emailList);
  }

  public void addEmail(String email) {
    emailList.add(email);
    System.out.println("Вывод списка в столбмк");
    for (String emails : emailList)
    {
      System.out.println(emails);
    }

    System.out.println("Полный список e-mail адресов:  " + emailList);
  }
}
