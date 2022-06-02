import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.*;

import static jdk.internal.dynalink.support.NameCodec.decode;


public class Main {

        //column_cord = {"R1": ((87, 10), (157, 539)), "R2": ((161, 10), (232, 539)),
        //  "R3": ((235, 10), (313, 539)), "R4": ((315, 10), (382, 539)),

        public static void main(String[] args) throws GeneralSecurityException, IOException {

//                String inputString = "Some String";
//                byte[] bytes = inputString.getBytes("UTF-8");
//                String encoded = Base64.getEncoder().encodeToString(bytes);
//
//                byte[] decoded = Base64.getDecoder().decode(encoded);
//                String decodedString = new String(decoded);
//
//                byte[] result = DatatypeConverter.parseBase64Binary(encoded);
//                String decodedString1 = new String(result);
//
//
//                System.out.println(encoded);
//                System.out.println(decoded);
//                System.out.println(decodedString);
//                System.out.println(decodedString1);

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



//                String timeStamp = new SimpleDateFormat("MM/dd/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
//                System.out.println(timeStamp);

                List<Object> objectList1 = new ArrayList<>();
                List<Object> objectList2 = new ArrayList<>();
                List<Object> objectList3 = new ArrayList<>();
                String itsA = "a";
                String itsB = "b";
                String itsC = "c";
                String itsD = "d";
                objectList1.add(itsA);
                objectList1.add(itsC);
                objectList2.add(itsB);
                objectList2.add(itsD);
                objectList3.add(itsB);
                objectList3.add(itsA);


                Map<String, List<Object>> someMap = new TreeMap<>();

                someMap.put("2020", objectList3);
                someMap.put("1998", objectList2);
                someMap.put("2018", objectList1);

                someMap.forEach((key, value) -> System.out.println("[Key] : " + key + " [Value] : " + value));
                someMap.forEach((key, value) ->{
                        String field = key;
                        System.out.println(key);
                        List<Object> valueList = value;
                        String type = (String) valueList.get(0);
                        String kind = (String) valueList.get(1);
                        if (type.equalsIgnoreCase("A")){
                                System.out.println("Its objectList1");
                                System.out.println("type ==> " + type);
                        }
                        else if (kind.equalsIgnoreCase("D") || kind.equalsIgnoreCase("A")){
                                System.out.println("Its objectList2 or objectList3");
                                System.out.println("kind ==> " + kind);
                        }
                        else {System.out.println("...");}

                        });
        }





//
//                for (Map.Entry<String, List<Object>> item : someMap.entrySet()) {
//                        System.out.printf("Key: %s  ", item.getKey());
//                }
//
//                Map<String, List<Object>> someMap1 = new HashMap<>();
//
//                checkMap(someMap);
//                checkMap(someMap1);




        public static void checkMap(Map<String, List<Object>> map){
                if (!map.isEmpty()){
                        System.out.printf("Not empty\n");
                }
                else {System.out.printf("Empty\n");}
        }
}









//                List<Object> resultList = someMap.get("1/1/2017");
//                if(resultList != null){
//                        System.out.println("Wrong");
//                }
//                else {
//                        System.out.println("Right");
//                }

//        }
//}


/*
                List<Object> people = new ArrayList<>();
                List<String> modifyPeople = new ArrayList<>();
                List<Object> withoutNull = new ArrayList<>();
                people.add("Hel,lo");
                people.add(null);
                people.add("Abraham, Fish");
                people.add("Luis, Viton");
                people.add("שנת הערכה");
                people.add(null);
                System.out.println(people);
                for (Object person : people) {
                        String newPerson = (String) person;
                        if (newPerson != null) {
                                newPerson = newPerson.replace(",", "");
                        }
                        modifyPeople.add(newPerson);
                }
                System.out.println(modifyPeople);
                String result = String.join(",", modifyPeople);
                System.out.println(result);
                withoutNull.addAll(0, modifyPeople);
                System.out.println(withoutNull);
                withoutNull.remove(5);
                System.out.println(withoutNull);

                List<List<Object>> rows = new ArrayList<>();
                rows.add(people);
                List<Object> modP = new ArrayList<Object>(modifyPeople);
                rows.add(modP);
                rows.add(withoutNull);

*/
//                try (CSVPrinter printer = new CSVPrinter(new FileWriter("csv.txt"), CSVFormat.DEFAULT.withDelimiter('|'))) {
//                        printer.printRecord(modifyPeople);
//                        printer.printRecord(withoutNull);
//                } catch (IOException ex) {
//                        ex.printStackTrace();
//                }

