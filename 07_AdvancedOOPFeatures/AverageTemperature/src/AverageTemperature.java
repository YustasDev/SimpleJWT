
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class AverageTemperature {
  static final double TEMP_MAX = 40.0;
  static final double TEMP_MIN = 32.0;
  static final int PATIENTS = 30;

  public static void main(String[] args) {

    double[] patients = DoubleStream.generate(() -> Math.random() * (TEMP_MAX - TEMP_MIN) + TEMP_MIN)
        .limit(PATIENTS).toArray();

    Double averageTemperature = Arrays.stream(patients).average().getAsDouble();
    System.out.printf("Средняя температура по больнице: " + "%.1f", averageTemperature);

    long healthy = Arrays.stream(patients).filter(p -> p >= 36.2).filter(p -> p <= 36.9).count();
    System.out.println("\nКоличество здоровых пациентов: " + healthy);
  }
}


