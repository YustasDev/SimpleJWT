import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static String dataFile = "data/movementList.csv";


  public static void main(String[] args) {

    ArrayList<BankStatement> extract = loadExtractFromFile(); // формируем список из элементов класса BankStatement

    extract.stream().forEach(System.out::println);

    Double sumIncome = extract.stream()
        .mapToDouble(BankStatement::getIncome).sum();
    System.out.println("Сумма доходов : " + sumIncome + " руб.");

    Double sumExpense = extract.stream()
        .mapToDouble(BankStatement::getExpense).sum();
    System.out.println("Сумма расходов : " + sumExpense + " руб.");



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

      for (String[] row : lines) {           // перебираем элементы каждой строки, проверяем, парсим и прибавляем к extract
       if (row.length != 8) {
            System.out.println("Wrong line: " + row);
            continue;
          }
       // некоторые элементы колонок в исходном файле имеют вид "ххх,хх", поэтому заменяем запятую на точку
       String element_6 = row[6].replace(',','.');
       String element_7 = row[7].replace(',','.');

       String s55 = row[5].replaceAll("[^a-zA-Z]", "");
        System.out.println("s55 = " + s55);



          String s5 = row[5];

        Pattern p = Pattern.compile("[a-zA-Z]+");

        Matcher m5 = p.matcher(s5);

       System.out.println("Words from string \"" + s5 + "\" : ");

        while (m5.find()) {

          System.out.println(m5.group());}

//        extract.add(new BankStatement(
//              element_5,
//              Double.parseDouble(element_6),
//              Double.parseDouble(element_7)
//          ));
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return extract;
  }
}

