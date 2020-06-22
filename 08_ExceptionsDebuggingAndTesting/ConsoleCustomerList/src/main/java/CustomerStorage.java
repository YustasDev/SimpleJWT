import MyExceptions.NotValidEmailException;
import MyExceptions.NotValidNameException;
import MyExceptions.NotValidPhoneException;
import java.util.HashMap;

public class CustomerStorage {

  final String TELEFONNUMBER = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3,4}\\)?[\\- ]?)?[\\d\\- ]{5,10}$";
  final String REGEXEMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
  private HashMap<String, Customer> storage;

  public CustomerStorage() {
    storage = new HashMap<>();
  }

  public void addCustomer(String data) throws Exception {
    String[] components = data.split("\\s+");
    if (components.length > 4 || components.length < 4) {
      throw new NotValidNameException("The number of elements in a command is not equal to four!",
          data);
    } else if (components.length == 4) {
      String name = components[0] + " " + components[1];
      if (components[3].matches(TELEFONNUMBER) && components[2].matches(REGEXEMAIL)) {
        storage.put(name, new Customer(name, components[3], components[2]));
      } else if (components[2].matches(REGEXEMAIL) && !(components[3].matches(TELEFONNUMBER))) {
        throw new NotValidPhoneException("Incorrect phone number", components[3]);
      } else if (components[3].matches(TELEFONNUMBER) && !(components[2].matches(REGEXEMAIL))) {
        throw new NotValidEmailException("Incorrect email address", components[2]);
      } else {
        System.out.println("Incorrect phone number and email address"
            + ", enter the information again");
      }
    }
  }

  public void listCustomers() {
    storage.values().forEach(System.out::println);
  }

  public void removeCustomer(String name) throws Exception {
    storage.remove(name);
  }

  public int getCount() {
    return storage.size();
  }
}