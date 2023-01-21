package Tubes.CRUD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import Tubes.Database.Nasabah;
import Tubes.Database.Transaksi;

public class Operasi {
    Utilitas U = new Utilitas();

    Scanner sc = new Scanner(System.in);
    Date tanggal = new Date();
    SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    String tgl = s.format(tanggal);
    Random random = new Random();
    int kodeT = random.nextInt(999999);

    public void SaveToFile(String nf) throws IOException { // menulis ke file
        System.out.println("========== SaveToFile ======");
        Nasabah R = new Nasabah();
        Transaksi T = new Transaksi();
        float Saldoku = 0;
        String No = "", Namaku = "", PIN = "";
        FileWriter nasabahOutput = new FileWriter(nf, true);
        FileWriter transaksiOutput = new FileWriter("D:\\DatabaseTransaksi_TUBES", true);
        BufferedWriter bufferNasabah = new BufferedWriter(nasabahOutput);
        // BufferedWriter bufferTransaksi = new BufferedWriter(transaksiOutput);
        Scanner terminalInput = new Scanner(System.in);

        for (int i = 0; i < 1; i++) {
            terminalInput = new Scanner(System.in);
            System.out.print("No Rekening : ");
            No = terminalInput.nextLine();
            System.out.print("Nama        : ");
            Namaku = terminalInput.nextLine();
            do {
                System.out.print("PIN       : ");
                PIN = terminalInput.nextLine();
                if (PIN.length() != 4) {
                    System.err.println("PIN harus terdiri dari 4 karakter!");
                }
            } while (PIN.length() != 4);
            System.out.print("Saldo       : ");
            Saldoku = terminalInput.nextFloat();

            String[] keywords = { No, Namaku, PIN, String.valueOf(Saldoku) };
            boolean isExist = U.CekDataDiDatabase(nf, keywords, false);

            if (!isExist) {
                R = new Nasabah(No, Namaku, PIN, Saldoku);
                // T = new Transaksi("0", "-", tgl, 00000, 0);
                System.out.println("----------------------------------------------");
                System.out.println("Nomor Rekening : " + R.getNorek());
                System.out.println("Nama           : " + R.getNama());
                System.out.println("PIN            : " + R.getPIN());
                System.out.println("Saldo          : " + R.getSaldo());
                boolean isTambah = U.getYesorNo("Apakah anda ingin menambah data tersebut?");

                if (isTambah) {
                    bufferNasabah.write(R.getNorek() + "," + R.getNama() + "," + R.getPIN() + "," + R.getSaldo());
                    // bufferTransaksi.write(T.getNorek() + "," + T.getJenisT() + "," +
                    // T.getTanggal() + "," + T.getKodeT()
                    // + "," + T.getJumlahT());
                    bufferNasabah.newLine();
                    // bufferTransaksi.newLine();
                    bufferNasabah.flush();
                    // bufferTransaksi.flush();
                }

            } else {
                System.out.println("Data yang anda akan masukan sudah tersedia di database");
                U.CekDataDiDatabase(nf, keywords, true);
            }
        }

        bufferNasabah.close();
        // bufferTransaksi.close();
    }

    public void SimpanTransaksi(String norek, String jenisT, String tanggal,
            int kodeT, float jumlahT) throws IOException {
        Transaksi T = new Transaksi();
        FileWriter transaksiOutput = new FileWriter("D:\\DatabaseTransaksi_TUBES", true);
        BufferedWriter bufferTransaksi = new BufferedWriter(transaksiOutput);
        T = new Transaksi(norek, jenisT, tanggal, kodeT, jumlahT);
        bufferTransaksi.write(T.getNorek() + "," + T.getJenisT() + "," + T.getTanggal() + "," + T.getKodeT()
                + "," + T.getJumlahT());
        bufferTransaksi.newLine();
        bufferTransaksi.flush();
        bufferTransaksi.close();
    }

