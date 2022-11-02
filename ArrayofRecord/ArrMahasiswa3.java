package ArrayofRecord;

import java.util.Scanner;

public class ArrMahasiswa3 {
	int N = 2;
	Mahasiswa [] a = new Mahasiswa[N];
	Scanner sc = new Scanner(System.in);
	
	void InitArray() {
		for (int i = 0; i < N; i++) {
			Mahasiswa R = new Mahasiswa();
			R.nrp = "";
			R.nama = "";
			R.IPK = 0;
			a[i] = R;
			System.out.println("Elemen ke " + i + ": " 
					+ a[i].nrp + ", " 
					+ a[i].nama + ", " 
					+ a[i].IPK);
		}
	}
	
	void IsiArray() {
		for (int i = 0; i < N; i++) {
			System.out.println("---Elemen " + (i + 1)); 
			a[i].BacaMahasiswa();
		}
	}
	
	void TampilArray() {
		for (int i = 0; i < N; i++) {
			a[i].TulisMahasiswa();
		}
		System.out.println();
	}
	
	Mahasiswa getElement(int i) {
		Mahasiswa R = new Mahasiswa();
		System.out.println("===Mengambil Data===");
		R = a[i];
		return R;
	}
	
	// Prosedur/Fungsi mengubah record tertentu dari input
	void setElement(int i) {
		Mahasiswa R = new Mahasiswa();
		System.out.println("===Mengubah Data===");
		R.BacaMahasiswa();
		a[i] = R;
	}
	
	int InputIndeks() {
		System.out.print("---Masukan index (Batas index adalah " + (N -1) + "): ");
		int indeks = sc.nextInt();
		return indeks;
	}
	
	public static void main(String[] args) {
		ArrMahasiswa3 A = new ArrMahasiswa3();
		Mahasiswa T = new Mahasiswa();
		
		System.out.println("===Inisialisasi Array===");
		A.InitArray();
		System.out.println("===Isi Array===");
		A.IsiArray();
		System.out.println("===Tampil Array===");
		A.TampilArray();
		
		System.out.println("===Manipulasi Array===");
		System.out.println("1. Mengambil Data");
		int inputAmbil = A.InputIndeks();
		T = A.getElement(inputAmbil);
		T.TulisMahasiswa();
		A.getElement(inputAmbil).TulisMahasiswa();
	
		System.out.println("2. Mengubah Data");
		int inputUbah = A.sc.nextInt();
		A.setElement(inputUbah);
		
		System.out.println("\n===Tampil Array (Diperbarui)===");
		A.TampilArray();	
	}
}