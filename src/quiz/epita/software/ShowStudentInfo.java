package quiz.epita.software;
import java.awt.*;
import java.awt.event.*; 
import java.sql.Statement;

import javax.swing.*;

import quiz.epita.database.H2database;
public class ShowStudentInfo extends WindowAdapter implements ActionListener
{
	JRadioButton j1,j2;
	
	Button b1=new Button("Begin the Quiz");
	Button b2=new Button("Return");
	Frame f=new Frame("Student INFO");
	Label l1=new Label("Student Info");
	
	Label l2=new Label("PrivateID:");
    TextField t1=new TextField();
    
	Label l3=new Label("First Name:");	
    TextField t2=new TextField();
    
	Label l6=new Label("Last Name:"); 
    TextField t3=new TextField();
    
	Label l4=new Label("Sex:"); 
	
    TextField t4=new TextField();
	Label l8=new Label("Nationality:");
	
	Label l5=new Label("Quiz Difficulty:");	
	String items[]={"Please Select: ","1","2","3"};
	JComboBox<?> jcb=new JComboBox<Object>(items);
	
	@SuppressWarnings("unused")
	private JComboBox<?> jpr;
	public ShowStudentInfo()
	{
		j1=new JRadioButton("M");
		j2=new JRadioButton("F"); 
		ButtonGroup bg=new ButtonGroup(); 
		bg.add(j1); 
		bg.add(j2);
		JPanel jpr=new JPanel(); 
	    jpr.add(j1); 
	 	jpr.add(j2);
	 	 
	 	f.setBackground(Color.LIGHT_GRAY);
	    f.setBounds(400,200,420,400);
	    f.setLayout(null);
        l1.setFont(new Font("Times New Roman",Font.BOLD,30));
        l1.setForeground(Color.BLACK);
	    l1.setBounds(130,60,270,50);
	    
	    l2.setBounds(60,150,80,20);
	    t1.setBounds(140,150,170,20);
	    l3.setBounds(60,180,80,20);
	    t2.setBounds(140,180,170,20);
	    l6.setBounds(60,210,80,20);
	    t3.setBounds(140,210,170,20);
	    l4.setBounds(60,245,60,20);
	    jpr.setBounds(100,240,120,25);

	    
	    l8.setBounds(60,274,80,20);
	    t4.setBounds(140,274,230,20);
	    l5.setBounds(60,300,90,20);
	    jcb.setBounds(150,300,120,20);

	    b1.setBounds(90,350,80,20);
	    b2.setBounds(190,350,80,20);
	    
	    f.addWindowListener(this);
	    jcb.addActionListener(this);

	    b1.addActionListener(this);
	    b2.addActionListener(this);   
        f.add(l1);

            f.add(l2);
            f.add(t1);
            f.add(l3);
            f.add(t2);
            f.add(l6);
            f.add(t3);
            f.add(l4);
            f.add(l8);
            f.add(t4);
            f.add(l5);
            f.add(jcb);
	        f.add(b1);
	        f.add(b2);
	        f.add(jpr);
	        
	    f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b2)
    	{
    		new LoginWindow();
    		f.setVisible(false);
    	}
    	if(e.getSource()==b1)
    	{
    		String sex="";
    		if(j1.isSelected()) 
    		sex=j1.getText(); 
    		else if(j2.isSelected())
    		sex=j2.getText(); 
    		new H2database();
    		Statement st;
    		try
    	    { 
    			st=H2database.conn.createStatement();
    			
    	    	String s="insert into STUDENT_INFO(PrivateID,firstname,lastname,sex,nationality,difficulty) values('"+t1.getText().trim()+"','"+t2.getText().trim()+"','"+t3.getText().trim()+"','"+sex+"','"+t4.getText().trim()+"','"+jcb.getSelectedItem()+"')";
    	    	int sucess=st.executeUpdate(s);
    	    	System.out.println(sucess);
    	    	if(sucess>0 & jcb.getSelectedItem().equals("1"))  
    	    	{
    	    		JOptionPane.showMessageDialog(null, "Student Information Adding Successfully!");
					new QuizDifficulty1();
					f.setVisible(false);
    	    	}
    	    	else if(sucess>0 & jcb.getSelectedItem().equals("2"))
    	    	{
    	    		JOptionPane.showMessageDialog(null, "Student Information Adding Successfully!");
					new QuizDifficulty2();
					f.setVisible(false);
    	    	}
    	    	else if(sucess>0 & jcb.getSelectedItem().equals("3"))
    	    	{
    	    		JOptionPane.showMessageDialog(null, "Student Information Adding Successfully!");
					new QuizDifficulty3();
					f.setVisible(false);
    	    	}
    	    	else
    	    	{
    	    		JOptionPane.showMessageDialog(null,"Failed! Please retype in the Student Infomation!");
    	    		t1.setText("");
    	    		t2.setText("");	
    	    		t3.setText("");
    	    		t4.setText("");	
    	    	}	
    	    }
    	    catch(Exception e1)
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
		new ShowStudentInfo();
	}
}