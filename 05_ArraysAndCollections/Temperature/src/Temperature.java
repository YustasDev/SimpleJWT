

public class Temperature {

  public static void main(String[] args) {
    int z = 0;
    double result = 0;
    double[] temperature = new double[30];

    for (int i = 0; i < temperature.length; i++) {
      temperature[i] = 32.0 + 8.0 * Math.random();
      // System.out.println(temperature[i]);

      result = result + temperature[i];

      if (temperature[i] >= 36.2 && temperature[i] <= 36.9) {
        z++;
      }
    }

    result = result / temperature.length;
    System.out.printf("Средняя температура по больнице: " + "%.1f", result);
    System.out.println();
    System.out.println("Количество здоровых пациентов: " + z);
  }
}
