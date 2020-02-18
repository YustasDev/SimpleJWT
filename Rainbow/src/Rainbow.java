

public class Rainbow {

  public static void main(String[] args) {

  String [] colors = {"Красный", "Оранжевый", "Желтый", "Зеленый", "Голубой", "Синий", "Фиолетовый"};
  String [] reverseColors = new String [7];
    int j = 0;
    String massege = "Распечатаем элементы первоначального массива <colors>";
    String massege1 = "Распечатаем элементы массива <reverseColors>";

    for(int i = colors.length - 1; i >= 0;  i--){
      reverseColors [j] = colors [i];
      j += 1;
    }

    System.out.println(massege);
    for (int a = 0; a < massege.length(); a++)
      System.out.print("=");
      System.out.println();
    for(int b = 0; b < colors.length; b++) {
      System.out.println(colors[b]);
    }
      System.out.println();
      System.out.println(massege1);
      for (int a = 0; a < massege1.length(); a++)
        System.out.print("=");
      System.out.println();
    for(int y = 0; y < reverseColors.length; y++) {
      System.out.println(reverseColors[y]);
    }
  }
}