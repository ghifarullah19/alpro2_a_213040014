package uts;

import java.util.Scanner;

public class ArrInt {
  int N = 100;
  int[] a = new int[N];

  void inputArray() {
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < N; i++) {
      a[i] = sc.nextInt();
    }
    sc.close();
  }

  void tampilArray() {
    for (int i = 0; i < N; i++) {
      System.out.println(a[i]);
    }
  }

  int sequentialSearch(int x) {
    int i = 0;

    while (a[i] != x && i < N) {
      i++;
    }

    if (a[i] == x) {
      return i;
    } else {
      return -1;
    }
  }

  public static void main(String[] args) {
    ArrInt ArrayInt = new ArrInt();

    ArrayInt.inputArray();
    ArrayInt.tampilArray();

    Scanner sc = new Scanner(System.in);
    int x = sc.nextInt();
    int hasil = ArrayInt.sequentialSearch(x);
    if (hasil != -1) {
      System.out.println("nilai ditemukan di indeks " + hasil);
    } else {
      System.err.println("nilai tidak ditemukan. error " + hasil);
    }

    sc.close();
  }
}
