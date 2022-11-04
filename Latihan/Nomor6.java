package Latihan;

import java.util.Scanner;

class Rec {
  String idBuku;
  String judulBuku;
  String pengarang;
  String penerbit;
  String jenisBuku;
  Scanner sc = new Scanner(System.in);
  
  public String getIdBuku() {
    return idBuku;
  }
  public void setIdBuku(String idBuku) {
    this.idBuku = idBuku;
  }
  public String getJudulBuku() {
    return judulBuku;
  }
  public void setJudulBuku(String judulBuku) {
    this.judulBuku = judulBuku;
  }
  public String getpengarang() {
    return pengarang;
  }
  public void setpengarang(String pengarang) {
    this.pengarang = pengarang;
  }
  public String getPenerbit() {
    return penerbit;
  }
  public void setPenerbit(String penerbit) {
    this.penerbit = penerbit;
  }
  public String getJenisBuku() {
    return jenisBuku;
  }
  public void setJenisBuku(String jenisBuku) {
    this.jenisBuku = jenisBuku;
  }
  
  void inputData() {
    System.out.print("id buku: ");setIdBuku(sc.next());
    System.out.print("judul buku: ");setJudulBuku(sc.next());
    System.out.print("pengarang buku: ");setpengarang(sc.next());
    System.out.print("penerbit buku: ");setPenerbit(sc.next());
    System.out.print("jenis buku: ");setJenisBuku(sc.next());
  }
  
  void showData() {
    System.out.println(getIdBuku() + ", " + getJudulBuku() + ", " + getpengarang() + ", " + getPenerbit() + ", " + getJenisBuku());
  }
}

public class Nomor6 {
  Scanner sc = new Scanner(System.in);
  Rec [] arrayRec = new Rec[3];
  
  void initArray() {
    for (int i = 0; i < arrayRec.length; i++) {
      Rec r = new Rec();
      r.idBuku = "";
      r.judulBuku = "";
      r.penerbit = "";
      r.pengarang = "";
      r.jenisBuku = "";
      arrayRec[i] = r;
    }
  }
  
  void inputArray() {
    for (int i = 0; i < arrayRec.length; i++) {
      Rec r = new Rec();
      r.inputData();
      arrayRec[i] = r;
    }
  }
  
  void showArray() {
    for (int i = 0; i < arrayRec.length; i++) {
      arrayRec[i].showData();
    }
  }
  
  public static void main(String[] args) {
    Nomor6 record = new Nomor6();
    
    record.initArray();
    record.inputArray();
    record.showArray();
  }
}