//                String fileCSV = "/home/progforce/java_basics/42/Example1/output/onlydir/example.csv";
//                String path = "/home/progforce/java_basics/42/Example1/output/onlydir";
//                File pathToCSV = new File(path);
//                boolean created = pathToCSV.mkdirs();
//
//                if (created) {
//                        try (
//                                BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileCSV), Charset.forName("UTF-8"));
//                                CSVPrinter csv = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter('|'));
//                        ) {
//
//                                for (List<Object> row : rows) {
//                                        csv.printRecord(row);
//                                }
//                                csv.flush();
//                        } catch (IOException e) {
//                                System.out.println("Ooooooooooooooooops!");
//                        }
//                }
//        }
//}





//           LocalDate someDate = LocalDate.of(1985, 3, 29);
//           LocalDate z = LocalDate.now();
//
//           System.out.println(someDate.toString() + " " + someDate);
//           System.out.println(z.toString() + " " + z);
//
//           Date currentDate = new Date();
//           System.out.println(currentDate.toString() + " " + currentDate);
//
//           SimpleDateFormat needDateFormat = new SimpleDateFormat("MM/dd/yyyy");
//           String formatedData = needDateFormat.format(currentDate);
//            System.out.println(formatedData.toString() + " " + formatedData);
//
//
//
//            int df = 13;
//            Double dg = 13.26;
//
//            try (CSVPrinter printer = new CSVPrinter(new FileWriter("csvOne.cvs"), CSVFormat.EXCEL)) {
//                printer.printRecord(dg, formatedData, "Mary", df, LocalDate.of(1985, 3, 29));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }




    /*
            MyInterface ob1 = new Other("other", 18);
            MyInterface ob2 = new Another(13, "ok");

            method1(ob1);
            method1(ob2);
            method2(ob2);


        }

        public static String method1(MyInterface myinterface){

            System.out.println(myinterface.toString());
            return myinterface.toString();
        }

    public static MyInterface method2(MyInterface myinterface){

        System.out.println(myinterface.toString());
        //Integer myinterface1 = myinterface.     ;
        return myinterface;
    }

  */


            //String password = System.getProperty("password");
//            String password = "ProgForce13";
//            if (password == null) {
//                throw new IllegalArgumentException("Run with -Dpassword=<password>");
//            }
//
//            // The salt (probably) can be stored along with the encrypted data
//            byte[] salt = new String("It is just salt13").getBytes();
//
//            // Decreasing this speeds down startup time and can be useful during testing, but it also makes it easier for brute force attackers
//            int iterationCount = 40000;
//            // Other values give me java.security.InvalidKeyException: Illegal key size or default parameters
//            int keyLength = 128;
//            SecretKeySpec key = createSecretKey(password.toCharArray(),
//                    salt, iterationCount, keyLength);
//
//            boolean inputFlag = true;
//            while (inputFlag) {
//                System.out.println("Enter the original password: ");
//                Scanner scanner = new Scanner(System.in);
//                String originalPassword = scanner.nextLine().trim();
//                if (originalPassword != null && !originalPassword.isEmpty()) {
//                    //String originalPassword = "NewPasswordForSkiforma13";
//                    System.out.println("Original password: " + originalPassword);
//                    String encryptedPassword = encrypt(originalPassword, key);
//                    System.out.println("Encrypted password: " + encryptedPassword);
//                    String decryptedPassword = decrypt(encryptedPassword, key);
//                    System.out.println("Decrypted password: " + decryptedPassword);
//                    inputFlag = false;
//                } else {
//                    System.out.println("The entered password is incorrect, enter the password again carefully");
//                }
//            }
//        }
//
//
//    private static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
//    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
//    PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
//    SecretKey keyTmp = keyFactory.generateSecret(keySpec);
//    return new SecretKeySpec(keyTmp.getEncoded(), "AES");
//    }
//
//    private static String encrypt(String property, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException {
//        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
//        AlgorithmParameters parameters = pbeCipher.getParameters();
//        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
//        byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
//        byte[] iv = ivParameterSpec.getIV();
//        return base64Encode(iv) + ":" + base64Encode(cryptoText);
//    }
//
//    private static String base64Encode(byte[] bytes) {
//        return Base64.getEncoder().encodeToString(bytes);
//    }
//
//    private static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
//        String iv = string.split(":")[0];
//        String property = string.split(":")[1];
//        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
//        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
//    }
//
//    private static byte[] base64Decode(String property) throws IOException {
//        return Base64.getDecoder().decode(property);







//            List<Object> fields = new ArrayList<>();
//            List<String> preSkills = Collections.emptyList();
//            List<String> skills = new ArrayList<>();
//            for (String skill : preSkills) {
//                String modifiedSkill = skill.replace(",", "");
//                skills.add(modifiedSkill);
//            }
//            String resultSkill = String.join(",", skills);
//            fields.add(resultSkill);
//            System.out.println(fields);
//
//
//







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
