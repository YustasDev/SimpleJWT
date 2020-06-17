import java.util.HashMap;

public class CustomerStorage {

    final String TELEFONNUMBER = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3,4}\\)?[\\- ]?)?[\\d\\- ]{5,10}$";
    final String REGEXEMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        if (components.length > 4 || components.length < 4) {
            try {
                throw new Exception("The number of elements in a command is not equal to four!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(Main.helpText);
                return;
            }
        }
        if (components.length == 4) {
            String name = components[0] + " " + components[1];
            if (components[3].matches(TELEFONNUMBER) && components[2].matches(REGEXEMAIL)) {
                storage.put(name, new Customer(name, components[3], components[2]));
            } else {
                try {
                    throw new Exception("Incorrect phone number or email address");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(Main.helpText);
                    return;
                }
            }
        }
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}