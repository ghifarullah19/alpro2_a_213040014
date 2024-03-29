package ArrayofRecord;

import java.util.Scanner;

public class ArrNasabah {
	int N = 1;
	Nasabah[] a = new Nasabah[N];
	Scanner sc = new Scanner(System.in);

	// Pertemuan 2 = Prosedur inisialisasi array
	void InitArray() {
		for (int i = 0; i < N; i++) {
			Nasabah R = new Nasabah();
			R.norek = "";
			R.nama = "";
			R.pin = "";
			R.saldo = 0;
			a[i] = R;
			System.out.println("Elemen ke " + i + ": " + a[i].norek + ", " + a[i].nama + ", "
					+ a[i].username + ", " + a[i].pin + ", " + a[i].saldo);
		}
	}

	// Pertemuan 2 = Prosedur isi array
	void IsiArray() {
		for (int i = 0; i < N; i++) {
			System.out.println("---Elemen " + (i + 1));
			a[i].BacaNasabah();
		}
	}

	// Pertemuan = Prosedur tampil array
	void TampilArray() {
		for (int i = 0; i < N; i++) {
			a[i].TulisNasabah();
		}
		System.out.println();
	}

	// Pertemuan 3 = Fungsi mendapatkan record tertentu dari input
	Nasabah getElement(int i) {
		Nasabah R = new Nasabah();
		System.out.println("===Mengambil Data===");
		R = a[i];
		return R;
	}

	// Pertemuan 3 = Prosedur mengubah record tertentu dari input
	void setElement(int i) {
		System.out.println("===Mengubah Data===");
		a[i].BacaNasabah();
	}

	int InputIndeks() {
		System.out.print("---Masukan index (Batas index adalah " + (N - 1) + "): ");
		int indeks = sc.nextInt();
		return indeks;
	}

	// Pertemuan 3 = Fungsi mencari nilai dalam array
	int MencariX(String norek) {
		int ix = -1;
		int i;
		for (i = 0; i < N; i++) {
			if (a[i].norek.equals(norek)) {
				ix = i;
			}
		}
		// System.out.println("i keluar dari loop: " + i);
		return ix;
	}

	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	int MencariNOREK1(String x) {
		int i = 0;
		while (i < N - 1 && !(a[i].norek.equals(x))) {
			i++;
		}

		if (a[i].norek.equals(x)) {
			return i;
		} else {
			return -1;
		}
	}

	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	int MencariNOREK2(String x) {
		int ix = -1;
		int i = 0;
		while (i < N - 1 && !(a[i].norek.equals(x))) {
			i++;
		}

		if (a[i].norek.equals(x)) {
			ix = i;
		} else {
			ix = -1;
		}
		return ix;
	}

	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	boolean MencariNOREK3(String x) {
		int i = 0;
		while (i < N - 1 && !(a[i].norek.equals(x))) {
			i++;
		}

		if (a[i].norek.equals(x)) {
			return true;
		} else {
			return false;
		}
	}

	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	boolean MencariNOREK4(String x) {
		int i = 0;
		boolean ketemu = false;
		while (i < N - 1 && !(a[i].norek.equals(x))) {
			i++;
		}

		if (a[i].norek.equals(x)) {
			ketemu = true;
		} else {
			ketemu = false;
		}
		return ketemu;
	}

	// Pertemuan 5 = Buat Prosedur LOGIN
	int Login() {
		System.out.println("LOGIN");
		int ulang = 0;
		int ix;
		do {
			System.out.print("Masukan norek: ");
			String norekS = sc.next();
			System.out.print("Masukan pin: ");
			String pass = sc.next();
			ix = -1;
			for (int i = 0; i < N; i++) {
				if (a[i].norek.equals(norekS) && a[i].pin.equals(pass)) {
					ix = i;
				}
			}
			ulang += 1;
		} while (ix == -1 && ulang < 3);
		return ix;
	}

	// Peretemuan 5 = Prosedur ubah nama suatu elemen
	void UbahNamaElemen(String no, String namaBaru) {
		int k = MencariX(no);
		if (k != -1)
			UbahNama(k, namaBaru);
		// a[k].nama = namaBaru;
		else
			System.out.println("Norek " + no + " tidak ada dalam array");
	}

