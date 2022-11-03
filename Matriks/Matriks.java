package Matriks;

import java.util.Scanner;

public class Matriks {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in); 
//    System.out.print("Masukan jumlah baris: ");
//    int row = sc.nextInt();
//    System.out.print("Masukan jumlah kolom: ");
//    int col = sc.nextInt();
    int [][] array = {
        {1,2,3,4,5},
        {1,2,3,4,5},
        {1,2,3,4,5},
        {1,2,3,4,5},
        {1,2,3,4,5}
    };
    
//    for (int i = 0; i < row; i++) {
//      for (int j = 0; j < col; j++) {
//        System.out.print("Masukan nilai " + "baris " + (i + 1) + " " + 
//                                            "kolom " + (j + 1) + ": ");
//        array[i][j] = sc.nextInt();
//      }
//    }
    
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        System.out.print(array[i][j] + " ");
      }
      System.out.println();
    }
    
    System.out.println();
    System.out.println("===PRINT I DAN J SAMA===");
    
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (j == i) {
          System.out.print(array[i][j] + " ");
        } else {
          System.out.print("* ");
        }
      }
      System.out.println();
    }
    
    System.out.println();
    System.out.println("===PRINT I >= J===");
    
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (j <= i) {
          System.out.print(array[i][j] + " ");
        } else {
          System.out.print("* ");
        }
      }
      System.out.println();
    }
    
    sc.close();
  }
}
