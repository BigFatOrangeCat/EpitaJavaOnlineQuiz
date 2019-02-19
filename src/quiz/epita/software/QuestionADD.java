package quiz.epita.software;

import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import quiz.epita.database.H2database;
public class QuestionADD extends WindowAdapter implements ActionListener
{
	Frame f=new Frame("Add Questions into the Quiz Database");
	Label l1=new Label("Question:");
	JTextArea t1=new JTextArea();
	Label l8=new Label("ID:");
	TextField t8=new TextField();
	Label l2=new Label("Answer A:");
	TextField t2=new TextField();
	Label l3=new Label("Answer B:");
	TextField t3=new TextField();
	Label l4=new Label("Answer C:");
	TextField t4=new TextField();
	Label l5=new Label("Answer D:");
	TextField t5=new TextField();
	Label l6=new Label("Difficulty:");
	TextField t6=new TextField();
	Label l7=new Label("Correct Answer:");
	TextField t7=new TextField();
	Button b1=new Button("Confirm");
	Button b2=new Button("Cancel");
	public QuestionADD()
	{
		t1.setLineWrap(true);
	    f.setLayout(null);   
	    f.setBackground(Color.LIGHT_GRAY);
	    f.setBounds(400,200,400,380);
	    l1.setBounds(20,50,70,20);
	    t1.setBounds(90,50,270,40);
	    
	    l8.setBounds(20,100,70,20);
	    t8.setBounds(90,100,270,20);
	    
	    l2.setBounds(20,150,70,20);
	    t2.setBounds(90,150,270,20);
	    
	    l3.setBounds(20,180,70,20);
	    t3.setBounds(90,180,270,20);
	    
	    l4.setBounds(20,210,70,20);
	    t4.setBounds(90,210,270,20);
	    
	    l5.setBounds(20,240,70,20);
	    t5.setBounds(90,240,270,20);
	    l6.setBounds(20,270,70,20);
	    t6.setBounds(90,270,70,20);
	    l7.setBounds(20,300,100,20);
	    t7.setBounds(120,300,70,20);
	    
	    b1.setBounds(80,330,80,20);
	    b2.setBounds(180,330,80,20);
	    f.addWindowListener(this);
	    b1.addActionListener(this);
	    b2.addActionListener(this);
	    f.add(l1);
	    f.add(t1);
	    f.add(l8);
	    f.add(t8);
	    f.add(l2);
	    f.add(t2);
	    f.add(l3);
	    f.add(t3);
	    f.add(l4);
	    f.add(t4);
	    f.add(l5);
	    f.add(t5);
	    f.add(l6);
	    f.add(t6);
	    f.add(l7);
	    f.add(t7);
	    f.add(b1);
	    f.add(b2);
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
    	    	String s="insert into QUESTION_INFO(id,difficulty,content,choiceA,choiceB,choiceC,choiceD,correct) values('"+t8.getText().trim()+"','"+t6.getText().trim()+"','"+t1.getText().trim()+"','"+t2.getText().trim()+"','"+t3.getText().trim()+"','"+t4.getText().trim()+"','"+t5.getText().trim()+"','"+t7.getText().trim()+"')";
    	    	System.out.println(s);
    	    	int sucess=st.executeUpdate(s);
    	    	System.out.println(sucess);
    	    	
    	    	if(sucess>0)  
    	    	{
    	    		JOptionPane.showMessageDialog(null, "Adding Successfully!");
    	    		t1.setText("");
    	    		t2.setText("");	
    	    		t3.setText("");
    	    		t4.setText("");	
    	    		t5.setText("");
    	    		t6.setText("");	
    	    	}
    	    	else
    	    	{
    	    		JOptionPane.showMessageDialog(null,"Adding Failed.");
    	    		t1.setText("");
    	    		t2.setText("");	
    	    		t3.setText("");
    	    		t4.setText("");	
    	    		t5.setText("");
    	    		t6.setText("");	
    	    	}	
    	    }
    	    catch(Exception e1)
    	    {
    	    	JOptionPane.showMessageDialog(null,"Database Error or the ID is occupied in the Database!");
    	    }
    	}
	}
	public void windowClosing(WindowEvent e)
	{
		((Frame) e.getComponent()).dispose();
	}
	
	public static void main(String args[])
	{
		new QuestionADD();
	}
}
