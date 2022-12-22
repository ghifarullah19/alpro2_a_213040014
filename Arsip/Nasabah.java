package Arsip;

import java.io.Serializable;
import java.util.Scanner;

class Nasabah implements Serializable {
	String norek, nama, username, pin;
	float saldo;
	
	Nasabah() {}
	
	Nasabah(String no, String nm, float uang) {
	  norek = no;
	  nama = nm;
	  saldo = uang;
	}
	
	// Pertemuan 2 = Prosedur baca nasabah
	void BacaNasabah() {
	    Scanner sc = new Scanner(System.in);
		System.out.println("Isi Data");
		System.out.print("norek: ");
		norek = sc.next();
		System.out.print("nama: ");
		nama = sc.next();
		System.out.print("pin: ");
		pin = sc.next();
		System.out.print("saldo: ");
		saldo = sc.nextFloat();
		sc.close();
	}
	
	String getNorek() {
		return norek;
	}
	
	String getNama() {
    	return nama;
    }
	
	float getSaldo() {
    	return saldo;
    }
	
	// Pertemuan 2 = Prosedur tulis nasabah
	void TulisNasabah() {
		System.out.println("Data " + ": " + norek + ", " + nama + ", " + saldo);
	}
	
	public static void main(String[] args) {
		Nasabah N = new Nasabah();
		N.TulisNasabah();
		N.BacaNasabah();
		N.TulisNasabah();
	}
}
