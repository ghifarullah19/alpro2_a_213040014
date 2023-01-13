package Tubes;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class MainFile {
	Scanner sc = new Scanner(System.in);
	Date tanggal = new Date();
	SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	String tgl = s.format(tanggal);
	Random random = new Random();
	int kodeT = random.nextInt(999999);

	public void SaveToFile(String nf) {
		Nasabah R = new Nasabah();
		float Saldoku = 0;
		String No = "", Namaku = "", PIN = "";
		int jumlahData = 0;
		System.out.println("========== SaveToFile ======");
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(nf));
			BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Masukan jumlah data yang akan dimasukan: ");
			jumlahData = sc.nextInt();
			for (int i = 0; i < jumlahData; i++) {
				try {
					System.out.print("No Rekening : ");
					No = brInput.readLine();
					System.out.print("Nama : ");
					Namaku = brInput.readLine();
					do {
						System.out.print("PIN (terdiri dari 4 karakter): ");
						PIN = brInput.readLine();
						if (PIN.length() != 4) {
							System.err.println("PIN harus terdiri dari 4 karakter!");
						}
					} while (PIN.length() != 4);
					System.out.print("Saldo : ");
					Saldoku = sc.nextFloat();
					R = new Nasabah(No, Namaku, PIN, Saldoku);
					out.writeObject(R);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ViewFileX(String nf) {
		Nasabah R = new Nasabah();
		int total = 0;
		System.out.println("========== ViewFile ======");
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(nf));
			Object curR = in.readObject();
			try {
				System.out.println("+-------------+--------+----------------------+-----------------+");
				System.out.printf("| %-11s | %-6s | %-20s | %-15s |\n", "No Rekening", "Pin", "Nama", "Saldo");
				System.out.println("+-------------+--------+----------------------+-----------------+");
				while (true) {
					R = (Nasabah) curR;
					System.out.printf("| %-11s | %-6s | %-20s | %-15.2f |\n", R.getNorek(), R.getPIN(), R.getNama(),
							R.getSaldo());
					total++;
					curR = in.readObject();
				}
			} catch (EOFException e) {
			}
			System.out.println("+-------------+--------+----------------------+-----------------+");
			System.out.println("Total record: " + total);
		} catch (ClassNotFoundException e) {
			System.out.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void SalinArsip(String nf1, String nf2) {
		Nasabah R = new Nasabah();
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		try {

			in = new ObjectInputStream(new FileInputStream(nf1));
			out = new ObjectOutputStream(new FileOutputStream(nf2));

			Object curR = in.readObject();
			try {

				while (true) {
					R = (Nasabah) curR;
					out.writeObject(R);
					curR = in.readObject();
				}
			} catch (EOFException e) {
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void SalinArsipDenganKondisi(String nf1, String nf2) {
		Nasabah R = new Nasabah();
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		try {

			in = new ObjectInputStream(new FileInputStream(nf1));
			out = new ObjectOutputStream(new FileOutputStream(nf2));

			Object curR = in.readObject();
			try {

				while (true) {
					R = (Nasabah) curR;
					if (R.getSaldo() > 5000) {
						out.writeObject(R);
					}
					curR = in.readObject();
				}
			} catch (EOFException e) {
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void MenambahData(String nf) {

		Nasabah R = new Nasabah();
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		float Saldoku = 0;
		String No = "", Namaku = "", PIN = "";
		String nftemp = "D:\\temp.dat";
		int jumlahData = 0;

		try {

			in = new ObjectInputStream(new FileInputStream(nf));
			out = new ObjectOutputStream(new FileOutputStream(nftemp));

			Object curR = in.readObject();
			try {

				while (true) {
					R = (Nasabah) curR;
					out.writeObject(R);
					curR = in.readObject();
				}
			} catch (EOFException e) {
			}
			in.close();
		} catch (ClassNotFoundException e) {
			System.err.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("========== MenambahDataBaru ======");
		try {

			BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Masukan jumlah data yang akan dimasukan: ");
			jumlahData = sc.nextInt();

			for (int i = 0; i < jumlahData; i++) {
				try {
					System.out.println("Data ke - " + (i + 1));
					System.out.print("No Rekening : ");
					No = brInput.readLine();
					System.out.print("Nama : ");
					Namaku = brInput.readLine();
					do {
						System.out.print("PIN (terdiri dari 4 karakter): ");
						PIN = brInput.readLine();
						if (PIN.length() != 4) {
							System.err.println("PIN harus terdiri dari 4 karakter!");
						}
					} while (PIN.length() != 4);
					System.out.print("Saldo : ");
					Saldoku = sc.nextFloat();
					R = new Nasabah(No, Namaku, PIN, Saldoku);
					out.writeObject(R);
					System.out.println("Data berhasil ditambah");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		SalinArsip(nftemp, nf);
	}

	void MengubahData(String nf) {
		System.out.println("========== MengubahData ======");
		System.out.print("Masukan norek dari data yang akan diubah: ");
		String norek = sc.next();
		Nasabah R = new Nasabah();
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		boolean ketemu = false;
		String nftemp = "D:\\temp.dat", namaBaru, pinBaru;

		try {

			in = new ObjectInputStream(new FileInputStream(nf));
			out = new ObjectOutputStream(new FileOutputStream(nftemp));

			Object curR = in.readObject();
			try {

				while (true && ketemu == false) {
					R = (Nasabah) curR;
					if (R.getNorek().equals(norek)) {
						ketemu = true;
					} else {
						out.writeObject(R);
						curR = in.readObject();
					}
				}
			} catch (EOFException e) {
			}
			if (ketemu) {
				BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Pilih data yang akan diubah: \n1. Nama \n2. PIN");
				System.out.print("==> ");
				int pilih = sc.nextInt();
				if (pilih == 1) {
					System.out.print("Masukan Nama baru: ");
					namaBaru = brInput.readLine();
					R.setNama(namaBaru);
				} else if (pilih == 2) {
					do {
						System.out.println("PIN (terdiri dari 4 karakter): ");
						pinBaru = brInput.readLine();
						if (pinBaru.length() != 4) {
							System.err.println("PIN harus terdiri dari 4 karakter!");
						}
					} while (pinBaru.length() != 4);
					R.setPIN(pinBaru);
				} else {
					System.err.println("Invalid Input.");
				}
				out.writeObject(R);
				System.out.println("Data berhasil diubah");
				curR = in.readObject();
				try {
					while (true) {
						R = (Nasabah) curR;
						out.writeObject(R);
						curR = in.readObject();
					}
				} catch (EOFException e) {
				}
			} else {
				System.err.println(R.getNorek() + " tidak ditemukan.");
			}
			in.close();
			out.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		SalinArsip(nftemp, nf);
	}

	void MenghapusData(String nf) {
		System.out.println("========== HapusData ======");
		System.out.print("Masukan norek dari data yang akan dihapus: ");
		String norek = sc.next();
		Nasabah R = new Nasabah();
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		boolean ketemu = false;
		String nftemp = "D:\\temp.dat";
		int total = 0;

		try {
			in = new ObjectInputStream(new FileInputStream(nf));
			out = new ObjectOutputStream(new FileOutputStream(nftemp));
			Object curR = in.readObject();
			try {
				while (true) {
					R = (Nasabah) curR;
					if (R.getNorek().equals(norek)) {
						ketemu = true;
					} else {
						out.writeObject(R);
					}
					curR = in.readObject();
				}
			} catch (EOFException e) {
			}
			in.close();
			out.close();
			if (!ketemu) {
				System.err.println(R.getNorek() + " tidak ditemukan.");
			} else {
				in = new ObjectInputStream(new FileInputStream(nftemp));
				out = new ObjectOutputStream(new FileOutputStream(nf));
				curR = in.readObject();
				try {
					while (true) {
						R = (Nasabah) curR;
						out.writeObject(R);
						total++;
						curR = in.readObject();
					}
				} catch (EOFException e) {
					System.out.println("Data berhasil dihapus");
					System.out.println("Total record : " + total);
				}
			}
			in.close();
			out.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void CekData(String nf) {
		System.out.println("========== CekData ======");
		System.out.print("Masukan norek dari data yang akan dilihat: ");
		String norek = sc.next();
		Nasabah R = new Nasabah();
		ObjectInputStream in = null;
		boolean ketemu = false;

		try {
			in = new ObjectInputStream(new FileInputStream(nf));
			Object curR = in.readObject();
			try {

				while (true && ketemu == false) {
					R = (Nasabah) curR;
					if (R.getNorek().equals(norek)) {
						ketemu = true;
					} else {
						curR = in.readObject();
					}
				}
			} catch (EOFException e) {
			}
			if (ketemu) {
				System.out.println("+-------------+--------+----------------------+-----------------+");
				System.out.printf("| %-11s | %-6s | %-20s | %-15s |\n", "No Rekening", "Pin", "Nama", "Saldo");
				System.out.println("+-------------+--------+----------------------+-----------------+");
				System.out.printf("| %-11s | %-6s | %-20s | %-15.2f |\n", R.getNorek(), R.getPIN(), R.getNama(),
						R.getSaldo());
				System.out.println("+-------------+--------+----------------------+-----------------+");
			} else {
				System.err.println(R.getNorek() + " tidak ditemukan.");
			}
			in.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String Login(String nf) {
		Nasabah R = new Nasabah();
		ObjectInputStream in = null;
		System.out.println("========== ATM BERKAH ======");
		int ulang = 0;
		String ix = "";

		try {

			in = new ObjectInputStream(new FileInputStream(nf));
			Object curR = in.readObject();

			do {
				R = (Nasabah) curR;
				System.out.print("No Rekening: ");
				String norekS = sc.next();
				System.out.print("PIN: ");
				String pass = sc.next();
				ix = "";
				in = null;
				try {
					in = new ObjectInputStream(new FileInputStream(nf));
					curR = in.readObject();
					try {
						while (ix.equals("")) {
							R = (Nasabah) curR;
							if (norekS.equals(R.getNorek()) && pass.equals(R.getPIN())) {
								ix = R.getNorek();
							} else {
								curR = in.readObject();
							}
						}
					} catch (EOFException e) {
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Class tidak ditemukan.");
				} catch (IOException e) {
					e.printStackTrace();
				}
				ulang += 1;
			} while (ix.equals("") && ulang < 3);
			in.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ix;
	}

	boolean LoginAdmin(String username, String pass) {
		boolean isTrue = false;
		int coba = 0;
		do {
			coba = coba + 1;
			if (username.equals("admin") && pass.equals("admin")) {
				isTrue = true;
			} else {
				isTrue = false;
			}
		} while (isTrue == false && coba < 3);
		return isTrue;
	}

	void TarikTunai(String nf, String norek) {

		Nasabah R = new Nasabah();
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		boolean ketemu = false;
		String jenisT = "Tarik Tunai";
		String nftemp = "D:\\temp.dat";

		try {

			in = new ObjectInputStream(new FileInputStream(nf));
			out = new ObjectOutputStream(new FileOutputStream(nftemp));

			Object curR = in.readObject();
			try {

				while (true && ketemu == false) {
					R = (Nasabah) curR;
					if (R.getNorek().equals(norek)) {
						ketemu = true;
					} else {
						out.writeObject(R);
						curR = in.readObject();
					}
				}
			} catch (EOFException e) {
			}
			if (ketemu) {
				System.out.println("========== TarikTunai ======");
				System.out.println("No Rekening: " + R.getNorek());
				System.out.println("Nama: " + R.getNama());
				System.out.println("Saldo Anda: " + R.getSaldo());
				System.out.print("Masukan besar penarikan: ");
				float besar = sc.nextFloat();
				if (R.getSaldo() == 0) {
					System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda Rp.0");
				} else if (R.getSaldo() <= besar) {
					System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda kurang/berada di limit");
				} else {
					R.setSaldo(R.getSaldo() - besar);
					System.out.println("Penarikan berhasil sebesar " + besar);
					SimpanTransaksi(norek, jenisT, tgl, kodeT, besar);
					out.writeObject(R);
				}
				try {
					curR = in.readObject();
					while (true) {
						R = (Nasabah) curR;
						out.writeObject(R);
						curR = in.readObject();
					}
				} catch (EOFException e) {
				}
			} else {
				System.err.println(R.getNorek() + " tidak ditemukan.");
			}
			in.close();
			out.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		SalinArsip(nftemp, nf);
	}

	void SetorTunai(String nf, String norek) {

		Nasabah R = new Nasabah();
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		boolean ketemu = false;
		String jenisT = "Setor Tunai";
		String nftemp = "D:\\temp.dat";

		try {

			in = new ObjectInputStream(new FileInputStream(nf));
			out = new ObjectOutputStream(new FileOutputStream(nftemp));

			Object curR = in.readObject();
			try {

				while (true && ketemu == false) {
					R = (Nasabah) curR;
					if (R.getNorek().equals(norek)) {
						ketemu = true;
					} else {
						out.writeObject(R);
						curR = in.readObject();
					}
				}
			} catch (EOFException e) {
			}
			if (ketemu) {
				System.out.println("========== SetorTunai ======");
				System.out.println("No Rekening: " + R.getNorek());
				System.out.println("Nama: " + R.getNama());
				System.out.println("Saldo Anda: " + R.getSaldo());
				System.out.print("Masukan besar penyetoran: ");
				float besar = sc.nextFloat();
				R.setSaldo(R.getSaldo() + besar);
				System.out.println("Penyetoran berhasil sebesar " + besar);
				// Menyimpan hasil transaksi ke file Transaksi.dat
				SimpanTransaksi(norek, jenisT, tgl, kodeT, besar);
				out.writeObject(curR);
			} else {
				System.out.println(R.getNorek() + " tidak ditemukan.");
			}
			try {
				curR = in.readObject();
				while (true) {
					R = (Nasabah) curR;
					out.writeObject(R);
					curR = in.readObject();
				}
			} catch (EOFException e) {
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			in.close();
			out.close();
		} catch (ClassNotFoundException e) {
			System.err.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		SalinArsip(nftemp, nf);
	}

	void CekSaldo(String nf, String norek) {

		Nasabah R = new Nasabah();
		ObjectInputStream in = null;
		boolean ketemu = false;

		try {

			in = new ObjectInputStream(new FileInputStream(nf));

			Object curR = in.readObject();
			try {

				while (true && ketemu == false) {
					R = (Nasabah) curR;
					if (R.getNorek().equals(norek)) {
						ketemu = true;
					} else {
						curR = in.readObject();
					}
				}
			} catch (EOFException e) {
			}
			if (ketemu) {
				System.out.println("========== CekSaldo ======");
				System.out.println("No Rekening: " + R.getNorek());
				System.out.println("Nama: " + R.getNama());
				System.out.println("Saldo Anda: " + R.getSaldo());
			} else {
				System.err.println(R.getNorek() + " tidak ditemukan.");
			}
			in.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	int Menu() {
		System.out.println("========== SelamatDatang ======");
		System.out.println("Menu: \n1. Cek Saldo \n2. Setor Tunai \n3. Tarik Tunai "
				+ "\n4. Riwayat Transaksi" + "\n0. Keluar");
		System.out.print("==> ");
		int pilihan = sc.nextInt();
		return pilihan;
	}

	int MenuAdmin() {
		System.out.println("========== SelamatDatangAdmin ======");
		System.out.println("Menu: \n1. Masukan Data Awal \n2. Lihat Data \n3. Cari Data \n4. Tambah Data "
				+ "\n5. Ubah Data \n6. Hapus Data \n7. Copy Data \n0. Keluar");
		System.out.print("==> ");
		int pilihan = sc.nextInt();
		return pilihan;
	}

	void PauseScreen() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
			} else {
				System.out.print("\033\143");
			}
		} catch (Exception ex) {
			System.err.println("tidak bisa clear screen");
		}
	}

	void ClearScreen() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033\143");
			}
		} catch (Exception ex) {
			System.err.println("tidak bisa clear screen");
		}
	}

	public void SaveToFileTransaksi(String nf) {
		Transaksi T = new Transaksi();
		Date tanggal = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String tgl = s.format(tanggal);
		System.out.println("========== SaveToFile ======");
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(nf));
			try {
				T = new Transaksi("0", "-", tgl, 00000, 0);
				out.writeObject(T);
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void RiwayatTransaksi(String norek) {
		Transaksi T = new Transaksi();
		System.out.println("======= Riwayat Transaksi =======");
		ObjectInputStream in = null;
		try {

			in = new ObjectInputStream(new FileInputStream("D:\\Transaksi.dat"));
			Object curR = in.readObject();
			System.out.println("+---------------------+----------+---------------+-----------------+");
			System.out.printf("| %-19s | %-8s | %-13s | %-15s|\n", "Tanggal", "Kode", "Jenis", "Jumlah");
			System.out.println("+---------------------+----------+---------------+-----------------+");
			try {
				while (true) {
					T = (Transaksi) curR;
					if (norek.equals(T.getNorek())) {
						System.out.printf("| %-19s | %-8d | %-13s | %-15.2f|\n", T.getTanggal(), T.getKodeT(),
								T.getJenisT(), T.getJumlahT());
					}
					curR = in.readObject();
				}
			} catch (EOFException e) {
			}
			System.out.println("+---------------------+----------+---------------+-----------------+");
		} catch (ClassNotFoundException e) {
			System.out.println("Class tidak ditemukan.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SimpanTransaksi(String norek, String jenisT, String tanggal,
			int kodeT, float jumlahT) {
		Transaksi T = new Transaksi();
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		try {
			in = new ObjectInputStream(new FileInputStream("D:\\Transaksi.dat"));
			Object curR = in.readObject();
			out = new ObjectOutputStream(new FileOutputStream("D:\\TransaksiTemp.dat"));
			try {
				while (true) {
					T = (Transaksi) curR;
					out.writeObject(T);
					curR = in.readObject();
				}
			} catch (EOFException e) {
			}
			T = new Transaksi(norek, jenisT, tanggal, kodeT, jumlahT);
			out.writeObject(T);
			in.close();
			out.close();
			try {
				in = new ObjectInputStream(new FileInputStream("D:\\TransaksiTemp.dat"));
				curR = in.readObject();
				out = new ObjectOutputStream(new FileOutputStream("D:\\Transaksi.dat"));
				try {
					while (true) {
						T = (Transaksi) curR;
						out.writeObject(T);
						curR = in.readObject();
					}
				} catch (EOFException e) {
				}
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			in.close();
			out.close();
		} catch (ClassNotFoundException e) {
			System.out.println("File tidak ditemukan.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		MainFile B = new MainFile();
		Scanner sc = new Scanner(System.in);
		String nf1, nf2, log, admin, pass;
		int pilihLog;
		boolean logAdmin;
		nf1 = "D:\\Nasabah_TUBES.dat";
		nf2 = "D:\\NasabahCopy_TUBES.dat";
		// nf3 = "D:\\Nasabah2_TUBES.dat";

		/* ===== NOTE ===== */
		// B.SaveToFile(nf1); // Comment atau Hapus baris ini, jika Nasabah_TUBES.dat
		// sudah ada di disk D.
		/* ===== NOTE ===== */

		System.out.println("Masuk sebagai: \n1. Admin \n2. User");
		System.out.print("==> ");
		pilihLog = sc.nextInt();
		B.ClearScreen();
		if (pilihLog == 1) {
			System.out.println("========== ATM BERKAH ======");
			System.out.print("Username: ");
			admin = sc.next();
			System.out.print("Password: ");
			pass = sc.next();
			B.ClearScreen();
			logAdmin = B.LoginAdmin(admin, pass);
			if (logAdmin == true) {
				int menu;
				do {
					menu = B.MenuAdmin();
					switch (menu) {
						case 1:
							B.SaveToFile(nf1);
							B.PauseScreen();
							B.ClearScreen();
							break;
						case 2:
							B.ViewFileX(nf1);
							B.PauseScreen();
							B.ClearScreen();
							break;
						case 3:
							B.CekData(nf1);
							B.PauseScreen();
							B.ClearScreen();
							break;
						case 4:
							B.MenambahData(nf1);
							B.PauseScreen();
							B.ClearScreen();
							break;
						case 5:
							B.MengubahData(nf1);
							B.PauseScreen();
							B.ClearScreen();
							break;
						case 6:
							B.MenghapusData(nf1);
							B.PauseScreen();
							B.ClearScreen();
							break;
						case 7:
							System.out
									.print("=== Masukan Jenis Salin Arsip: ===\n1. Salin Semua Data \n2. Salin Data Berdasarkan Kondisi");
							int input = sc.nextInt();
							B.ClearScreen();
							if (input == 1) {
								System.out.println("========== SalinArsip ======");
								B.SalinArsip(nf1, nf2);
								B.PauseScreen();
								B.ClearScreen();
							} else if (input == 2) {
								System.out.println("========== SalinArsipDenganKondisi ======");
								B.SalinArsipDenganKondisi(nf1, nf2);
								B.PauseScreen();
								B.ClearScreen();
							} else {
								System.err.println("Input Salah!");
								B.PauseScreen();
								B.ClearScreen();
							}
							break;
						case 0:
							System.err.println("Anda telah keluar.");
							B.ClearScreen();
							break;
						default:
							System.err.println("Invalid Input!");
							B.PauseScreen();
							B.ClearScreen();
							break;
					}
				} while (menu != 0);
			} else {
				System.err.println("Username/Password Salah!");
			}
		} else if (pilihLog == 2) {
			log = B.Login(nf1);
			/* ===== NOTE ===== */
			B.SaveToFileTransaksi("D:\\Transaksi.dat"); // Comment atau Hapus baris ini, jika Transaksi.dat
			// sudah ada di disk D.
			/* ===== NOTE ===== */
			B.ClearScreen();
			if (!(log.equals(""))) {
				int menu;
				do {
					menu = B.Menu();
					B.ClearScreen();
					switch (menu) {
						case 1:
							B.CekSaldo(nf1, log);
							B.PauseScreen();
							B.ClearScreen();
							break;
						case 2:
							B.SetorTunai(nf1, log);
							B.PauseScreen();
							B.ClearScreen();
							break;
						case 3:
							B.TarikTunai(nf1, log);
							B.PauseScreen();
							B.ClearScreen();
							break;
						case 4:
							B.RiwayatTransaksi(log);
							B.PauseScreen();
							B.ClearScreen();
							break;
						case 0:
							System.err.println("Anda telah keluar.");
							B.ClearScreen();
							break;
						default:
							System.err.println("Invalid Input!");
							B.PauseScreen();
							B.ClearScreen();
							break;
					}
				} while (menu != 0);
				B.ViewFileX(nf1);
			} else {
				System.err.println("Anda tidak dapat login. Norek/PIN Salah!");
			}
		} else {
			System.err.println("Input Salah!");
		}
		sc.close();
	}
}