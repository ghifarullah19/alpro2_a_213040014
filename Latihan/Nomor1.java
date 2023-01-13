package Latihan;

import java.util.Scanner;

public class Nomor1 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = 5;
    int[] array = new int[N];

    for (int i = 0; i < N; i++) {
      System.out.print("Masukan nilai elemen ke-" + i + ": ");
      array[i] = sc.nextInt();
    }

    for (int j = 0; j < N; j++) {
      System.out.print(array[j] + " ");
    }
    sc.close();
  }
}
