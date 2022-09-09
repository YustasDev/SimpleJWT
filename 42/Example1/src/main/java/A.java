public class A<T> {

    private static Object object;

    public A(T ret) {
        object = (A<T>) ret;
    }

    public A() {
        object = null;
    }

    static Object GetValue() {
        Object obj = ((A) object).GetValue();
        return obj;
    }

    public static void main(String[] args) {
        A ret = new A<>();
        A a = new A<Object>(ret);
        a.GetValue();
    }
}


