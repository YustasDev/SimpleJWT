import java.lang.reflect.Proxy;

public class Main {

  public static void main(String[] args) {

    Person person = new Person();

    IPerson personproxy = (IPerson) Proxy.newProxyInstance(Person.class.getClassLoader(),
        Person.class.getInterfaces(),
        new NeverSleepingEye(person));

    person.setName("Гриша");
    String h = person.getName();
    person.rename("Вася");

    System.out.println(h + person);

    personproxy.setName("Петя");
    String hh = personproxy.getName();
    personproxy.rename("Федя");

    System.out.println(personproxy);
  }
}
