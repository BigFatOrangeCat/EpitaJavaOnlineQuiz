package quiz.epita.software;
import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import javax.swing.*;
import quiz.epita.database.H2database;

public class StudentDelete extends WindowAdapter implements ActionListener
{
	Frame f=new Frame("Delete the Student's Info");
	Label l1=new Label("Type in the Student's PrivateID");
    TextField t1=new TextField();
    
	Button b1=new Button("Confirm");
	Button b2=new Button("Return");
	
	public StudentDelete()
	{
	 	f.setBackground(Color.LIGHT_GRAY);
	    f.setBounds(400,200,400,400);
	    f.setSize(320,350);
	    f.setLayout(null);   //Set the Label Windows empty for easy 
	    l1.setBounds(20,120,180,20);
	    t1.setBounds(200,120,110,20);
	    b1.setBounds(60,240,100,20);
	    b2.setBounds(180,240,100,20);
	    f.addWindowListener(this);
	    b1.addActionListener(this);
	    b2.addActionListener(this);
        f.add(l1);
        f.add(t1);
        f.add(b1);
        f.add(b2);
	    f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
            if(e.getSource()== b2)
            {
            new TeacherMenu();
            f.setVisible(false);
            }
            
            if(e.getSource()==b1)
        	{
        		new H2database();
        		Statement st;
        		try
        		{
        			st=H2database.conn.createStatement();
        			String s="delete from STUDENT_INFO where privateID='"+t1.getText().trim()+"'";
        			int sucess=st.executeUpdate(s);
        			if(sucess>0)
        			{
        				JOptionPane.showMessageDialog(null, "Delete Successfully!");
        				t1.setText("");
        				
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(null,"Delete Failed.");
        				t1.setText("");		
        			}
        		}
        		catch(Exception e1)
        		{
        	    	JOptionPane.showMessageDialog(null,"Error occured!");
        	    }
        	    
        	}
         }
		
	public void windowClosing(WindowEvent e)
	{
		((Frame) e.getComponent()).dispose();
		//System.exit(0);
	}
        public static void main(String args[])
	{
		new StudentDelete();
	}
}