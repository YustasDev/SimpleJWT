import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.bson.Document;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    private static Scanner scanner;
    private final static String COMMAND_SET = "(set)(\\D+)";
    private final static String COMMAND_ADD = "(add)(\\D+)(\\d+)";
    private final static String COMMAND_PLACE = "(place)\\s+(\\D+)\\s+(\\D+)";
    private final static String COMMAND_STATISTICS = "statistics";
    private final static String COMMAND_END = "END";


    public static void main(String[] args) {

//        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
//        MongoDatabase database = mongoClient.getDatabase("mongoStores");
//        MongoCollection<Document> listStores = database.getCollection("stores");
//        MongoCollection<Document> ListProducts = database.getCollection("products");
//        listStores.drop();
//        ListProducts.drop();

        DB db = new MongoClient().getDB("mongoStores");
        Jongo jongo = new Jongo(db);
        MongoCollection stores = jongo.getCollection("stores");
        MongoCollection products = jongo.getCollection("products");







        printСondition();

        for (; ; ) {
            scanner = new Scanner(System.in);
            String input = inputCommand(
                    "Введите команду:  ");

            commandRecognition(input);

           // if





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

    private static Map<String, Document> commandRecognition (String command){
        Map<String, Document> dataMap = new HashMap<>();

        if (command.matches(COMMAND_SET)) {
            Pattern pattern = Pattern.compile(COMMAND_SET);
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                String storeName = matcher.group(2);
                Document store = new Document()
                        .append("storeName", storeName);
                dataMap.put("store", store);
            }
        }
        else if (command.matches(COMMAND_ADD)) {
            Pattern pattern = Pattern.compile(COMMAND_ADD);
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                String productName = matcher.group(2);
                String productPrice = matcher.group(3);
                Document product = new Document()
                        .append("productName", productName)
                        .append("productPrice", productPrice);
                dataMap.put("product", product);
            }
        }
        else if (command.matches(COMMAND_PLACE)) {
            String[] delimiter = command.split(" ");
            String placeProductName = delimiter[1];
            String placeStoreName = delimiter[2];
            Document placeProductInStore = new Document()
                    .append("placeProductName", placeProductName)
                    .append("placeStoreName", placeStoreName);
            dataMap.put("placeProductInStore", placeProductInStore);
        }
        else if (command.matches(COMMAND_STATISTICS)) {


        }

        else if (command.matches(COMMAND_END)) {
            System.exit(0);
        }
        else {
            System.out.println("Команда не распознана. Введите еще раз.");
        }
        return dataMap;
    }
}
