package Latihan;

/*
 * Jawaban:
 * Binary Search digunakan ketika data yang ada berurutan mengecil atau membesar. Contohnya pada kamus.
 * Sequential Search digunakan ketika data yang ada tidak berurutan. Contohnya buku catatan.
 * 
 * Berikut implementasi kodenya:
 */

public class Nomor5 {
  int SequentialSearch(String[] arr, String x) {
    int i = 0;
    int N = arr.length;
    while (i < N-1 && !(arr[i].equals(x))) {
      i++;
    }
    if (arr[i].equals(x)) {
      return i;
    } else {
      return -1;
    }
  }
  
  int BinarySearch(String[] arr, String x) {
    int ia = 0;
    int ik = arr.length - 1;
    int it = 0;
    boolean ketemu = false;
    
    while (ketemu == false && ia <= ik) {
      it = (ia + ik) / 2;
      if (arr[it].equals(x)) {
        ketemu = true;
      } else {
        if (arr[it].charAt(0) > x.charAt(0)) {
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
  public static void main(String[] args) {
    Nomor5 jawab = new Nomor5();
    String [] buku_catatan = {"Nomor", "Maksiat", "Lurah", "Jaringan", "Komputer"};
    String [] buku_kamus = {"Jaringan", "Komputer", "Lurah", "Maksiat", "Nomor"};
    
    System.out.println(jawab.SequentialSearch(buku_catatan, "Jaringan"));
    System.out.println(jawab.BinarySearch(buku_kamus, "Jaringan"));
  }
}
