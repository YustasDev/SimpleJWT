import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {


  public static void main(String[] args) {

    MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

    MongoDatabase database = mongoClient.getDatabase("mongoStores");

    MongoCollection<Document> listStore = database.getCollection("stores");
    MongoCollection<Document> productsRegistry = database.getCollection("products");

    System.out.println("Вы можете воспользоваться следующими функциями: \n"
        + "1. Добавить в базу данных магазин. Для этого ввести команду: \n"
        + "set + название магазина. В одно слово, без пробела. Пример: setПятерочка\n"
        + "\n"
        + "2. Добавить в базу данных товар. Для этого ввести команду:   \n"
        + "add + название товара, в одно слово, без пробела. Затем укажите целое число — цену товара в рублях.\n"
        + "Пример: addвафли54\n"
        + "\n"
        + "3. Показать цену определенного товара в магазине. Для этого ввести команду: \n"
        + "show + название товара и магазина через пробелы.\n"
        + "Пример: show вафли Пятерочка\n"
        + "\n"
        + "4. Команда statistics выведет статистику товаров по всем магазинам");


  }
}