    public void ViewFileX(String nf) throws IOException {
        System.out.println("========== ViewFile ======");
        Nasabah R = new Nasabah();
        int total = 0;
        FileReader fileInput;
        BufferedReader bufferInput;

        try {
            fileInput = new FileReader(nf);
            bufferInput = new BufferedReader(fileInput);
        } catch (Exception e) {
            System.err.println("Database tidak ditemukan");
            System.err.println("Silahkan tambah data terlebih dahulu");
            SaveToFile(nf);
            return;
        }

        System.out.println(
                "-------------------------------------------------------------------------------------");
        System.out.println("| No |\tNomor Rekening |\tNama                |\tPIN   |\tSaldo               |");
        System.out.println(
                "-------------------------------------------------------------------------------------");

        String curR = bufferInput.readLine();
        int nomorData = 0;

        while (curR != null) {
            nomorData++;
            StringTokenizer stringToken = new StringTokenizer(curR, ",");

            R = new Nasabah(stringToken.nextToken(), stringToken.nextToken(), stringToken.nextToken(),
                    Float.valueOf(stringToken.nextToken()));
            System.out.printf("| %2d ", nomorData);
            System.out.printf("|\t%-15s", R.getNorek());
            System.out.printf("|\t%-20s", R.getNama());
            System.out.printf("|\t%4s  ", R.getPIN());
            System.out.printf("|\t%-20s|", U.Rupiah("", R.getSaldo()));
            System.out.print("\n");
            total++;
            curR = bufferInput.readLine();
        }

        System.out.println(
                "-------------------------------------------------------------------------------------");

        System.out.println("Total record: " + total);

        bufferInput.close();
    }

    public void RiwayatTransaksi(String norek) throws IOException {
        Transaksi T = new Transaksi();
        System.out.println("======= Riwayat Transaksi =======");
        FileReader fileInput = new FileReader("D:\\DatabaseTransaksi_TUBES");
        BufferedReader bufferInput = new BufferedReader(fileInput);

        System.out.println(
                "---------------------------------------------------------------------------------------------");
        System.out.println("| No |\tTanggal                |\tKode          |\tJenis       |\tJumlah              |");
        System.out.println(
                "---------------------------------------------------------------------------------------------");

        String curR = bufferInput.readLine();
        int nomorData = 0;

        while (curR != null) {
            nomorData++;
            StringTokenizer stringToken = new StringTokenizer(curR, ",");

            T = new Transaksi(stringToken.nextToken(), stringToken.nextToken(), stringToken.nextToken(),
                    Integer.valueOf(stringToken.nextToken()),
                    Float.valueOf(stringToken.nextToken()));
            System.out.printf("| %2d ", nomorData);
            System.out.printf("|\t%-23s", T.getTanggal());
            System.out.printf("|\t%-14s", T.getKodeT());
            System.out.printf("|\t%-12s", T.getJenisT());
            System.out.printf("|\t%-20s|", U.Rupiah("", T.getJumlahT()));
            System.out.print("\n");
            curR = bufferInput.readLine();
        }

        System.out.println(
                "---------------------------------------------------------------------------------------------");

        bufferInput.close();
    }

    public void TransaksiTerakhir(String norek) throws IOException {
        Transaksi T = new Transaksi();
        System.out.println("======= Transaksi Berhasil =======");
        FileReader fileInput = new FileReader("D:\\DatabaseTransaksi_TUBES");
        BufferedReader bufferInput = new BufferedReader(fileInput);

        System.out.println(
                "---------------------------------------------------------------------------------------------");
        System.out.println("|\tTanggal                |\tKode          |\tJenis       |\tJumlah              |");
        System.out.println(
                "---------------------------------------------------------------------------------------------");

        StringTokenizer stringToken;
        String curR = bufferInput.readLine();

        while (curR != null) {
            stringToken = new StringTokenizer(curR, ",");

            T = new Transaksi(stringToken.nextToken(), stringToken.nextToken(), stringToken.nextToken(),
                    Integer.valueOf(stringToken.nextToken()),
                    Float.valueOf(stringToken.nextToken()));
            curR = bufferInput.readLine();
        }
        System.out.printf("|\t%-23s", T.getTanggal());
        System.out.printf("|\t%-14s", T.getKodeT());
        System.out.printf("|\t%-12s", T.getJenisT());
        System.out.printf("|\t%-20s|", U.Rupiah("", T.getJumlahT()));
        System.out.print("\n");

        System.out.println(
                "---------------------------------------------------------------------------------------------");

        bufferInput.close();
    }

