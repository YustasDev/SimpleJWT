import java.util.ArrayList;

  public class TodoList {

    ArrayList<String> manageList = new ArrayList<>();


    public void printList() {
      System.out.println("Полный список дел: " + manageList);
    }

    public void addToListIndex(int i, String group) {
      manageList.add(i, group);
      System.out.println(manageList);
    }

    public void addText(String group) {
      manageList.add(group);
      System.out.println(manageList);
    }

    public void editList(int i, String group) {
      manageList.set(i, group);
      System.out.println(manageList);
    }

    public void deleteList(int i) {
      manageList.remove(i);
      System.out.println(manageList);
    }
  }







