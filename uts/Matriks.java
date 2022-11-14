package uts;

import java.util.Scanner;

public class Matriks {
  
  public static void main(String[] args) {
    int[][] matriks = new int[5][5];
    Scanner sc = new Scanner(System.in);
    
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        System.out.print("[" + i +"]" + "[" + j + "]" + "= ");
        matriks[i][j] = sc.nextInt();
      }
    }
    
    // jawaban no 3.b
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (i == 2 && j == 2 && i == j) {
          System.out.println(matriks[i][j]);
        }
      }
    }
    // jawaban no 3.b
    
  }

}
