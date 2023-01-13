package uts;

import java.util.Scanner;

public class Barang {
  String id;
  String nama;
  int jumlah;
  float harga;

  void inputData() {
    Scanner sc = new Scanner(System.in);
    System.out.print("id: ");
    id = sc.next();
    System.out.print("nama: ");
    nama = sc.next();
    System.out.print("jumlah: ");
    jumlah = sc.nextInt();
    System.out.print("harga: ");
    harga = sc.nextFloat();
    sc.close();
  }

  void tampilData() {
    System.out.print(id + ", " + nama + ", " + jumlah + ", " + harga);
  }
}
