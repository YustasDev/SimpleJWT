

public class Temperature {

  public static void main(String[] args) {
    final int PATIENTS = 30;        // количество пациентов
    final double MAX_TEMP = 40.0;   // максимальная температура генерации пациента
    final double FACTOR_MAX = 8.0;   // коэффициент для генерации максимальной температуры
    final double MIN_TEMP = 32.0;   // минимальная температура генерации пациента
    final double MIN_HEALTH = 36.2; // минимальная температура здорового пациента
    final double MAX_HEALTH = 36.9; // максимальная температура здорового пациента

    int z = 0; // количество пациентов, температура которых находится в промежутке между 36,2 и 36,9
    double result = 0; // переменная средней температуры всех пациентов
    double sum = 0; // переменная суммарной температуры всех пациентов

    double[] temperature = new double[PATIENTS];

    for (int i = 0; i < temperature.length; i++) {
      temperature[i] = MIN_TEMP + FACTOR_MAX * Math.random();
      // System.out.println(temperature[i]);

      sum = sum + temperature[i];

      if (temperature[i] >= MIN_HEALTH && temperature[i] <= MAX_HEALTH) {
        z++;
      }
    }

    result = sum / temperature.length;
    System.out.printf("Средняя температура по больнице: " + "%.1f", result);
    System.out.println();
    System.out.println("Количество здоровых пациентов: " + z);
  }
}
