

public class SubstitutionAccount implements java.lang.reflect.InvocationHandler {

  private Object obj;

  public SubstitutionAccount(Object account) {
    obj = account;
  }

  public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
      throws Throwable {

    if (method.getName().startsWith("set")) {
      System.out.println("Ваш счет заблокирован ");
      return null;
    }
    return method.invoke(obj, args);
  }
}