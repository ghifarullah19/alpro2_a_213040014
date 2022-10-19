package ArrayofRecord;

import java.util.Scanner;

public class ArrNasabah {
	int N = 1;
	Nasabah[] a = new Nasabah[N];
	Scanner sc = new Scanner(System.in);

	void InitArray() {
		for (int i = 0; i < N; i++) {
			Nasabah R = new Nasabah();
			R.norek = "";
			R.nama = "";
			R.pin = "";
			R.saldo = 0;
			a[i] = R;
			System.out.println("Elemen ke " + i + ": "
					+ a[i].norek + ", "
					+ a[i].nama + ", "
					+ a[i].username + ", "
					+ a[i].pin + ", "
					+ a[i].saldo);
		}
	}

	void IsiArray() {
		for (int i = 0; i < N; i++) {
			System.out.println("---Elemen " + (i + 1));
			a[i].BacaNasabah();
		}
	}

	void TampilArray() {
		for (int i = 0; i < N; i++) {
			a[i].TulisNasabah();
		}
		System.out.println();
	}

	Nasabah getElement(int i) {
		Nasabah R = new Nasabah();
		System.out.println("===Mengambil Data===");
		R = a[i];
		return R;
	}

	// Prosedur mengubah record tertentu dari input
	void setElement(int i) {
		System.out.println("===Mengubah Data===");
		a[i].BacaNasabah();
	}

	int InputIndeks() {
		System.out.print("---Masukan index (Batas index adalah " + (N - 1) + "): ");
		int indeks = sc.nextInt();
		return indeks;
	}

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

	// Buat Prosedur LOGIN
	int Login() {
	    System.out.println("LOGIN");
		int ulang = 1;
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
			ulang -= 1;
		} while (ix == -1 && ulang < 3);
		return ix;
	}

	void UbahNamaElemen(String no, String namaBaru) {
		int k = MencariX(no);
		if (k != -1)
			UbahNama(k, namaBaru);
		// a[k].nama = namaBaru;
		else
			System.out.println("Norek " + no + " tidak ada dalam array");
	}

	void UbahNama(int i, String name) {
		a[i].nama = name;
	}

	// Prosedur setor tabungan, tarik tunai, cek saldo
	void TarikTunai(int i) {
		System.out.print("Masukan besar penarikan: ");
		float besar = sc.nextFloat();
		if (a[i].saldo == 0 ) {
		  System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda Rp.0");
		} else if (a[i].saldo <= besar){
		  System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda kurang/berada di limit");
		} else {
		  a[i].saldo -= besar;
	      System.out.println("Tarik tunai berhasil sebesar: Rp." + besar); 
		}
	}

	void SetorTunai(int i) {
		System.out.print("Masukan besar penyetoran: ");
		float besar = sc.nextFloat();
		a[i].saldo += besar;
		System.out.println("Setor tunai berhasil sebesar: Rp." + besar);
	}

	void CekSaldo(int i) {
		System.out.println("Saldo anda: Rp." + a[i].saldo);
	}
	
	void MenuKembali(String s, String fungsi, int y) {
        while (!(s.equals("Ya"))) {
          if (fungsi.equals("cs"))
            CekSaldo(y);
          else if (fungsi.equals("tu"))
            TarikTunai(y);
          else if (fungsi.equals("st"))
            SetorTunai(y);
          System.out.print("Kembali (Ya/Tidak): ");
          s = sc.next();
        }
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrNasabah A = new ArrNasabah();
		Nasabah T = new Nasabah();
		Nasabah U = new Nasabah();

		System.out.println("===Inisialisasi Array===");
		A.InitArray();
		System.out.println("===Isi Array===");
		A.IsiArray();
		System.out.println("===Tampil Array===");
		A.TampilArray();

		/*
		 * System.out.println("===Manipulasi Array===");
		 * System.out.println("1. Mengambil Data");
		 * int inputAmbil = A.InputIndeks();
		 * T = A.getElement(inputAmbil);
		 * T.TulisNasabah();
		 * 
		 * System.out.println("2. Mengubah Data");
		 * int inputUbah = A.InputIndeks();
		 * A.setElement(inputUbah);
		 * 
		 * System.out.println("\n===Tampil Array (Diperbarui)===");
		 * A.TampilArray();
		 */

		/*
		 * System.out.println("Mencari nilai tertentu dalam array");
		 * System.out.print("Masukan nilai ");
		 * String x = sc.next();
		 * int k = A.MencariX(x);
		 * if (k != -1) {
		 * System.out.println("Nilai yang dicari yaitu " + x + " ada di indeks " + k);
		 * U = A.getElement(k);
		 * U.TulisNasabah();
		 * } else {
		 * System.out.println("Nilai tidak ditemukan");
		 * }
		 */

		// System.out.println("Sistem Login Sederhana");
		// System.out.print("Masukan username: ");
		// String norek = sc.next();
		// System.out.print("Masukan pin: ");
		// String pin = sc.next();
		// A.Login(norek, pin);

		// System.out.println("Mengubah Nama");
		// System.out.print("Masukan norek: ");
		// String norek = sc.next();
		// System.out.print("Masukan nama baru: ");
		// String namaBaru = sc.next();
		// A.UbahNamaElemen(norek, namaBaru);
		// A.TampilArray();

		int y = A.Login();
		if (y != -1) {
			System.out.println("Anda berhasil login");
			boolean login = true; String kembali = ""; String fungsi = "";
			while (login == true) {
				System.out.println("Menu: \n1. Cek Saldo \n2. Tarik Tunai \n3. Setor Tunai \n0. Keluar");
				int menu = sc.nextInt();
				switch (menu) {
					case 1:
						A.CekSaldo(y);
						System.out.print("Kembali (Ya/Tidak): ");
						kembali = sc.next();
						fungsi = "cs";
						A.MenuKembali(kembali, fungsi, y);
						break;
					case 2:
						A.TarikTunai(y);
						System.out.print("Kembali (Ya/Tidak): ");
                        kembali = sc.next();
                        fungsi = "tu";
                        A.MenuKembali(kembali, fungsi, y);
                        break;
					case 3:
						A.SetorTunai(y);
						System.out.print("Kembali (Ya/Tidak): ");
                        kembali = sc.next();
                        fungsi = "st";
                        A.MenuKembali(kembali, fungsi, y);
                        break;
					case 0:
					    System.out.println("Anda telah keluar");
						login = false;
						break;
					default:
						System.out.println("Invalid Input");
						break;
				}
			}
		} else {
			System.out.println("Anda gagal login");
		}
	}
}