import core.Line;
import core.Station;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import junit.framework.TestCase;

public class RouteCalculatorTest extends TestCase {

  List<Station> route;
  List<Station> twoTransferRoutes;
  List<Station> oneTransferRoute;
  List<Station> noTransferRoute;

  StationIndex stationIndex;
  RouteCalculator calculator;

  Station A, B, C, D, E, F, G, H, I, K, L, M, N, P;

  @Override
    protected void setUp() throws Exception {
    //Схема тестовой линии
    /**
     *
     *                                 Line 2
     *                                   L
     *                                   |
     *      line 1          A - B - C - D/M - E - F
     *                                   |
     *                                   N
     *                                   |
     *      line 3                  H - I/P - G - K
     *
     */

    stationIndex = new StationIndex();
    calculator = new RouteCalculator(stationIndex);

    Line line1 = new Line(1, "Line1");
    Line line2 = new Line(2, "Line2");
    Line line3 = new Line(3, "Line3");

    A = new Station("A", line1);
    B = new Station("B", line1);
    C = new Station("C", line1);
    D = new Station("D", line1);
    E = new Station("E", line1);
    F = new Station("F", line1);

    L = new Station("L", line2);
    M = new Station("M", line2);
    N = new Station("N", line2);
    P = new Station("P", line2);

    H = new Station("H", line3);
    I = new Station("I", line3);
    G = new Station("G", line3);
    K = new Station("K", line3);

    Stream.of(line1, line2, line3).forEach(stationIndex::addLine);
    Stream.of(A,B,C,D,E,F,L,M,N,P,H,I,G,K).peek(s -> s.getLine()
        .addStation(s)).forEach(stationIndex::addStation);

    stationIndex.addConnection(Stream.of(D,M).collect(Collectors.toList()));
    stationIndex.addConnection(Stream.of(I,P).collect(Collectors.toList()));

//тестовые маршруты
    noTransferRoute = Stream.of(A,B,C,D,E,F).collect(Collectors.toList());
    oneTransferRoute = Stream.of(A,B,C,D,M,L).collect(Collectors.toList());
    twoTransferRoutes = Stream.of(A,B,C,D,M,N,P,I,H).collect(Collectors.toList());

  }


  public void testCalculateDurationNoTransferRoute() {

    double actual = RouteCalculator.calculateDuration(noTransferRoute);
    double expected = 12.5;
    assertEquals(expected, actual);
  }

    public void testCalculateDurationOneTransferRoute() {
      double actual1 = RouteCalculator.calculateDuration(oneTransferRoute);
      double expected1 = 13.5;
      assertEquals(expected1, actual1);
    }

    public void testCalculateDurationTwoTransferRoute() {
    double actual2 = RouteCalculator.calculateDuration(twoTransferRoutes);
    double expected2 = 22;
    assertEquals(expected2, actual2);
  }

    public void testGetShortestRouteNoTransfer() {
      List<Station> actualNoTransfer = calculator.getShortestRoute(A, F);
      List<Station> expectedNoTransfers = noTransferRoute;
      assertEquals(actualNoTransfer, expectedNoTransfers);
    }

    public void testGetShortestRouteOneTransfer() {
      List<Station> actualOneTransfer = calculator.getShortestRoute(A, L);
      List<Station> expectedOneTransfers = oneTransferRoute;
      assertEquals(actualOneTransfer, expectedOneTransfers);
    }

    public void testGetShortestRouteTwoTransfer() {
      List<Station> actualTwoTransfers = calculator.getShortestRoute(A,H);
      List<Station> expectedTwoTransfers = twoTransferRoutes;
      assertEquals(actualTwoTransfers, expectedTwoTransfers);
    }

  @Override
  protected void tearDown() throws Exception {

  }
}
