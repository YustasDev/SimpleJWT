
import java.util.Arrays;

public class AverageTemperature {

  public static void main(String[] args) {
    int z = 0;
    double result = 0;
    double[] temperature = new double[30];

    for (int i = 0; i < temperature.length; i++) {
      temperature[i] = 32.0 + 8.0 * Math.random();

      result = result + temperature[i];

      if (temperature[i] >= 36.2 && temperature[i] <= 36.9) {
        z++;
      }
    }

    result = result / temperature.length;
    System.out.printf("Средняя температура по больнице (циклом for): " + "%.1f", result);
    System.out.println();
    System.out.println("Количество здоровых пациентов: " + z);

    Double averageTemperature = Arrays.stream(temperature).average().getAsDouble();
    System.out.printf("Средняя температура по больнице (stream): " + "%.1f", averageTemperature);

    long healthy = Arrays.stream(temperature).filter(p -> p >= 36.2).filter(p -> p <= 36.9).count();
    System.out.println("\nКоличество здоровых пациентов (stream): " + healthy);
  }
}


