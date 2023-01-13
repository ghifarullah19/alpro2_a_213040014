package Matriks;

import java.util.Scanner;

public class MatriksInt {

  int N = 10;
  int M = 10;
  int[][] a = new int[N][M];

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
  void IsiMatriks(int baris, int kolom) {
    System.out.println("\nMembaca nilai input");
    for (int i = 0; i < baris; i++) {
      for (int j = 0; j < kolom; j++) {
        System.out.print("Elemen ke [" + i + "," + j + "]: ");
        a[i][j] = sc.nextInt();
      }
    }
  }

  // Pertemuan 9 = Prosedur tampil Matriks
  void TampilMatriks(int baris, int kolom) {
    System.out.println("\nTampil Matriks");
    for (int i = 0; i < baris; i++) {
      for (int j = 0; j < kolom; j++) {
        System.out.print(a[i][j] + " ");
      }
      System.out.println();
    }
  }

  void JumlahMatriks(int baris, int kolom) {
    System.out.println("\nJumlah Matriks");
    int jumlah = 0;
    for (int i = 0; i < baris; i++) {
      for (int j = 0; j < kolom; j++) {
        jumlah += a[i][j];
      }
    }
    System.out.println("Jumlah = " + jumlah);
  }

  int GetElement(int i, int j) {
    return a[i][j];
  }

  void SetElement(int i, int j, int x) {
    a[i][j] = x;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    MatriksInt B = new MatriksInt();
    MatriksInt A = new MatriksInt();

    // Pertemuan 9
    B.InitMatriks();
    B.TampilMatriks(B.N, B.M);

    System.out.println("Baris: ");
    int barisB = sc.nextInt();
    System.out.println("Kolom: ");
    int kolomB = sc.nextInt();
    B.IsiMatriks(barisB, kolomB);
    B.TampilMatriks(barisB, kolomB);
    B.JumlahMatriks(barisB, kolomB);

    System.out.println("Baris: ");
    int barisA = sc.nextInt();
    System.out.println("Kolom: ");
    int kolomA = sc.nextInt();
    A.IsiMatriks(barisA, kolomA);
    A.TampilMatriks(barisA, kolomA);
    A.JumlahMatriks(barisA, kolomA);
    sc.close();
  }
}
