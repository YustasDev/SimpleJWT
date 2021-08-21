import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bson.Document;
import java.util.Scanner;


public class Main {

  private static Scanner scanner;
  private final static String COMMAND_SET = "(set)(\\D+)";
  private final static String COMMAND_ADD = "(add)(\\D+)(\\d+)";
  private final static String COMMAND_PLACE = "(place)\\s+(\\D+)\\s+(\\D+)";
  private final static String COMMAND_STATISTICS = "statistics";
  private final static String COMMAND_END = "END";


  public static void main(String[] args) {

    MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

    MongoDatabase database = mongoClient.getDatabase("mongoStores");

    MongoCollection<Document> listStore = database.getCollection("stores");
    MongoCollection<Document> productsRegistry = database.getCollection("products");
    printСondition();

    for (; ; ) {
      scanner = new Scanner(System.in);
      String input = inputCommand(
          "Введите команду:  ");

      commandRecognition(input);






    }


  }

  private static String inputCommand(String message) {
    for (; ; ) {
      System.out.println(message);
      String line = scanner.nextLine().trim();
      if (line != null) {
        return line;
      }
    }
  }

  private static void printСondition() {
    System.out.println("Вы можете воспользоваться следующими функциями: \n"
        + "1. Добавить в базу данных магазин. Для этого ввести команду: \n"
        + "set + название магазина. В одно слово, без пробела. Пример: setПятерочка\n"
        + "\n"
        + "2. Добавить в базу данных товар. Для этого ввести команду:   \n"
        + "add + название товара, в одно слово, без пробела. Затем укажите целое число — цену товара в рублях.\n"
        + "Пример: addвафли54\n"
        + "\n"
        + "3. Разместить определенный товар на продажу в магазине. Для этого ввести команду: \n"
        + "place + название товара и магазина через пробелы.\n"
        + "Пример: place вафли Пятерочка\n"
        + "\n"
        + "4. Команда statistics выведет статистику товаров по каждому магазину \n"
        + "5. Если Вы хотите прекратить выполнение программы, наберите команду: END ");
  }

  private static String commandRecognition (String command){

    if (command.matches(COMMAND_SET)) {
      Pattern pattern = Pattern.compile(COMMAND_SET);
      Matcher matcher = pattern.matcher(command);
      if (matcher.find()) {
        String storeName = matcher.group(2);
      }
    }
    if (command.matches(COMMAND_ADD)) {
      Pattern pattern = Pattern.compile(COMMAND_ADD);
      Matcher matcher = pattern.matcher(command);
      if (matcher.find()) {
        String productName = matcher.group(2);
        String productPrice = matcher.group(3);
      }
    }
    if (command.matches(COMMAND_PLACE)) {
      String[] delimiter = command.split(" ");
        String productName = delimiter[1];
        String storeName = delimiter[2];
  }






}

