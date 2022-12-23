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

public class MainFile {
    Scanner sc = new Scanner(System.in);

    public void SaveToFile() { // menulis ke file
		Nasabah R= new Nasabah();
		float Saldoku=0;
		String No="", Namaku="", PIN = "";
		
		System.out.println("========== SaveToFile ======");
		ObjectOutputStream out = null;
		try {  
			// 1. buka file untuk ditulis
			out=new ObjectOutputStream(new FileOutputStream
				("D:\\"+ "Nasabah.dat"));// nama direktori+file
			BufferedReader brInput= new BufferedReader
				(new InputStreamReader(System.in));
			
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
					out.writeObject(R);// tulis record ke file
				} catch (IOException e){
					e.printStackTrace();  
				}
			}                       
		out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ViewFile() {
	    Nasabah R = new Nasabah();
	    int total=0;
	    System.out.println("========== ViewFile ======");     
	    ObjectInputStream in = null;
	    try {
	    	// 1. buka file untuk dibaca	
	       in=new ObjectInputStream(new FileInputStream
	            ("D:\\Nasabah.dat"));
	       Object curR = in.readObject();					
	       try {	
	       // 2. baca dan proses setiap record yang dibaca                 
	          while (true) {
	            R = (Nasabah) curR; //inputstream -> objek customer
				System.out.println("No Rekening: "+R.getNorek());
				System.out.println("Nama : "+R.getNama());	
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
	
	   public void ViewFile2() {
	        Nasabah R = new Nasabah();
	        int total=0;
	        System.out.println("========== ViewFile ======");     
	        ObjectInputStream in = null;
	        try {
	            // 1. buka file untuk dibaca    
	           in=new ObjectInputStream(new FileInputStream
	                ("D:\\NasabahCopy.dat"));
	           Object curR = in.readObject();                   
	           try {    
	           // 2. baca dan proses setiap record yang dibaca                 
	              while (true) {
	                R = (Nasabah) curR; //inputstream -> objek customer
	                System.out.println("No Rekening: "+R.getNorek());
	                System.out.println("Nama : "+R.getNama());  
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
	   
	   public void ViewFileX(String nf) {
         Nasabah R = new Nasabah();
         int total=0;
         System.out.println("========== ViewFile ======");     
         ObjectInputStream in = null;
         try {
             // 1. buka file untuk dibaca    
            in=new ObjectInputStream(new FileInputStream
                 (nf));
            Object curR = in.readObject();                   
            try {    
            // 2. baca dan proses setiap record yang dibaca                 
               while (true) {
                 R = (Nasabah) curR; //inputstream -> objek customer
                 System.out.println("No Rekening: " + R.getNorek());
                 System.out.println("Nama : " + R.getNama());  
                 System.out.println("Saldo : " + R.getSaldo());
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
	
	void SalinArsip() {
	  Nasabah R = new Nasabah();
	  ObjectInputStream in = null; // pointer ke F1
	  ObjectOutputStream out = null; // pointer ke F2
      try {
        // 1. buka file untuk dibaca    
        in = new ObjectInputStream(new FileInputStream("D:\\Nasabah.dat")); // F1
        out = new ObjectOutputStream(new FileOutputStream("D:\\"+ "NasabahCopy.dat"));
        
        Object curR = in.readObject();               
       try {    
       // 2. baca dan proses setiap record yang dibaca                 
         while (true) {
           R = (Nasabah) curR; //inputstream -> objek customer
           out.writeObject(R);// tulis record ke file F2
           curR = in.readObject(); // baca lagi dari file F1          
         }
        } catch (EOFException e) {}           
        } catch (ClassNotFoundException e) {
          System.out.println("Class tidak ditemukan.");
        } catch (IOException e) {
          e.printStackTrace();
        }
	}
	
    void SalinArsipDenganKondisi() {
      Nasabah R = new Nasabah();
      ObjectInputStream in = null; // pointer ke F1
      ObjectOutputStream out = null; // pointer ke F2
      try {
        // 1. buka file untuk dibaca    
        in = new ObjectInputStream(new FileInputStream("D:\\Nasabah.dat")); // F1
        out = new ObjectOutputStream(new FileOutputStream("D:\\"+ "NasabahCopy.dat"));
        
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
	
	public static void main(String[] args) {
    	// Nasabah  R= new Nasabah();
		MainFile B= new MainFile();
		String nf1, nf2;
		nf1 = "D:\\Nasabah.dat";
		nf2 = "D:\\NasabahCopy.dat";
				
		B.SaveToFile(); // tulis ke file
//		B.ViewFile();  // baca isi file
		B.SalinArsipDenganKondisi();
//		B.ViewFile2();
		B.ViewFileX(nf1);
		B.ViewFileX(nf2);
    }
}