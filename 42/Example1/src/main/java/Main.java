import org.apache.commons.collections4.ListUtils;

import javax.management.modelmbean.InvalidTargetObjectTypeException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.util.Collections.sort;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class Main {

        //column_cord = {"R1": ((87, 10), (157, 539)), "R2": ((161, 10), (232, 539)),
        //  "R3": ((235, 10), (313, 539)), "R4": ((315, 10), (382, 539)),

        //        private Path namesPath = Paths.get("/home/progforce/11/passFor Email.txt");
        public Logger logger = Logger.getLogger(this.getClass().getName());
        static FileHandler fhLog = null;
        String a, b, c =


        public static void main(String[] args) {

                Main mainA = new Main();
                mainA.foo();

        }


        void foo(){

                String m = "Hello";
                out.println(m);
                bar(m);
                out.println(m);
                m += "World";
                out.println(m);

        }

        void bar(String m){
                m += "World";
        }

//                List<String> ls = new ArrayList<>();
//                String a = "";
//               // String b = null;
//                String c = " ";
//                String d = ".";
//                String e = " good  ";
//                ls.addAll(Stream.of(a,c,d,e).collect(Collectors.toList()));
//                for(String s : ls){
//                        String st = s.trim();
//                        out.println(s.trim().isEmpty());
//                }




//                List<String> list = Stream.of("foo", "bar", "care").collect(Collectors.toList());
//                int count = 0;
//                count = list.indexOf("care");
//                System.out.println(list + "  " + count);


/*
                String str = "Abc abc";
                String reg = "abc";

//                String s2 = "<strong>Абв</strong>" + "  <strong>абв</strong>";
//                String regx = "абв";

                String s2 = "Абв абв";
                String regx = "абв";

               String result = str.replaceAll("(?i)"+ reg, "DEF");
               System.out.println("After replacement:\n" + "   " + result);

                String rest = s2.replaceAll("(?iu)"+ regx, "Ооопс!");
                System.out.println("After replacement:\n" + "   " + rest);


                // Logger logger = Logger.getLogger("MyLog");

 */
/*
                try {
                        fhLog = new FileHandler("/home/progforce/2/myLogFile1.log");
                } catch (IOException e) {
                        e.printStackTrace();
                }
                Main maind = new Main();
                Logger log = maind.getLogger();
                log.addHandler(fhLog);
                SimpleFormatter formatter = new SimpleFormatter();
                fhLog.setFormatter(formatter);

                // the following statement is used to log any messages
                log.info("It's static log");

                Another.doSome(log);

*/


//=============================================================================================>
//                        InetAddress currentIp;
//                        InetAddress nameIp;
//                        try {
//                                currentIp = InetAddress.getLocalHost();
//                                // output IP address of local computer
//                                System.out.println("current IP -> " + currentIp.getHostAddress());
//                                nameIp = InetAddress.getByName("mail.ru");
//                                System.out.println("NAME IP address -> " + nameIp.getHostAddress());
//                        } catch (UnknownHostException e) {
//                                e.printStackTrace();
//                        }

//================================================================================================>
  /*
                long result;
                long startTime = System.currentTimeMillis();

                result = LongStream.range(0, 1_000)
                        .boxed()
                        .parallel()
                        .map(x -> x / 7)
                        .reduce((x,y)-> x + (int) (3 * Math.sin(y)))
                        .get();
                long endTime = System.currentTimeMillis();
                System.out.println("Total execution time for parallel: " + (endTime-startTime) + "ms");
                System.out.println(result);

                long startTime1 = System.currentTimeMillis();

                result = LongStream.range(0, 1_000)
                        .boxed()
                        .map(x -> x / 7)
                        .peek(v -> System.out.println(Thread.currentThread().getName()))
                        .reduce((x,y)-> x + (int) (3 * Math.sin(y)))
                        .get();
                long endTime1 = System.currentTimeMillis();
                System.out.println("Total execution time for single: " + (endTime1-startTime1) + "ms");
                System.out.println(result);

*/
//=================================================================================================>
  /*              Properties props = new Properties();
                try {
                        props.setProperty("db.driver", "com.mysql.cj.jdbc.Driver");
                        props.setProperty("user", "root");
                        props.setProperty("password", "pass");
                        props.setProperty("poolsize", "5");
                        props.setProperty("db.url", "jdbc:mysql://127.0.0.1:3306/testphones");
                        props.store(new FileWriter("/home/progforce/2/pro_base.properties"), "No Comment’s");
                } catch (IOException e) {
                        e.printStackTrace();
                }

                try {
                        props.load(new FileReader("/home/progforce/2/pro_base.properties"));
                } catch (IOException e) {
                        e.printStackTrace();
                }
                String dbUrl = props.getProperty("db.url");
                String password = props.getProperty("password");
                // following two names are missing in the file
                String maxIdle = props.getProperty("maxIdle"); // maxIdle = null
                // value "20" will be assigned to the key if it is not found in the file
                String maxActive = props.getProperty("maxActive", "20");
                System.out.println("dbUrl: " + dbUrl);
                System.out.println("password: " + password);
                System.out.println("maxIdle: " + maxIdle );
                System.out.println("maxActive: " + maxActive);
*/
//=======================================================================>
//                String encrypted = "MyPassword";
//                MessageDigest messageDigest = null;
//                byte[] bytesEncoded = null;
//                try {
//                        messageDigest = MessageDigest.getInstance("SHA-1"); // only once !
//                        messageDigest.update(encrypted.getBytes("utf8"));
//                        bytesEncoded = messageDigest.digest();
//                } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                }
//                BigInteger bigInt = new BigInteger(1, bytesEncoded); //1(sign+) or -1(sign-)
//                String resHex = bigInt.toString(16);
//                System.out.println(resHex);
   //=================================================================================<


//                String xssString = "<script>alert('hello')</script>";
//                xssString = xssString.replaceAll("</?script>","");
//                out.println(xssString);
//
//                String xssString1 = "<script>alert('hello')</script>";
//                xssString1 = xssString1.replaceAll("</?/script>","");
//                out.println(xssString1);
//
//                String abc = "tef_abc_bcd_def_abc";
//                abc = abc.replaceAll("/?_abc", "");
//                out.println(abc);


//                Function<String, Integer> ir = build("3");
//                Integer itr = ir.apply("5");
//                System.out.println(itr);


//
//        }

        public static Function<String, Integer> build(String strNum) {
                int[] count = {1};
                ++count[0];
                return t -> Integer.valueOf(strNum + t)+ ++count[0];
        }


        public Logger getLogger() {
                return logger;
        }



        // Gson gson = new Gson();
//                Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//                List<List<Object>> llo = new ArrayList<>();
//                List<Object> lo = new ArrayList<>();
//                List<Object> lo1 = new ArrayList<>();
//                List<Object> lo2 = new ArrayList<>();
//                Another a1 = new Another(1, "One");
//                Another a2 = new Another(2, "Two");
//                Another a3 = new Another(3, "Three");
//                lo.add(a1);
//                lo1.add(a2);
//                lo2.add(a3);
//                llo.add(lo);
//                llo.add(lo1);
//                llo.add(lo2);
//
//                try (FileWriter writer = new FileWriter("/home/progforce/2/tempList.json")) {
//                        gson.toJson(llo, writer);
//                } catch (IOException e) {
//                        e.printStackTrace();
//                }
//
//
//                try (Reader reader = new FileReader("/home/progforce/2/tempList.json")) {
//                        // Convert JSON File to Java Object
//                        List<List<Object>> lloFromJson = gson.fromJson(reader, List.class);
//                        System.out.println(lloFromJson);
//                } catch (IOException e) {
//                        e.printStackTrace();
//                }

//        }


//
//                String str42 = "42";
//                Integer val = Integer.decode(str42);
//                System.out.println(val);
//
//                BigDecimal big1 = new BigDecimal("5.0");
//                BigDecimal big2 = new BigDecimal("2.4");
//                BigDecimal bigRes = big1.subtract(big2, MathContext.DECIMAL32);
//                System.out.println(bigRes);

//                Main mn = new Main();
//                List<String> ls = mn.get().collect(Collectors.toList());
//                System.out.println(ls);






//        public Stream<String> get() {
//                logger.info("Time:  " + Instant.now());
//                try {
//                        return Files.lines(namesPath);
//                } catch (IOException e) {
//                        e.printStackTrace();
//                        return null;
//                }
//        }








                /*
                Gson gson = new Gson();
                Another ar = new Another(7, "Another7");

                Map <Integer, String> mymap = new HashMap<>();
                mymap.put(1, "Один");

                Collection collection = new ArrayList();
                collection.add("string");
                collection.add(10);
                collection.add(mymap);
                collection.add(ar);
                String jsn = gson.toJson(collection);
                out.println(jsn);

                for (Object ob : collection){
                    out.println(ob.toString() + "  " + ob.getClass());
                    if(ob instanceof HashMap){
                            out.println("It's OK");
                        }
                    if(ob.getClass() == Another.class){
                          out.println("It's OK");
                        }

                }


                System.out.println("ZonedDateTime.now(): " + ZonedDateTime.now());
                ZonedDateTime zd = ZonedDateTime.now();
                LocalDate ld = LocalDate.from(ZonedDateTime.now());
                System.out.println("LocalDate : " + ld);
                Date dt = Date.from(Instant.from(zd));
                System.out.println("Date : " + dt);


                Date dateD = new SimpleDateFormat("dd-MMM-yy").parse("15-JAN-85");
                System.out.println("dataD = " + dateD);

                Date dateD2 = new SimpleDateFormat("dd-MMM-yy").parse("15-JAN-05");
                System.out.println("dataD2 = " + dateD2);



                String strTime = "15-Jul-85";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
                LocalDate localDate = LocalDate.parse(strTime, formatter);
                System.out.println("localDate = " + localDate);

                LocalDateTime localDateTime = LocalDateTime.now();
                System.out.println("localDateTime = " + localDateTime);

                Date dtFromLD = Date.from(localDate.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant());

                System.out.println(dtFromLD);
 */
//                Format formatterDT = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
//                ZonedDateTime zd = ZonedDateTime.now();
//                LocalDateTime ldt = LocalDateTime.from(ZonedDateTime.now());
//                System.out.println("ldt = " + ldt);
//
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
//                String ldtStr = formatter.format(ldt);
//                System.out.println("ldtStr = " + ldtStr);
//
//
//
//                String s19 = "15-JAN-28";
//                String s19_1 = "15-JAN-85";
//                String s20 = "15-JAN-09";
//                String s20_1 = "15-JAN-22";
//                System.out.println("s19 = " + from(s19));
//                System.out.println("s19_1 = " + from(s19_1));
//                System.out.println("s20 =" + from(s20));
//                System.out.println("s20_1 = " + from(s20_1));

             //   String callback = "";
             //   CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "One_one");

//               // future.thenAccept(result -> System.out.println(result));
//
//                Date date = convertToDateViaSqlDate(from("15-JAN-82"));
//                out.println(date);
//
//                int CPU = Runtime.getRuntime().availableProcessors();
//                long memory = Runtime.getRuntime().freeMemory();
//                out.println("CPU = " + CPU + "\n" + "freeMemory = " + memory);
//
////                try {
////                       callback = future.get();
////                } catch (InterruptedException e) {
////                        e.printStackTrace();
////                } catch (ExecutionException e) {
////                        e.printStackTrace();
////                }
//
//                out.println(callback + "   " + Thread.currentThread().getName());
//
//            //    future.thenApply(result -> {
//             //           System.out.println(result + " all" + "  " + Thread.currentThread().getName());
//             //           return result;
//                });
//
//           //     future.thenApply(result -> {
//           //             System.out.println(result + ", world!" + "  " + Thread.currentThread().getName());
//          //              return result;
//        //        });
//
//
////                CompletableFuture<Integer> futInt = CompletableFuture.supplyAsync(() -> 10)
////                        .thenCompose(result ->
////                                CompletableFuture.supplyAsync(() -> result * 2)
////                        ).thenCompose(result ->
////                                CompletableFuture.supplyAsync(() -> result * 5)
////                        );
////                try {
////                        System.out.println(futInt.get() + "  " + Thread.currentThread().getName());
////                } catch (InterruptedException e) {
////                        e.printStackTrace();
////                } catch (ExecutionException e) {
////                        e.printStackTrace();
////                }

//                String newResult = "";
//                String curThread = "";
//                CompletableFuture<String> completableFuture = new CompletableFuture<String>();
//                CompletableFuture<String> trtr = completableFuture.supplyAsync(() -> {
//                        out.println("Oooops!");
//                        return Thread.currentThread().getName();
//                });
//                try {
//
//                        trtr.thenAccept(res -> System.out.println(res));
//                        completableFuture.complete("Done!");
//                        newResult = trtr.get();
//                        curThread = Thread.currentThread().getName();
//                } catch (InterruptedException e) {
//                        e.printStackTrace();
//                } catch (ExecutionException e) {
//                        e.printStackTrace();
//                }
//
//                out.println(newResult + "   " + curThread);

//                try {
//                        System.out.println("Результат сложения = " + addThis(combine(3,5)).get());
//                } catch (InterruptedException e) {
//                        e.printStackTrace();
//                } catch (ExecutionException e) {
//                        e.printStackTrace();
//                }


//                Nullable nullable = null;
//                System.out.println(nullable.hello());
//
//                String[] names = {"Java", "Kotlin", "Java"};
//                String name = "Java";
//                Predicate predicate = name::equals;
//                Stream.of(names).filter(predicate).count();
//                name = "Kotlin";
//                Stream.of(names).filter(predicate).count();




        public static LocalDate from(String s) {
                return LocalDate.parse(s, new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern("dd-MMM-")
                        .appendValueReduced(ChronoField.YEAR, 2, 2, Year.now().getValue() - 80)
                        .toFormatter()
                );
        }

        public static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
                return java.sql.Date.valueOf(dateToConvert);
        }


        public static String combine(int x, int y) {
                String result = "";
                CompletableFuture<Integer> completableFuture =
                        CompletableFuture.supplyAsync(() -> x)
                                .thenCombine(CompletableFuture.supplyAsync(() -> y),
                                        (n1, n2) -> n1 + n2).handle((val, exc) -> val != null ? val : null);
                if(completableFuture != null){
                        try {
                                result = String.valueOf(completableFuture.get());
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        } catch (ExecutionException e) {
                                e.printStackTrace();
                        }
                        return result;
                }
                else {
                        result = "Chief, it's all gone!";
                        return result;
                }
        }

        public static CompletableFuture<Integer> addThis (String num){
                return CompletableFuture.supplyAsync(() -> Integer.parseInt(num))
                                .handle((val, exc) -> val != null ? val : null);
        }



