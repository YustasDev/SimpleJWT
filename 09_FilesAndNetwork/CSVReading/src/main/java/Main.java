import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  private static String dataFile = "data/movementList.csv";
  private static Map <String, Double> sumExpOrg = new TreeMap<>();


  public static void main(String[] args) {

    ArrayList<BankStatement> extract = loadExtractFromFile(); // формируем список из элементов класса BankStatement

   // extract.stream().forEach(System.out::println);

    Double sumIncome = extract.stream()
        .mapToDouble(BankStatement::getIncome).sum();
    System.out.println("Сумма доходов : " + sumIncome + " руб.");

    Double sumExpense = extract.stream()
        .mapToDouble(BankStatement::getExpense).sum();
    System.out.println("Сумма расходов : " + sumExpense + " руб.");

    String [] denominationOrganizations = extract.stream()
        .map(BankStatement::getOrganization)
        .distinct().toArray(String[]::new);

    Map <String, Double> sumExpOrg = extract.stream()     // группируем расходы по организациям
        .collect(Collectors.groupingBy(BankStatement::getOrganization,
            Collectors.summingDouble(BankStatement::getExpense)));

    System.out.println("Суммы расходов по организациям: ");
    for (Map.Entry entry: sumExpOrg.entrySet()) {
      System.out.println(entry);
    }

//    mapNeed = extract.stream()
//        .collect(Collectors.groupingBy(BankStatement::getOrganization),

            //Collectors.groupingBy(BankStatement::getExpense));
//


  }



  private static ArrayList<BankStatement> loadExtractFromFile() {

    ArrayList<BankStatement> extract = new ArrayList<>();

    try {

      FileReader filereader = new FileReader(dataFile);

      // создаем объект csvReader и пропускаем первую строку (заголовок)
      CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
      CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).withSkipLines(1)
          .build();
      List<String[]> lines = csvReader.readAll(); // читаем все строки файла и записываем в список

      for (String[] row : lines) {   // перебираем элементы каждой строки, проверяем, приводим к читаемому формату
       if (row.length != 8) {
            System.out.println("Wrong line: " + row);
            continue;
          }
       // некоторые элементы колонок в исходном файле имеют вид "ххх,хх", поэтому заменяем запятую на точку
       String element_6 = row[6].replace(',','.');
       String element_7 = row[7].replace(',','.');

       String element_5 = row[5].replaceAll("[^a-zA-Z]", "");  // преобразуем колонку "описание операции" в колонку "организация"

        extract.add(new BankStatement(    //  парсим и прибавляем к extract
              element_5,
              Double.parseDouble(element_6),
              Double.parseDouble(element_7)
          ));
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return extract;
  }
}

