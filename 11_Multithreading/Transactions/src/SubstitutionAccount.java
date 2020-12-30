

public class SubstitutionAccount implements java.lang.reflect.InvocationHandler {

  private Object obj;

  public SubstitutionAccount(Object account){
    obj = account;
  }

  public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
      throws Throwable {
    System.out.println("Ваш счет заблокирован ");
    return method.getName() ;
  }
}