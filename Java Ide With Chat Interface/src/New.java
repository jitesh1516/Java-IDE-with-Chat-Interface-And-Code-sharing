import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class New extends JFrame implements ActionListener
{       
	public JFrame newFrame,newFrame1;
	public JTextField jtf,jtf1;
	public JButton okay,cancel,okay1;
	public JLabel classLabel,classLabel1;
	public JCheckBox mainCheck;
	public int width,width1;
	public int height,height1;
	public MyEditor me;
	public static int count=0;
        public String className2 = "";

        New()//default constructor for chat class
        {}

	New(MyEditor me) throws Exception
	{ 
                count++;
		this.me = me;
		MyEditor.lookAndFeel();

		newFrame = new JFrame();
		newFrame.setLayout(null);
		Toolkit t = newFrame.getToolkit();
                Dimension screenSize = 	t.getScreenSize();
		width = screenSize.width;
		height = screenSize.height;
		newFrame.setBounds(width/3,height/3,260,145);
		classLabel = new JLabel("Class/File Name");
		classLabel.setBounds(10,10,85,25);
		newFrame.add(classLabel);
		jtf = new JTextField();
		jtf.setBounds(100,10,140,30);
		newFrame.add(jtf);
		mainCheck = new JCheckBox("Include main");
		mainCheck.setBounds(10,50,85,30);
		mainCheck.addActionListener(this);
		newFrame.add(mainCheck);
		okay = new JButton("okay");
		okay.setBounds(100,50,60,30);
		newFrame.add(okay);
		okay.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setBounds(165,50,65,30);
		newFrame.add(cancel);
             System.out.println("Constructor chla gya..");
		
	if(count==1)
         {      
            me.jta3 = new JTextArea(50,50); // display mess text area
            //me.jsp3 = new JScrollPane(me.jta3);//
            me.jsp3 = new JScrollPane(me.jta3,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            me.jsp3.setViewportView(me.jta3);
            me.jta3.scrollRectToVisible(me.jta3.getVisibleRect());
             
           me.jta3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
  
           me.jsp3.setBounds(width-595,0,width-950,height-305);
           me.jta3.setEditable(false);// this line change
           me.jf.add(me.jsp3);
           me.jta3.setFont(new Font("serif",Font.PLAIN,20));
 
           me.jta4 = new JTextArea(50,50); // write message text area
           me.jsp4 = new JScrollPane(me.jta4);
           me.jsp4.setBounds(width-595,height-300,
           width-950,height-520);
           me.jf.add(me.jsp4);
           me.jta4.setFont(new Font("serif",Font.PLAIN,20));

           me.jta5 = new JTextArea(50,50); //connexted user show
           me.jsp5 = new JScrollPane(me.jta5);
           me.jsp5.setBounds(width-175,0,width-1200,height-305);
           me.jta5.setEditable(false);
           me.jf.add(me.jsp5);
           me.jta5.setFont(new Font("serif",Font.PLAIN,20));
           me.jta3.setText("hello1 chal to rha hai");

           me.b1 = new JButton("send_message");
           me.b1.setBounds(width-175,height-300,width-1200,30);
           me.b1.addActionListener(me);
           me.b1.setForeground(Color.BLUE);
           me.b1.setBackground(Color.red);
           me.jf.add(me.b1);
           
           me.b2 = new JButton("Share Program");
           me.b2.setBounds(width-175,height-260,width-1200,30);
           me.b2.addActionListener(me);
           me.b2.setForeground(Color.BLUE);
           me.b2.setBackground(Color.red);
           me.jf.add(me.b2);
           
           me.b2.setVisible(false);
 
          MyClient3 mc3 = new MyClient3(me);
           mc3.clientChat(me);
          
       }
          newFrame.setVisible(true);
           me.jta5.setText("\n"+me.s4);
         
             
}


    public void actionPerformed(ActionEvent e)
	{       
            
		if(e.getSource()==okay && !jtf.getText().equals(""))
		{
		if(count==1)
                  {
		    me.jta = new JTextArea(50,50);  //me.jta.setBackground(me.choose);
                    me.jsp = new JScrollPane(me.jta);
		    me.jsp.setBounds(0,0,width-600,height-305);//15,85
		    me.jf.add(me.jsp);
		    me.jta.setFont(new Font("serif",Font.PLAIN,20));
              
                    me.jta2 = new JTextArea(50,50); 
                    me.jsp2 = new JScrollPane(me.jta2);
		    me.jsp2.setBounds(0,height-300,width-600,height-500);//15,85
		    me.jf.add(me.jsp2);
                    me.jta2.setEditable(false);
		    me.jta2.setFont(new Font("serif",Font.PLAIN,20));
                 }

                  if(me.jta.getText()!="")
                  {
                     className2 = me.jta.getText().trim();
                  }

                   if(mainCheck.isSelected())
                   {   
                        me.jta.setText("");
                        me.jta2.setText("");
			me.jta.setText("public class "+jtf.getText()+"\n"+"{"+"\n"+"public static void main(String args[])"+"\n"+"{"+"\n"+""+"\n"+"}"+"\n"+"}");
		}
                  else{
                        me.jta.setText("");
                        me.jta2.setText("");
                        me.jta.setText("public class "+jtf.getText()+"\n"+"{"+"\n"+"}");
		        }
             newFrame.dispose();
	}
	else if(e.getSource()==cancel)
		newFrame.dispose();
		
	}
}