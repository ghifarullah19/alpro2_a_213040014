package Latihan;

import java.util.Scanner;

public class Nomor4 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Masukan jumlah baris: "); int row = sc.nextInt();
    System.out.print("Masukan jumlah kolom: "); int col = sc.nextInt();
    int [][] array = new int[row][col];
    int hasil = 0;
    
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        System.out.print("Masukan elemen baris " + (i+1) + " kolom " + (j+1) + ": "); 
        array[i][j] = sc.nextInt();
      }
    }
    
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        System.out.print(array[i][j] + " ");
      }
      System.out.println();
    }
    
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (j == i) {
          hasil += array[i][j];
        }
      }
    }
    
    System.out.println(hasil);
    sc.close();
  }
}
