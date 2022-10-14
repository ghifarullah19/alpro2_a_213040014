package ArrayofRecord;

import java.util.Scanner;

public class ArrMahasiswa2 {
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
		R = a[i];
		return R;
	}
	
	// Prosedur mengubah record tertentu dari input
	void setElement(int i) {
		System.out.println("\n===Mengubah Data===");
		a[i].BacaMahasiswa();
	}
	
	int InputIndeks() {
		System.out.print("---Masukan index (Batas index adalah " + (N -1) + "): ");
		int indeks = sc.nextInt();
		return indeks;
	}
	
	int MencariX(String input) {
		int ix = -1;
		int i;
		for (i = 0; i < N; i++) {
			if (a[i].nrp.equals(input) || 
				a[i].nama.equals(input) || 
				a[i].username.equals(input)) {
				ix = i;
			}
		}
		return ix;
	}
	
	// Buat Prosedur LOGIN
	boolean Login(String username, String password) {
		boolean login = false;
		for (int i = 0; i < N; i++) {
			if (a[i].username.equals(username) && a[i].password.equals(password))	{
				login = true;
				break;
			} else {
				login = false;
			}
		}
		return login;
	}
	
	public static void main(String[] args) {
		ArrMahasiswa2 A = new ArrMahasiswa2();
		Mahasiswa T = new Mahasiswa();
		
		System.out.println("===Inisialisasi Array===");
		A.InitArray();
		System.out.println("===Isi Array===");
		A.IsiArray();
		System.out.println("===Tampil Array===");
		A.TampilArray();
		
		System.out.println("Mencari nilai tertentu dalam array");
		System.out.print("Masukan nilai ");
		String x = A.sc.next();
		int k = A.MencariX(x);
		if (k != -1) {
			System.out.println("Nilai yang dicari yaitu " + x + " ada di indeks " + k);
			T = A.getElement(k);
			T.TulisMahasiswa();
		} else {
			System.out.println("Nilai tidak ditemukan");
		}
		
		/*System.out.println("===LOGIN===");
		System.out.print("Masukan username: ");
		String username = A.sc.next();
		System.out.print("Masukan password: ");
		String password = A.sc.next();
		boolean login = A.Login(username, password);
		if (login == true) {
			System.out.println("Anda telah Login");
			System.out.println("Apakah anda ingin mengubah data?");
			System.out.println("1. Ya");
			System.out.println("2. Tidak");
			
			System.out.print("---Masukan pilihan: ");
			int pilihan = A.sc.nextInt();
			
			if (pilihan == 1) {
				System.out.println("===Manipulasi Array===");
				System.out.println("1. Mengambil Data");
				System.out.println("2. Mengubah Data");
				
				System.out.print("---Masukan angka: ");
				int i = A.sc.nextInt();
				
				switch (i) {
					case 1: 
						System.out.println("\n1. Mengambil Data");
						int inputAmbil = A.InputIndeks();
						Mahasiswa U = new Mahasiswa();
						U = A.getElement(inputAmbil);
						U.TulisMahasiswa();
						break;
					case 2:
						System.out.println("\n2. Mengubah Data");
						int inputBaru = A.InputIndeks();
						A.setElement(inputBaru);
						System.out.println("\n===Tampil Array (Diperbarui)===");
						A.TampilArray();
						break;
					default:
						System.out.println("---Input yang dimasukan tidak valid!---");
						break;
				}
			} else {
				System.out.println("Terima kasih");
			}
		} else {
			System.out.println("Username / password salah");
		}*/
	}
}
