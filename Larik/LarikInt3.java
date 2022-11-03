package Larik;

import java.util.Scanner;

public class LarikInt3 {

	int N = 5;
	int [] a = new int[N];
	
	Scanner sc = new Scanner(System.in);

	// Pertemuan 2 = Prosedur inisialisasi array
	void InitArray() {
		System.out.println("Inisialisasi Array");
		for (int i = 0; i < N; i++) {
			a[i] = i;	
		}
	}
	
	// Pertemuan 2 = Prosedur isi array
	void IsiArray() {
		System.out.println("\nMembaca nilai input");
		for (int i = 0; i < N; i++) {
			System.out.print("Elemen ke " + i + ": ");
			a[i] = sc.nextInt();
		}
	}
	
	// Pertemuan 2 = Prosedur tampil array
	void TampilArray() {
		System.out.println("\nTampil Array");
		for (int i = 0; i < N; i++) {
			System.out.println("Elemen ke " + i + ": " + a[i]);	
		}
	}
	
	// Pertemuan 3 = Prosedur Mencari rata-rata
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
	
	// Pertemuan 3 = Fungsi Mencari rata-rata
	double MencariRataRata2(double ukuran) {
		System.out.println("\nMencari Rata-Rata");
		double sum = 0;
		double avg = 0;
		for (int i = 0; i < ukuran; i++) {
			sum += a[i];
		}
		avg = sum / ukuran;
		System.out.println("Rata-rata: " + avg);
		return avg;
	}
	
	// Pertemuan 3 = Fungsi Mencari nilai dalam array
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
	
	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	boolean MencariX2(int x) {
        int i = 0;
        while (i < N-1 && a[i] != x) {
          i++;
        }
        
        if (a[i] == x) {
          return true;
        } else {
          return false;
        }
      }
	
	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	boolean MencariX3(int x) {
        int i = 0;
        boolean ketemu = false;
        while (i < N-1 && ketemu != true) {
          if (a[i] == x) {
            ketemu = true;
          } else {
            i++;
          }
        }
        return ketemu;
      }
  	
	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	int MencariX4(int x) {
        int i = 0;
        while (i < N-1 && a[i] != x) {
          i++;
        }
        
        if (a[i] == x) {
          return i;
        } else {
          return -1;
        }
     }
	
	
	// Pertemuan 6 = Menerapkan Pencarian Sekuensial
	int MencariX5(int x) {
  	  int ix = -1;
        int i = 0;
        while (i < N-1 && a[i] != x) {
          i++;
        }
        
        if (a[i] == x) {
          ix = i;
        } else {
          ix = -1;
        }
        return ix;
     }

//	Pertemuan 7
	int MencariX6(int x) {
	  int i;
	  for (i = 0; i < N; i++) {
	    if (a[i] == x) {
	      return i;
	    }
	  }
	  return i;
	}
	
//  Pertemuan 7
	int MencariX7(int x) {
      int i = 0;
      while (i < N && a[i] != x) {
        i++;
      }
      if (a[i] == x) {
        return i;
      } else {
        return -1;
      }
    }
	
	//  Pertemuan 7
	int BinarySearch(int x) {
	  int iawal = 0;
	  int iakhir = N - 1;
	  boolean ketemu = false;
	  int k = 0;
	  
	  while (ketemu == false && iawal <= iakhir) {
	    k = (iawal + iakhir) / 2;
	    if (a[k] == x) {
	      ketemu = true;
	    } else { 
	      if (a[k] > x) {
	        iakhir = k - 1;
	      } else {
	        iawal = k + 1;
	      }
	    }
	  }
	  
	  if (ketemu) {
	    return k;
	  } else {
	    return -1;	    
	  }
	}
	
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
		LarikInt3 B = new LarikInt3();
		
		// Pertemuan 2
		B.InitArray();
		B.TampilArray();
		B.IsiArray();
		B.TampilArray();
		
		// Pertemuan 3
		B.MencariRataRata();
		B.a[3] = 5;
		B.MencariRataRata2(5);
		System.out.println("Mencari nilai tertentu dalam array");
		System.out.print("Masukan nilai ");
		int x = sc.nextInt();
		int k = B.MencariX(x);
		if (k != -1) {
			System.out.println("Nilai yang dicari yaitu " + x + " ada di indeks " + k);
		} else {
			System.out.println("Nilai tidak ditemukan");
		}
		
		// Pertemuan 6
		boolean j = B.MencariX2(x);
        if (j != false) {
            System.out.println("Nilai yang dicari ditemukan");
        } else {
            System.out.println("Nilai tidak ditemukan");
        }
        
        // Pertemuan 6
        int ix;
        int y;
        for (int i = 0; i < 3; i++) {
          System.out.print("Masukan nilai ");
          y = sc.nextInt();
          ix = B.BinarySearch(y);
          System.out.println(ix);
        }
        
        // Pertemuan 6
        boolean ketemu;
        for (int i = 0; i < 3; i++) {
          System.out.print("Masukan nilai ");
          y = sc.nextInt();
          ketemu = B.MencariX2(y);
          System.out.println(ketemu);
        }
        
        sc.close();
	}
}
