import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.logging.*;
public class App  {
    public App(){
        frame();
    }
    public void frame(){
        long start=System.currentTimeMillis();
        JFrame container=new JFrame("Calculate Prime Number"); 
        JLabel label1, label2, label3;
        /**************N*************/
        label1=new JLabel("Enter N : ");  
        label1.setBounds(30,20, 100,30);  
        
        final JTextField input1=new JTextField();
        input1.setBounds(30,50, 180,20);
        
        /**************Buffer Size*************/
        label2=new JLabel("Buffer Size : ");  
        label2.setBounds(30,70, 100,30); 
        
        final JTextField input2=new JTextField();  
        input2.setBounds(30,100, 180,20);
        
        /**************File Name*************/
        label3=new JLabel("Enter The File Name : ");  
        label3.setBounds(30,125, 180,30); 

        final JTextField input3=new JTextField();  
        input3.setBounds(30,150, 180,20);
        
        /*****Button******/
        JButton b=new JButton("Start Producer");  
        b.setBounds(30,200,150,20);
        b.addActionListener(new ActionListener() {
            int bigNum=0,primesCnt=0;
            public void actionPerformed(ActionEvent event){
                int n=Integer.valueOf(input1.getText());
                int bufferSize=Integer.valueOf(input2.getText());
                String fileName=String.valueOf(input3.getText());
                Buffer buf; Consumer C; Producer P;
                try {
                    buf = new Buffer(n,bufferSize);
                    P = new Producer(buf);
                    C = new Consumer(buf,fileName);
                    P.start();
                    C.start();
                    P.join();
                    C.join();
                    bigNum=buf.getBigestPrime();
                    primesCnt=buf.getPrimesCnt();
                } catch (IOException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end=System.currentTimeMillis();

                JFrame container2=new JFrame(); 
                JLabel label4, label5, label6;
                label4=new JLabel("The largest prime Number : "+bigNum);  
                label4.setBounds(30,20, 1000,30);  

                label5=new JLabel("# of elements (prime number) generated : "+primesCnt);  
                label5.setBounds(30,70, 1000,30); 

                label6=new JLabel("Time "+(end-start));  
                label6.setBounds(30,100, 500,30); 

                container2.add(label4); container2.add(label5); container2.add(label6);
                container2.setSize(400,300); 
                container2.setLayout(null);  
                container2.setVisible(true);

    
            }
            
        });
        /******Setup The Data Form******/
        container.add(input1); container.add(input2); container.add(input3);
        container.add(label1); container.add(label2); container.add(label3);
        container.setSize(400,300); container.add(b); 
        container.setLayout(null);  
        container.setVisible(true);
        /******************************************************/
        
        
    }
    public static void main(String[] args) throws Exception {
        new App();

    }
}
