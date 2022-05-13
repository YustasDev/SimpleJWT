import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    //column_cord = {"R1": ((87, 10), (157, 539)), "R2": ((161, 10), (232, 539)),
      //  "R3": ((235, 10), (313, 539)), "R4": ((315, 10), (382, 539)),

        public static void main(String[] args) {
            List<Object> people = new ArrayList<>();
            List<String> modifyPeople = new ArrayList<>();
            List<Object> withoutNull = new ArrayList<>();
            people.add("Hel,lo");
            people.add(null);
            people.add("Abraham, Fish");
            people.add("Luis, Viton");
            people.add(null);
            System.out.println(people);
            for (Object person : people){
                String newPerson = (String) person;
                if (newPerson!=null){
                newPerson = newPerson.replace(",", "");}
                modifyPeople.add(newPerson);
            }
            System.out.println(modifyPeople);
            String result = String.join(",", modifyPeople);
            System.out.println(result);
            withoutNull.addAll(0, modifyPeople);
            System.out.println(withoutNull);
            withoutNull.remove(4);
            System.out.println(withoutNull);

            List<List<Object>> rows = new ArrayList<>();
            rows.add(people);
            List<Object> modP = new ArrayList<Object>(modifyPeople);
            rows.add(modP);
            rows.add(withoutNull);


            try (CSVPrinter printer = new CSVPrinter(new FileWriter("csv.txt"), CSVFormat.EXCEL)) {
                printer.printRecord(modifyPeople);
                printer.printRecord(withoutNull);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            String path = "/home/progforce/java_basics/42/Example1/example.csv";

            try (
                    BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), Charset.forName("UTF-8"));
                    CSVPrinter csv = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter('|'));
            ) {

                for (List<Object> row : rows) {
                    csv.printRecord(row);
                }
                csv.flush();
            } catch (IOException e) {
                System.out.println("Ooooooooooooooooops!");
            }
        }
}



//        int [][] R1 = {{87,10}, {157, 539}};
//        int [][] R2 = {{161,10}, {232, 539}};
//        int [][] R3 = {{235,10}, {313, 539}};
//        int [][] R4 = {{315,10}, {382, 539}};
//        Map<String, int[][] > columnCord = new TreeMap<>();
//        columnCord.put("R1", R1);
//        columnCord.put("R2", R2);
//        columnCord.put("R3", R3);
//        columnCord.put("R4", R4);
//
//        // Проверим, что получилось:
//       columnCord.entrySet().forEach(entry ->
//       {System.out.println(entry.getKey() + " = " +  Arrays.deepToString(entry.getValue()));
//       });
// }
//}
