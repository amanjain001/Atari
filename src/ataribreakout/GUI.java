package ataribreakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI implements  ActionListener
{
	
	JButton b1=new JButton(" Play ");
	JButton b2=new JButton("About us");
	public static JFrame frame1= new JFrame(" Atari Breakout ");
	
	public static void main(String args[])
	{
		GUI g = new GUI();
		g.fun();
	}
	
          
          
    protected void fun()
    {
    	
     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
   
	
	b1.setBounds(190,150,100, 70);//x axis, y axis, width, height
	b2.setBounds(190,250,100, 70);

	
	frame1.add(b1);//adding button in JFrame  
     frame1.add(b2);

	b1.addActionListener(this);
	b2.addActionListener(this);
	
	
	GUI.frame1.setLocation(dim.width/4-GUI.frame1.getSize().width/4,50); // for center alignment         
	frame1.setSize(500,500);//500 width and 500 height  
	frame1.setLayout(null);//using no layout managers  
	frame1.setResizable(false);

	frame1.setVisible(true);//making the frame visible  
	
	
    
     frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	
	}
    
	public void actionPerformed(ActionEvent ae)
	{
		Object src = ae.getSource();
	    if (src == b1)
	    {

	         Main m =new Main();
	         m.frame.setVisible(true);
	         GUI.frame1.setVisible(false);
	         
	    } 
	    
	    else if (src == b2) 
	    {
	    	
	    	JOptionPane.showMessageDialog(frame1,
	    	        " Atari Breakout game developed by   "
	    	        + " Abhay Pratap Singh ,"+" \n"+" Abhinav Mittal "
	    	        		+ "and Aman Jain ",
	    	        "Atari Breakout ",
	    	        JOptionPane.INFORMATION_MESSAGE);
	    	
	    	
	    	
	    }
	}
}
	

	

	

