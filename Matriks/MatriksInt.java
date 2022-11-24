package Matriks;

import java.util.Scanner;

public class MatriksInt {

    int N = 3; int M = 3;
    int [][] a = new int[N][M];
    
    Scanner sc = new Scanner(System.in);

    // Pertemuan 9 = Prosedur inisialisasi Matriks
    void InitMatriks() {
        System.out.println("Inisialisasi Matriks");
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            a[i][j] = 0;               
          }
        }
    }
    
    // Pertemuan 9 = Prosedur isi Matriks
    void IsiMatriks() {
        System.out.println("\nMembaca nilai input");
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            System.out.print("Elemen ke [" + i + "," + j + "]: ");
            a[i][j] = sc.nextInt();            
          }
        }
    }
    
    // Pertemuan 9 = Prosedur tampil Matriks
    void TampilMatriks() {
        System.out.println("\nTampil Matriks");
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            System.out.print(a[i][j] + " ");             
          }
          System.out.println();
        }
    }
    
    void HitungMatriks() {
      System.out.println("\nHitung Matriks");
      for (int i = 0; i < N; i++) {
        int jumlah = 0;
        for (int j = 0; j < M; j++) {
          jumlah += a[i][j];             
        }
        System.out.println("Jumlah = " + jumlah);
      }
  }
    
    int GetElement(int i, int j) {
      return a[i][j];
    }
    
    void SetElement(int i, int j, int x) {
      a[i][j] = x;
    }
      
    public static void main(String[] args) {
        MatriksInt B = new MatriksInt();
        
        // Pertemuan 9
        B.InitMatriks();
        B.TampilMatriks();
        B.IsiMatriks();
        B.TampilMatriks();
        B.HitungMatriks();
        
    }
}
