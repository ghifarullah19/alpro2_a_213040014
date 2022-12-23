package Tubes;

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
					System.out.println("No Rekening : ");
					No=brInput.readLine();
				} catch (IOException e) {
					e.printStackTrace();  
				}    
				try {
					System.out.println("Nama :");
					Namaku=brInput.readLine();
				} catch (IOException e) {
					e.printStackTrace();  
				}
        try {
					System.out.println("PIN : ");
					PIN = brInput.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try { 
					System.out.println("Saldo : ");
					Saldoku=sc.nextFloat();
					R = new Nasabah(No,Namaku,PIN,Saldoku);    
					out.writeObject(R); // tulis record ke file
				} catch (IOException e){
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
          System.out.println("Saldo : "+R.getSaldo());
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
      System.out.println("Class tidak ditemukan.");
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("========== TambahFileBaru ======");
    try {  
      // 3. peubah untuk menerima input dari ketikan
      BufferedReader brInput= new BufferedReader(new InputStreamReader(System.in));
      
      // 4. input data
      for (int i=0;i<3;i++){
        try {
          System.out.println("No Rekening : ");
          No=brInput.readLine();
        } catch (IOException e) {
          e.printStackTrace();  
        }    
        try {
          System.out.println("Nama :");
          Namaku=brInput.readLine();
        } catch (IOException e) {
          e.printStackTrace();  
        } 
        try {
					System.out.println("PIN : ");
					PIN = brInput.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
        try { 
          System.out.println("Saldo : ");
          Saldoku=sc.nextFloat();
          R = new Nasabah(No,Namaku,PIN,Saldoku);    
          out.writeObject(R); // tulis record ke file
        } catch (IOException e){
          e.printStackTrace();  
        }
      }                       
    out.close();
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
            System.out.println("Norek/PIN Salah!");
          }
          System.out.println(ix);
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
	
  //  void TarikTunai(String nf, String norek) {
  //   /* procedure TarikTunai */
  //   /* mengubah saldo dari norek di rekaman baru lalu menyalin ke file F */
    
  //   /* DEKLARASI */
  //   Nasabah R = new Nasabah();
  //   ObjectInputStream in = null; // pointer ke F1
  //   ObjectOutputStream out = null; // pointer ke F2
  //   boolean ketemu = false;
  //   String nftemp = "D:\\temp.dat";
	// 	System.out.print("Masukan besar penarikan: ");
	// 	float besar = sc.nextFloat();

  //   try {
  //     // 1. buka file untuk dibaca dan ditulis
  //     in = new ObjectInputStream(new FileInputStream(nf)); // F1
  //     out = new ObjectOutputStream(new FileOutputStream(nftemp)); //F2
      
  //     Object curR = in.readObject();               
  //     try {    
  //       // 2. baca dan proses setiap record yang dibaca                 
  //       while (true && ketemu == false) {
  //         R = (Nasabah) curR; //inputstream -> objek customer
  //         if (R.getNorek().equals(norek)) {
  //           ketemu = true;
  //         } else {
  //           out.writeObject(R);// tulis record ke file F2
  //           curR = in.readObject(); // baca lagi dari file F1          
  //         }
  //       }
  //     } catch (EOFException e) {}
  //     if (ketemu) {
  //       System.out.println(R.getNorek() + ", " + R.getNama() + ", " + R.getSaldo());
  //       if (R.getSaldo() == 0 ) {
  //         System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda Rp.0");
  //       } else if (R.getSaldo() <= besar){
  //         System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda kurang/berada di limit");
  //       } else {
  //         R.setSaldo(R.getSaldo() - besar);
  //         System.out.println("Tarik tunai berhasil sebesar: Rp." + besar); 
  //         out.writeObject(R);// tulis record ke file F2
  //       }
  //       curR = in.readObject();               
  //       try {    
  //         // 2. baca dan proses setiap record yang dibaca                 
  //         while (true) {
  //           R = (Nasabah) curR; //inputstream -> objek customer
  //           out.writeObject(R);// tulis record ke file F2
  //           curR = in.readObject(); // baca lagi dari file F1          
  //         }
  //       } catch (EOFException e) {}
  //     } else {
  //       System.out.println(R.getNorek() + " tidak ditemukan.");
  //     }
  //     in.close();  
  //     out.close();     
  //   } catch (ClassNotFoundException e) {
  //     System.out.println("Class tidak ditemukan.");
  //   } catch (IOException e) {
  //     e.printStackTrace();
  //   }

  //   SalinArsip(nftemp, nf);
	// }

  void TarikSetorTunai(String nf, String norek) {
    /* procedure SetorTunai */
    /* mengubah saldo dari norek di rekaman baru lalu menyalin ke file F */
    
    /* DEKLARASI */
    Nasabah R = new Nasabah();
    ObjectInputStream in = null; // pointer ke F1
    ObjectOutputStream out = null; // pointer ke F2
    boolean ketemu = false;
    int pilih = -1;
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
        System.out.println(R.getNorek() + ", " + R.getNama() + ", " + R.getSaldo());
        System.out.println("Apakah anda ingin melakukan penyetoran atau penarikan?: ");
        System.out.println("1. Tarik Tunai");
        System.out.println("2. Setor Tunai");
        System.out.println("Masukan Pilihan: ");
        pilih = sc.nextInt();
        if (pilih == 1) {
          System.out.print("Masukan besar penarikan: ");
          float besar = sc.nextFloat();
          if (R.getSaldo() == 0 ) {
            System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda Rp.0");
          } else if (R.getSaldo() <= besar){
            System.out.println("Anda tidak dapat melakukan penarikan. Saldo anda kurang/berada di limit");
          } else {
            R.setSaldo(R.getSaldo() - besar);
            System.out.println("Tarik tunai berhasil sebesar: Rp." + besar);
          }
        } else if (pilih == 2) {
          System.out.print("Masukan besar penyetoran: ");
          float besar = sc.nextFloat();
          R.setSaldo(R.getSaldo() + besar);
          System.out.println("Setor tunai berhasil sebesar: Rp." + besar); 
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
        System.out.println(R.getNorek() + " tidak ditemukan.");
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
    	// Nasabah  R= new Nasabah();
		MainFile2 B= new MainFile2();
		String nf1, nf2, nf3, log;
		nf1 = "D:\\Nasabah_TUBES.dat";
		nf2 = "D:\\NasabahCopy_TUBES.dat";
		nf3 = "D:\\Nasabah2_TUBES.dat";
		
		// B.SaveToFile(nf1); // tulis ke file
		// B.SalinArsip(nf1, nf2); // salin file
		// B.SalinArsipDenganKondisi(nf1, nf2); // salin file dengan kondisi
		// B.ViewFileX(nf1); // baca file
		// B.ViewFileX(nf2); // baca file
		// B.MenambahData(nf1); // sisip data
    // B.ViewFileX(nf1);
    log = B.Login(nf1);
    if (!(log.equals(""))) {
      // B.ViewFileX(nf1); // baca data
      // B.TarikTunai(nf1, log);
      // B.ViewFileX(nf1);
      B.TarikSetorTunai(nf1, log);
      B.ViewFileX(nf1);
    } else {
      System.out.println("Anda tidak dapat login. Norek/PIN Salah!");
    }
  }
}