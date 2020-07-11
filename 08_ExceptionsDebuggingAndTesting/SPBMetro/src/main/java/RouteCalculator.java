import core.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RouteCalculator
{
    private StationIndex stationIndex;

    private static double interStationDuration = 2.5;
    private static double interConnectionDuration = 3.5;

    public RouteCalculator(StationIndex stationIndex)
    {
        this.stationIndex = stationIndex;
    }

    public List<Station> getShortestRoute(Station from, Station to)
    {
        List<Station> route = getRouteOnTheLine(from, to);
        if(route != null) {  System.err.println("launch onTheLine");
            return route;
        }

        route = getRouteWithOneConnection(from, to);
        if(route != null) {  System.err.println("launch oneConnection");
            return route;
        }

        System.err.println("launch twoConnection");
        route = getRouteWithTwoConnections(from, to);
        return route;
    }

    public static double calculateDuration(List<Station> route)
    {
        double duration = 0;
        Station previousStation = null;
        for(int i = 0; i < route.size(); i++)
        {
            Station station = route.get(i);
            if(i > 0)
            {
                duration += previousStation.getLine().equals(station.getLine()) ?
                    interStationDuration : interConnectionDuration;
            }
            previousStation = station;
        }
        return duration;
    }

    //=========================================================================

    private List<Station> getRouteOnTheLine(Station from, Station to)
    {
        if(!from.getLine().equals(to.getLine())) {
            return null;
        }
        ArrayList<Station> route = new ArrayList<>();
        List<Station> stations = from.getLine().getStations();
        int direction = 0;
        for(Station station : stations)
        {
            if(direction == 0)
            {
                if(station.equals(from)) {
                    direction = 1;
                } else if(station.equals(to)) {
                    direction = -1;
                }
            }
            System.err.println(station);
            System.err.println("direction =  " + direction);


            if(direction != 0) {
                route.add(station);
            }

            if((direction == 1 && station.equals(to)) ||
                (direction == -1 && station.equals(from))) {
                break;
            }
        }
        if(direction == -1) {
            Collections.reverse(route);
        }
        System.err.println("direction_итог =  " + direction);
        return route;
    }

    private List<Station> getRouteWithOneConnection(Station from, Station to)
    {
        if(from.getLine().equals(to.getLine())) {
            return null;
        }

        ArrayList<Station> route = new ArrayList<>();

        List<Station> fromLineStations = from.getLine().getStations();
        List<Station> toLineStations = to.getLine().getStations();
        for(Station srcStation : fromLineStations)
        {
            System.err.println("srcStation =  " + srcStation);
            for(Station dstStation : toLineStations)
            {
              System.err.println("dstStation =  " + dstStation);

                if(isConnected(srcStation, dstStation))
                {   System.err.println("isConnected = " + isConnected(srcStation, dstStation));
                    ArrayList<Station> way = new ArrayList<>();
                    way.addAll(getRouteOnTheLine(from, srcStation));
                    System.err.println("way1 =  " + way);

                    way.addAll(getRouteOnTheLine(dstStation, to));
                    System.err.println("upshot =  " + way);

                    if(route.isEmpty() || route.size() > way.size())
                    {
                        route.clear();

                        //route.addAll(way);
                    }
                }
            }
        }
        return route;
    }

    private boolean isConnected(Station station1, Station station2)
    {
        Set<Station> connected = stationIndex.getConnectedStations(station1);
        return connected.contains(station2);
    }

    private List<Station> getRouteViaConnectedLine(Station from, Station to)
    {
        Set<Station> fromConnected = stationIndex.getConnectedStations(from);
        Set<Station> toConnected = stationIndex.getConnectedStations(to);
        for(Station srcStation : fromConnected)
        {
            for(Station dstStation : toConnected)
            {
                if(srcStation.getLine().equals(dstStation.getLine())) {
                    return getRouteOnTheLine(srcStation, dstStation);
                }
            }
        }
        return null;
    }

    private List<Station> getRouteWithTwoConnections(Station from, Station to)
    {
        if (from.getLine().equals(to.getLine())) {
            return null;
        }

        ArrayList<Station> route = new ArrayList<>();

        List<Station> fromLineStations = from.getLine().getStations();
        List<Station> toLineStations = to.getLine().getStations();
        for(Station srcStation : fromLineStations)
        {
            for (Station dstStation : toLineStations)
            {
                List<Station> connectedLineRoute =
                    getRouteViaConnectedLine(srcStation, dstStation);
                if(connectedLineRoute == null) {
                    continue;
                }
                System.err.println("connectedLineRoute =  " + connectedLineRoute);


                ArrayList<Station> way = new ArrayList<>();
                way.addAll(getRouteOnTheLine(from, srcStation));
                way.addAll(connectedLineRoute);
                way.addAll(getRouteOnTheLine(dstStation, to));
                if(route.isEmpty() || route.size() > way.size())
                {
                    route.clear();
                    route.addAll(way);
                }
            }
        }

        return route;
    }
}