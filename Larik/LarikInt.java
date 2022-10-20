package Larik;

import java.util.Scanner;

public class LarikInt {
	
	
	public static void main(String[] args) {
		int N = 5;
		int [] a = new int[N];
		
		Scanner sc = new Scanner(System.in);

		// Pertemuan 1
		System.out.println("Inisialisasi Array");
		for (int i = 0; i < N; i++) {
			a[i] = i;	
		}
		
		// Pertemuan 1
		System.out.println("\nTampil Array");
		for (int i = 0; i < N; i++) {
			System.out.println("Elemen ke " + i + ": " + a[i]);	
		}
			
		// Pertemuan 1
		System.out.println("\nMembaca nilai input");
		for (int i = 0; i < N; i++) {
			System.out.print("Elemen ke " + i + ": ");
			a[i] = sc.nextInt();
		}
		
		// Pertemuan 1
		System.out.println("\nTampil Array");
		for (int i = 0; i < N; i++) {
			System.out.println("Elemen ke " + i + ": " + a[i]);	
		}
	}
}
