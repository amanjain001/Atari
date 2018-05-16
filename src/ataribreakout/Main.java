package ataribreakout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    JLabel lb=new JLabel("");
    
	public JFrame frame= new JFrame("Atari Breakout");
	
	Main()
	{
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		BlockBreakerPanel panel=new BlockBreakerPanel();
		panel.add(lb);
	
		frame.getContentPane().add(panel);
		panel.setBackground(Color.white);
	
		//frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		//frame.setLocation((dim.width/4-frame.getSize().width/4)-80,1);
		frame.setSize(1375,800);//850 width and 800 height  
		 frame.setResizable(false);
		// frame.setLocationRelativeTo(null);
		frame.setVisible(true);//making the frame visible 
		 
		 

    }
}



