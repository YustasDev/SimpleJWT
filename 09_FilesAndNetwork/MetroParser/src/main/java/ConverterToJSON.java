
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ConverterToJSON {

  //private static String baseFile = "c:/Users/Yustas/java_basics/09_FilesAndNetwork/MetroParser/data/metro.json";
  private static String baseFile = "metro.json";

//  public static String toJSON(List list) throws Exception {
//    JSONArray jsonArray = new JSONArray();
//    for (Object i : list) {
//      String jstr = toJSON((List) i);
//      JSONObject jsonObject = new JSONObject(jstr);
//      jsonArray.put(jsonObject);
//    }
//    return jsonArray.toString();
//  }


  public static void toJSON(List list) throws Exception {
      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(new File(baseFile), list);
      System.out.println("json created!");
  }






}
