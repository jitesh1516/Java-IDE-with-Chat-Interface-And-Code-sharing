
import java.io.*;
import java.net.*;
class MyClient3
{
 public Socket s;
 public DataInputStream din;
 static public DataOutputStream dout;
 public MyEditor me;
 public static String s4;

MyClient3(MyEditor me)
{
 try
 {
  this.me=me;
  this.s4=me.s4;
  s= new Socket("13.127.175.55",8095);
 
  din = new DataInputStream(s.getInputStream());
  dout = new DataOutputStream(s.getOutputStream());

  MyThread3 t1=new MyThread3(din,me);
  t1.start();
 }
 catch(Exception e)
 {System.out.println(e);}
}

 public void clientChat(MyEditor me) 
 {
   try
   {
 
    String str;
    dout.writeUTF("\n"+s4);
    dout.flush();
   }
   catch(Exception e)
   {System.out.println(e);}
 }

}


class MyThread3 extends Thread
{
 static DataInputStream din;
 String s1;
 String s2="";
 MyEditor me;

 MyThread3( DataInputStream din,MyEditor me)
 {
  this.din=din;
  this.me=me;
 }

public void run()
{
  try
   {
    while(true)
    {
      s1=din.readUTF();
      s2=s2+s1;
      me.jta5.setText(s2);
    }
   }
  catch(IOException e)
  {e.printStackTrace();}
}

}

  