    public void CekData(String nf, String norek) throws IOException {
        try {
            new File(nf);
        } catch (Exception e) {
            System.err.println("Database tidak ditemukan.");
            System.err.println("Silahkan tambah data terlebih dahulu.");
            SaveToFile(nf);
            return;
        }

        String[] keywords = norek.split("\\s+");

        U.CekDataDiDatabase(nf, keywords, true);
    }

    public void MengubahData(String nf, String norek) throws IOException {
        Nasabah R = new Nasabah();
        Scanner terminalInput = new Scanner(System.in);

        // Mengambil database original
        File database = new File(nf);
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        // Membuat database sementara
        File tempDB = new File("D:\\Temp_TUBES.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        // Menampilkan data
        System.out.println("List Data");
        ViewFileX(nf);

        // Menampilkan data yang akan diupdate
        String data = bufferedInput.readLine();

        while (data != null) {
            StringTokenizer stringToken = new StringTokenizer(data, ",");

            // Menampilkan stringToken (data dalam satu baris) == norek
            if (stringToken.nextToken().equals(norek)) {
                System.out.println("\nData yang akan diubah adalah: ");
                System.out.println("------------------------------------------");
                System.out.printf("No Rekening: %s \n", norek);
                System.out.printf("Nama       : %s \n", stringToken.nextToken());
                System.out.printf("PIN        : %s \n", stringToken.nextToken());
                System.out.printf("Saldo      : Rp. %s \n", stringToken.nextToken());
                System.out.print("\n");

                // Mengambil input dari user
                String[] fieldData = { "norek", "nama", "PIN", "saldo" };
                String[] tempData = new String[4];
                float tempSaldo = 0;
                stringToken = new StringTokenizer(data, ",");
                String originalData = stringToken.nextToken();

                for (int i = 0; i < fieldData.length; i++) {
                    // User hanya dapat update nama dan pin
                    if (i != 0 && i != 3) {
                        boolean isUpdate = U.getYesorNo("Apakah anda akan merubah " + fieldData[i]);
                        originalData = stringToken.nextToken();

                        if (isUpdate) {
                            // User input
                            terminalInput = new Scanner(System.in);
                            System.out.print("\nMasukan " + fieldData[i] + " baru: ");
                            tempData[i] = terminalInput.nextLine();
                        } else {
                            tempData[i] = originalData;
                        }
                    } else {
                        if (i == 3) {
                            originalData = stringToken.nextToken();
                            tempSaldo = Float.valueOf(originalData);
                        } else {
                            tempData[i] = originalData;
                        }
                    }
                }

                // Menampilkan data baru ke layar
                stringToken = new StringTokenizer(data, ",");
                System.out.println("\nData baru adalah: ");
                System.out.println("---------------------------------");
                System.out.printf("No Rekening: %s --->>> %s\n", stringToken.nextToken(), tempData[0]);
                System.out.printf("Nama       : %-9s --->>> %s\n", stringToken.nextToken(), tempData[1]);
                System.out.printf("PIN        : %-9s --->>> %s\n", stringToken.nextToken(), tempData[2]);
                System.out.printf("Saldo      : %-9s --->>> %s\n", stringToken.nextToken(), tempSaldo);

                boolean isUpdate = U.getYesorNo("Apakah anda yakin akan mengubah data tersebut");

                if (isUpdate) {
                    // Format data baru ke database
                    R.setNorek(tempData[0]);
                    R.setNama(tempData[1]);
                    R.setPIN(tempData[2]);
                    R.setSaldo(tempSaldo);

                    // Menulis data ke database
                    bufferedOutput.write(R.getNorek() + "," + R.getNama() + "," + R.getPIN() + "," + R.getSaldo());
                } else {
                    // Salin data
                    bufferedOutput.write(data);
                }
            } else {
                // Salin data
                bufferedOutput.write(data);
            }

            bufferedOutput.newLine();
            data = bufferedInput.readLine();
        }

        // Menulis data ke file
        bufferedOutput.flush();

        // Menutup semua resources
        bufferedOutput.close();
        fileOutput.close();
        bufferedInput.close();
        fileInput.close();

        // Hapus file original
        database.delete();

        // Rename file temporary ke database
        tempDB.renameTo(database);
    }

    public void MenghapusData(String nf, String norek) throws IOException {
        Nasabah R = new Nasabah();

        // Megambil database original
        File database = new File(nf);
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedReader = new BufferedReader(fileInput);

        // Membuat database sementara
        File tempDB = new File("D:\\Temp_DB");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);

        // Menampilkan data
        System.out.println("List Buku");
        ViewFileX(nf);

        // Membaca tiap baris dan melewati data yang akan dihapus
        boolean isFound = false;
        String data = bufferedReader.readLine();

        while (data != null) {
            boolean isDelete = false;
            StringTokenizer stringToken = new StringTokenizer(data, ",");

            // Menampilkan data yang akan dihapus
            if (stringToken.nextToken().equals(norek)) {
                System.out.println("\nData yang akan anda hapus adalah: ");
                System.out.println("--------------------------------------------");
                System.out.println("Nomor Rekening: " + norek);
                System.out.println("Nama          : " + stringToken.nextToken());
                System.out.println("PIN           : " + stringToken.nextToken());
                System.out.println("Saldo         : " + stringToken.nextToken());
                isDelete = U.getYesorNo("Apakan anda yakin akan menghapus data ini");
                isFound = true;
            }

            if (isDelete) {
                // Lewati data original ke temporary
                System.out.println("Data berhasil dihapus");
            } else {
                // Salin data original ke temporary
                stringToken = new StringTokenizer(data, ",");
                R = new Nasabah(stringToken.nextToken(), stringToken.nextToken(), stringToken.nextToken(),
                        Float.valueOf(stringToken.nextToken()));
                bufferedWriter.write(R.getNorek() + "," + R.getNama() + "," + R.getPIN() + "," + R.getSaldo());
                bufferedWriter.newLine();
            }

            data = bufferedReader.readLine();
        }
        if (!isFound) {
            System.err.println("Data tidak ditemukan.");
        }

        // Menulis data ke file
        bufferedWriter.flush();

        // Menutup semua resources
        bufferedWriter.close();
        fileOutput.close();
        bufferedReader.close();
        fileInput.close();

        // Hapus file original
        database.delete();

        // Rename file temporary ke database
        tempDB.renameTo(database);
    }

