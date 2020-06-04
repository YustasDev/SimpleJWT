

import airport.Airport;
import airport.Flight;
import airport.Flight.Type;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
  public static void main(String[] args)
  {
    Airport londonVirt = Airport.getInstance();

    List<Flight> departureFlight = getFlightStream(londonVirt);

    departureFlight.forEach(System.out::println);
  }

  public static List<Flight> getFlightStream(Airport londonVirt) {

    Date currentTime = new Date();
    Calendar unnecessary = Calendar.getInstance();
    unnecessary.add(Calendar.HOUR_OF_DAY, 2);
    Date currentTimePlus2 = unnecessary.getTime();

    System.out.println(currentTime);
    System.out.println(currentTimePlus2);

    final List <Flight> flights = londonVirt.getTerminals().stream().flatMap(terminal -> terminal.getFlights().stream())
        .filter(a -> a.getType().equals(Type.DEPARTURE)).filter(a -> a.getDate().after(currentTime))
        .filter(a -> a.getDate().before(currentTimePlus2))
        .collect(Collectors.toList());
    return flights;
  }
}

