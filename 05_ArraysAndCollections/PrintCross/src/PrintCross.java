import java.util.Scanner;

public class PrintCross {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.println("Введите количество строк, из которых будет состоять крестик");
    int n = s.nextInt() - 1;
    s.close();

    String[][] cross = new String[n + 1][n + 1];

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= n; j++) {
        if (j == i) {
          System.out.print("X");
          cross[i][j] = "O";
        } else if (j == n - (i)) {
          System.out.print("X");
          cross[i][j] = "O";
        } else {
          System.out.print(" ");
          cross[i][j] = " ";
        }
      }
      System.out.println();
    }

    System.out.println();
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= n; j++) {
        System.out.print(cross[i][j]);
      }
      System.out.println();
    }
  }
}

