package quiz.epita.software;

import java.awt.*;
import java.awt.event.*; 
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import quiz.epita.database.H2database;
public class StudentShow extends WindowAdapter implements ActionListener
{
    JTable t2;
	Button b1=new Button("Search");
	Button b2=new Button("Return");
	Frame f=new Frame("Show the Students' Info");
    JScrollPane scr;
	DefaultTableModel tableModel=new DefaultTableModel();
	String[] titles={"PrivateID","First Name","Last Name","Sex","Nationality","Difficulty"};        
	String a1,a2,a3,a4,a5,a6;
	public StudentShow()
	{
        tableModel.setColumnIdentifiers(titles);
		t2=new JTable(tableModel);
		scr=new JScrollPane(t2);
		scr.setBounds(30,110,340,180);
	 	f.setBackground(Color.LIGHT_GRAY);
	    f.setBounds(400,200,400,400);
	    f.setLayout(null);   
	    b1.setBounds(90,350,60,20);
	    b2.setBounds(190,350,60,20);
	    f.addWindowListener(this);
	    b1.addActionListener(this);
	    b2.addActionListener(this);   
	    f.add(b1);
	    f.add(b2);
	    f.add(scr);
	    f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
    	if(e.getSource()==b2)
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
    			String s="select * from STUDENT_INFO";
    			ResultSet rs=st.executeQuery(s);
    			while(rs.next())
    			{
    				a1=rs.getString(1);
    				a2=rs.getString(2);
    				a3=rs.getString(3);
    				a4=rs.getString(4);
    				a5=rs.getString(5);
    				a6=rs.getString(6);

    				Vector <String> rowData=new Vector<String>();
    				rowData.add(a1);
    				rowData.add(a2);
    				rowData.add(a3);
    				rowData.add(a4);
    				rowData.add(a5);
    				rowData.add(a6);

    				tableModel.addRow(rowData);	
    			}	
    			
    			/*String t="select MAX(credit) from RESULT_INFO";
    			ResultSet rs1=st.executeQuery(t);
    			while(rs1.next())
    			{
    				a7=rs1.getString(2);
    				Vector <String> rowData=new Vector<String>();
    				rowData.add(a7);
    				tableModel.addRow(rowData);	
    				
    			}
    			*/ //Try to add some Filter Function but failed :(
    		}
    		catch(Exception e1)
    		{
    	    	System.out.println("Database Error!");
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
		new StudentShow();
	}
}