//                String pathToArchive = "/home/progforce/Downloads/Archive";
//                Path path = Paths.get(pathToArchive);
//
//                String stringToListCSVFiles = "/home/progforce/Downloads/ListCSVFiles";
//                Path pathToListCSVFiles = Paths.get(stringToListCSVFiles);
//
//                List<Path> pathsCSVFiles = findByFileExtension(pathToListCSVFiles, ".csv");
//                List<File> listfilesCSV = new ArrayList<>();
//                for (Path pathThis : pathsCSVFiles) {
//                        File f = pathThis.toFile();
//                        if (f.isFile())
//                                listfilesCSV.add(f);
//                }
//
//
//                for (File f : listfilesCSV) {
//                        Path pathFromFile = f.toPath();
//                        String csvFile = pathFromFile.toString();
//                        System.out.println("pathString =  " + csvFile);  // TODO only for development
//                        System.out.println(f.getName() + " time = " + f.lastModified());   // TODO only for development}
//
//                String pathResult = pathToArchive + File.separator + f.getName();
//                System.out.println("pathResult to Arhive ==> " + pathResult);
//        }

//                Optional<String> op2 = Optional.ofNullable(null);
//
//                if(op2.equals(Optional.empty())) {
//                        System.out.println("Optional 2: " + op2);
//                }
//                else {
//                        System.out.println("something's wrong");
//                }

