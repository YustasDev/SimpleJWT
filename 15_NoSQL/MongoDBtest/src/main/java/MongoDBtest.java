import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;
import org.bson.BsonDocument;
import org.bson.Document;

public class MongoDBtest {

  public static void main(String[] args) {
    MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

    MongoDatabase database = mongoClient.getDatabase("local");

    // Создаем коллекцию
    MongoCollection<Document> collection = database.getCollection("TestMongoDBDemo");

    // Удалим из нее все документы
    collection.drop();

    // Создадим первый документ
    Document firstDocument = new Document()
        .append("Type", 1)
        .append("Description", "Это мой первый документ в MongoDB")
        .append("Author", "Me")
        .append("Time", new SimpleDateFormat().format(new Date()));


    // Вложенный объект
    Document nestedObject = new Document()
        .append("Course", "NoSQL Базы Данных")
        .append("Author", "Yustas Goosseff");

    firstDocument.append("Skillbox", nestedObject);


    // Вставляем документ в коллекцию
    collection.insertOne(firstDocument);

    collection.find().forEach((Consumer<Document>) document -> {
      System.out.println("Это первый документ:\n" + document);
    });

    // Используем JSON-синтаксис для создания объекта
    Document secondDocument = Document.parse(
        "{Type: 2, Description:\"Этот документ создан с помощью JSON-синтаксиса\"}"
    );
    collection.insertOne(secondDocument);

    // Используем JSON-синтаксис для написания запроса (выбираем документы с Type=2)
    BsonDocument query = BsonDocument.parse("{Type: {$eq: 2}}");
    collection.find(query).forEach((Consumer<Document>) document -> {
      System.out.println("Это второй документ:\n" + document);
    });
  }
}