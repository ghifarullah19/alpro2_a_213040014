package ArrayofRecord;

import java.util.Scanner;

public class Mahasiswa {
	String nrp, nama, username, password;
	float IPK;
	
	Scanner sc = new Scanner(System.in);
	
	// Pertemuan 2 = Prosedur baca mahasiswa
	void BacaMahasiswa() {
		System.out.println("Isi Data");
		System.out.print("NRP: ");
		nrp = sc.next();
		System.out.print("Nama: ");
		nama = sc.next();
		System.out.print("Username: ");
		username = sc.next();
		System.out.print("Password: ");
		password = sc.next();
		System.out.print("IPK: ");
		IPK = sc.nextFloat();
	}
	
	// Pertemuan 2 = Prosedur tulis mahasiswa
	void TulisMahasiswa() {
		System.out.println("Data " + ": " + nrp + ", " + username + ", " + nama + ", " + IPK);
	}
	
	public static void main(String[] args) {
		Mahasiswa M = new Mahasiswa();
		M.TulisMahasiswa();
		M.BacaMahasiswa();
		M.TulisMahasiswa();
	}
}
