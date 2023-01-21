package Tubes.CRUD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

import Tubes.Database.Nasabah;

public class Utilitas {
    public boolean CekDataDiDatabase(String nf, String[] keywords, boolean isDisplay) throws IOException {
        Nasabah R = new Nasabah();
        FileReader fileInput = new FileReader(nf);
        BufferedReader bufferInput = new BufferedReader(fileInput);

        String data = bufferInput.readLine();
        boolean isExist = false;
        int nomorData = 0;

        if (isDisplay) {
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
            System.out.println("| No |\tNomor Rekening |\tNama                |\tPIN   |\tSaldo ");
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
        }

        while (data != null) {
            nomorData++;
            StringTokenizer stringToken = new StringTokenizer(data, ",");

            R = new Nasabah(stringToken.nextToken(), stringToken.nextToken(), stringToken.nextToken(),
                    Float.valueOf(stringToken.nextToken()));

            if (R.getNorek().equals(keywords[0])) {
                isExist = true;
                if (isDisplay) {
                    System.out.printf("| %2d ", nomorData);
                    System.out.printf("|\t%-15s", R.getNorek());
                    System.out.printf("|\t%-20s", R.getNama());
                    System.out.printf("|\t%4s  ", R.getPIN());
                    System.out.print(Rupiah("|\tRp. ", R.getSaldo()));
                    System.out.print("\n");
                } else {
                    break;
                }
            }

            data = bufferInput.readLine();
        }

        if (isDisplay) {
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
        }

        bufferInput.close();

        return isExist;
    }

    public String Login(String nf) throws IOException {
        Nasabah R = new Nasabah();
        FileReader fileInput = new FileReader(nf);
        BufferedReader bufferInput = new BufferedReader(fileInput);

        System.out.println("LOGIN");
        int ulang = 0;
        String ix = "";

        Scanner sc = new Scanner(System.in);
        System.out.print("Masukan norek: ");
        String norekS = sc.next();
        System.out.print("Masukan pin: ");
        String pass = sc.next();

        String data = bufferInput.readLine();

        do {
            ix = "";
            while (data != null) {
                StringTokenizer stringToken = new StringTokenizer(data, ",");

                R = new Nasabah(stringToken.nextToken(), stringToken.nextToken(), stringToken.nextToken(),
                        Float.valueOf(stringToken.nextToken()));

                if (R.getNorek().equals(norekS) && R.getPIN().equals(pass)) {
                    ix = R.getNorek();
                }

                data = bufferInput.readLine();
            }
            ulang += 1;
        } while (ix.equals("") && ulang < 3);
        bufferInput.close();

        return ix;
    }

    public boolean LoginAdmin(String username, String pass) {
        boolean isTrue = false;
        int coba = 0;
        do {
            coba = coba + 1;
            if (username.equals("admin") && pass.equals("admin")) {
                isTrue = true;
            } else {
                isTrue = false;
            }
        } while (isTrue == false && coba < 3);
        return isTrue;
    }

    public String Rupiah(String pesan, float rp) {
        Locale LocaleID = new Locale("id", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(LocaleID);
        return pesan + " " + formatRupiah.format(rp);
    }

    public int Menu() {
        System.out.println("========== SelamatDatang ======");
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu: \n1. Cek Saldo \n2. Setor Tunai \n3. Tarik Tunai  \n4. Riwayat Transaksi"
                + "\n0. Keluar");
        System.out.print("==> ");
        int pilihan = sc.nextInt();
        return pilihan;
    }

    public int MenuAdmin() {
        System.out.println("========== SelamatDatangAdmin ======");
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu: \n1. Tambah Data \n2. Lihat Data \n3. Cari Data "
                + "\n4. Ubah Data \n5. Hapus Data \n6. Copy Data \n0. Keluar");
        System.out.print("==> ");
        int pilihan = sc.nextInt();
        return pilihan;
    }

    public boolean getYesorNo(String message) {
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\n" + message + " (y/n)? ");
        String pilihanUser = terminalInput.next();

        while (!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")) {
            System.err.println("Pilihan anda bukan y atau n");
            System.out.print("\n" + message + " (y/n)? ");
            pilihanUser = terminalInput.next();
        }

        return pilihanUser.equalsIgnoreCase("y");
    }

    public void PauseScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex) {
            System.err.println("tidak bisa clear screen");
        }
    }

    public void ClearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex) {
            System.err.println("tidak bisa clear screen");
        }
    }

}
