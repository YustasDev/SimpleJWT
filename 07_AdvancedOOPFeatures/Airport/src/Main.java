import com.skillbox.airport.*;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {


    Airport londonVirt = Airport.getInstance();

        londonVirt.getAllAircrafts();
        londonVirt.getTerminals();

        System.out.println(londonVirt.getAllAircrafts());

        int size = londonVirt.getAllAircrafts().size();
        System.out.println("Количество самолетов в londonVirt:    " + size);

    }
}

