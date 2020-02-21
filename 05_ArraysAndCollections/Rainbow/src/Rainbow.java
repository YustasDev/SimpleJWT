

public class Rainbow {

  public static void main(String[] args) {

    String[] colors = {"Красный", "Оранжевый", "Желтый", "Зеленый", "Голубой", "Синий",
        "Фиолетовый"};

    int m = colors.length - 1;
    String s = null;
    String massege = "Распечатаем элементы массива <colors> до процедуры";
    String massege1 = "Распечатаем элементы массива <colors> после процедуры";

    System.out.println(massege);
    for (int a = 0; a < massege.length(); a++) {
      System.out.print("=");
    }
    System.out.println();
    for (int b = 0; b < colors.length; b++) {
      System.out.println(colors[b]);
    }

    for (int i = 0; i < colors.length; i++) {
      if (i == m) {
        break;
      }
      s = colors[i];
      colors[i] = colors[m];
      colors[m] = s;
      m--;
    }

    System.out.println();
    System.out.println(massege1);
    for (int a = 0; a < massege1.length(); a++) {
      System.out.print("=");
    }
    System.out.println();
    for (int b = 0; b < colors.length; b++) {
      System.out.println(colors[b]);
    }
  }
}