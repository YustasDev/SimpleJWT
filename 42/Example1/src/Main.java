import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    //column_cord = {"R1": ((87, 10), (157, 539)), "R2": ((161, 10), (232, 539)),
      //  "R3": ((235, 10), (313, 539)), "R4": ((315, 10), (382, 539)),

        public static void main(String[] args) {

        int [][] R1 = {{87,10}, {157, 539}};
        int [][] R2 = {{161,10}, {232, 539}};
        int [][] R3 = {{235,10}, {313, 539}};
        int [][] R4 = {{315,10}, {382, 539}};
        Map<String, int[][] > columnCord = new TreeMap<>();
        columnCord.put("R1", R1);
        columnCord.put("R2", R2);
        columnCord.put("R3", R3);
        columnCord.put("R4", R4);

        // Проверим, что получилось:
       columnCord.entrySet().forEach(entry ->
       {System.out.println(entry.getKey() + " = " +  Arrays.deepToString(entry.getValue()));
       });
 }
}
