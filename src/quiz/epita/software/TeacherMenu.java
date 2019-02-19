package quiz.epita.software;
import javax.swing.*;

import java.awt.event.*;  
public class TeacherMenu extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -5561391586696519308L;
	JLabel imageLabel;
    JMenuBar myMenu = new JMenuBar();
    JMenu[] menus = {
        new JMenu("Data Create"),
        new JMenu("Data Search"),
        new JMenu("Data Delete"),
        new JMenu("Exit")};
    JMenuItem[] fileMenuItems = {
        new JMenuItem("Add new questions")
            };
    JMenuItem[] editMenuItems = {
        new JMenuItem("Show the Student Result")
	        };
    JMenuItem[] shanchu = {
        new JMenuItem("Delete the Student Information by ID")
            };
    JMenuItem[] tuichu = {
        new JMenuItem("Exit")
            };
    
	public TeacherMenu() {
		ImageIcon image=new ImageIcon("images/bgtp1.png");
	    imageLabel = new JLabel(image);
	    imageLabel.setBounds(0,0,360,350);
	    add(imageLabel);
		    setBounds(400,200,360,350);
			editMenuItems[0].addActionListener(this); //Register the Moniteur
	        fileMenuItems[0].addActionListener(this);
	        shanchu[0].addActionListener(this);
	        tuichu[0].addActionListener(new ActionListener()
	        {
	           public void actionPerformed(ActionEvent event)
	           {
	              System.exit(0);
	           }
	        });
	        setTitle("Quiz Manage System");
	
	        // Meun Option for Add
	        for (int i = 0; i < fileMenuItems.length; i++) {
	            menus[0].add(fileMenuItems[0]);
	        }
	        // Meun Option for Search
	        for (int i = 0; i < editMenuItems.length; i++) {
	            menus[1].add(editMenuItems[0]);
	        }
	        // Meun Option for Delete
	        for (int i = 0; i < shanchu.length; i++) {
	            menus[2].add(shanchu[i]);
	        }
	        // Meun Option for Exit
	        for (int i = 0; i < tuichu.length; i++) {
	            menus[3].add(tuichu[i]);
	        }
	        for (int i = 0; i < menus.length; i++) {
	            myMenu.add(menus[i]);
	        }
	        this.setJMenuBar(myMenu);
	        setSize(300,300);
	        setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==fileMenuItems[0]) new QuestionADD();
	    if(e.getSource()==shanchu[0]) new StudentDelete();
		if(e.getSource()==editMenuItems[0]) new StudentShow();
	}
		
	public static void main(String args[]) {
		TeacherMenu application = new TeacherMenu();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
}
