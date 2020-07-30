import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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
      List<String> lines = Files.readAllLines(Paths.get(dataFile));
      for (String line : lines) {
        String[] fragments = line.split(",");
        if (fragments.length != 8) {
          System.out.println("Wrong line: " + line);
          continue;
        }
        ArrayList<String> columnList = new ArrayList<String>();
        for (int i = 0; i < fragments.length; i++) {
          //Если колонка начинается на кавычки или заканчиваеться на кавычки
          if (IsColumnPart(fragments[i])) {
            String lastText = columnList.get(columnList.size() - 1);
            columnList.set(columnList.size() - 1, lastText + "," + fragments[i]);
          } else {
            columnList.add(fragments[i]);
          }

          try {
            extract.add(new BankStatement(
                fragments[5],
                Double.parseDouble(fragments[6]),
                Double.parseDouble(fragments[7])
            ));
          } catch (Exception e) {
            e.printStackTrace();
            System.out
                .println("fragments[6]) = " + fragments[6] + "fragments[7]) = " + fragments[7]);
          }
        }
      }
    }
    catch(Exception ex){
        ex.printStackTrace();
      }
      return extract;
    }



  //Проверка является ли колонка частью предыдущей колонки
  private static boolean IsColumnPart(String text) {
    String trimText = text.trim();
    //Если в тексте одна ковычка и текст на нее заканчивается значит это часть предыдущей колонки
    return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
  }
}

