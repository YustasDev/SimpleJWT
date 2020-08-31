
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Collections;


public class Converter {

  private static String baseFile = "c:/Users/Yustas/java_basics/09_FilesAndNetwork/MetroParser/data/metro.json";

  // используем Jackson Framework для преобразования из объекта в json.файл
  public static File fromPrototypeMetroToJSON(PrototypeMetro prototypeMetro)
      throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    File outputFile = new File(baseFile);
    mapper.writeValue(outputFile, prototypeMetro);
    System.out.println("json created!");
    return outputFile;
  }

  public static PrototypeMetro jsonReaderFromFile(File outputFile)
      throws Exception {  // читаем файл и переводим в объект
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(outputFile, PrototypeMetro.class);
  }

  public static void printCountingStations(File outputFile) throws Exception {
    // выводит в консоль количество станций на каждой линии
    for (MetroLine line : jsonReaderFromFile(outputFile).getLines()) {
      int stations = jsonReaderFromFile(outputFile).stations
          .getOrDefault(line.getNumber(), Collections.emptyList())
          .size();
      System.out.printf("Линия '%s' - станций: %d%n", line.getName(), stations);
    }
  }
}

