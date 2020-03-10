import java.util.ArrayList;

public class TodoList {

  ArrayList<String> manageList = new ArrayList<>();

  public void printList() {
    System.out.println("Полный список дел: " + manageList);
  }

  public boolean addToListIndex(int i, String group) {
    if (i < 0 || i >= manageList.size()) {
      return false;
    }
    manageList.add(i, group);
    return true;
   }

  public void addText(String group) {
    manageList.add(group);
    System.out.println(manageList);
  }

  public boolean editList(int i, String group) {
    if (i < 0 || i >= manageList.size()) {
      return false;
    }
    manageList.set(i, group);
    return true;
  }

  public boolean deleteList(int i) {
    if (i < 0 || i >= manageList.size()) {
    return false;
    }
    manageList.remove(i);
    return true;
  }
}