    public void SalinArsip(String nf1, String nf2) throws IOException {
        Nasabah R = new Nasabah();
        Utilitas U = new Utilitas();

        // Mengambil database original
        File database = new File(nf1);
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        // Membuat database sementara
        File tempDB = new File(nf2);
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        // Menampilkan data
        System.out.println("List Data");
        ViewFileX(nf1);

        // Menampilkan data yang akan diupdate
        String data = bufferedInput.readLine();
        int nomorData = 0;

        bufferedOutput.write(
                "-------------------------------------------------------------------------------------\n");
        bufferedOutput.write("| No |\tNomor Rekening |\tNama                |\tPIN   |\tSaldo             |\n");
        bufferedOutput.write(
                "-------------------------------------------------------------------------------------\n");
        while (data != null) {
            nomorData++;
            StringTokenizer stringToken = new StringTokenizer(data, ",");

            // Salin data original ke temporary
            R = new Nasabah(stringToken.nextToken(), stringToken.nextToken(), stringToken.nextToken(),
                    Float.valueOf(stringToken.nextToken()));
            bufferedOutput.write(String.format("| %2d ", nomorData));
            bufferedOutput.write(String.format("|\t%-15s", R.getNorek()));
            bufferedOutput.write(String.format("|\t%-20s", R.getNama()));
            bufferedOutput.write(String.format("|\t%4s  ", R.getPIN()));
            bufferedOutput.write(String.format("|\t%-18s|", U.Rupiah("", R.getSaldo())));
            bufferedOutput.newLine();

            data = bufferedInput.readLine();
        }
        bufferedOutput.write(
                "-------------------------------------------------------------------------------------\n");
        // Menulis data ke file
        bufferedOutput.flush();

        // Menutup semua resources
        bufferedOutput.close();
        fileOutput.close();
        bufferedInput.close();
        fileInput.close();
    }

    public void SalinArsipDenganKondisi(String nf1, String nf2) throws IOException {
        Nasabah R = new Nasabah();

        // Mengambil database original
        File database = new File(nf1);
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedReader = new BufferedReader(fileInput);

        // Membuat database sementara
        File tempDB = new File(nf2);
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);

