import java.io.*;
import java.net.*;
class MyClient2
{
 public Socket s;
 public DataInputStream din;
 static public DataOutputStream dout;
 public MyEditor me;

MyClient2(MyEditor me)
{
 try
 {
  this.me=me;
  s= new Socket("13.127.175.55",8090);
 
  din = new DataInputStream(s.getInputStream());
  dout = new DataOutputStream(s.getOutputStream());

  MyThread1 t1=new MyThread1(din,me);
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
   str=me.jta2.getText();
   dout.writeUTF("\n"+str);
   dout.flush();
   }
   catch(Exception e)
   {System.out.println(e);}
 }

}


class MyThread1 extends Thread
{
 static DataInputStream din;
 String s1;
 MyEditor me;

 MyThread1( DataInputStream din,MyEditor me)
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
      me.jta2.setText(s1);
    }
   }
  catch(IOException e)
  {e.printStackTrace();}
}

}

  