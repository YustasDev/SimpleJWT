
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


public class Converter {

  // используем Jackson Framework для преобразования из объекта в json.файл
  public static File fromPrototypeMetroToJSON(PrototypeMetro prototypeMetro)
      throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    File outputFile = new File(String.valueOf(Main.OUTPUT_FILE));
    mapper.writeValue(outputFile, prototypeMetro);
    return outputFile;
  }

  public static PrototypeMetro jsonReaderFromFile(File outputFile)
      throws Exception {  // читаем файл и переводим в объект
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(outputFile, PrototypeMetro.class);
  }

  public static Map<String, Integer> returnCountingStations(File outputFile) throws Exception {

    Map<String, Integer> countingStations = new LinkedHashMap<>();
    for (MetroLine line : jsonReaderFromFile(outputFile).getLines()) {
      Integer stations = jsonReaderFromFile(outputFile).stations
          .getOrDefault(line.getNumber(), Collections.emptyList())
          .size();
      countingStations.put(line.getName(), stations);
    }
    return countingStations;
  }
}

