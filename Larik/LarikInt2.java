package Larik;

import java.util.Scanner;

public class LarikInt2 {
	int N = 5;
	int [] a = new int[N];
	
	Scanner sc = new Scanner(System.in);

	// Pertemuan 1 = Prosedur inisialisasi array
	void InitArray() {
		System.out.println("Inisialisasi Array");
		for (int i = 0; i < N; i++) {
			a[i] = i;	
		}
	}
	
	// Pertemuan 1 = Prosedur isi array
	void IsiArray() {
		System.out.println("\nMembaca nilai input");
		for (int i = 0; i < N; i++) {
			System.out.print("Elemen ke " + i + ": ");
			a[i] = sc.nextInt();
		}
	}
	
	// Pertemuan 1 = Prosedur tampil array
	void TampilArray() {
		System.out.println("\nTampil Array");
		for (int i = 0; i < N; i++) {
			System.out.println("Elemen ke " + i + ": " + a[i]);	
		}
	}
	
	public static void main(String[] args) {
		LarikInt2 B = new LarikInt2();
		
		B.InitArray();
		B.TampilArray();
		B.IsiArray();
		B.TampilArray();
	}
}
