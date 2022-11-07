package ArrayofRecord;

import java.util.Scanner;

public class Karyawan {
  Scanner sc = new Scanner(System.in);
  String NIK;
  String Nama;
  String Golongan;
  float Gaji;
  
  void inputData() {
    System.out.print("NIK: "); NIK = sc.next();
    System.out.print("Nama: "); Nama = sc.next();
    System.out.print("Golongan: "); Golongan = sc.next();
    System.out.print("Gaji: "); Gaji = sc.nextFloat();
  }
  
  void tampilData() {
    System.out.println("Data: " + NIK + ", " + 
                                  Nama + ", " + 
                                  Golongan + ", " + 
                                  Gaji);
  }
}