        // Menampilkan data
        System.out.println("List Data");
        ViewFileX(nf1);

        // Mengambil user input
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\nMasukan nomor rekening yang akan disalin: ");
        String norek = terminalInput.nextLine();

        // Menampilkan data yang akan diupdate
        String data = bufferedReader.readLine();
        boolean isFound = false;

        while (data != null) {
            boolean isCopy = false;
            StringTokenizer stringToken = new StringTokenizer(data, ",");

            // Menampilkan data yang akan dihapus
            if (stringToken.nextToken().equals(norek)) {
                System.out.println("\nData yang akan disalin adalah: ");
                System.out.println("--------------------------------------------");
                System.out.println("Nomor Rekening: " + norek);
                System.out.println("Nama          : " + stringToken.nextToken());
                System.out.println("PIN           : " + stringToken.nextToken());
                System.out.println("Saldo         : " + stringToken.nextToken());
                isCopy = U.getYesorNo("Apakan anda yakin akan menyalin data ini");
                isFound = true;
            }

            if (isCopy) {
                // Salin data original ke temporary
                stringToken = new StringTokenizer(data, ",");
                R = new Nasabah(stringToken.nextToken(), stringToken.nextToken(), stringToken.nextToken(),
                        Float.valueOf(stringToken.nextToken()));
                bufferedWriter.write(R.getNorek() + "," + R.getNama() + "," + R.getPIN() + "," + R.getSaldo());
                bufferedWriter.newLine();
                // Lewati data original ke temporary
                System.out.println("Data berhasil disalin");
            }

            data = bufferedReader.readLine();
        }
        if (!isFound) {
            System.err.println("Data tidak ditemukan.");
        }

        // Menulis data ke file
        bufferedWriter.flush();