	// Peretemuan 5 = Prosedur ubah nama suatu elemen
	void UbahNama(int i, String name) {
		a[i].nama = name;
	}

	// Pertemuan 5 PR = Prosedur setor tabungan, tarik tunai, cek saldo
	void TarikTunai(int i) {
		System.out.print("Masukan besar penarikan: ");
		float besar = sc.nextFloat();
		if (a[i].saldo == 0) {
			System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda Rp.0");
		} else if (a[i].saldo <= besar) {
			System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda kurang/berada di limit");
		} else {
			a[i].saldo -= besar;
			System.out.println("Tarik tunai berhasil sebesar: Rp." + besar);
		}
	}

	// Pertemuan 5 PR = Prosedur setor tabungan, tarik tunai, cek saldo
	void SetorTunai(int i) {
		System.out.print("Masukan besar penyetoran: ");
		float besar = sc.nextFloat();
		a[i].saldo += besar;
		System.out.println("Setor tunai berhasil sebesar: Rp." + besar);
	}

	// Pertemuan 5 PR = Prosedur setor tabungan, tarik tunai, cek saldo
	void CekSaldo(int i) {
		System.out.println("Saldo anda: Rp." + a[i].saldo);
	}

	// Pertemuan 7
	int Menu() {
		System.out.println("===Selamat Datang===");
		System.out.println("Menu: \n1. Cek Saldo \n2. Setor Tunai \n3. Tarik Tunai \n0. Keluar");
		int pilihan = sc.nextInt();
		return pilihan;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrNasabah A = new ArrNasabah();
		Nasabah T = new Nasabah();
		Nasabah U = new Nasabah();

		// Pertemuan 2
		System.out.println("===Inisialisasi Array===");
		A.InitArray();
		System.out.println("===Isi Array===");
		A.IsiArray();
		System.out.println("===Tampil Array===");
		A.TampilArray();

		// Pertemuan 3
		System.out.println("===Manipulasi Array===");
		System.out.println("1. Mengambil Data");
		int inputAmbil = A.InputIndeks();
		T = A.getElement(inputAmbil);
		T.TulisNasabah();

		System.out.println("2. Mengubah Data");
		int inputUbah = A.InputIndeks();
		A.setElement(inputUbah);

		System.out.println("\n===Tampil Array (Diperbarui)===");
		A.TampilArray();

		// Pertemuan 3
		System.out.println("Mencari nilai tertentu dalam array");
		System.out.print("Masukan nilai: ");
		String x = sc.next();
		int k = A.MencariX(x);
		if (k != -1) {
			System.out.println("Nilai yang dicari yaitu " + x + " ada di indeks " + k);
			U = A.getElement(k);
			U.TulisNasabah();
		} else {
			System.out.println("Nilai tidak ditemukan");
		}

		// Pertemuan 3
		System.out.println("Mengubah Nama");
		System.out.print("Masukan norek: ");
		String norekS = sc.next();
		System.out.print("Masukan nama baru: ");
		String namaBaru = sc.next();
		A.UbahNamaElemen(norekS, namaBaru);
		A.TampilArray();

		// Pertemuan 7
		int y = A.Login();
		if (y != -1) {
			System.out.println("Anda berhasil login");
			int menu = A.Menu();
			while (menu != 0) {
				switch (menu) {
					case 1:
						A.CekSaldo(y);
						break;
					case 2:
						A.SetorTunai(y);
						break;
					case 3:
						A.TarikTunai(y);
						break;
					default:
						System.out.println("Invalid input");
						break;
				}
				menu = A.Menu();
			}
			System.out.println("Anda telah keluar");
		} else {
			System.out.println("Anda gagal login");
		}

		// Pertemuan 6
		int ix;
		String norek;
		for (int i = 0; i < 3; i++) {
			System.out.print("Masukan nilai: ");
			norek = sc.next();
			ix = A.MencariNOREK1(norek);
			System.out.println(ix);
		}

		// Pertemuan 6
		boolean ketemu;
		for (int i = 0; i < 3; i++) {
			System.out.println("Masukan nilai: ");
			norek = sc.next();
			ketemu = A.MencariNOREK3(norek);
			System.out.println(ketemu);
		}

		sc.close();
	}
}