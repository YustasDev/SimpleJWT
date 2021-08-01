import static com.mongodb.client.model.Aggregates.limit;
import static com.mongodb.client.model.Aggregates.sample;
import static com.mongodb.client.model.Aggregates.sort;
import static com.mongodb.client.model.Aggregates.sortByCount;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

public class MongoDBtest {

  public static void main(String[] args) {
    MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

    MongoDatabase database = mongoClient.getDatabase("my_books");

    MongoCollection<Document> collection = database.getCollection("books");

    collection.drop();

    Document book1 = new Document()
        .append("Book_title", "Игра в бисер")
        .append("Author", "Герман Гессе")
        .append("Year_of_publication", 1971);

    Document book2 = new Document()
        .append("Book_title", "Степной волк")
        .append("Author", "Герман Гессе")
        .append("Year_of_publication", 1965);

    Document book3 = new Document()
        .append("Book_title", "Морской волк")
        .append("Author", "Джек Лондон")
        .append("Year_of_publication", 1972);

    Document book4 = new Document()
        .append("Book_title", "Человек в поисках смысла")
        .append("Author", "Виктор Франкл")
        .append("Year_of_publication", 1985);

    Document book5 = new Document()
        .append("Book_title", "Мастер и Маргарита")
        .append("Author", "Михаил Булгаков")
        .append("Year_of_publication", 1971);

    List<Document> listOfBooks = new ArrayList<>();
    listOfBooks.add(book1);
    listOfBooks.add(book2);
    listOfBooks.add(book3);
    listOfBooks.add(book4);
    listOfBooks.add(book5);

    collection.insertMany(listOfBooks);

    //var bookNeed = database.getCollection("books").find().sort({"Year_of_publication" : -1}).limit(1);

    //database.getCollection("books").aggregate( [ { $project : { Year_of_publication : 1 } } ] )

   // var year = collection.find(new BsonDocument()).filter(sortByCount("Year_of_publication"));


    

    collection.find()


//    for(Document doc : ) {
//      System.out.println(printDoc(doc));
//    }
//





        //min(y -> y.getField("Year_of_publication"));

        //sort(y -> y.getField("Year_of_publication");

   // collection.aggregate([ { $project : { Year_of_publication : 1 } } ])




  }

  private static String printDoc(Document document) {
    var settings = JsonWriterSettings.builder()
        .indent(true)
        .outputMode(JsonMode.SHELL)
        .build();
    return document.toJson(settings);
  }



}