        // Menutup semua resources
        bufferedWriter.close();
        fileOutput.close();
        bufferedWriter.close();
        fileInput.close();
    }

    public void TarikTunai(String nf, String norek) throws IOException {
        Nasabah R = new Nasabah();
        Scanner terminalInput = new Scanner(System.in);

        // Mengambil database original
        File database = new File(nf);
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        // Membuat database sementara
        File tempDB = new File("D:\\Temp_TUBES.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        // Menampilkan data yang akan diupdate
        String jenisT = "Tarik Tunai";
        String data = bufferedInput.readLine();

        while (data != null) {
            StringTokenizer stringToken = new StringTokenizer(data, ",");

            // Menampilkan stringToken (data dalam satu baris) == norek
            if (stringToken.nextToken().equals(norek)) {
                System.out.println("\nBerikut data anda: ");
                System.out.println("------------------------------------------");
                System.out.printf("No Rekening: %s \n", norek);
                System.out.printf("Nama       : %s \n", stringToken.nextToken());
                System.out.printf("PIN        : %s \n", stringToken.nextToken());
                System.out.printf("Saldo      : Rp. %s \n", stringToken.nextToken());
                System.out.print("\n");

                // Mengambil input dari user
                String[] fieldData = { "norek", "nama", "PIN", "saldo" };
                String[] tempData = new String[4];
                float tempSaldo = 0;
                float tarik = 0;
                stringToken = new StringTokenizer(data, ",");
                String originalData = stringToken.nextToken();

                for (int i = 0; i < fieldData.length; i++) {
                    // User hanya dapat update nama dan pin
                    if (i == 3) {
                        boolean isUpdate = U.getYesorNo("Apakah anda akan tarik " + fieldData[i]);
                        // originalData = stringToken.nextToken();

                        if (isUpdate) {
                            // User input
                            terminalInput = new Scanner(System.in);
                            System.out.print("\nMasukan jumlah penarikan " + fieldData[i] + ": ");
                            tarik = terminalInput.nextFloat();
                            tempSaldo = Float.valueOf(originalData) - tarik;
                        } else {
                            tempData[i] = originalData;
                        }
                    } else {
                        tempData[i] = originalData;
                        originalData = stringToken.nextToken();
                    }
                }

                boolean isUpdate = U.getYesorNo("Apakah anda yakin akan melakukan penarikan saldo");
                if (isUpdate) {
                    // Format data baru ke database
                    R.setNorek(tempData[0]);
                    R.setNama(tempData[1]);
                    R.setPIN(tempData[2]);
                    R.setSaldo(tempSaldo);

                    // Menulis data ke database
                    SimpanTransaksi(norek, jenisT, tgl, kodeT, tarik);
                    bufferedOutput.write(R.getNorek() + "," + R.getNama() + "," + R.getPIN() + "," + R.getSaldo());
                } else {
                    // Salin data
                    bufferedOutput.write(data);
                }
            } else {
                // Salin data
                bufferedOutput.write(data);
            }

            bufferedOutput.newLine();
            data = bufferedInput.readLine();
        }

        // Menulis data ke file
        bufferedOutput.flush();

        TransaksiTerakhir(norek);

        // Menutup semua resources
        bufferedOutput.close();
        fileOutput.close();
        bufferedInput.close();
        fileInput.close();

        // Hapus file original
        database.delete();

        // Rename file temporary ke database
        tempDB.renameTo(database);
    }

    public void SetorTunai(String nf, String norek) throws IOException {
        Nasabah R = new Nasabah();
        Scanner terminalInput = new Scanner(System.in);

        // Mengambil database original
        File database = new File(nf);
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        // Membuat database sementara
        File tempDB = new File("D:\\Temp_TUBES.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        // Menampilkan data yang akan diupdate
        String jenisT = "Setor Tunai";
        String data = bufferedInput.readLine();

        while (data != null) {
            StringTokenizer stringToken = new StringTokenizer(data, ",");

            // Menampilkan stringToken (data dalam satu baris) == norek
            if (stringToken.nextToken().equals(norek)) {
                System.out.println("\nBerikut data anda: ");
                System.out.println("------------------------------------------");
                System.out.printf("No Rekening: %s \n", norek);
                System.out.printf("Nama       : %s \n", stringToken.nextToken());
                System.out.printf("PIN        : %s \n", stringToken.nextToken());
                System.out.printf("Saldo      : Rp. %s \n", stringToken.nextToken());
                System.out.print("\n");

                // Mengambil input dari user
                String[] fieldData = { "norek", "nama", "PIN", "tunai" };
                String[] tempData = new String[4];
                float tempSaldo = 0;
                float setor = 0;
                stringToken = new StringTokenizer(data, ",");
                String originalData = stringToken.nextToken();

                for (int i = 0; i < fieldData.length; i++) {
                    // User hanya dapat update nama dan pin
                    if (i == 3) {
                        boolean isUpdate = U.getYesorNo("Apakah anda akan setor " + fieldData[i]);
                        // originalData = stringToken.nextToken();

                        if (isUpdate) {
                            // User input
                            terminalInput = new Scanner(System.in);
                            System.out.print("\nMasukan jumlah penyetoran " + fieldData[i] + ": ");
                            setor = terminalInput.nextFloat();
                            tempSaldo = Float.valueOf(originalData) + setor;
                        } else {
                            tempData[i] = originalData;
                        }
                    } else {
                        tempData[i] = originalData;
                        originalData = stringToken.nextToken();
                    }
                }

                boolean isUpdate = U.getYesorNo("Apakah anda yakin akan melakukan penyetoran tunai");
                if (isUpdate) {
                    // Format data baru ke database
                    R.setNorek(tempData[0]);
                    R.setNama(tempData[1]);
                    R.setPIN(tempData[2]);
                    R.setSaldo(tempSaldo);

                    // Menulis data ke database
                    SimpanTransaksi(norek, jenisT, tgl, kodeT, setor);
                    bufferedOutput.write(R.getNorek() + "," + R.getNama() + "," + R.getPIN() + "," + R.getSaldo());
                } else {
                    // Salin data
                    bufferedOutput.write(data);
                }
            } else {
                // Salin data
                bufferedOutput.write(data);
            }

            bufferedOutput.newLine();
            data = bufferedInput.readLine();
        }

        // Menulis data ke file
        bufferedOutput.flush();

        TransaksiTerakhir(norek);

        // Menutup semua resources
        bufferedOutput.close();
        fileOutput.close();
        bufferedInput.close();
        fileInput.close();

        // Hapus file original
        database.delete();

        // Rename file temporary ke database
        tempDB.renameTo(database);
    }

}
