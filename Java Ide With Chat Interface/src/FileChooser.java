import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
public class FileChooser extends JFrame 
{
JFileChooser jfc;
MyEditor my;
public  FileChooser (MyEditor my) 
{ 
  super(" FileChooser "); 
  this.my=my;
 jfc =new JFileChooser("Desktop");//manish123
 int x=jfc.showOpenDialog(null);//showSaveDialog(null)
 if(x==JFileChooser.APPROVE_OPTION)
 {
  File f =jfc.getSelectedFile();
  String filepath = f.getPath();    
   try{ 
     BufferedReader br=new BufferedReader(new FileReader(filepath));    
     String s1="",s2="";                         
        while((s1=br.readLine())!=null)
        {    
          s2+=s1+"\n";    
        }    
     my.jta.setText(s2);    
     br.close();
    }
    catch(Exception ex)
   {ex.printStackTrace();}  
    
}
 
 if(x==JFileChooser.CANCEL_OPTION)
 {
 System.out.println("cancel");
 }

} 
  
}
    


/*File f=fc.getSelectedFile();    
        String filepath=f.getPath();    
        try{  
        BufferedReader br=new BufferedReader(new FileReader(filepath));    
        String s1="",s2="";                         
        while((s1=br.readLine())!=null){    
        s2+=s1+"\n";    
        }    
        ta.setText(s2);    
        br.close();

*/