/*
                Integer value = 3;
                Optional<Integer> z = Optional.ofNullable(value);
                out.println(z.get());

                value = null;
                z = Optional.ofNullable(value);
                out.println(z.orElse(-1));
                out.println(z.orElseGet(getElseRet()));

                out.println(fibon(3));

*/
/*
        double erD3 = 3;
        long erlD3 = doubleToLongBits(erD3);
        String ss3 = toBinaryString(erlD3);

        int er3 = 3;
        String erl3 = Integer.toBinaryString(er3);

        Long restoreERL = Long.parseLong(ss3, 2);
        Double erRestored = Double.longBitsToDouble(restoreERL);


                Double y = 9.0;
                Double u = 3.0;
                double zafter = Math.sqrt(y);
                Double yd = y/u;
                boolean z = yd % 1 == 0;
            out.println(z);


                boolean z1;
                BigDecimal y1 = new BigDecimal("9");
                BigDecimal u1 = BigDecimal.valueOf(3L);
                BigDecimal w = y1.divide(u1);
                BigDecimal[] bd = w.divideAndRemainder(BigDecimal.ONE);
                BigDecimal remains = bd[1];
                BigDecimal check0 = BigDecimal.valueOf(0L);
                int ch = remains.compareTo(check0);
                if(remains.compareTo(check0) == 0) {
                       z1 = true;
                }
                else {
                        z1 = false;
                }
                out.println(z1);
*/

