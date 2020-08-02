import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {

  private static final int INDEX_ORGANIZATION = 5;
  private static final int INDEX_INCOM = 6;
  private static final int INDEX_EXPENSE = 7;
  private static String dataFile = "data/movementList.csv";
  private static ArrayList<BankStatement> extract;

  public static void main(String[] args) {
    extract = loadExtractFromFile(); // формируем список из элементов класса BankStatement
    printSumExpense();
    printSumIncome();
    printAmountExpensesByOrganization();
  }

  private static void printSumExpense() {
    Double sumExpense = extract.stream()
        .mapToDouble(BankStatement::getExpense).sum();
    System.out.println("Сумма расходов : " + sumExpense + " руб.");
  }

  private static void printSumIncome() {
    Double sumIncome = extract.stream()
        .mapToDouble(BankStatement::getIncome).sum();
    System.out.println("Сумма доходов : " + sumIncome + " руб.");
  }

  private static void printAmountExpensesByOrganization() {
    Map<String, Double> sumExpenseOrg = extract
        .stream().collect(Collectors.groupingBy(BankStatement::getOrganization, TreeMap::new,
            Collectors.summingDouble(BankStatement::getExpense)));

    System.out.println(
        "\nСуммы расходов по организациям: \n ");  // распечатываем в порядке уменьшения расходов
    sumExpenseOrg.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(System.out::println);
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
        String elementIncome = row[INDEX_INCOM].replace(',', '.');
        String elementExpense = row[INDEX_EXPENSE].replace(',', '.');

        String elementOrganization = row[INDEX_ORGANIZATION].replaceAll("[^a-zA-Z]",
            "");  // преобразуем колонку "описание операции" в колонку "организация"

        extract.add(new BankStatement(    //  парсим и прибавляем к extract
            elementOrganization,
            Double.parseDouble(elementIncome),
            Double.parseDouble(elementExpense)
        ));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(13);         //  что-то пошло не так
    }
    return extract;
  }
}

