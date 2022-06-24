
package hmw1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hmw1 {

        public static void main(String[] args) {
         
        JFrame frame=new JFrame();
        frame.setTitle("Hamming Error-Correcting Code Simulator");
        frame.setSize(888, 488);
       
        JLabel label=new JLabel("Please enter the 4, 8 or 16 bits of data");
        label.setBounds(20, 20, 250, 40);
        
        JTextField txt1=new JTextField();
        txt1.setBounds(20, 60, 200, 20);
         
        JButton bt1=new JButton("Enter ");
        bt1.setBounds(20, 95, 75, 20);
         
        frame.add(label);
        frame.add(txt1);
        frame.add(bt1);
      
        int length=txt1.getText().length();
        
        bt1.addActionListener(new ActionListener(){//1.butona tiklaninca
         @Override
         public void actionPerformed(ActionEvent e) { 
    
          if(txt1.getText().length()<0 ){//data girilmezse
            JOptionPane.showMessageDialog(null, "Please enter the data!");
      }
           else if(txt1.getText().length()!=4&&txt1.getText().length()!=8 && txt1.getText().length()!=16){//4 ,8 ya da 16 bit data girilmezse
             JOptionPane.showMessageDialog(null, "Please enter valid bits of data!");
             }
     
            else{
             String str = txt1.getText();
             int M = str.length();
             int k = 1;
     
            while (Math.pow(2, k) < (M + k + 1)) {//check bit sayisini buluyoruz
              k++;
        }
       
         
        String arr[]= new String [k+str.length()+1]; //tum data bit pozisyonlarına göre
        int sonuc = 0;
         
         if(k==3){//girilen veri 4 bit
           arr[3]=txt1.getText().substring(k, k+1);//girilen datayı check bitleri de hesaba katılarak bit pozisyonlarına göre diziye yerleştirme
           arr[5]=txt1.getText().substring(k-1, k);
           arr[6]=txt1.getText().substring(k-2, k-1);
           arr[7]=txt1.getText().substring(k-3, k-2);
           //check bitleriin yerlesecegi indeksler su anlik null
            
            int a;
            int flag=0;
            for(int i=0;i<arr.length;i++){//xorlama islemiyle check bitleri bulma
                if("1".equals(arr[i])){//datada 1 olan bit pozisyonlarini xorlayarak check bitlerinin decimal değerini buluyor
                    a=i;
                    flag++;
                    if(flag>=1){
                      sonuc = sonuc^a;  
                    }
                    else{
                        sonuc=a;//decimal
                    }
                   
                }
                   
            }
         
         final int Sonuc=sonuc;
         String  parity= Integer.toBinaryString(sonuc);//binary string
         final String parity2=parity;//ilk check bitlerini parity2de tutyorum
         
           if(parity.length()!=3){//3 bitten azsa baslara 0 koyma islemi
               if(parity.length()==1){
                arr[1]=parity.substring(parity.length()-1, parity.length());
                arr[2]="0";
                arr[4]="0";
               }
               
               else {
                arr[1]=parity.substring(parity.length()-1, parity.length());
                arr[2]=parity.substring(parity.length()-2, parity.length()-1);
                arr[4]="0";
               }
           }
           
           else{//tam olarak 3 bitse
            arr[1]=parity.substring(parity.length()-1, parity.length());
            arr[2]=parity.substring(parity.length()-2, parity.length()-1);
            arr[4]=parity.substring(parity.length()-3, parity.length()-2);
           }
           
           
        String[][] data = {
            {"C4","C2","C1"},
            {arr[4],arr[2],arr[1] }
            
        };
             String columns[] =  {
            "C3", "C2", "C1"
        };
             
        JTable table = new JTable(data, columns);
        table.setBounds(20, 100, 250, 35);
         
         String[][] data2 = {
            {"7","6","5","4","3","2","1"},
            {arr[7],arr[6],arr[5] ,arr[4],arr[3],arr[2],arr[1]},
            {"D4","D3","D2","C4","D1","C2","C1"}
             };
         
          String column[] =  {
            "D4","D3","D2","C4","D1","C2","C1"
        };
             
          JTable table2 = new JTable(data2, column);
          table2.setBounds(20, 170, 250, 45);
          
          JFrame frame2=new JFrame();
          
          JLabel label2=new JLabel("Check Bits");
          label2.setBounds(20,70, 200, 37);
          frame2.add(label2);
          
          JLabel label3=new JLabel("Hamming Word");
          label3.setBounds(20,140, 200, 37);
          frame2.add(label3);
          
          JLabel label4=new JLabel("Enter the bit position to alter original data");
          label4.setBounds(20,250, 300, 37);
          frame2.add(label4);
          
          JTextField txt2=new JTextField();
          txt2.setBounds(20, 280, 75, 20);
          frame2.add(txt2);
          
          JButton bt2=new JButton("Enter ");
          bt2.setBounds(20, 305, 75, 20);
          frame2.add(bt2);
          
          bt2.addActionListener(new ActionListener(){//2.butona tiklanınca

         @Override
         public void actionPerformed(ActionEvent e) { 
            String str2=txt2.getText(); 
            int position=Integer.parseInt(str2.trim());//girilen string inte çevrildi
            if("0".equals(arr[position])){//error yaratma
                arr[position]="1";//verilen indisteki bit 0sa 1 yap
            }
            else{
                arr[position]="0";//1se 0 yap
            }
          
            arr[1]=null;//check bitleri sifirlama
            arr[2]=null;
            arr[4]=null;
            arr[0]=null;
            
            int a;
            int sonuc2 = 0;
            int flag=0;
            for(int i=0;i<arr.length;i++){//yeni check bitleri icin xorlama islemi
                if("1".equals(arr[i])){//data 1 ise
                    a=i;
                    flag++;
                    if(flag>1){//datada 1 den fazla 1 varsa
                      sonuc2 = sonuc2^a;  
                    }
                    else{
                        sonuc2=a;
                    }
                   
                }
               
            }
            
            String  parity= Integer.toBinaryString(sonuc2);//yeni check bitlerini binary stringe çevirme
         
           if(parity.length()!=3){//yeni check bitleri 3 bitten azsa önündeki bitlere 0 koyma
               if(parity.length()==1){
                arr[1]=parity.substring(parity.length()-1, parity.length());
                arr[2]="0";
                arr[4]="0";  
               }
               else {
                arr[1]=parity.substring(parity.length()-1, parity.length());
                arr[2]=parity.substring(parity.length()-2, parity.length()-1);
                arr[4]="0";
               }
           }
           else{
             arr[1]=parity.substring(parity.length()-1, parity.length());
             arr[2]=parity.substring(parity.length()-2, parity.length()-1);
             arr[4]=parity.substring(parity.length()-3, parity.length()-2);
           }
           
           JLabel label5=new JLabel("New Check Bits ");
           label5.setBounds(20,70, 200, 37);
          
           String[][] data = {
            {"C4","C2","C1"},
            {arr[4],arr[2],arr[1] }
        };
             String columns[] =  {
            "C3", "C2", "C1"
        };
   
            int syndrome =Sonuc ^sonuc2;//hata olmadan bulununa check bitleri ve hatadan sonra bulunan check bitlerinin xorlanmasıyla sendrom bulunur
            
            String[][] data2 = {
            {"7","6","5","4","3","2","1"},
            {arr[7],arr[6],arr[5] ,arr[4],arr[3],arr[2],arr[1]},
            {"D4","D3","D2","C4","D1","C2","C1"}
        };
            
            String column[] =  {
            "D4","D3","D2","C4","D1","C2","C1"
        };
             
          JTable table4 = new JTable(data2, column);
          table4.setBounds(20, 170, 250, 45);
          
          JLabel label6=new JLabel("New Hamming Word ");
          label6.setBounds(20,140, 300, 37); 
             
          String  Syndrome= Integer.toBinaryString(syndrome);
             
          JLabel label7=new JLabel("Syndrome: "+ parity2+" ^ "+parity+" = "+Syndrome);
          label7.setBounds(20,300, 300, 37); 
          //sendrom hatanın kacıncı bit pozisyonunda oldugunu gösterir 
          JLabel label8 = new JLabel("The bit position of error is "+"("+Syndrome+")2 = "+"("+syndrome+")10");
          label8.setBounds(20,320, 300, 37); 
      
          JTable table3 = new JTable(data, columns);
          table3.setBounds(20, 100, 250, 35);
 
           JFrame frame3=new JFrame();
           frame3.add(table3);
           frame3.add(table4);
           frame3.setSize(888, 488);
           frame3.add(label5);
           frame3.add(label7);
           frame3.add(label8);
           frame3.add(label6);
           frame3.setLayout(null);
           frame3.setVisible(true);
               
        }

 });
            
           frame2.add(table);
           frame2.add(table2);
           frame2.setSize(888, 488);
           frame2.setLayout(null);
           frame2.setVisible(true);

         }
       
           int length=txt1.getText().length();
         
         if(k==4){//8 bitlik veri için
             
            arr[3]=txt1.getText().substring(length-1, length);
            arr[5]=txt1.getText().substring(length-2, length-1);
            arr[6]=txt1.getText().substring(length-3, length-2);
            arr[7]=txt1.getText().substring(length-4, length-3);
            arr[9]=txt1.getText().substring(length-5, length-4);
            arr[10]=txt1.getText().substring(length-6, length-5);
            arr[11]=txt1.getText().substring(length-7, length-6);
            arr[12]=txt1.getText().substring(length-8, length-7);
   
            int a;
            int flag=0;
            for(int i=0;i<arr.length;i++){
                if("1".equals(arr[i])){
                  
                    a=i;
                    flag++;
                    if(flag>1){
                      sonuc = sonuc^a;  
                    }
                    else{
                        sonuc=a;
                    }
                   
                }
                  
            }
  
           final int Sonuc=sonuc;
         
          String  parity= Integer.toBinaryString(sonuc);
          String parity2=parity;
         
         if(parity.length()!=4){
             if(parity.length()==1){
               arr[1]=parity.substring(parity.length()-1, parity.length());
               arr[2]="0";
               arr[4]="0";
               arr[8]="0";
             }
            
             else if(parity.length()==2){
              arr[1]=parity.substring(parity.length()-1, parity.length());
              arr[2]=parity.substring(parity.length()-2, parity.length()-1);
              arr[4]="0";
              arr[8]="0";
             }
             
            else{
             arr[1]=parity.substring(parity.length()-1, parity.length());
             arr[2]=parity.substring(parity.length()-2, parity.length()-1);
             arr[4]=parity.substring(parity.length()-3, parity.length()-2);
             arr[8]="0";
            }}
             
         else{ //binarye çevirilen bit sayısı tamsa 
           arr[1]=parity.substring(parity.length()-1, parity.length());
           arr[2]=parity.substring(parity.length()-2, parity.length()-1);
           arr[4]=parity.substring(parity.length()-3, parity.length()-2);
           arr[8]=parity.substring(parity.length()-4, parity.length()-3);  
         }
         
        String[][] data = {
            {"C8","C4","C2","C1"},
            {arr[8],arr[4],arr[2],arr[1] }
        };
         String columns[] =  {
           "C4","C3", "C2", "C1"
        };
             
         JTable table = new JTable(data, columns);
         table.setBounds(20, 100, 250, 35);

         String[][] data2 = {
            {"12","11","10","9","8","7","6","5","4","3","2","1"},
            {arr[12],arr[11],arr[10] ,arr[9],arr[8],arr[7],arr[6],arr[5],arr[4],arr[3],arr[2],arr[1]},
            {"D8","D7","D6","D5","C8","D4","D3","D2","C4","D1","C2","C1"}
        };
            
             String column[] =  {
            "D8","D7","D6","D5","C8","D4","D3","D2","C4","D1","C2","C1"
        };

          JTable table2 = new JTable(data2, column);
          table2.setBounds(20, 170, 250, 45);
   
          JFrame frame2=new JFrame();
          
          JLabel label2=new JLabel("Check Bits");
          label2.setBounds(20,70, 200, 37);
          frame2.add(label2);
          
          JLabel label3=new JLabel("Hamming Word");
          label3.setBounds(20,140, 200, 37);
          frame2.add(label3);
          
           frame2.add(table);
           frame2.add(table2);
           frame2.setSize(888, 488);
           frame2.setLayout(null);
           frame2.setVisible(true);
             
           
          JLabel label4=new JLabel("Enter the bit position to alter original data ");
          label4.setBounds(20,250, 300, 37);
          frame2.add(label4);
          
          JTextField txt2=new JTextField();
          txt2.setBounds(20, 280, 75, 20);
          frame2.add(txt2);
          
          JButton bt2=new JButton("Enter ");
          bt2.setBounds(20, 305, 75, 20);
          frame2.add(bt2);
            
          bt2.addActionListener(new ActionListener(){//2.butona tiklanınca

         @Override
         public void actionPerformed(ActionEvent e) { 
            String str2=txt2.getText(); 
            int position=Integer.parseInt(str2.trim());
            if("0".equals(arr[position])){
                arr[position]="1";
            }
            else{
                arr[position]="0";
            }
          
            arr[1]=null;
            arr[2]=null;
            arr[4]=null;
            arr[0]=null;
            arr[8]=null;
  
            int a;
            int sonuc2 = 0;
            int flag=0;
            for(int i=0;i<arr.length;i++){
                if("1".equals(arr[i])){
                    a=i;
                    flag++;
                    if(flag>1){
                      sonuc2 = sonuc2^a;  
                    }
                    else{
                        sonuc2=a;
                    }
                   
                }
                  
            }
            String parity=(Integer.toBinaryString(sonuc2));
           if(parity.length()!=4){
             if(parity.length()==1){
                arr[1]=parity.substring(parity.length()-1, parity.length());
                arr[2]="0";
                arr[4]="0";
                arr[8]="0";
             }
            
            else if(parity.length()==2){
              arr[1]=parity.substring(parity.length()-1, parity.length());
              arr[2]=parity.substring(parity.length()-2, parity.length()-1);
              arr[4]="0";
              arr[8]="0";
             }
             
            else{
              arr[1]=parity.substring(parity.length()-1, parity.length());
              arr[2]=parity.substring(parity.length()-2, parity.length()-1);
              arr[4]=parity.substring(parity.length()-3, parity.length()-2);
              arr[8]="0";
            }}
             
         else{ //binarye çevirilen bit sayısı tamsa 
           arr[1]=parity.substring(parity.length()-1, parity.length());
           arr[2]=parity.substring(parity.length()-2, parity.length()-1);
           arr[4]=parity.substring(parity.length()-3, parity.length()-2);
           arr[8]=parity.substring(parity.length()-4, parity.length()-3); 
         }
 
          JLabel label5=new JLabel("New Check Bits ");
          label5.setBounds(20,70, 200, 37);
          
           String[][] data = {
            {"C8","C4","C2","C1"},
            {arr[8],arr[4],arr[2],arr[1] }   
        };
             String columns[] =  {
           "C4","C3", "C2", "C1"
        };

             int syndrome =Sonuc ^sonuc2;

             String[][] data2 = {
            {"12","11","10","9","8","7","6","5","4","3","2","1"},
            {arr[12],arr[11],arr[10],arr[9],arr[8],arr[7],arr[6],arr[5] ,arr[4],arr[3],arr[2],arr[1]},
            {"D8","D7","D6","D5","C8","D4","D3","D2","C4","D1","C2","C1"}
        };
            
             String column[] =  {
            "D8","D7","D6","D5","C8","D4","D3","D2","C4","D1","C2","C1"
        };
             
  
          JTable table4 = new JTable(data2, column);
          table4.setBounds(20, 170, 250, 45);
          
          JLabel label6=new JLabel("New Hamming Word ");
          label6.setBounds(20,140, 300, 37); 
             
          String  Syndrome= Integer.toBinaryString(syndrome);
             
          JLabel label7=new JLabel("Syndrome: "+ parity2+" ^ "+parity+" = "+Syndrome);
          label7.setBounds(20,300, 300, 37); 
            
          JLabel label8 = new JLabel("The bit position of error is  "+"("+Syndrome+")2 = "+"("+syndrome+")10");
          label8.setBounds(20,320, 300, 37); 
             
          JTable table3 = new JTable(data, columns);
          table3.setBounds(20, 100, 250, 35);


           JFrame frame3=new JFrame();
           frame3.add(table3);
           frame3.add(table4);
           frame3.setSize(888, 488);
           frame3.add(label5);
           frame3.add(label7);
           frame3.add(label8);
           frame3.add(label6);
           frame3.setLayout(null);
           frame3.setVisible(true);
        }
   });
   
 }  
         
   
         if(k==5){//16 bit veri için
             
            arr[3]=txt1.getText().substring(length-1, length);
            arr[5]=txt1.getText().substring(length-2, length-1);
            arr[6]=txt1.getText().substring(length-3, length-2);
            arr[7]=txt1.getText().substring(length-4, length-3);
            arr[9]=txt1.getText().substring(length-5, length-4);
            arr[10]=txt1.getText().substring(length-6, length-5);
            arr[11]=txt1.getText().substring(length-7, length-6);
            arr[12]=txt1.getText().substring(length-8, length-7);
            arr[13]=txt1.getText().substring(length-9, length-8);
            arr[14]=txt1.getText().substring(length-10, length-9);
            arr[15]=txt1.getText().substring(length-11, length-10);
            arr[17]=txt1.getText().substring(length-12, length-11);
            arr[18]=txt1.getText().substring(length-13, length-12);
            arr[19]=txt1.getText().substring(length-14, length-13);
            arr[20]=txt1.getText().substring(length-15, length-14);
            arr[21]=txt1.getText().substring(length-16, length-15);
            
            int a;
            int flag=0;
            for(int i=0;i<arr.length;i++){
                if("1".equals(arr[i])){
                    a=i;
                    flag++;
                    if(flag>1){
                      sonuc = sonuc^a;  
                    }
                    else{
                        sonuc=a;
                    }
                   
                }

            } 
            final int Sonuc=sonuc;

         String  parity= Integer.toBinaryString(sonuc);
         String parity2=parity;
         
         
         if(parity.length()!=5){
             if(parity.length()==1){
              arr[1]=parity.substring(parity.length()-1, parity.length());
              arr[2]="0";
              arr[4]="0";
              arr[8]="0";
              arr[16]="0";
             }
            
            else if(parity.length()==2){
             arr[1]=parity.substring(parity.length()-1, parity.length());
             arr[2]=parity.substring(parity.length()-2, parity.length()-1);
             arr[4]="0";
             arr[8]="0";
             arr[16]="0";
             }
             
            else if(parity.length()==3){
                 
              arr[1]=parity.substring(parity.length()-1, parity.length());
              arr[2]=parity.substring(parity.length()-2, parity.length()-1);
              arr[4]=parity.substring(parity.length()-3, parity.length()-2);
              arr[8]="0";
              arr[16]="0";
         
            }
             
              else {   
               arr[1]=parity.substring(parity.length()-1, parity.length());
               arr[2]=parity.substring(parity.length()-2, parity.length()-1);
               arr[4]=parity.substring(parity.length()-3, parity.length()-2);
               arr[8]=parity.substring(parity.length()-4, parity.length()-3);
               arr[16]="0";
            }}
         
         else{
            arr[1]=parity.substring(parity.length()-1, parity.length());  
           arr[2]=parity.substring(parity.length()-2, parity.length()-1);         
           arr[4]=parity.substring(parity.length()-3, parity.length()-2);
           arr[8]=parity.substring(parity.length()-4, parity.length()-3);
           arr[16]=parity.substring(parity.length()-5, parity.length()-4);
         }
 
         

        String[][] data = {
            {"C16","C8","C4","C2","C1"},
            {arr[16],arr[8],arr[4],arr[2],arr[1] }
        };
             String columns[] =  {
           "C5","C4","C3", "C2", "C1"
        };
             
        JTable table = new JTable(data, columns);
          table.setBounds(20, 100, 250, 35);

         String[][] data2 = {
            {"21","20","19","18","17","16","15","14","13","12","11","10","9","8","7","6","5","4","3","2","1"},
            {arr[21],arr[20],arr[19] ,arr[18],arr[17],arr[16],arr[15],arr[14],arr[13],arr[12],arr[11],arr[10],arr[9],arr[8],arr[7],arr[6],arr[5],arr[4],arr[3],arr[2],arr[1]},
            {"D16","D15","D14","D13","D12","C16","D11","D10","D9","D8","D7","D6","D5","C8","D4","D3","D2","C4","D1","C2","C1"}
        };
             String column[] =  {
           "D16","D15","D14","D13","D12","C16","D11","D10","D9", "D8","D7","D6","D5","C8","D4","D3","D2","C4","D1","C2","C1"
        };
             
          JTable table2 = new JTable(data2, column);
          table2.setBounds(20,170,600, 45);

           JFrame frame2=new JFrame();
          
           JLabel label2=new JLabel("Check Bits");
           label2.setBounds(20,70, 200, 37);
           frame2.add(label2);
          
           JLabel label3=new JLabel("Hamming Word");
           label3.setBounds(20,140, 200, 37);
           frame2.add(label3); 
        
           frame2.add(table);
           frame2.add(table2);
           frame2.setSize(888, 488);
           frame2.setLayout(null);
           frame2.setVisible(true);
             
           JLabel label4=new JLabel("Enter the bit position to alter original data");
           label4.setBounds(20,250, 300, 37);
           frame2.add(label4);
          
           JTextField txt2=new JTextField();
           txt2.setBounds(20, 280, 75, 20);
           frame2.add(txt2);
          
           JButton bt2=new JButton("Enter ");
           bt2.setBounds(20, 305, 75, 20);
           frame2.add(bt2);
            
            bt2.addActionListener(new ActionListener(){//2.butona tiklanınca

         @Override
         public void actionPerformed(ActionEvent e) { 
            String str2=txt2.getText(); 
            int position=Integer.parseInt(str2.trim());
            if("0".equals(arr[position])){
                arr[position]="1";
            }
            else{
                arr[position]="0";
            }
          
            arr[1]=null;
            arr[2]=null;
            arr[4]=null;
            arr[0]=null;
            arr[8]=null;
            arr[16]=null;
                
            int a;
            int sonuc2 = 0;
            int flag=0;
            for(int i=0;i<arr.length;i++){
                if("1".equals(arr[i])){
                    
                    a=i;
                    flag++;
                    if(flag>1){
                      sonuc2 = sonuc2^a;  
                    }
                    else{
                        sonuc2=a;
                    }
                   
                }
  
            }
            String parity=(Integer.toBinaryString(sonuc2));
 
         if(parity.length()!=5){
             if(parity.length()==1){
               arr[1]=parity.substring(parity.length()-1, parity.length());  
               arr[2]="0";
               arr[4]="0";
               arr[8]="0";
               arr[16]="0";
             }
            
            else if(parity.length()==2){
              arr[1]=parity.substring(parity.length()-1, parity.length());
              arr[2]=parity.substring(parity.length()-2, parity.length()-1);          
              arr[4]="0";
              arr[8]="0";
              arr[16]="0";
             }
             
            else if(parity.length()==3){ 
              arr[1]=parity.substring(parity.length()-1, parity.length());
              arr[2]=parity.substring(parity.length()-2, parity.length()-1);
              arr[4]=parity.substring(parity.length()-3, parity.length()-2);
              arr[8]="0";
              arr[16]="0";
            }
             
              else {
                 
               arr[1]=parity.substring(parity.length()-1, parity.length());
               arr[2]=parity.substring(parity.length()-2, parity.length()-1);
               arr[4]=parity.substring(parity.length()-3, parity.length()-2);
               arr[8]=parity.substring(parity.length()-4, parity.length()-3);
               arr[16]="0";
            }}
         else{
           arr[1]=parity.substring(parity.length()-1, parity.length());
           arr[2]=parity.substring(parity.length()-2, parity.length()-1);
           arr[4]=parity.substring(parity.length()-3, parity.length()-2);
           arr[8]=parity.substring(parity.length()-4, parity.length()-3);
           arr[16]=parity.substring(parity.length()-5, parity.length()-4);
         }
  
          JLabel label5=new JLabel("New Check Bits ");
          label5.setBounds(20,70, 200, 37);
          
            String[][] data = {
            {"C16","C8","C4","C2","C1"},
            {arr[16],arr[8],arr[4],arr[2],arr[1] }
        };
             String columns[] =  {
           "C5","C4","C3", "C2", "C1"
        };
      
             int syndrome =Sonuc ^sonuc2;

             String[][] data2 = {
            {"21","20","19","18","17","16","15","14","13","12","11","10","9","8","7","6","5","4","3","2","1"},
            {arr[21],arr[20],arr[19] ,arr[18],arr[17],arr[16],arr[15],arr[14],arr[13],arr[12],arr[11],arr[10],arr[9],arr[8],arr[7],arr[6],arr[5],arr[4],arr[3],arr[2],arr[1]},
            {"D16","D15","D14","D13","D12","C16","D11","D10","D9","D8","D7","D6","D5","C8","D4","D3","D2","C4","D1","C2","C1"}
        };
             String column[] =  {
           "D16","D15","D14","D13","D12","C16","D11","D10","D9", "D8","D7","D6","D5","C8","D4","D3","D2","C4","D1","C2","C1"
        };
             
          JTable table4 = new JTable(data2, column);
          table4.setBounds(20, 170, 600, 45);
          
          JLabel label6=new JLabel("New Hamming Word ");
          label6.setBounds(20,140, 300, 37); 
             
          String  Syndrome= Integer.toBinaryString(syndrome);
             
          JLabel label7=new JLabel("Syndrome: "+ parity2+" ^ "+parity+" = "+Syndrome);
          label7.setBounds(20,300, 300, 37); 
            
          JLabel label8 = new JLabel("The bit position of error is "+"("+Syndrome+")2 = "+"("+syndrome+")10");
          label8.setBounds(20,320, 300, 37); 

          JTable table3 = new JTable(data, columns);
          table3.setBounds(20, 100, 250, 35);
  
           JFrame frame3=new JFrame();
           frame3.add(table3);
           frame3.add(table4);
           frame3.setSize(888, 488);
           frame3.add(label5);
           frame3.add(label7);
           frame3.add(label8);
           frame3.add(label6);
           frame3.setLayout(null);
           frame3.setVisible(true);
               }//16 bit ifi
         
            });
      }
 
   }//dogru veri girilince
     
   }
           
 }); 
        frame.add(label);
        frame.add(txt1);
        frame.add(bt1);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
}
