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
		Nasabah R= new Nasabah();
		float Saldoku=0;
		String No="", Namaku="";
		
		System.out.println("========== SaveToFile ======");
		ObjectOutputStream out = null;
		try {  
			// 1. buka file untuk ditulis
			out=new ObjectOutputStream(new FileOutputStream
				(nf));// nama direktori+file
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
					System.out.println("Saldo : ");
					Saldoku=sc.nextFloat();
					R = new Nasabah(No,Namaku,Saldoku);    
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
	
	void SalinArsip(String nf1, String nf2) {
	  Nasabah R = new Nasabah();
	  ObjectInputStream in = null; // pointer ke F1
	  ObjectOutputStream out = null; // pointer ke F2
      try {
        // 1. buka file untuk dibaca    
        in = new ObjectInputStream(new FileInputStream(nf1)); // F1
        out = new ObjectOutputStream(new FileOutputStream(nf2));
        
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
	
    void SalinArsipDenganKondisi(String nf1, String nf2) {
      Nasabah R = new Nasabah();
      ObjectInputStream in = null; // pointer ke F1
      ObjectOutputStream out = null; // pointer ke F2
      try {
        // 1. buka file untuk dibaca    
        in = new ObjectInputStream(new FileInputStream(nf1)); // F1
        out = new ObjectOutputStream(new FileOutputStream(nf2));
        
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
		MainFile2 B= new MainFile2();
		String nf1, nf2,nf3;
		nf1 = "D:\\Nasabah.dat";
		nf2 = "D:\\NasabahCopy.dat";
		nf3 = "D:\\Nasabah2.dat";
		
		B.SaveToFile(nf1); // tulis ke file
		B.SalinArsip(nf1, nf3);
		B.SalinArsipDenganKondisi(nf1, nf2);
		B.ViewFileX(nf1);
		B.ViewFileX(nf2);
    }
}