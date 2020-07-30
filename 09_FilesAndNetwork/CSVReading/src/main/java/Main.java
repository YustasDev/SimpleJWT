import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

  private static String dataFile = "data/movementList.csv";


  public static void main(String[] args) {

    ArrayList<BankStatement> extract = loadExtractFromFile();

    extract.stream().forEach(System.out::println);
  }

  private static ArrayList<BankStatement> loadExtractFromFile() {

    ArrayList<BankStatement> extract = new ArrayList<>();

    try {

      FileReader filereader = new FileReader(dataFile);

      // создаем объект csvReader и пропускаем первую строку
      CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
      CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).withSkipLines(1)
          .build();
      List<String[]> lines = csvReader.readAll();

      for (String[] row : lines) {
        // System.out.print("Длина строки = " + row.length + "\t");
        for (int i = 0; i < row.length; i++) {
          // System.out.print("i = " + i + "  " + row[i] + "\t");
          if (row.length != 8) {
            System.out.println("Wrong line: " + row);
            continue;
          }
          extract.add(new BankStatement(
              row[5],
              Double.parseDouble(row[6]),
              Double.parseDouble(row[7])
          ));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return extract;
  }
}

