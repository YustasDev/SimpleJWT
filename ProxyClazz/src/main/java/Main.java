import java.lang.reflect.Proxy;

public class Main {

  Person person = new Person();

  IPerson personproxy = (IPerson) Proxy.newProxyInstance(Person.class.getClassLoader(),
      Person.class.getInterfaces(),
      new NeverSleepingEye(person));

	personproxy.setName("Гриша");
  String h  = personproxy.getName() ;
	personproxy.rename("Вася");



}
