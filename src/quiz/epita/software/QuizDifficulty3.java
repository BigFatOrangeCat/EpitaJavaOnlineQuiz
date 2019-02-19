package quiz.epita.software;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.*;

import quiz.epita.database.H2database;
public class QuizDifficulty3 extends WindowAdapter implements ActionListener
{	
	String a,b,c,d,g;
	int z=1,sum=0;

	JRadioButton j1,j2,j3,j4;
	Frame f=new Frame("Epita Java Online Quiz");
	Label l1=new Label("Good Luck");
	Label l3=new Label("Quiz Content");
	Label l2=new Label("Please Attention");
	Label l4=new Label("Time left or Mark");
	Date d1=new Date();
	String s = "Date£º";
	Label l5=new Label(s+d1);
	JTextArea jta=new JTextArea("1.Be Patient\n2.Be more Patient\n3.Be most Patient",15,15);
	JTextArea jta1=new JTextArea();
	Button b1=new Button("Show the Mark");
	Button b2=new Button("Pause the time");
	Button b3=new Button("Start the Quiz");
	Button b4=new Button("Next");


	public QuizDifficulty3()
	{
		j1=new JRadioButton("A");
		j2=new JRadioButton("B");
		j3=new JRadioButton("C");
		j4=new JRadioButton("D");
		ButtonGroup bg=new ButtonGroup(); 
		bg.add(j1); 
		bg.add(j2);
		bg.add(j3); 
		bg.add(j4);
		JPanel jpr=new JPanel(); 
	    jpr.add(j1); 
	 	jpr.add(j2);
	    jpr.add(j3); 
	 	jpr.add(j4);
	    f.setBounds(400,200,590,440);
	    f.setLayout(null);
        l1.setFont(new Font("Times New Roman",Font.BOLD,24));
        f.setBackground(Color.LIGHT_GRAY);
        l1.setForeground(Color.blue);
	    l1.setBounds(100,35,300,40);
	    l3.setBounds(370,80,60,20);
	    l2.setBounds(80,80,60,20);
	    l4.setBounds(40,340,120,20);
	    l5.setBounds(10,420,200,20);
	    jta.setBounds(5,100,210,200);
	    jta1.setBounds(220,100,360,290);
	    jpr.setBounds(220,400,190,30);
	    b1.setBounds(115,310,80,25);
	    b2.setBounds(100,395,80,25);
	    b3.setBounds(15,310,80,25);
	    b4.setBounds(460,400,80,25);
	    f.addWindowListener(this);
	    b1.addActionListener(this);
	    b2.addActionListener(this);
	    b3.addActionListener(this);
	    b4.addActionListener(this);
            f.add(l1);
            f.add(l2);
            f.add(l3);
            f.add(l4);
            f.add(l5);
    		f.add(jta);
    	    f.add(jta1);
    	    f.add(jpr);
	        f.add(b1);
	        f.add(b2);
	        f.add(b3);
	        f.add(b4);
	        f.setVisible(true);

	}
	public void actionPerformed(ActionEvent e)
	
	{
		if (e.getSource() == b3)
		{
			new H2database();
			Statement st;
			try
			{
				st=H2database.conn.createStatement();
				{
				String s="select * from QUESTION_INFO where difficulty='3'";
				ResultSet rs=st.executeQuery(s);
				if(rs.next())
				{
					a=rs.getString(3);
    				b=rs.getString(4);
    				c=rs.getString(5);
    				d=rs.getString(6);
    				g=rs.getString(7);
					jta1.setText(a+"\n"+"\n"+b+"\n"+c+"\n"+d+"\n"+g);
				}	
				}
			}
				catch(Exception  e3)
				{
					JOptionPane.showMessageDialog(null,"Database Error!");
				} 
		}
		if (e.getSource() == b4)		
		{
			z+=1;
			new H2database();
			Statement st;
			try
			{
				st=H2database.conn.createStatement();
				{
				String s="select * from QUESTION_INFO where id='"+z+"' and difficulty='3'";
				ResultSet rs=st.executeQuery(s);
				/*String num = rs.getString(2);
				String maxID="select MAX(id) from QUESTION_INFO;";
				int inum=Integer.parseInt(num);
				int imaxID=Integer.parseInt(maxID);
				if(inum>=imaxID)
				{
				JOptionPane.showMessageDialog(null,"You have finished all the Question! Please view your credit.");
				}else{
				*/
				if(rs.next())
				{
					a=rs.getString(3);
    				b=rs.getString(4);
    				c=rs.getString(5);
    				d=rs.getString(6);
    				g=rs.getString(7);
					jta1.setText(a+"\n"+"\n"+b+"\n"+c+"\n"+d+"\n"+g);
				}
				String daan="";
	    		if(j1.isSelected()) 
	    		daan=j1.getText(); 
	    		else if(j2.isSelected())
	    		daan=j2.getText(); 
	    		else if(j3.isSelected())
		    		daan=j3.getText(); 
	    		else if(j4.isSelected())
		    		daan=j4.getText(); 
	    		new H2database();
				try
				{
					String s1="select * from QUESTION_INFO  where correct='"+daan+"'";
					ResultSet rs1=st.executeQuery(s1);
					if(rs1.next()) 
					{
						sum=sum+1;
						String s2="insert into RESULT_INFO(answer,credit) values('"+daan+"','"+sum+"')";
		    	    	st.executeUpdate(s2);
					}	
					else
	     			{
						String s2="insert into RESULT_INFO(answer) values('"+daan+"')";
		    	    	st.executeUpdate(s2);
	     			}
				}
				catch(Exception  e3)
				{
					JOptionPane.showMessageDialog(null,"Database Error!");
				}
				}
				
			}
			
			catch(Exception  e3)
			{
				JOptionPane.showMessageDialog(null,"Searching from Database Error!");
			}
			
	    }
		if (e.getSource() == b1)
		{
			new H2database();
			Statement st;
			try
			{
				st=H2database.conn.createStatement();
				{
				String s="select * from RESULT_INFO ";
				ResultSet rs=st.executeQuery(s);
				if(rs.next())
				{
					JOptionPane.showMessageDialog(null,"Your mark is "+sum+"");
				}	
				}
			}
				catch(Exception  e3)
				{
					JOptionPane.showMessageDialog(null,"Database Error!");
				} 
		}
    }	
	public void windowClosing(WindowEvent e)
	{
		((Frame) e.getComponent()).dispose();
		System.exit(0);
	}
        public static void main(String args[])
	{
		new QuizDifficulty3();
	}
}