package Latihan;

import java.util.Scanner;

public class Nomor2 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = 5;
    int jumlah = 0;
    int[] array = new int[N];

    for (int i = 0; i < N; i++) {
      System.out.print("Nilai elemen ke-" + i + ": ");
      array[i] = sc.nextInt();
      jumlah += array[i];
    }

    float avg = jumlah / N;
    System.out.println(avg);
    sc.close();
  }
}
