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

public class MainFile {
        Scanner sc = new Scanner(System.in);
        
       public void SaveToFile(){ // menulis ke file
            Nasabah R= new Nasabah();
            int Saldoku=0;
            String No="", Namaku="";
            
            System.out.println("========== SaveToFile ======");
            ObjectOutputStream out = null;
            try {  
             // 1. buka file untuk ditulis
              out=new ObjectOutputStream(new FileOutputStream
                  ("C:\\Documents\\Nasabah.dat"+ ""));// nama direktori+file

              BufferedReader brInput= new BufferedReader
                        (new InputStreamReader(System.in)); 
              for (int i=0;i<3;i++){
                try {
                    System.out.println("No Rekening : ");
                    No=brInput.readLine();
                } catch (IOException e){
                    e.printStackTrace();  
                }    
                try {
                    System.out.println("Nama :");
                    Namaku=brInput.readLine();
                  } catch (IOException e){
                    e.printStackTrace();  
                  } 
               try {
                     System.out.println("Saldo : ");
                     Saldoku=sc.nextInt();
                     R=new Nasabah(No,Namaku,Saldoku);    
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

    public void ViewFile(){
        Nasabah R = new Nasabah();
        int total=0;
        System.out.println("========== ViewFile ======");     
        ObjectInputStream in = null;
        try {
         // 1. buka file untuk dibaca   
           in=new ObjectInputStream(new FileInputStream
                          ("C:\\@MYDATA\\Nasabah.dat"));
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
         
    public static void main(String[] args) {
        Nasabah  R= new Nasabah();
        MainFile B= new MainFile();
              
        B.SaveToFile(); // tulis ke file
        B.ViewFile();  // baca isi file
    }  
}