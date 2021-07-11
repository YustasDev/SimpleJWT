import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.redisson.client.protocol.ScoredEntry;

public class RedisTest {

  // Запуск докер-контейнера:
  // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

  // Один из 100 пользователей оплачивает услугу
  private static final int USER_WHO_PAYS = 100;

  // добавим задержку между регистрациями пользователей
  private static final int PAUSE_REGISTRATION = 10; // 10 миллисекунд

  // задержка между циклами показа
  private static final int SLEEP = 1000;

  private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

  private static void log(int UsersOnline) {
    String log = String.format("[%s] Пользователей онлайн: %d", DF.format(new Date()), UsersOnline);
    System.out.println(log);
  }

  public static void main(String[] args) throws InterruptedException {

    RedisStorage redis = new RedisStorage();
    redis.init();
    for (; ; ) {
      // Зарегистрируем 100 пользователей
      int numberOfUsers = 100;
      for (int userId = 1; userId < numberOfUsers +1; userId++) {
        redis.logPageVisit(userId);
        Thread.sleep(PAUSE_REGISTRATION);
      }

      // Создаем список пользователей по порядку регистрации
      Collection<String> listUsers = new ArrayList<>();
      listUsers = redis.getRankUsers();
      int count = 1;
      // номер в каждой десятке, на который припадает выбор пользователя, который платит
      int chance = new Random().nextInt(10) +1;
      int countChance = 1;

      for (int userId = 1; userId < listUsers.size() +1; userId++) {

        if (count == chance) {
          int numberUserWhoPaid = new Random().nextInt(USER_WHO_PAYS) + 1;
          if (redis.getUser(numberUserWhoPaid).isEmpty()){
            System.out.println("В этот раз никто не оплатил услугу показа на главной странице");
          }
          String userWhoPaid = redis.getUser(numberUserWhoPaid);
          System.out.println("Пользователь " + numberUserWhoPaid + " оплатил платную услугу");
          System.out.println(userWhoPaid);
          redis.deleteUser(userWhoPaid);
        }
        String user = redis.getUser(userId);
        System.out.println(user);
        count++;
        if (count % 10 == 0) {
          chance = (new Random().nextInt(10) +1) + countChance*10;
          countChance++;
        }
      }

      Thread.sleep(SLEEP);
    //  redis.shutdown();
    }
  }
}


