import java.awt.*;
import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class MyEditor extends JFrame implements ActionListener , Runnable
{       
        public static MyEditor my;
        public MyClient1 mc1;
        public MyClient2 mc2;
        public static String path;
        public String filepath;
        public int set=1; 
        public MyClient myclient;
        public Color choose;
        public DataOutputStream dout;
	public JFrame jf;
	public JButton jbrun,jbcompile,b1,b2;
        public static JButton okay1;
        public static JTextField jtf1;
        public static JFrame newFrame1;
	public JTextField jtf;
	public JTextArea jta,jta1,jta2,jta3,jta4,jta5;
	public JScrollPane jsp,jsp1,jsp2,jsp3,jsp4,jsp5;
	public JLabel jl;
	public String str = "";
	public String fname = "";
	public String className = "";
	public String result = "";
	public String result1 = "";
        public String s4;
        public String s3=" ";
	public Runtime r;
	public int width;
	public int height;
	public JMenuBar menuBar;
	public JMenu menu,subMenu;
	public JMenuItem menuItem;
	public static New n;
	public boolean compile = false;
	
	MyEditor()
	{
		jf = new JFrame("JAVA IDE WITH CHAT");
		jf.setLayout(null);
		Toolkit t=jf.getToolkit();
		Dimension ScreenSize=t.getScreenSize();
		width=ScreenSize.width;
		height=ScreenSize.height;
		jf.setBounds(0,0,width,height);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame();
                jf.setBackground(Color.WHITE);
		jf.setVisible(true);
             
	}

	public static void lookAndFeel()
	{
		try
		{
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
	}
	     
             public void mainFrame() 
	     {    
                menuBar	= new JMenuBar();
		menuBar.setBackground(Color.CYAN);
                menuBar.setOpaque(true);
		//File Menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		menuItem = new JMenuItem("New",KeyEvent.VK_N);
		menu.add(menuItem);
		KeyStroke  i=KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i);
		menu.addSeparator();
		menuItem.addActionListener(this);
		
		menuItem = new JMenuItem("Open",KeyEvent.VK_O);
		menu.add(menuItem);
		KeyStroke  i1=KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i1);
		menu.addSeparator();
		
		menuItem.addActionListener(this);
		menuItem = new JMenuItem("Rename File",KeyEvent.VK_R);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Save",KeyEvent.VK_S);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		KeyStroke  i2=KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i2);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Save as");
		menu.add(menuItem);
		menuItem.addActionListener(this);
                menuItem.setActionCommand("Save as");
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit",KeyEvent.VK_E);
		menu.add(menuItem);
		KeyStroke  i3=KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i3);
		menuItem.addActionListener(this);
		
		//Edit Menu
		menu =  new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Undo",KeyEvent.VK_U);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Redo",KeyEvent.VK_R);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Cut",KeyEvent.VK_U);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Copy",KeyEvent.VK_C);
		menu.add(menuItem);
		menuItem.addActionListener(this);
                menuItem.setActionCommand("Copy");
		menu.addSeparator();
		
		menuItem = new JMenuItem("Paste",KeyEvent.VK_P);
		menu.add(menuItem);
		menuItem.addActionListener(this);
                menuItem.setActionCommand("Paste");
		
		//search Menu
		menu = new JMenu("Search");
		menu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Find",KeyEvent.VK_F);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Find Next",KeyEvent.VK_N);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Find previous",KeyEvent.VK_P);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Replace",KeyEvent.VK_R);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Mark All",KeyEvent.VK_M);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Unmark all",KeyEvent.VK_U);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		
		//Compile Menu
		menu = new JMenu("Compile");
		menu.setMnemonic(KeyEvent.VK_C);
		menuBar.add(menu);
		menuItem = new JMenuItem("Compile code",KeyEvent.VK_C);
		menu.add(menuItem);
		KeyStroke  i4=KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i4);
		menuItem.addActionListener(this);
		
		//Run Menu
		menu = new JMenu("Run");
		menu.setMnemonic(KeyEvent.VK_R);
		menuBar.add(menu);
		menuItem = new JMenuItem("Run Code",KeyEvent.VK_R);	
		menu.add(menuItem);
		KeyStroke  i5=KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i5);
		menuItem.addActionListener(this);
		 
                //Runtime menu
		r = Runtime.getRuntime();
		
		jf.setJMenuBar(menuBar);
               
               
                
                menu = new JMenu("Bg_color");
		menu.setMnemonic(KeyEvent.VK_B);
		menuBar.add(menu);

		menuItem = new JMenuItem("Background Color",KeyEvent.VK_G);
		menu.add(menuItem);
		KeyStroke  i7=KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i7);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("choose");


               
		menuItem = new JMenuItem("Upper_left_bg",KeyEvent.VK_W);
		menu.add(menuItem);
		KeyStroke  i8=KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i8);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Upper_left");
                
                menuItem = new JMenuItem("Upper_Middle_bg",KeyEvent.VK_M);
		menu.add(menuItem);
		KeyStroke  i9=KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i9);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Upper_Middle");

                
		menuItem = new JMenuItem("Lower_left_bg",KeyEvent.VK_R);
		menu.add(menuItem);
		KeyStroke  i10=KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i10);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Lower_left");

                
                menuItem = new JMenuItem("Lower_Middle_bg",KeyEvent.VK_M);
		menu.add(menuItem);
		KeyStroke  i11=KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i11);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Lower_Middle");
        
                menuItem = new JMenuItem("Upper_Right_bg",KeyEvent.VK_M);
		menu.add(menuItem);
		KeyStroke  i12=KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i12);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Upper_Right_bg");


                
                menu = new JMenu("Text_Color");
                menu.setMnemonic(KeyEvent.VK_B);
		menuBar.add(menu);

                menuItem = new JMenuItem("Text_Color_upper_left",KeyEvent.VK_C);
		menu.add(menuItem);
		KeyStroke  i13=KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i13);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Text_color1");

                
		menuItem = new JMenuItem("Text_Color_Lower_left",KeyEvent.VK_K);
		menu.add(menuItem);
		KeyStroke  i14=KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i14);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Text_color2");


                menuItem = new JMenuItem("Upper_Middle",KeyEvent.VK_H);
		menu.add(menuItem);
		KeyStroke  i15=KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i15);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Text_color3");

                menuItem = new JMenuItem("Lower_Middle",KeyEvent.VK_L);
		menu.add(menuItem);
		KeyStroke  i16=KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i16);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Text_color4");

                menuItem = new JMenuItem("Upper_Right",KeyEvent.VK_R);
		menu.add(menuItem);
		KeyStroke  i17=KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i17);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Text_color5");
                
               
                menu = new JMenu("Enable Share code");
		menu.setMnemonic(KeyEvent.VK_T);
		menuBar.add(menu);
                menuItem = new JMenuItem("Program Share",KeyEvent.VK_T);
		menu.add(menuItem);
		KeyStroke  i35=KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i35);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Program_share");
              

 
                
                menu = new JMenu("Chat Enable");
		menu.setMnemonic(KeyEvent.VK_D);
		menuBar.add(menu);
             menuItem = new JMenuItem("Chat Enable",KeyEvent.VK_D);
		menu.add(menuItem);
		KeyStroke  i31=KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i31);
		menu.addSeparator();
		menuItem.addActionListener(this);
		menuItem.setActionCommand("Chatt1");
}


           public void run()
          {
            new MyClient(this);
          }


	
	public void actionPerformed(ActionEvent e)
	{       
                if(e.getSource()==okay1 && !jtf1.getText().equals(""))
	        {   
                  s4 = jtf1.getText();
                  newFrame1.dispose();
                }
 
                if(e.getSource()==b1)
                {
                    s3=jta4.getText();
                    MyClient mc = new MyClient();
                    mc.write(s3,s4);
                  
                   jta4.setText("");
                }

                if(e.getActionCommand().equals("Copy"))
		{
		  str=jta.getSelectedText();  
		}

                if(e.getActionCommand().equals("Paste"))
		{
		  //pos1=jta.getCaretPosition();  
                      jta.insert(str,jta.getCaretPosition());  
		}
           
                if(e.getActionCommand().equals(" as"))
		{
               try{
                 JFileChooser fileChooser = new JFileChooser();

               if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                 File file = fileChooser.getSelectedFile();
                  filepath = file.getPath();
                  BufferedWriter br=new BufferedWriter(new FileWriter(filepath)); 
                 if(file.length()!= 0)
                  {
                   String str3 = jta1.getText();
                   br.write(str3);
                   br.flush();
                  }
                 
                }
             }catch(Exception e2)
             {System.out.println();}
                   //new FileChooser(this);
		  //new SaveAs(this);
	     }

                
		if(e.getActionCommand().equals("Open"))
		{
		  new FileChooser(this);
		}

                if ("Chatt1".equals(e.getActionCommand()))
                {  
                  Thread t1 = new Thread(this);
                  t1.start();
                  
                }
               
                if ("Program_share".equals(e.getActionCommand()))
                {  
                  b2.setVisible(true);
                }
  
                if(e.getSource()==b2)
                {
                  mc1.clientChat(this);
                  mc2.clientChat(this);
                }
              



               
                if ("choose".equals(e.getActionCommand()))
                {
                  Color choose = JColorChooser.showDialog(this, "Choose JButton Background",Color.BLACK);
                  jf.getContentPane().setBackground(choose);
 
                }
 

                if ("Upper_left".equals(e.getActionCommand())  )
                {
                   Color choose1 = JColorChooser.showDialog(this, "Choose JButton Background",Color.BLACK);
                   jta.setBackground(choose1);
                }

                if ("Upper_Middle".equals(e.getActionCommand())  )
                {
                   Color choose2 = JColorChooser.showDialog(this, "Choose JButton Background",Color.BLACK);
                   jta3.setBackground(choose2);
                }
                
                 if ("Lower_left".equals(e.getActionCommand())  )
                {
                   Color choose3 = JColorChooser.showDialog(this, "Choose JButton Background",Color.BLACK);
                   jta2.setBackground(choose3);
                }

                if ("Lower_Middle".equals(e.getActionCommand())  )
                {
                   Color choose4 = JColorChooser.showDialog(this, "Choose JButton Background",Color.BLACK);
                   jta4.setBackground(choose4);
                }

                if ("Upper_Right_bg".equals(e.getActionCommand())  )
                {
                   Color choose5 = JColorChooser.showDialog(this, "Choose JButton Background",Color.BLACK);
                   jta5.setBackground(choose5);
                }

                if ("Text_color1".equals(e.getActionCommand()))
                {
                   Color choose6 = JColorChooser.showDialog(this, "Choose JButton Background",Color.BLACK);
                   jta.setForeground(choose6);
                }
                if ("Text_color2".equals(e.getActionCommand()))
                {
                   Color choose7 = JColorChooser.showDialog(this, "Choose JButton Background",Color.BLACK);
                   jta2.setForeground(choose7);
                }
                if ("Text_color3".equals(e.getActionCommand()))
                {
                   Color choose8 = JColorChooser.showDialog(this, "Choose JButton Background",Color.BLACK);
                   jta3.setForeground(choose8);
                }
                if ("Text_color4".equals(e.getActionCommand()))
                {
                   Color choose8 = JColorChooser.showDialog(this, "Choose JButton Background",Color.BLACK);
                   jta4.setForeground(choose8);
                }
                if ("Text_color5".equals(e.getActionCommand()))
                {
                   Color choose9 = JColorChooser.showDialog(this, "Choose JButton Background",Color.BLACK);
                   jta5.setForeground(choose9);
                }

              
                if(e.getActionCommand().equals("New"))
		{
		  try
                    {
                       n = new New(this);
                    }catch(Exception e1){}
		}
		
		
              if(e.getActionCommand().equals("Save"))
		{  
                 	str = "";
			try
			{
				className = n.jtf.getText().trim();
                                fname = className+".java";
				str = jta.getText();
				FileWriter fw = new FileWriter(fname);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(str);
				pw.flush();
				
			}
			catch(Exception e2)
			{
	JOptionPane.showMessageDialog(jf,"Make class first ");
			}
                }





		//}
		
		if(e.getActionCommand().equals("Compile code"))
		{
			try
			{
				if(!str.equals(""))
				{//"C:\\Program Files\\Java\\jdk-9.0.1\\bin\\javac "
            // System.out.println("me"+path+"\\\\javac "+fname);
            //System.out.println("C:\\Program Files\\Java\\jdk-9.0.1\\bin\\javac "+fname);
Process error = r.exec(path+"\\javac "+fname);
BufferedReader err = new BufferedReader(new InputStreamReader(error.getErrorStream()));
					///cmd = new MyCmd();
					result = "";
					
					while(true)
					{
						String temp = err.readLine();
						if(temp!=null)
						{
							result+=temp;
							result+="\n";
						}
						else 
							break;
					}
					
					if(!result.equals(""))
					{
						jta2.setText(result);//cmd.cmdta==jta2
					}
					else
					{
						jta2.setText("Compilation Succesful");
						compile = true;
					}
				}
				else
						JOptionPane.showMessageDialog(jf,"Nothing to compile");
				}
			catch(Exception e3)
			{
				System.out.println("Compilation menu error : "+e3);
			}
		}
		
		if(e.getActionCommand().equals("Run Code"))
		{
			try
			{
			if(compile)
			{
         //"C:\\Program Files\\Java\\jdk-9.0.1\\bin\\java "
         //System.out.println("me"+path+"\\java "+className);
         //System.out.println(+className);
	Process run = r.exec(path+"\\java "+className);
BufferedReader error = new BufferedReader(new InputStreamReader(run.getErrorStream()));
  BufferedReader output = new BufferedReader(new InputStreamReader(run.getInputStream()));
				
				while(true)
				{
					String temp = output.readLine();
					
					if(temp!=null)
					{
						result+=temp;
						result+="\n";
					}
					else
						break;
				}
				
				while(true)
				{
					String temp = error.readLine();
					
					if(temp!=null)
					{
						result1+=temp;
						result1+="\n";
					}
					else
						break;
				}
  
				
				jta2.setText(result+result1);//new MyCmd(). in front paste
			}
			else
			{
				JOptionPane.showMessageDialog(jf,"First Compile the code Successfully");
			}
			
			}
			catch(Exception e4)
			{
				System.out.println(e4);
			}
		}
		
		if(e.getActionCommand().equals("Exit"))
		{
			jf.dispose();
		}
	}
	
	public static void main(String... s)
	{

	 my = new MyEditor();
 
        //JFrame newFrame1;
	JLabel classLabel1;
        int width1;
	int height1;
	
        newFrame1 = new JFrame();
        newFrame1.setLayout(null);
        newFrame1.setBackground(Color.CYAN);
	Toolkit t1 = newFrame1.getToolkit();
        Dimension screenSize1 = t1.getScreenSize();
	width1 = screenSize1.width;
	height1 = screenSize1.height;
	newFrame1.setBounds(width1/3,height1/3,330,145);
	classLabel1 = new JLabel("Enter Your Name");
	classLabel1.setBounds(10,10,105,25);
	newFrame1.add(classLabel1);

	jtf1 = new JTextField();
	jtf1.setBounds(130,10,140,30);
	newFrame1.add(jtf1);
	okay1 = new JButton("Submit");
	okay1.setBounds(130,50,75,30);
	newFrame1.add(okay1);
        okay1.addActionListener(my);
        
        newFrame1.setVisible(true);
      
   
      PATH p1 = new PATH();
      path = p1.path();
      
      }
}











