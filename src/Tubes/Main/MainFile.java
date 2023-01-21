package Tubes.Main;

import java.io.IOException;
import java.util.Scanner;

import Tubes.CRUD.*;

public class MainFile {
  Scanner sc = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    Utilitas U = new Utilitas();
    Operasi O = new Operasi();
    Scanner sc = new Scanner(System.in);
    String nf1, nf2, nf3, log, admin, pass;
    int pilihLog;
    boolean logAdmin;
    nf1 = "D:\\DatabaseNasabah_TUBES.txt";
    nf2 = "D:\\Nasabah_TUBES.txt";
    nf3 = "D:\\Nasabah2_TUBES.txt";

    System.out.print("Login sebagai: \n1. Admin \n2. User\n==> ");
    pilihLog = sc.nextInt();
    if (pilihLog == 1) {
      System.out.print("Username: ");
      admin = sc.next();
      System.out.print("Password: ");
      pass = sc.next();
      logAdmin = U.LoginAdmin(admin, pass);
      if (logAdmin == true) {
        int menu;
        do {
          menu = U.MenuAdmin();
          switch (menu) {
            case 1:
              O.SaveToFile(nf1);
              U.PauseScreen();
              U.ClearScreen();
              break;
            case 2:
              O.ViewFileX(nf1);
              U.PauseScreen();
              U.ClearScreen();
              break;
            case 3:
              System.out.print("Masukan norek dari data yang akan dilihat: ");
              log = sc.next();
              O.CekData(nf1, log);
              U.PauseScreen();
              U.ClearScreen();
              break;
            case 4:
              System.out.print("Masukan norek dari data yang akan diubah: ");
              log = sc.next();
              O.MengubahData(nf1, log);
              U.PauseScreen();
              U.ClearScreen();
              break;
            case 5:
              System.out.print("Masukan norek dari data yang akan dihapus: ");
              log = sc.next();
              O.MenghapusData(nf1, log);
              U.PauseScreen();
              U.ClearScreen();
              break;
            case 6:
              System.out
                  .print("Masukan Jenis Salin Arsip: \n1. Salin Semua Data \n2. Salin Data Berdasarkan Kondisi\n=> ");
              int input = sc.nextInt();
              if (input == 1) {
                O.SalinArsip(nf1, nf2);
              } else if (input == 2) {
                O.SalinArsipDenganKondisi(nf1, nf3);
              } else {
                System.err.println("Input Salah!");
              }
              U.PauseScreen();
              U.ClearScreen();
              break;
            case 0:
              System.err.println("Anda telah keluar.");
              U.ClearScreen();
              break;
            default:
              System.err.println("Invalid Input!");
              U.PauseScreen();
              U.ClearScreen();
              break;
          }
        } while (menu != 0);
      } else {
        System.err.println("Username/Password Salah!");
      }
    } else if (pilihLog == 2) {
      log = U.Login(nf1);
      if (!(log.equals(""))) {
        int menu;
        do {
          menu = U.Menu();
          switch (menu) {
            case 1:
              O.CekData(nf1, log);
              U.PauseScreen();
              U.ClearScreen();
              break;
            case 2:
              O.SetorTunai(nf1, log);
              U.PauseScreen();
              U.ClearScreen();
              break;
            case 3:
              O.TarikTunai(nf1, log);
              U.PauseScreen();
              U.ClearScreen();
              break;
            case 4:
              O.RiwayatTransaksi(log);
              U.PauseScreen();
              U.ClearScreen();
              break;
            case 0:
              System.err.println("Anda telah keluar.");
              U.ClearScreen();
              break;
            default:
              System.err.println("Invalid Input!");
              U.PauseScreen();
              U.ClearScreen();
              break;
          }
        } while (menu != 0);
      } else {
        System.err.println("Anda tidak dapat login. Norek/PIN Salah!");
      }
    } else {
      System.err.println("Input Salah!");
    }
  }
}