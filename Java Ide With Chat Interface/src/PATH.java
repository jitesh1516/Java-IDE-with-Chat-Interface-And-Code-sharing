import java.lang.*;
public class PATH{
 public String path()
 {
   int c=0;
   String s2="";
   String s3="";
   char ch1[] = {'b','i','n'};

   String s1= System.getenv("path");
   String[] str1=s1.split(";");
   //for(int i=0;i<str1.length;i++)
   //{System.out.println(str1[i]);}
   
    for(int i=0;i<str1.length;i++)
    {
     s2=str1[i];
     char ch[] = s2.toCharArray();
     for(int j=0;j<ch.length-2;j++)
     {
      if(ch[j]=='b'&& ch[j+1]=='i' && ch[j+2]=='n')
      {
        c=i;
        


      }
     }
    }
  
  s3=str1[c];
 //s3 = s3.replace("\\", "\\\\");
 return s3;  
 }
 
}
