package uts;

public class ArrBarang {
  int N = 20;
  Barang[] a = new Barang[N];
  
  void initArray() {
    for (int i = 0; i < N; i++) {
      Barang b = new Barang();
      b.id = "";
      b.nama = "";
      b.jumlah = 0;
      b.harga = 0;
      a[i] = b;
    }
  }
  
  void inputArray() {
    for (int i = 0; i < N; i++) {
      a[i].inputData();
    }
  }
  
  void tampilArray() {
    for (int i = 0; i < N; i++) {
      a[i].tampilData();
    }
  }
  
  public static void main(String[] args) {
    ArrBarang ArrayB = new ArrBarang();
    ArrayB.initArray();
    ArrayB.inputArray();
    ArrayB.tampilArray();
  }
}
