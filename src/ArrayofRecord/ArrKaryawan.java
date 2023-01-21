package ArrayofRecord;

public class ArrKaryawan {
  int N = 20;
  Karyawan[] K = new Karyawan[N];

  void isiArray() {
    for (int i = 0; i < N; i++) {
      K[i].inputData();
    }
  }

  void tampilArray() {
    for (int i = 0; i < N; i++) {
      K[i].tampilData();
    }
  }

  public static void main(String[] args) {
    ArrKaryawan ArrayK = new ArrKaryawan();
    ArrayK.isiArray();
    ArrayK.tampilArray();
  }
}
