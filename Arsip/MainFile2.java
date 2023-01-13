package Arsip;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MainFile2 {
  Scanner sc = new Scanner(System.in);

  public void SaveToFile(String nf) { // menulis ke file
    Nasabah R = new Nasabah();
    float Saldoku = 0;
    String No = "", Namaku = "", PIN = "";

    System.out.println("========== SaveToFile ======");
    ObjectOutputStream out = null;
    try {
      // 1. buka file untuk ditulis
      out = new ObjectOutputStream(new FileOutputStream(nf)); // nama direktori+file
      BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));

      for (int i = 0; i < 3; i++) {
        try {
          System.out.print("No Rekening : ");
          No = brInput.readLine();
          System.out.print("Nama :");
          Namaku = brInput.readLine();
          do {
            System.out.print("PIN (PIN harus terdiri dari 4 karakter): ");
            PIN = brInput.readLine();
            if (PIN.length() != 4) {
              System.err.println("PIN harus terdiri dari 4 karakter!");
            }
          } while (PIN.length() != 4);
          System.out.print("Saldo : ");
          Saldoku = sc.nextFloat();
          R = new Nasabah(No, Namaku, PIN, Saldoku);
          out.writeObject(R); // tulis record ke file
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
      // 1. buka file untuk dibaca
      in = new ObjectInputStream(new FileInputStream(nf));
      Object curR = in.readObject();
      try {
        // 2. baca dan proses setiap record yang dibaca
        while (true) {
          R = (Nasabah) curR; // inputstream -> objek customer
          System.out.println("No Rekening: " + R.getNorek());
          System.out.println("Nama : " + R.getNama());
          System.out.println("PIN : " + R.getPIN());
          System.out.println("Saldo : " + R.getSaldo());
          total++;
          curR = in.readObject(); // baca lagi...
        }
      } catch (EOFException e) {
      }
      System.out.println("Total record: " + total);
    } catch (ClassNotFoundException e) {
      System.out.println("Class tidak ditemukan.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void SalinArsip(String nf1, String nf2) {
    Nasabah R = new Nasabah();
    ObjectInputStream in = null; // pointer ke F1
    ObjectOutputStream out = null; // pointer ke F2
    try {
      // 1. buka file untuk dibaca dan ditulis
      in = new ObjectInputStream(new FileInputStream(nf1)); // F1
      out = new ObjectOutputStream(new FileOutputStream(nf2)); // F2

      Object curR = in.readObject();
      try {
        // 2. baca dan proses setiap record yang dibaca
        while (true) {
          R = (Nasabah) curR; // inputstream -> objek customer
          out.writeObject(R); // tulis record ke file F2
          curR = in.readObject(); // baca lagi dari file F1
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
    ObjectInputStream in = null; // pointer ke F1
    ObjectOutputStream out = null; // pointer ke F2
    try {
      // 1. buka file untuk dibaca dan ditulis
      in = new ObjectInputStream(new FileInputStream(nf1)); // F1
      out = new ObjectOutputStream(new FileOutputStream(nf2)); // F2

      Object curR = in.readObject();
      try {
        // 2. baca dan proses setiap record yang dibaca
        while (true) {
          R = (Nasabah) curR; // inputstream -> objek customer
          if (R.getSaldo() > 5000) {
            out.writeObject(R);// tulis record ke file F2
          }
          curR = in.readObject(); // baca lagi dari file F1
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
    /* procedure MenambahData */
    /* menambah beberapa rekaman baru ke file F */

    /* DEKLARASI */
    Nasabah R = new Nasabah();
    ObjectInputStream in = null; // pointer ke F1
    ObjectOutputStream out = null; // pointer ke F2
    float Saldoku = 0;
    String No = "", Namaku = "", PIN = "";
    String nftemp = "D:\\temp.dat";

    /* ALGORITMA */
    try {
      // 1. buka file untuk dibaca dan ditulis
      in = new ObjectInputStream(new FileInputStream(nf)); // F1
      out = new ObjectOutputStream(new FileOutputStream(nftemp)); // F2

      Object curR = in.readObject();
      try {
        // 2. baca dan proses setiap record yang dibaca
        while (true) {
          R = (Nasabah) curR; // inputstream -> objek customer
          out.writeObject(R);// tulis record ke file F2
          curR = in.readObject(); // baca lagi dari file F1
        }
      } catch (EOFException e) {
      }
      in.close();
    } catch (ClassNotFoundException e) {
      System.err.println("Class tidak ditemukan.");
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("========== TambahFileBaru ======");
    try {
      // 3. peubah untuk menerima input dari ketikan
      BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));

      // 4. input data
      for (int i = 0; i < 1; i++) {
        try {
          System.out.print("No Rekening : ");
          No = brInput.readLine();
          System.out.print("Nama :");
          Namaku = brInput.readLine();
          do {
            System.out.print("PIN (PIN harus terdiri dari 4 karakter): ");
            PIN = brInput.readLine();
            if (PIN.length() != 4) {
              System.err.println("PIN harus terdiri dari 4 karakter!");
            }
          } while (PIN.length() != 4);
          System.out.print("Saldo : ");
          Saldoku = sc.nextFloat();
          R = new Nasabah(No, Namaku, PIN, Saldoku);
          out.writeObject(R); // tulis record ke file
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

  void MengubahData(String nf, String norek) {
    /* procedure TarikTunai */
    /* mengubah nama dari norek di rekaman baru lalu menyalin ke file F */

    /* DEKLARASI */
    Nasabah R = new Nasabah();
    ObjectInputStream in = null; // pointer ke F1
    ObjectOutputStream out = null; // pointer ke F2
    boolean ketemu = false;
    String nftemp = "D:\\temp.dat", namaBaru, pinBaru;

    try {
      // 1. buka file untuk dibaca dan ditulis
      in = new ObjectInputStream(new FileInputStream(nf)); // F1
      out = new ObjectOutputStream(new FileOutputStream(nftemp)); // F2

      Object curR = in.readObject();
      try {
        // 2. baca dan proses setiap record yang dibaca
        while (true && ketemu == false) {
          R = (Nasabah) curR; // inputstream -> objek customer
          if (R.getNorek().equals(norek)) {
            ketemu = true;
          } else {
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1
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
            System.out.println("PIN (PIN harus terdiri dari 4 karakter): ");
            pinBaru = brInput.readLine();
            if (pinBaru.length() != 4) {
              System.err.println("PIN harus terdiri dari 4 karakter!");
            }
          } while (pinBaru.length() != 4);
          R.setPIN(pinBaru);
        } else {
          System.err.println("Invalid Input.");
        }
        out.writeObject(R);// tulis record ke file F2
        curR = in.readObject();
        try {
          // 2. baca dan proses setiap record yang dibaca
          while (true) {
            R = (Nasabah) curR; // inputstream -> objek customer
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1
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

  void MenghapusData(String nf, String norek) {
    /* procedure HapusData */
    /* menghapus data melalui norek di rekaman */

    /* DEKLARASI */
    Nasabah R = new Nasabah();
    ObjectInputStream in = null; // pointer ke F1
    ObjectOutputStream out = null; // pointer ke F2
    boolean ketemu = false;
    String nftemp = "D:\\temp.dat";
    int total = 0;

    try {
      // 1. buka file untuk dibaca dan ditulis
      in = new ObjectInputStream(new FileInputStream(nf)); // F1
      out = new ObjectOutputStream(new FileOutputStream(nftemp)); // F2
      Object curR = in.readObject();
      try {
        // 2. baca dan proses setiap record yang dibaca
        while (true) {
          R = (Nasabah) curR; // inputstream -> objek customer
          if (R.getNorek().equals(norek)) {
            ketemu = true;
          } else {
            out.writeObject(R);// tulis record ke file F2
          }
          curR = in.readObject(); // baca lagi dari file F1
        }
      } catch (EOFException e) {
      }
      in.close();
      out.close();
      if (!ketemu) {
        System.err.println(R.getNorek() + " tidak ditemukan.");
      } else {
        in = new ObjectInputStream(new FileInputStream(nftemp)); // F2
        out = new ObjectOutputStream(new FileOutputStream(nf)); // F1
        curR = in.readObject();
        try {
          // 2. baca dan proses setiap record yang dibaca
          while (true) {
            R = (Nasabah) curR; // inputstream -> objek customer
            out.writeObject(R);// tulis record ke file F1
            total++;
            curR = in.readObject(); // baca lagi dari file F2
          }
        } catch (EOFException e) {
          System.out.println("Data nasabah telah dihapus");
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

    SalinArsip(nftemp, nf);
  }

  public static void main(String[] args) {
    // Nasabah R= new Nasabah();
    MainFile2 B = new MainFile2();
    String nf1, nf2, nf3, norek;
    nf1 = "D:\\Nasabah.dat";
    nf2 = "D:\\NasabahCopy.dat";
    nf3 = "D:\\Nasabah2.dat";
    norek = "1";

    B.SaveToFile(nf1); // tulis ke file
    B.SalinArsip(nf1, nf3); // salin file
    B.SalinArsipDenganKondisi(nf1, nf2); // salin file dengan kondisi
    B.ViewFileX(nf1); // baca file
    B.ViewFileX(nf2); // baca file
    B.MenambahData(nf1); // sisip data
    B.ViewFileX(nf1); // baca data
    B.MengubahData(nf1, norek); // ubah data
    B.ViewFileX(nf1); // baca file
    B.MenghapusData(nf1, norek);
    B.ViewFileX(nf1);
  }
}