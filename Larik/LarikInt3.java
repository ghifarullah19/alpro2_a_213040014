package Larik;

import java.util.Scanner;

public class LarikInt3 {

	int N = 5;
	int [] a = new int[N];
	
	Scanner sc = new Scanner(System.in);

	void InitArray() {
		System.out.println("Inisialisasi Array");
		for (int i = 0; i < N; i++) {
			a[i] = i;	
		}
	}
	
	void IsiArray() {
		System.out.println("\nMembaca nilai input");
		for (int i = 0; i < N; i++) {
			System.out.print("Elemen ke " + i + ": ");
			a[i] = sc.nextInt();
		}
	}
	
	void TampilArray() {
		System.out.println("\nTampil Array");
		for (int i = 0; i < N; i++) {
			System.out.println("Elemen ke " + i + ": " + a[i]);	
		}
	}
	
	void MencariRataRata() {
		System.out.println("\nMencari Rata-Rata");
		double sum = 0;
		double avg = 0;
		for (int i = 0; i < N; i++) {
			sum += a[i];
		}
		avg = sum / N;
		System.out.println("Rata-rata: " + avg);
	}
	
	/*double MencariRataRata(double ukuran) {
		System.out.println("\nMencari Rata-Rata");
		double sum = 0;
		double avg = 0;
		for (int i = 0; i < ukuran; i++) {
			sum += a[i];
		}
		avg = sum / ukuran;
		System.out.println("Rata-rata: " + avg);
		return avg;
	}*/
	
	int MencariX(int x) {
		int ix = -1;
		int i;
		for (i = 0; i < N; i++) {
			if (a[i] == x) {
				ix = i;
			}
		}
		System.out.println("i keluar dari loop: " + i);
		return ix;
	}
	
	public static void main(String[] args) {
		LarikInt3 B = new LarikInt3();
		
		//B.InitArray();
		//B.TampilArray();
		B.IsiArray();
		B.TampilArray();
		//B.MencariRataRata();
		//B.a[3] = 5;
		//B.MencariRataRata(5);\
		System.out.println("Mencari nilai tertentu dalam array");
		Scanner sc = new Scanner(System.in);
		System.out.print("Masukan nilai ");
		int x = sc.nextInt();
		int k = B.MencariX(x);
		if (k != -1) {
			System.out.println("Nilai yang dicari yaitu " + x + " ada di indeks " + k);
		} else {
			System.out.println("Nilai tidak ditemukan");
		}
	}
}
