import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TestClass {

    static int count = 2;

    public static void main(String[] args) {
//       TestClass testClass = new TestClass();
//        testClass.testFunc();
        testFunc();
    }

    synchronized private static void testFunc() {
        //callMe("start testFunc()");
        TestCallMe.callMe("start testFunc()");
        try {
            FutureTask ft = new FutureTask<>(new TestThread());
            new Thread(ft).start();
            System.out.println("result: " + ft.get());
        } catch (Exception e) {
            System.out.println("error of future task: " + e);
        }
    }

    synchronized static void callMe(String message) {
        System.out.println("Thread: " + Thread.currentThread().getName() + "   went into 'callMe'");
        System.out.println("'callMe', message: " + message);
    }

}

class TestThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("start 'call()' of future task");
        System.out.println("Thread: " + Thread.currentThread().getName() + "  wants to call 'callMe'");

        //TestClass.callMe("called from future task");
        TestCallMe.callMe("called from future task");
        return 42;
    }

}
