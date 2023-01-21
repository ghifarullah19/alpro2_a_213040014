package ArrayofRecord;

import java.util.Scanner;

public class Nasabah {
	String norek, nama, username, pin;
	float saldo;

	Scanner sc = new Scanner(System.in);

	// Pertemuan 2 = Prosedur baca nasabah
	void BacaNasabah() {
		System.out.println("Isi Data");
		System.out.print("norek: ");
		norek = sc.next();
		System.out.print("nama: ");
		nama = sc.next();
		System.out.print("pin: ");
		pin = sc.next();
		System.out.print("saldo: ");
		saldo = sc.nextFloat();
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
