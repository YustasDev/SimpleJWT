import com.skillbox.airport.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
    Airport londonVirt = Airport.getInstance();

       londonVirt.getAllAircrafts().forEach(System.out::println);


        int size = londonVirt.getAllAircrafts().size();
        System.out.println("Количество самолетов в londonVirt:    " + size);

      List<Flight> departureFlight = getFlightStream(londonVirt);

      departureFlight.forEach(System.out::println);
    }



  public static List<Flight> getFlightStream(Airport londonVirt) {
    final List <Flight> flights = londonVirt.getTerminals().stream().flatMap(terminal -> terminal.getFlights().stream())
        .collect(Collectors.toList());
      return flights;
  }

}

