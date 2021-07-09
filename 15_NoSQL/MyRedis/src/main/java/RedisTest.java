import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RedisTest {

  // Запуск докер-контейнера:
  // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

  // Для теста будем считать неактивными пользователей, которые не заходили 2 секунды
  private static final int DELETE_SECONDS_AGO = 2;

  // Допустим пользователи делают 500 запросов к сайту в секунду
  private static final int RPS = 500;

  // И всего на сайт заходило 1000 различных пользователей
  private static final int USERS = 1000;

  // Также мы добавим задержку между регистрациями
  private static final int SLEEP = 100; // 100 миллисекунд

  private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

  private static void log(int UsersOnline) {
    String log = String.format("[%s] Пользователей онлайн: %d", DF.format(new Date()), UsersOnline);
    System.out.println(log);
  }

  public static void main(String[] args) throws InterruptedException {

    RedisStorage redis = new RedisStorage();
    redis.init();
    // запускаем бесконечный цикл
    for (; ; ) {
      // Зарегистрируем 20 пользователей
      for (int userId = 1; userId < 21; userId++) {
        redis.logPageVisit(userId);
       // Thread.sleep(SLEEP);
      }

      System.out.println(redis.getAllusers());
      System.out.println(redis.getRankUsers());
      System.out.println(redis.getAllusers());
//.
//
//      for(int request = 0; request <= RPS; request++) {
//        int user_id = new Random().nextInt(USERS);
//        redis.logPageVisit(user_id);
//
//
//        Thread.sleep(SLEEP);
//      }
//      redis.deleteOldEntries(DELETE_SECONDS_AGO);
//      int usersOnline = redis.calculateUsersNumber();
//      log(usersOnline);
//    }
      redis.shutdown();
    }
  }
}