//            Map<Long, BigDecimal> cache = new HashMap<>();
//            cache.put(7l, new BigDecimal("7"));
//            BigDecimal big = cache.getOrDefault(3L, new BigDecimal("0.013"));
//            out.println(big);
//            out.println(fib(3L, cache));
//            out.println(fib(3L, cache));
//            out.println(fib(4L, cache));


/*
            //    sumDoublesDivisibleBy3(1, 100);
             List <Integer> intList = new ArrayList<>();
            // IntStream.rangeClosed(0, 5).forEach(x -> intList.add(x));
             intList = IntStream.rangeClosed(0, 5)
                        .boxed()
                        .collect(Collectors.toList());
             System.out.println(intList);

                IntStream stream = IntStream.range(1, 100);

                List<Integer> primes = stream.filter(Main::isPrime)
                        .boxed()
                        .collect(Collectors.toList());
                System.out.println(primes);
                */
  //      }


        private static List<Path> findByFileExtension(Path path, String fileExtension) {

                if (!Files.isDirectory(path)) {
                        throw new IllegalArgumentException("Path must be a directory!");
                }

                List<Path> result = null;
                try (Stream<Path> walk = Files.walk(path)) {
                        result = walk
                                .filter(Files::isRegularFile)   // is a file
                                .filter(p -> p.getFileName().toString().endsWith(fileExtension))
                                .collect(Collectors.toList());
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return result;

        }
        


        static Supplier<? extends Integer> getElseRet(){
              Supplier<Integer> intSupplier = () -> 13;
              return  intSupplier;

        }


        static long fibon(long i) {
                if (i == 0) return 0;
                if (i == 1) return 1;
                long x = fibon(i - 1);
                long y = fibon(i - 2);
                long zx;
                return zx = x + y;
        }


        public static BigInteger fib(long i, Map<Long, BigInteger> cache) {
                if (i == 0) return BigInteger.ZERO;
                if (i == 1) return BigInteger.ONE;
                return cache.computeIfAbsent(i, n -> fib(n - 2, cache).add(fib(n - 1, cache)));
        }


        public static boolean isPrimeMod(int num) {
                int limit = (int) (Math.sqrt(num) + 1);
                boolean z;
                z = num == 2 || num > 1 && IntStream.range(2, limit)
		                      .noneMatch(divisor -> num % divisor == 0);
                return z;
        }



        public static boolean isPrime(int i)
        {
                IntPredicate isDivisible = index -> i % index == 0;

                boolean x = i > 1 && IntStream.range(2, i).noneMatch(isDivisible);
                return x;
        }





        public static void sumDoublesDivisibleBy3(int start, int end) {
        IntStream.rangeClosed(start, end)
                        .peek(n -> {System.out.println("исходное число = " + n);
                        })
                        .filter(n -> n % 3 == 0)
                        .forEach(n -> {System.out.println("на 3 делится => " + n);
                                });
        }




//
//                List<Integer> listInt = getList(1, 2);
//                List<String> listString = getList("2", "3");
//
//                System.out.println(listInt);    // [1, 2]
//                System.out.println(listString); // [2, 3]
//        }
//
//        public static <T> List<T> getList(T... arg) {
//                return Arrays.asList(arg);
//        }



/*
                String[] strings = "this is an array of strings".split(" ");
                long count = Arrays.stream(strings)
                        .map(String::length).count();
                System.out.println("Всего существует " + count + " строк");

                OptionalDouble ave = Arrays.stream(strings)
                        .mapToInt(String::length).average();
                System.out.println("Средняя длина равна " + ave);

                Double d = ave.getAsDouble();
                OptionalDouble od = OptionalDouble.of(d);



                List<BigDecimal> nums = iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE) )
                                .limit(10)
                                .collect(Collectors.toList());
                System.out.println(nums);

                BigDecimal d = BigDecimal.ONE;
                BigDecimal d1 = d.add(BigDecimal.TEN);
                System.out.println("d = " + d);
                System.out.println("d1 = " + d1);

                //long count =
                Stream.generate(Math::random)
                        .limit(10)
                        .forEach(System.out::println);

                List<Double> countList = Stream.generate(Math::random)
                        .limit(10).collect(Collectors.toList());

                System.out.println("Math_random List ==> " + countList);

*/



//                List<String> lst = Arrays.asList("dsg, sasa", "klklj,sasiw", "rere erer", "ewe opo");
//                //  String modlst = lst.stream().filter(s -> s.contains(",")).map(m->m.replaceAll(",", "")).collect(Collectors.joining(", "));
//                String modlst = lst.stream().map(m -> m.replaceAll(",\\w", " ")).map(m -> m.replaceAll(",\\s+", " ")).collect(Collectors.joining(", "));
//
//                out.println(modlst);
//
//                List<String> names = Arrays.asList("dsg, sasa", "klklj,sasiw", "rere erer", "ewe opo", "eyuy pojh");
//                String mn = names.stream().filter(s -> s.startsWith("e")).collect(Collectors.joining(", "));
//                out.println(mn);
//
//                String[] smas = {"Коля", "Петя", "Вася", "Маша", "марина"};
//                String needName1 = "М";
//                String needName2 = "м";
//                Predicate<String> condition = (x) -> x.startsWith(needName1) || x.startsWith(needName2);
//                String fd = getNamesSatisfyingCondition(condition, smas);
//                out.println(fd);
//
//        }
//
//        public static String getNamesSatisfyingCondition(Predicate<String> condition, String... names) {
//                        return Arrays.stream(names).filter((Predicate<? super String>) condition).collect(Collectors.joining(", "));
//                }


//
//            ExecutorService executorService = Executors.newFixedThreadPool(1);
//            Future<Object> future = executorService.submit(() ->
//            {
//                System.out.println("so far everything is fine");
//                double result = 10 / 0;
//                System.out.println("everything is still fine");
//                throw new IllegalStateException("I dont want to work");
//            });
//
//            try
//            {
//                future.get();
//            }
//            catch (InterruptedException e)
//            {
//                System.err.println("Execution was interrupted.");
//                Thread.currentThread().interrupt();
//            }
//            catch (ExecutionException e)
//            {
//                System.err.println("An error occured during execution. Handling it.");
//                // handle exception
//            }
//            executorService.shutdown();
//
//            try {
//                executorService.awaitTermination(1,
//                        TimeUnit.MINUTES); // см. https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//

//            ExecutorService executorService = Executors.newFixedThreadPool(1);
//            executorService.submit(()->{
//                try {
//                    System.out.println("so far everything is fine");
//                    double result = 10 / 0;
//                    System.out.println("everything is still fine");
//                }
//                catch (Throwable e){
//                    System.out.println("something went wrong");
//                }
//            });
//            executorService.shutdown();
//            try {
//                executorService.awaitTermination(1, TimeUnit.MINUTES);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//


/*


                new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                                out.println("Thread: " + Thread.currentThread().getName());
                                        }
                                }).start();

                new Thread(() -> out.println("Thread: " + Thread.currentThread().toString())).start();

                Runnable r = () -> out.println("Thread: " + Thread.currentThread().getName());
                new Thread(r).start();

                out.println("Current Thread is: " + Thread.currentThread().getName());

                List <Integer> intList = new ArrayList<>();
                String s = "someWord";
                int sz = s.length();
                s.chars().forEach(x -> intList.add(x));
                System.out.println(intList);

                List <Integer> strLength = new ArrayList<>();
                String[] strMas = {"hiklm", "abc", "defg"};
                List<String> strMasList = Arrays.asList(strMas);

                List<String> stList = Arrays.asList("zx", "zxy", "zxyu");

                Stream.of(strMas).forEach(x-> strLength.add(x.length()));

                Stream.of(strMas).map(String::length).forEach(System.out::println);

                Stream.of(strMas).forEach(x-> System.out.println(x.length()));


            List<String> sorted = strMasList.stream().sorted(String::compareTo).collect(Collectors.toList());

            System.out.println(strMasList);
            System.out.println(sorted);


                String source = "Ослепительный черно-фиолетового кольца камерыОкружая музыка будет!«вечной» Превосходное антибликовое стекло с матированным покрытиемМатовое покрытие сводит к минимуму количество отпечатков пальцев на телефоне ";
                String[] disassembledText = source.trim().split("(\\s+)|(?=[А-Я]{1,})");


                for (String str : disassembledText) {

                        String s = str.replaceAll("[^А-Яа-я \\pP-]", "").replaceAll("\\sр\\s", "")
                                .replaceAll("\\sГБ\\s", "").replaceAll("[\\p{P}&&[^\\-]]", " ");




//                        String s = str.toLowerCase().replaceAll("[\\p{Punct}\\s&&[^\\h]&&[^-]]", "");
//                        if (!(s == null || s.isEmpty() || s.trim().isEmpty())) {

                                System.out.println(s);

                      //  }
                }

*/




/*
                String url = "jdbc:mysql://localhost:3306/search_engine?useSSL=false&serverTimezone=Europe/Moscow&useUnicode=true&characterEncoding=utf8";
                String user = "root";
                String password = "password";

                try {
                        Connection conn = DriverManager.getConnection(url, user, password);
                        Statement statement = conn.createStatement();

                        LocalDate today = LocalDate.now();
                        LocalDate today1 = LocalDate.of(2022, Month.AUGUST, 5);
                        LocalDateTime ldt = LocalDateTime.now();

                        String data = "08/05/2022";
                        Date dataNow = null;
                        try {
                              dataNow = new SimpleDateFormat("MM/dd/yyyy").parse(data);
                        }
                        catch (ParseException e) {
                              e.printStackTrace();
                              }


                       // String sql = "INSERT INTO timeshift(timevar)" + "VALUES (?);";
                        String sql1 = "INSERT INTO timeshift(absoluttime)" + "VALUES (?);";

                        PreparedStatement preparedStatement = conn.prepareStatement (sql1);
                        preparedStatement.setObject ( 1, dataNow);
                        preparedStatement.executeUpdate();
//
//                        preparedStatement.setObject ( 1, today1);
//                        preparedStatement.executeUpdate ();
//
//                        preparedStatement.setObject ( 1, ldt);
//                        preparedStatement.executeUpdate ();

                        statement.close();
                        conn.close();

                } catch (Exception ex) {
                        ex.printStackTrace();
                }




*/




/*
                String s = "Прошлое,теперь;в!прошлом?думаю«нет»";

                String[] disassembledText = s.trim().split("\\pP");
                String[] disassembledText1 = s.trim().split("\\pP");
                String ds = s.replaceAll("\\pP", " ");

                for (String d : disassembledText) {
                        System.out.println(d);
                }
                System.out.println("==============================================");
                for (String d1 : disassembledText1) {
                        System.out.println(d1);
                }
                System.out.println(ds);

*/

//                Double d = 3.74891;
//                BigDecimal bd = new BigDecimal(d).setScale(1, RoundingMode.HALF_UP);
//                Double d1 = bd.doubleValue();
//
//                System.out.println("bd = " + bd + "\n" + "d1 = " + d1);


/*
                List<Integer> list = new ArrayList<Integer>();
                list.add(1);
                list.add(2);
                list.add(3);
                for (int i = 4; i < 20; i++) {
                        list.add(i);
                }

                assertThat(list.get(18), is(new Integer(19)));
                assertThat(list.get(18), is(19));

                int z = list.set(4, 0);
                System.out.println(list.size());
                list.set(5, null);
                System.out.println(list.size());
                System.out.println(list);
                list.add(null);
                System.out.println(list.size());
                System.out.println(list);
                assertThat(list.contains(null), equalTo(true));
                assertThat(list.size()>20, equalTo(false));

                try {
                     //   list.set(-1, 0);
                        fail("Do you catch me?");
                } catch (IndexOutOfBoundsException e) {
                        System.out.println(list);
                }
                catch (IllegalArgumentException e) {
                        System.out.println("yeah1");
                }
                catch (final RuntimeException e) {
                        System.out.println("yeah2");
                }
                catch (Exception ex) {
                        System.out.println("yeah3");
                }
                catch (Throwable e) {
                        System.out.println("It's ok");
                }

                System.out.println(list);

*/
//            Map<String, String> map1 = new HashMap<>();
//            Map<String, String> map2 = Collections.EMPTY_MAP;
//            Map<String, String> map3 = new HashMap<>();
//            String s = map1.get("Conv");
//            if(s != null){
//                System.out.println("Oooops!");
//            }
//
//            //map1.put("Conv", "");
//            String z = map1.get("Conv");
//            if(z != null && z.length()>0){
//                System.out.println("OooopsZ!");
//            }
//            else { System.out.println(z);}
//
//
//
//            map3.put("Conv", "");
//            String y = map3.get("Conv");
//            if(y.length()>0){
//                System.out.println("OooopsY!");
//            }
//
//
//            System.out.println(s);
//            System.out.println(z);
//            System.out.println(y);

//            StringBuffer str = new StringBuffer();
//            str.append("Vidvan"); // appends a string in the previously defined string.
//            System.out.println(str);
//            str.append("0"); // appends a number in the previously defined string.
//            System.out.println(str);
//
//            String s = "onlyStr";
//            String result = str + s + "\n" + s + str;
//            System.out.println(result);


/*
                String inputString = "Some String";
                byte[] bytes = inputString.getBytes("UTF-8");
                String encoded = Base64.getEncoder().encodeToString(bytes);

                byte[] decoded = Base64.getDecoder().decode(encoded);
                String decodedString = new String(decoded);

                byte[] result = DatatypeConverter.parseBase64Binary(encoded);
                String decodedString1 = new String(result);


                System.out.println(encoded);
                System.out.println(decoded);
                System.out.println(decodedString);
                System.out.println(decodedString1);

                String s = "TmlzMTIzNDU2IQ==";
                byte[] decoded2 = Base64.getDecoder().decode(s);
                String decodedString2 = new String(decoded2);

                byte[] result1 = DatatypeConverter.parseBase64Binary(s);
                String decodedString22 = new String(result1);

                System.out.println(decodedString2);
                System.out.println(decodedString22);

            System.out.println("=========================");
            String login = "UZRuuPxyrAKLOwmJXnDicx5bgcKLWkcOk2lUqwD1FYg=";
            byte[] decodedZ = Base64.getDecoder().decode(login);
            String decodedStringZ = new String(decodedZ);
            System.out.println(decodedStringZ);

            String pass = "bzBnTkEzUFhoQXA0eC9OTVh1MU4xUT09";
            byte[] decodedZZ = Base64.getDecoder().decode(pass);
            String decodedStringZZ = new String(decodedZZ);
            System.out.println(decodedStringZZ);

*/


//            Example exc = new Example();
//            System.out.println(exc);


//        }


// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



//                String timeStamp = new SimpleDateFormat("MM/dd/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
//                System.out.println(timeStamp);

//                List<Object> objectList1 = new ArrayList<>();
//                List<Object> objectList2 = new ArrayList<>();
//                List<Object> objectList3 = new ArrayList<>();
//                String itsA = "a";
//                String itsB = "b";
//                String itsC = "c";
//                String itsD = "d";
//                objectList1.add(itsA);
//                objectList1.add(itsC);
//                objectList2.add(itsB);
//                objectList2.add(itsD);
//                objectList3.add(itsB);
//                objectList3.add(itsA);
//
//
//                Map<String, List<Object>> someMap = new TreeMap<>();
//
//                someMap.put("2020", objectList3);
//                someMap.put("1998", objectList2);
//                someMap.put("2018", objectList1);
//
//                someMap.forEach((key, value) -> System.out.println("[Key] : " + key + " [Value] : " + value));
//                someMap.forEach((key, value) ->{
//                        String field = key;
//                        System.out.println(key);
//                        List<Object> valueList = value;
//                        String type = (String) valueList.get(0);
//                        String kind = (String) valueList.get(1);
//                        if (type.equalsIgnoreCase("A")){
//                                System.out.println("Its objectList1");
//                                System.out.println("type ==> " + type);
//                        }
//                        else if (kind.equalsIgnoreCase("D") || kind.equalsIgnoreCase("A")){
//                                System.out.println("Its objectList2 or objectList3");
//                                System.out.println("kind ==> " + kind);
//                        }
//                        else {System.out.println("...");}
//
//                        });
//        }
//
//



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
                        out.printf("Not empty\n");
                }
                else {
                    out.printf("Empty\n");}
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
