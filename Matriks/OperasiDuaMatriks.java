package Matriks;

import java.util.Scanner;

public class OperasiDuaMatriks {

  // Pertemuan 9 - Prosedur Penjumlahan Matriks
  static void PenjumlahanMatriks(int baris, int kolom, MatriksInt C, MatriksInt A, MatriksInt B) {
    int x;
    for (int i = 0; i < baris; i++) {
      for (int j = 0; j < kolom; j++) {
        x = A.GetElement(i, j) + B.GetElement(i, j);
        C.SetElement(i, j, x);
      }
    }
  }
  
  //PR : Buat Perkalian Dua Matriks
  static void PerkalianMatriks(int baris1, int kolom1, int kolom2, MatriksInt C, MatriksInt A, MatriksInt B) {
    for (int i = 0; i < baris1; i++) {
      for (int j = 0; j < kolom2; j++) {
        int x = 0;
        for (int k = 0; k < kolom1; k++) {
          x = x + A.GetElement(i, k) * B.GetElement(k, j);
//          x = x + A[i][k] * B[k][j];
        }
        C.SetElement(i, j, x);
      }
    }
  }
  
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      MatriksInt A = new MatriksInt();
      MatriksInt B = new MatriksInt();
      MatriksInt C = new MatriksInt();
      
      // Pertemuan 9
      System.out.print("Baris: ");
      int baris1 = sc.nextInt();
      System.out.print("Kolom: ");
      int kolom1 = sc.nextInt();
      A.IsiMatriks(baris1, kolom1);
      A.TampilMatriks(baris1, kolom1);

      System.out.print("Baris: ");
      int baris2 = sc.nextInt();
      System.out.print("Kolom: ");
      int kolom2 = sc.nextInt();
      B.IsiMatriks(baris2, kolom2);
      B.TampilMatriks(baris2, kolom2);
      
      PenjumlahanMatriks(baris2, kolom2, C, A, B);
      
      C.TampilMatriks(baris2, kolom2);
      
      PerkalianMatriks(baris1, kolom1, kolom2, C, A, B);
      
      C.TampilMatriks(baris1, kolom2);
  }
}