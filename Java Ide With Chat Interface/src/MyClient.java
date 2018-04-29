import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.*;
import javax.*;
import java.awt.event.*;

class MyClient 
{

Socket s;
public DataInputStream din;
public DataOutputStream dout;
static DataOutputStream dout2;
MyEditor me;
String s1;

public MyClient1 mc11;
public MyClient2 mc22;

MyClient()
{}

MyClient(MyEditor me)
{
 this.me=me;
   try
    {
      me.jta3.setText("YOU ARE NOW CONNECTED WITH SERVER");
      s = new Socket("13.127.175.55",8080);
     

      din = new DataInputStream(s.getInputStream());
      dout = new DataOutputStream(s.getOutputStream());
      
      
      MyThread0 t1=new MyThread0(s,din,me);
      t1.start();
      MyClient.set(dout,me);
 
   }
   catch(Exception e)
   {System.out.println(e);}
}

public static void set(DataOutputStream dout,MyEditor me)
{
    dout2=dout;
     me.mc1 = new MyClient1(me);
     me.mc2 = new MyClient2(me);
}

public void write(String s1,String s4)
{ 
  try
  { 
    
    String str =s1;
    s1="\n"+s4+": "+s1;
    if(s1==s4+": ")
    {
     JOptionPane.showMessageDialog(me.jf,"Plz Enter Message."   ,"Alert",JOptionPane.WARNING_MESSAGE);
    }
    dout2.writeUTF(s1);
    dout2.flush();
    s1="";
  }catch(Exception e)
  {System.out.println(e);}
}


}





class MyThread0 extends Thread
{
Socket s;
DataInputStream din;
String s1="";
MyEditor me;

MyThread0( Socket s, DataInputStream din,MyEditor me)
{
 this.s=s;
 this.din=din;
 this.me=me;
}

public void run()
{
try
 {
 String s2="";
  while(true)
  {
   s1=din.readUTF();
   s2= s2+s1;
   me.jta3.setText(s2);
   
  }
 }
 catch(IOException e)
 {e.printStackTrace();}
}
}




/*
import java.io.*;
import java.net.*;
class MyClient
{
Socket s;
DataInputStream din;
DataOutputStream dout;

MyClient(this)
{
 try
 {
  s= new Socket("13.127.175.55",8080);
 // s= new Socket("localhost",10);
  System.out.println(s);
  din = new DataInputStream(s.getInputStream());
  dout = new DataOutputStream(s.getOutputStream());
  MyThread t1=new MyThread(s,din);
  t1.start();
  clientChat();
  
 }
 catch(Exception e)
 {System.out.println(e);}
}
public void clientChat() throws IOException
{
  
  String str;
  BufferedReader br = new BufferedReader( new  InputStreamReader       (System.in)); 
 while(true)
 {
  str=br.readLine();
  dout.writeUTF(str);
  dout.flush();
 }

}
public static void main(String args[])
{
 new MyClient();
}
}


class MyThread extends Thread
{
Socket s;
DataInputStream din;
String s1;
MyThread( Socket s, DataInputStream din)
{
 this.s=s;
 this.din=din;
}
public void run()
{
try
 {
  while(true)
  {
   s1=din.readUTF();
   System.out.println(s1);
  }
 }
 catch(IOException e)
 {e.printStackTrace();}
}
}

*/