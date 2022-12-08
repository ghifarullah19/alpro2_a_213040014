package Matriks;

import java.util.Scanner;

public class MatriksChar {

      int N = 5; int M = 5;
      char [][] a = new char[N][M];
      
      Scanner sc = new Scanner(System.in);

      // Pertemuan 9 = Prosedur inisialisasi Matriks
      void InitMatriks() {
        System.out.println("Inisialisasi Matriks");
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            a[i][j] = '+';
          }
        }
      }
      
      // Pertemuan 9 = Prosedur tampil matriks
      void IsiMatriks() {
          System.out.println("Isi Matriks");
          for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
              if (i == 2 && j == 2 && i == j) {
                a[i][j] = sc.next().charAt(0);                               
              }
            }
          }
      }
      
      void TampilMatriks() {
        System.out.println("Tampil Matriks");
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            System.out.print(a[i][j] + " ");
          }
          System.out.println();
        }
    }
      
      
        
      public static void main(String[] args) {
          MatriksChar B = new MatriksChar();
          
          // Pertemuan 9
          B.InitMatriks();
          B.TampilMatriks();
          B.IsiMatriks();
          B.TampilMatriks();
      }
  }
