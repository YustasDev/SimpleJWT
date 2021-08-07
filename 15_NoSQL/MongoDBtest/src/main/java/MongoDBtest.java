import static com.mongodb.client.model.Aggregates.count;
import static com.mongodb.client.model.Aggregates.match;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

public class MongoDBtest {

  private static final int INDEX_NAMESTUDENT = 0;
  private static final int INDEX_AGESTUDENT = 1;
  private static final int INDEX_COURSESSTUDENT = 2;
  private static String dataFile = "data/mongo.csv";


  public static void main(String[] args) {

    MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

    MongoDatabase database = mongoClient.getDatabase("students");

    MongoCollection<Document> collection = database.getCollection("listStudents");

    collection.drop();
    List<Document> listingEnrolledStudents = loadExtractFromFile(dataFile);
    collection.insertMany(listingEnrolledStudents);

    long studentNumber = collection.countDocuments();

    Document gt40 = collection.aggregate(Arrays.asList(
        match(Filters.gt("ageStudent", 40)),
        count())).first();

    FindIterable<Document> youngestStudent = collection
        .find()
        .sort(new Document("ageStudent", 1))
        .limit(1);

    FindIterable<Document> oldestStudent = collection
        .find()
        .sort(new Document("ageStudent", -1))
        .limit(1);

    System.out.println("Oбщее количество студентов в базе:  " + studentNumber);
    System.out.println("Количество студентов старше 40 лет:  " + gt40.get("count"));
    System.out.println("Имя самого молодого студента:  " + youngestStudent.first().get("nameStudent"));
    System.out.println("Cписок курсов самого старого студента:  " + oldestStudent.first().get("coursesStudent"));
  }

  private static String printDoc(Document document) {
    var settings = JsonWriterSettings.builder()
        .indent(true)
        .outputMode(JsonMode.SHELL)
        .build();
    return document.toJson(settings);
  }

  private static List<Document> loadExtractFromFile(String dataFile) {

    List<Document> extract = new ArrayList<>();
    try {
      FileReader filereader = new FileReader(dataFile);
      CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
      CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).build();
      List<String[]> lines = csvReader.readAll();

      for (int i = 0; i < lines.size(); i++) {
        String[] oneLine = lines.get(i);
        if (oneLine.length != 3) {
          System.out.println("Wrong line in dataFile: " + oneLine);
          System.out.println("There is an urgent need to address this issue!");
          lines.remove(i);
        }
      }

      for (String[] row : lines) {
        String nameStudent = row[INDEX_NAMESTUDENT];
        Integer ageStudent = Integer.parseInt(row[INDEX_AGESTUDENT]);
        String coursesStudent = row[INDEX_COURSESSTUDENT];

        Document student = new Document()
            .append("nameStudent", nameStudent)
            .append("ageStudent", ageStudent)
            .append("coursesStudent", coursesStudent);

        extract.add(student);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return extract;
  }
}
