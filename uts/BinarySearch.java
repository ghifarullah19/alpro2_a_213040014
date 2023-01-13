package uts;

import java.util.Scanner;

public class BinarySearch {
  int N = 5;
  int[] a = new int[N];

  void inputArray() {
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < N; i++) {
      a[i] = sc.nextInt();
    }
    sc.close();
  }

  // jawaban no 3.a
  int binarySearch(int x) {
    int ia = 0;
    int ik = N - 1;
    int it = 0;
    boolean ketemu = false;

    while (ia <= ik && ketemu == false) {
      it = (ia + ik) / 2;
      if (a[it] == x) {
        ketemu = true;
      } else {
        if (a[it] > x) {
          ik = it - 1;
        } else {
          ia = it + 1;
        }
      }
    }

    if (ketemu) {
      return it;
    } else {
      return -1;
    }
  }
  // jawaban 3.a

  public static void main(String[] args) {
    BinarySearch b = new BinarySearch();
    Scanner sc = new Scanner(System.in);

    b.inputArray();
    int x = sc.nextInt();
    int hasil = b.binarySearch(x);
    if (hasil != -1) {
      System.out.println("nilai ditemukan di indeks " + hasil);
    } else {
      System.err.println("nilai tidak ditemukan. error" + hasil);
    }

    sc.close();
  }
}
