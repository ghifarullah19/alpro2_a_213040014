package Latihan;

import java.util.Scanner;

class Record {
  String nrp;
  String nama;
  float ipk;
  Scanner sc = new Scanner(System.in);

  void BacaMhs() {
    System.out.print("NRP: ");
    nrp = sc.next();
    System.out.print("Nama: ");
    nama = sc.next();
    System.out.print("IPK: ");
    ipk = sc.nextFloat();
  }

}

public class Nomor3 {
  Scanner sc = new Scanner(System.in);
  int N = sc.nextInt();
  Record[] R = new Record[N];

  void InitArray() {
    for (int i = 0; i < N; i++) {
      Record r = new Record();
      r.nrp = "";
      r.nama = "";
      r.ipk = 0;
      R[i] = r;
    }
  }

  void InputArray() {
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < N; i++) {
      Record r = new Record();
      r.BacaMhs();
      R[i] = r;
    }
    sc.close();
  }

  void GetElement(float x) {
    for (int i = 0; i < N; i++) {
      if (R[i].ipk >= x) {
        System.out.println(R[i].nrp + " " + R[i].nama + " " + R[i].ipk);
      }
    }
  }

  public static void main(String[] args) {
    Nomor3 jwb = new Nomor3();

    jwb.InitArray();
    jwb.InputArray();
    jwb.GetElement(3);
  }
}