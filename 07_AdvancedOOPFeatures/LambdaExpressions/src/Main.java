import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();

        Calendar calendar = new GregorianCalendar(2018, 0 , 01);
        Date dateBefore = calendar.getTime();

        Calendar calendar1 = new GregorianCalendar(2017, 0 , 01);
        Date dateAfter = calendar1.getTime();

             Integer maxSalary = staff.stream()
            .filter(e -> e.getWorkStart().before(dateBefore))
            .filter(e -> e.getWorkStart().after(dateAfter))
            .map(Employee::getSalary)
            .max(Integer::compareTo)
            .orElse(null);
            System.out.println("Максимальный размер зарплаты у сотрудника, среди пришедших в 2017 году: " + maxSalary);


            Employee maxEmployeeSalary = staff.stream()
            .filter(e -> e.getWorkStart().before(dateBefore))
            .filter(e -> e.getWorkStart().after(dateAfter))
            .max((p1, p2) -> p1.getSalary().compareTo(p2.getSalary()))
            .get();
                System.out.println("Сотрудник с максимальной зарплатой из пришедших в 2017 году: " + maxEmployeeSalary);

    }


    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }

   }