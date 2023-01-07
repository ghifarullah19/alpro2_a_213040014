package Tubes;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class MainFile2 {
  Scanner sc = new Scanner(System.in);

  public void SaveToFile(String nf) { // menulis ke file
    Nasabah R= new Nasabah();
    float Saldoku=0;
    String No="", Namaku="", PIN = "";
		
	System.out.println("========== SaveToFile ======");
	ObjectOutputStream out = null;
	try {  
		// 1. buka file untuk ditulis
		out=new ObjectOutputStream(new FileOutputStream(nf)); // nama direktori+file
		BufferedReader brInput= new BufferedReader(new InputStreamReader(System.in));
			
		for (int i=0;i<3;i++){
		  try {
		    System.out.print("No Rekening : ");
			No=brInput.readLine();
			System.out.print("Nama :");
	        Namaku=brInput.readLine();
	        do {
	          System.out.print("PIN (PIN harus terdiri dari 4 karakter): ");
	          PIN = brInput.readLine();
	          if (PIN.length() != 4) {
	            System.err.println("PIN harus terdiri dari 4 karakter!");
	          }
	        } while (PIN.length() != 4);
            System.out.print("Saldo : ");
            Saldoku=sc.nextFloat();
            R = new Nasabah(No,Namaku,PIN,Saldoku);    
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
    int total=0;
    System.out.println("========== ViewFile ======");     
    ObjectInputStream in = null;
    try {
      // 1. buka file untuk dibaca    
      in=new ObjectInputStream(new FileInputStream(nf));
      Object curR = in.readObject();                   
      try {    
        // 2. baca dan proses setiap record yang dibaca                 
        while (true) {
          R = (Nasabah) curR; //inputstream -> objek customer
          System.out.println("No Rekening: "+R.getNorek());
          System.out.println("Nama : "+R.getNama());  
          System.out.println("PIN : "+R.getPIN());
          Rupiah("Saldo:", R.getSaldo());
          total++;                        
          curR = in.readObject(); // baca lagi...
        }
      } catch (EOFException e) {}
    System.out.println("Total record: "+ total);                
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
          R = (Nasabah) curR; //inputstream -> objek customer
          out.writeObject(R); // tulis record ke file F2
          curR = in.readObject(); // baca lagi dari file F1          
        }
      } catch (EOFException e) {}           
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
          R = (Nasabah) curR; //inputstream -> objek customer
          if (R.getSaldo() > 5000) {
            out.writeObject(R);// tulis record ke file F2             
          }
          curR = in.readObject(); // baca lagi dari file F1          
        }
      } catch (EOFException e) {}           
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
    float Saldoku=0;
    String No="", Namaku="", PIN = "";
    String nftemp = "D:\\temp.dat";
    
    /* ALGORITMA */
    try {
      // 1. buka file untuk dibaca dan ditulis
      in = new ObjectInputStream(new FileInputStream(nf)); // F1
      out = new ObjectOutputStream(new FileOutputStream(nftemp)); //F2
      
      Object curR = in.readObject();               
      try {    
        // 2. baca dan proses setiap record yang dibaca                 
        while (true) {
          R = (Nasabah) curR; //inputstream -> objek customer
          out.writeObject(R);// tulis record ke file F2
          curR = in.readObject(); // baca lagi dari file F1          
        }
      } catch (EOFException e) {}
      in.close();       
    } catch (ClassNotFoundException e) {
      System.err.println("Class tidak ditemukan.");
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("========== TambahFileBaru ======");
    try {  
      // 3. peubah untuk menerima input dari ketikan
      BufferedReader brInput= new BufferedReader(new InputStreamReader(System.in));
      
      // 4. input data
      for (int i=0;i<1;i++){
        try {
          System.out.print("No Rekening : ");
          No=brInput.readLine();
          System.out.print("Nama :");
          Namaku=brInput.readLine();
          do {
            System.out.print("PIN (PIN harus terdiri dari 4 karakter): ");
            PIN = brInput.readLine();
            if (PIN.length() != 4) {
              System.err.println("PIN harus terdiri dari 4 karakter!");
            }
          } while (PIN.length() != 4);
          System.out.print("Saldo : ");
          Saldoku=sc.nextFloat();
          R = new Nasabah(No,Namaku,PIN,Saldoku);    
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
      out = new ObjectOutputStream(new FileOutputStream(nftemp)); //F2
      
      Object curR = in.readObject();               
      try {    
        // 2. baca dan proses setiap record yang dibaca                 
        while (true && ketemu == false) {
          R = (Nasabah) curR; //inputstream -> objek customer
          if (R.getNorek().equals(norek)) {
            ketemu = true;
          } else {
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1          
          }
        }
      } catch (EOFException e) {}
      if (ketemu) {
        BufferedReader brInput= new BufferedReader(new InputStreamReader(System.in));
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
            R = (Nasabah) curR; //inputstream -> objek customer
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1          
          }
        } catch (EOFException e) {}
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
      out = new ObjectOutputStream(new FileOutputStream(nftemp)); //F2
      Object curR = in.readObject();               
      try {    
        // 2. baca dan proses setiap record yang dibaca                 
        while (true) {
          R = (Nasabah) curR; //inputstream -> objek customer
          if (R.getNorek().equals(norek)) {
            ketemu = true;
          } else {
            out.writeObject(R);// tulis record ke file F2
          }
          curR = in.readObject(); // baca lagi dari file F1          
        }
      } catch (EOFException e) {}
      out.close();
      if (!ketemu) {
        System.err.println(R.getNorek() + " tidak ditemukan.");        
      } else {
        in = new ObjectInputStream(new FileInputStream(nftemp)); // F2
        out = new ObjectOutputStream(new FileOutputStream(nf)); //F1
        curR = in.readObject(); 
        try {    
          // 2. baca dan proses setiap record yang dibaca                 
          while (true) {
            R = (Nasabah) curR; //inputstream -> objek customer
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
   
  void CekData(String nf, String norek) {
    /* procedure SetorTunai */
    /* mengubah saldo dari norek di rekaman baru lalu menyalin ke file F */
    
    /* DEKLARASI */
    Nasabah R = new Nasabah();
    ObjectInputStream in = null; // pointer ke F1
    ObjectOutputStream out = null; // pointer ke F2
    boolean ketemu = false;
    String nftemp = "D:\\temp.dat";

    try {
      // 1. buka file untuk dibaca dan ditulis
      in = new ObjectInputStream(new FileInputStream(nf)); // F1
      out = new ObjectOutputStream(new FileOutputStream(nftemp)); //F2
      
      Object curR = in.readObject();               
      try {    
        // 2. baca dan proses setiap record yang dibaca                 
        while (true && ketemu == false) {
          R = (Nasabah) curR; //inputstream -> objek customer
          if (R.getNorek().equals(norek)) {
            ketemu = true;
          } else {
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1          
          }
        }
      } catch (EOFException e) {}
      if (ketemu) {
        System.out.println("========== CekData ======");
        System.out.println("Norek: " + R.getNorek());
        System.out.println("Nama: " + R.getNama());
        Rupiah("Saldo:", R.getSaldo());
        out.writeObject(R);// tulis record ke file F2
        curR = in.readObject();               
        try {    
          // 2. baca dan proses setiap record yang dibaca                 
          while (true) {
            R = (Nasabah) curR; //inputstream -> objek customer
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1          
          }
        } catch (EOFException e) {}
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
 
  String Login(String nf) {
    Nasabah R = new Nasabah();
    ObjectInputStream in = null; // pointer ke F1
    System.out.println("LOGIN");
    int ulang = 0;
    String ix = "";
    
    /* ALGORITMA */
    try {
      // 1. buka file untuk dibaca
      in = new ObjectInputStream(new FileInputStream(nf)); // F1
      Object curR = in.readObject();               
      try {    
        // 2. baca dan proses setiap record yang dibaca
        do {
          R = (Nasabah) curR; //inputstream -> objek customer
          System.out.print("Masukan norek: ");
          String norekS = sc.next();
          System.out.print("Masukan pin: ");
          String pass = sc.next();
          ix = "";
          if (R.getNorek().equals(norekS) && R.getPIN().equals(pass)) {
            ix = R.getNorek();
          } else {
            System.err.println("Norek/PIN Salah!");
          }
          curR = in.readObject(); // baca lagi dari file F1   
          ulang += 1;
        } while (ix.equals("") && ulang < 3);
      } catch (EOFException e) {}
      in.close();       
    } catch (ClassNotFoundException e) {
      System.out.println("Class tidak ditemukan.");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ix;
  }
	
  boolean LoginAdmin(String username, String pass) {
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
  
  void TarikTunai(String nf, String norek) {
    /* procedure TarikTunai */
    /* mengubah saldo dari norek di rekaman baru lalu menyalin ke file F */
    
    /* DEKLARASI */
    Nasabah R = new Nasabah();
    ObjectInputStream in = null; // pointer ke F1
    ObjectOutputStream out = null; // pointer ke F2
    boolean ketemu = false;
    String nftemp = "D:\\temp.dat";
	System.out.print("Masukan besar penarikan: ");
	float besar = sc.nextFloat();

    try {
      // 1. buka file untuk dibaca dan ditulis
      in = new ObjectInputStream(new FileInputStream(nf)); // F1
      out = new ObjectOutputStream(new FileOutputStream(nftemp)); //F2
      
      Object curR = in.readObject();               
      try {    
        // 2. baca dan proses setiap record yang dibaca                 
        while (true && ketemu == false) {
          R = (Nasabah) curR; //inputstream -> objek customer
          if (R.getNorek().equals(norek)) {
            ketemu = true;
          } else {
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1          
          }
        }
      } catch (EOFException e) {}
      if (ketemu) {
        System.out.println(R.getNorek() + ", " + R.getNama() + ", " + R.getSaldo());
        if (R.getSaldo() == 0 ) {
          System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda Rp.0");
        } else if (R.getSaldo() <= besar){
          System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda kurang/berada di limit");
        } else {
          R.setSaldo(R.getSaldo() - besar);
          Rupiah("Penarikan saldo berhasil sebesar", besar);
          out.writeObject(R);// tulis record ke file F2
        }
        curR = in.readObject();               
        try {    
          // 2. baca dan proses setiap record yang dibaca                 
          while (true) {
            R = (Nasabah) curR; //inputstream -> objek customer
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1          
          }
        } catch (EOFException e) {}
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

  void SetorTunai(String nf, String norek) {
    /* procedure TarikTunai */
    /* mengubah saldo dari norek di rekaman baru lalu menyalin ke file F */
    
    /* DEKLARASI */
    Nasabah R = new Nasabah();
    ObjectInputStream in = null; // pointer ke F1
    ObjectOutputStream out = null; // pointer ke F2
    boolean ketemu = false;
    String nftemp = "D:\\temp.dat";
	System.out.print("Masukan besar penyetoran: ");
	float besar = sc.nextFloat();

    try {
      // 1. buka file untuk dibaca dan ditulis
      in = new ObjectInputStream(new FileInputStream(nf)); // F1
      out = new ObjectOutputStream(new FileOutputStream(nftemp)); //F2
      
      Object curR = in.readObject();               
      try {    
        // 2. baca dan proses setiap record yang dibaca                 
        while (true && ketemu == false) {
          R = (Nasabah) curR; //inputstream -> objek customer
          if (R.getNorek().equals(norek)) {
            ketemu = true;
          } else {
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1          
          }
        }
      } catch (EOFException e) {}
      if (ketemu) {
        System.out.println("========== SetorTunai ======");
        R.setSaldo(R.getSaldo() + besar);
        Rupiah("Penyetoran saldo berhasil sebesar", besar);
        out.writeObject(R);// tulis record ke file F2
        curR = in.readObject();               
        try {    
          // 2. baca dan proses setiap record yang dibaca                 
          while (true) {
            R = (Nasabah) curR; //inputstream -> objek customer
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1          
          }
        } catch (EOFException e) {}
      } else {
        System.out.println(R.getNorek() + " tidak ditemukan.");
      }
      in.close();  
      out.close();     
    } catch (ClassNotFoundException e) {
      System.err.println("Class tidak ditemukan.");
    } catch (IOException e) {
      e.printStackTrace();
    }

    SalinArsip(nftemp, nf);
  }

  void CekSaldo(String nf, String norek) {
    /* procedure SetorTunai */
    /* mengubah saldo dari norek di rekaman baru lalu menyalin ke file F */
    
    /* DEKLARASI */
    Nasabah R = new Nasabah();
    ObjectInputStream in = null; // pointer ke F1
    ObjectOutputStream out = null; // pointer ke F2
    boolean ketemu = false;
    String nftemp = "D:\\temp.dat";

    try {
      // 1. buka file untuk dibaca dan ditulis
      in = new ObjectInputStream(new FileInputStream(nf)); // F1
      out = new ObjectOutputStream(new FileOutputStream(nftemp)); //F2
      
      Object curR = in.readObject();               
      try {    
        // 2. baca dan proses setiap record yang dibaca                 
        while (true && ketemu == false) {
          R = (Nasabah) curR; //inputstream -> objek customer
          if (R.getNorek().equals(norek)) {
            ketemu = true;
          } else {
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1          
          }
        }
      } catch (EOFException e) {}
      if (ketemu) {
        System.out.println("========== CekSaldo ======");
        Rupiah("Saldo Anda:", R.getSaldo());
        out.writeObject(R);// tulis record ke file F2
        curR = in.readObject();               
        try {    
          // 2. baca dan proses setiap record yang dibaca                 
          while (true) {
            R = (Nasabah) curR; //inputstream -> objek customer
            out.writeObject(R);// tulis record ke file F2
            curR = in.readObject(); // baca lagi dari file F1          
          }
        } catch (EOFException e) {}
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
  
  void Rupiah(String pesan, float rp) {
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    System.out.println(pesan + " " + formatRupiah.format(rp));
  }
  
  int Menu() {
	  System.out.println("========== SelamatDatang ======");
	  System.out.println("Menu: \n1. Cek Saldo \n2. Setor Tunai \n3. Tarik Tunai "
	      + "\n0. Keluar");
	  System.out.print("==> ");
	  int pilihan = sc.nextInt();
	  return pilihan;
  }

  int MenuAdmin() {
    System.out.println("========== SelamatDatangAdmin ======");
    System.out.println("Menu: \n1. Lihat Data \n2. Cari Data \n3. Tambah Data "
        + "\n4. Ubah Data \n5. Hapus Data \n6. Copy Data \n0. Keluar");
    System.out.print("==> ");
    int pilihan = sc.nextInt();
    return pilihan;
}

  void PauseScreen(){
    try {
      if (System.getProperty("os.name").contains("Windows")){
        new ProcessBuilder("cmd","/c","pause").inheritIO().start().waitFor();
      } else {
        System.out.print("\033\143");
      }
    } catch (Exception ex){
      System.err.println("tidak bisa clear screen");
    }
  }

  void ClearScreen(){
    try {
      if (System.getProperty("os.name").contains("Windows")){
        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
      } else {
        System.out.print("\033\143");
      }
    } catch (Exception ex){
      System.err.println("tidak bisa clear screen");
    }
  }

  public static void main(String[] args) {
    // Nasabah  R= new Nasabah();
	MainFile2 B= new MainFile2();
	Scanner sc = new Scanner(System.in);
	String nf1, nf2, nf3, log, admin, pass;
	int pilihLog; boolean logAdmin;
	nf1 = "C:\\Users\\ghifa\\OneDrive\\Documents\\TUBES ALPRO\\FILE\\Nasabah_TUBES.dat";
	nf2 = "C:\\Users\\ghifa\\OneDrive\\Documents\\TUBES ALPRO\\COPY\\NasabahCopy_TUBES.dat";
	nf3 = "C:\\Users\\ghifa\\OneDrive\\Documents\\TUBES ALPRO\\FILE\\Nasabah2_TUBES.dat";
		
//	 B.SaveToFile(nf1); // tulis ke file
//	 B.SalinArsip(nf1, nf2); // salin file
	// B.SalinArsipDenganKondisi(nf1, nf2); // salin file dengan kondisi
	// B.ViewFileX(nf1); // baca file
	// B.ViewFileX(nf2); // baca file
	// B.MenambahData(nf1); // sisip data
    // B.ViewFileX(nf1);
	System.out.println("Login sebagai: \n1. Admin \n2. User");
	pilihLog = sc.nextInt();
	if (pilihLog == 1) {
	  System.out.print("Username: "); admin = sc.next();
	  System.out.print("Password: "); pass = sc.next();
	  logAdmin = B.LoginAdmin(admin, pass);
	  if (logAdmin == true) {
  	  int menu;
        do {
          menu = B.MenuAdmin();
          switch (menu) {
            case 1:
              B.ViewFileX(nf1);
              B.PauseScreen();
              B.ClearScreen();
              break;
            case 2:
              System.out.print("Masukan norek dari data yang akan dilihat: ");
              log = sc.next();
              B.CekData(nf1, log);
              B.PauseScreen();
              B.ClearScreen();
              break;
            case 3:
              B.MenambahData(nf1);
              B.PauseScreen();
              B.ClearScreen();
              break;
            case 4:
              System.out.print("Masukan norek dari data yang akan diubah: ");
              log = sc.next();
              B.MengubahData(nf1, log);
              B.PauseScreen();
              B.ClearScreen();
              break;
            case 5:
              System.out.print("Masukan norek dari data yang akan dihapus: ");
              log = sc.next();
              B.MenghapusData(nf1, log);
              B.PauseScreen();
              B.ClearScreen();
              break;
            case 6:
              System.out.print("Masukan Jenis Salin Arsip: \n1. Salin Semua Data \n2. Salin Data Berdasarkan Kondisi");
              int input = sc.nextInt();
              if (input == 1) {
                B.SalinArsip(nf1,nf2);
              } else if (input == 2) {
                B.SalinArsipDenganKondisi(nf1, nf2);
              } else {
                System.err.println("Input Salah!");
              }
              B.PauseScreen();
              B.ClearScreen();
              break;
            case 0:
              System.err.println("Anda telah keluar.");
              B.ClearScreen();
              break;
            default:
              System.err.println("Invalid Input!");
              B.PauseScreen();
              B.ClearScreen();
              break;
          } 
        } while (menu != 0);
	  } else {
	    System.err.println("Username/Password Salah!");
	  }
	} else if (pilihLog == 2) {
	  log = B.Login(nf1);
	    if (!(log.equals(""))) {
	      int menu;
	      do {
	        menu = B.Menu();
	        switch (menu) {
	          case 1:
	            B.CekSaldo(nf1, log);
	            B.PauseScreen();
	            B.ClearScreen();
	            break;
	          case 2:
	            B.SetorTunai(nf1, log);
	            B.PauseScreen();
	            B.ClearScreen();
	            break;
	          case 3:
	            B.TarikTunai(nf1, log);
	            B.PauseScreen();
	            B.ClearScreen();
	            break;
	          case 0:
	            System.err.println("Anda telah keluar.");
	            B.ClearScreen();
	            break;
	          default:
	            System.err.println("Invalid Input!");
	            B.PauseScreen();
	            B.ClearScreen();
	            break;
	        } 
	      } while (menu != 0);
	       B.ViewFileX(nf1);
	    } else {
	      System.err.println("Anda tidak dapat login. Norek/PIN Salah!");
	    }
	} else {
	  System.err.println("Input Salah!");
	}
  }
}