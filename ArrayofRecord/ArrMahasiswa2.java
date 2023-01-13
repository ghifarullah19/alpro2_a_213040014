package ArrayofRecord;

import java.util.Scanner;

public class ArrMahasiswa2 {
	int N = 5;
	Mahasiswa[] a = new Mahasiswa[N];
	Scanner sc = new Scanner(System.in);

	// Pertemuan 2 = Prosedur inisialisasi array
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

	// Pertemuan 2 = Prosedur isi array
	void IsiArray() {
		for (int i = 0; i < N; i++) {
			System.out.println("---Elemen " + (i + 1));
			a[i].BacaMahasiswa();
		}
	}

	// Pertemuan 2 = Prosedur tampil array
	void TampilArray() {
		for (int i = 0; i < N; i++) {
			a[i].TulisMahasiswa();
		}
		System.out.println();
	}

	// Pertemuan 3 = Fungsi mendapatkan record tertentu dari input
	Mahasiswa getElement(int i) {
		Mahasiswa R = new Mahasiswa();
		R = a[i];
		return R;
	}

	// Pertemuan 3 = Prosedur mengubah record tertentu dari input
	void setElement(int i) {
		System.out.println("\n===Mengubah Data===");
		a[i].BacaMahasiswa();
	}

	int InputIndeks() {
		System.out.print("---Masukan index (Batas index adalah " + (N - 1) + "): ");
		int indeks = sc.nextInt();
		return indeks;
	}

	// Pertemuan 3 = Fungsi mencari nilai dalam array
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

	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	int MencariNRP1(String x) {
		int i = 0;
		while (i < N - 1 && !(a[i].nrp.equals(x))) {
			i++;
		}

		if (a[i].nrp.equals(x)) {
			return i;
		} else {
			return -1;
		}
	}

	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	int MencariNRP2(String x) {
		int ix = -1;
		int i = 0;
		while (i < N - 1 && !(a[i].nrp.equals(x))) {
			i++;
		}

		if (a[i].nrp.equals(x)) {
			ix = i;
		} else {
			ix = -1;
		}
		return ix;
	}

	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	boolean MencariNRP3(String x) {
		int i = 0;
		while (i < N - 1 && !(a[i].nrp.equals(x))) {
			i++;
		}

		if (a[i].nrp.equals(x)) {
			return true;
		} else {
			return false;
		}
	}

	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	boolean MencariNRP4(String x) {
		int i = 0;
		boolean ketemu = false;
		while (i < N - 1 && !(a[i].nrp.equals(x))) {
			i++;
		}

		if (a[i].nrp.equals(x)) {
			ketemu = true;
		} else {
			ketemu = false;
		}
		return ketemu;
	}

	// Pertemuan 5 = Buat Prosedur LOGIN
	boolean Login(String username, String password) {
		boolean login = false;
		for (int i = 0; i < N; i++) {
			if (a[i].username.equals(username) && a[i].password.equals(password)) {
				login = true;
				break;
			} else {
				login = false;
			}
		}
		return login;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrMahasiswa2 A = new ArrMahasiswa2();
		Mahasiswa T = new Mahasiswa();

		// Pertemuan 2
		System.out.println("===Inisialisasi Array===");
		A.InitArray();
		System.out.println("===Isi Array===");
		A.IsiArray();
		System.out.println("===Tampil Array===");
		A.TampilArray();

		// Pertemuan 3
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

		// Pertemuan 5
		System.out.println("===LOGIN===");
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
		}

		// Pertemuan 6
		int ix;
		String nrp;
		for (int i = 0; i < 3; i++) {
			System.out.print("Masukan nilai ");
			nrp = sc.next();
			ix = A.MencariNRP1(nrp);
			System.out.println(ix);
		}

		// Pertemuan 6
		boolean ketemu;
		for (int i = 0; i < 3; i++) {
			System.out.println("Masukan nilai ");
			nrp = sc.next();
			ketemu = A.MencariNRP3(nrp);
			System.out.println(ketemu);
		}

		sc.close();
	}
}
