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

  // Для теста будем считать неактивными пользователей, которые не заходили 2 секунды
  private static final int DELETE_SECONDS_AGO = 2;

  // Допустим пользователи делают 500 запросов к сайту в секунду
  private static final int RPS = 500;

  // Один из 20 пользователей оплачивает услугу
  private static final int USER_WHO_PAYS = 20;

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
    // запускаем бесконечный цикл
    for (; ; ) {
      // Зарегистрируем 20 пользователей
      int numberOfUsers = 20;
      for (int userId = 1; userId < numberOfUsers +1; userId++) {
        redis.logPageVisit(userId);
        Thread.sleep(PAUSE_REGISTRATION);
      }

//      System.out.println(redis.getAllusers());
//      System.out.println(redis.getRankUsers());
//      List <Double> listOfScore = new ArrayList<>();
//      redis.getRankUsers().forEach(element ->
//           listOfScore.add(redis.getScoreUsers(element)));
//      listOfScore.forEach(System.out::println);
//


      // Создаем список пользователей по порядку регистрации
      Collection<String> listUsers = new ArrayList<>();
      listUsers = redis.getRankUsers();
      int count = 1;
      int chance = new Random().nextInt(10) +1; // в одном из 10 случаев
      //Collection<String> finalListUsers = listUsers;
      //redis.deleteUser(user);

      for (int userId = 1; userId < listUsers.size() +1; userId++) {

        if (count == chance) {
          int numberUserWhoPaid = new Random().nextInt(USER_WHO_PAYS) + 1;
          String userWhoPaid = redis.getUser(numberUserWhoPaid);
          System.out.println("Пользователь " + numberUserWhoPaid + " оплатил платную услугу");
          System.out.println(userWhoPaid);
          redis.deleteUser(userWhoPaid);
        }
        String user = redis.getUser(userId);
        System.out.println(user);
        count++;
        if (count % 10 == 0) {
          chance = new Random().nextInt(10) +1;
        }

      }


//
//
//      for (String visitor : listUsers) {
//        System.out.println(visitor);
//        if (listUsers.contains(user)) {
//          System.out.println("Пользователь " + user_id + " оплатил платную услугу");
//          System.out.println(user);
//          listUsers.remove(user);
//        }
//      }

//      finalListUsers.forEach(el -> {
//
//        if (finalListUsers.contains(user)){
//          System.out.println("Пользователь " + user_id + " оплатил платную услугу");
//          System.out.println(user);
//          finalListUsers.remove(user);
//        }
//        try {
//          Thread.sleep(SLEEP);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//        System.out.println(el);
//
//          }
//      );





//
//      for (int userId = 1; userId <= sizelistUsers; userId++) {
//
//
//        System.out.println(listUsers.("Пользователь № " + userId));



      //listOfUsers.forEach(System.out::println);


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
      Thread.sleep(SLEEP);
      redis.shutdown();
    }
  }
}


