import java.io.*;
import java.net.*;
class MyClient1
{
 public Socket s;
 public DataInputStream din;
 static public DataOutputStream dout;
 public MyEditor me;

MyClient1(MyEditor me)
{
 try
 {
  this.me=me;
  s= new Socket("13.127.175.55",8085);
 
  din = new DataInputStream(s.getInputStream());
  dout = new DataOutputStream(s.getOutputStream());

  MyThread t1=new MyThread(din,me);
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
     str=me.jta.getText();
     dout.writeUTF("\n"+str);
     dout.flush();
    }
    catch(Exception e)
    {System.out.println(e);}
 }

}


class MyThread extends Thread
{
 static DataInputStream din;
 String s1;
 MyEditor me;

 MyThread( DataInputStream din,MyEditor me)
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
      me.jta.setText(s1);
    }
   }
  catch(IOException e)
  {e.printStackTrace();}
}

}

