package Matriks;

public class OperasiDuaMatriks {

  // Pertemuan 9 - Prosedur Penjumlahan Matriks
  static void PenjumlahanMatriks(int N, int M, MatriksInt C, MatriksInt A, MatriksInt B) {
    System.out.println("\nPenjumlahan Matriks");
    System.out.println("Menghitung Matriks...");
    int x;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        x = A.GetElement(i, j) + B.GetElement(i, j);
        C.SetElement(i, j, x);
      }
    }
    System.out.println("Perhitungan selesai");
  }
  
  //PR : Buat Perkalian Dua Matriks
  static void PerkalianMatriks(int N, int M, MatriksInt C, MatriksInt A, MatriksInt B) {
    System.out.println("\nPerkalian Matriks");
    System.out.println("Menghitung Matriks...");
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        int x = 0;
        for (int k = 0; k < N; k++) {
          x = x + A.GetElement(i, k) * B.GetElement(k, j);
        }
        C.SetElement(i, j, x);
      }
    }
    System.out.println("Perhitungan selesai");
  }
  
    public static void main(String[] args) {
      MatriksInt A = new MatriksInt();
      MatriksInt B = new MatriksInt();
      MatriksInt C = new MatriksInt();
      
      // Pertemuan 9
      A.IsiMatriks();
      B.IsiMatriks();
      A.TampilMatriks();
      B.TampilMatriks();
      
      PenjumlahanMatriks(A.N, A.M, C, A, B);
      
      C.TampilMatriks();
      
      PerkalianMatriks(A.N, A.M, C, A, B);
      
      C.TampilMatriks();
      
      
  }
}