package quiz.epita.software;

import quiz.epita.database.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;
public class LoginWindow extends WindowAdapter implements ActionListener
{
	Frame f=new Frame("Epita Java Online Quiz");
	Label l1=new Label("Epita Java Online Quiz");
	Label l2=new Label("PrivateID£º");
    TextField t2=new TextField();
    Label l3=new Label("Name£º");
    TextField t3=new TextField();
    Label l4=new Label("Place£º");
	String items1[]={"Please select the Quiz Place","Kremlin-Bicetre","Villejuif"};
	JComboBox<?> jcb1 = new JComboBox<Object>(items1);

	String items[]={"Identity","Student","Teacher"};
	JComboBox<?> jcb = new JComboBox<Object>(items);

	Button b1=new Button("Login");
	Button b2=new Button("Reset");

	public LoginWindow()
	{
	    f.setBounds(400,200,410,350);
	    f.setLayout(null);
        l1.setFont(new Font("Times New Roman",Font.BOLD,18));
        l1.setForeground(Color.BLACK);
	    l1.setBounds(100,35,250,50);
	    f.setBackground(Color.LIGHT_GRAY);
	    l2.setBounds(60,130,60,20);
	    t2.setBounds(130,130,200,20);
	    l3.setBounds(60,160,60,20);
	    t3.setBounds(130,160,200,20);
	    b1.setBounds(60,250,60,20);
	    b2.setBounds(130,250,60,20);
	    jcb.setBounds(200,250,80,20);
	    jcb1.setBounds(130,190,200,20);
	    l4.setBounds(60,190,60,20);
	    f.addWindowListener(this);
	    b1.addActionListener(this);
	    b2.addActionListener(this);
	    jcb.addActionListener(this);
	    jcb1.addActionListener(this);
            f.add(l1);
            f.add(l2);
            f.add(t2);
            f.add(l3);
            f.add(t3);
	        f.add(b1);
	        f.add(b2);
	        f.add(jcb);
	        f.add(jcb1);
	        f.add(l4);
	    f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == b2)	
		{
			t2.setText("");	
			t3.setText("");
		}
		if (e.getSource() == b1)		
		{
			String zkzh = t2.getText();//PrivateID
		    String xm = t3.getText();  //Name
		    String dlsf =(String)jcb.getSelectedItem();
		    String ksdd =(String)jcb1.getSelectedItem();
			new H2database();
			Statement st;
			try
			{
				st=H2database.conn.createStatement();
				String s="select * from USER_INFO  where PRIVATEID='"+zkzh+"' and NAME='"+xm+"' and PLACE='"+ksdd+"' and IDENTITY='"+dlsf+"'";
				ResultSet rs=st.executeQuery(s);
				if(rs.next()) 
				{
					if(dlsf == "Student")
					{
					JOptionPane.showMessageDialog(null,"Student Login Successfully!");
					new ShowStudentInfo();
					f.setVisible(false);
					}
					else
					{
					this.setVisible(true);
					ImageIcon image = new ImageIcon("Feelsgoodman.jpg");
					JOptionPane.showMessageDialog(null,"Welcome Thomas!");
					JOptionPane.showMessageDialog(null,image);
					new TeacherMenu();
					f.setVisible(false);
					}
				}	
				else
     			{
     				JOptionPane.showMessageDialog(null,"Access Denied! Please entre the correct ID and Password!");
     				t2.setText("");	
     				t3.setText("");
     			}
			}
			catch(Exception  e3)
			{
				JOptionPane.showMessageDialog(null,"Database login error!");
			}
	    }
    }
	private void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosing(WindowEvent e)
	{
		((Frame) e.getComponent()).dispose();
		System.exit(0);
	}
        public static void main(String args[])
	{
		new LoginWindow();
	}
}