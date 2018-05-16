package ataribreakout;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements MouseMotionListener,KeyListener,MouseListener
{
	int i;
	int size=30;
	Main m;
	
ArrayList<Block>blocks=new ArrayList<Block>();
ArrayList<Block>ball=new ArrayList<Block>();
ArrayList<Block>powerups=new ArrayList<Block>();
Block paddle;
Thread thread;
Animate animate;
static int score=0; 
static int block_count=1;
protected JLabel lab; 
 int cont=1;    //  var cont used for stop the increment in ball speed while pressing enter and mouse click
//static int count;
static boolean  ballD1;
static boolean ballD2=false;
JPanel p;
 int count1;

BlockBreakerPanel()
{
	//count=n;
	paddle=new Block(190,600,160,22,"paddle.png");
	
	 p = new JPanel();
     
    lab= new JLabel("", JLabel.RIGHT);
    
    p.setLayout(new FlowLayout()); 
    
    lab.setSize(100,100);
    p.add(lab);
    add(p);
    
	for(i=0;i<19;i++)
	{
		block_count+=5;
		blocks.add(new Block((i*70)+15,95,65,35,"17.jpg"));
		blocks.add(new Block((i*70)+15,130,70,40,"2.jpg"));
		blocks.add(new Block((i*70)+15,170,65,35,"3.jpg"));
		blocks.add(new Block((i*70)+15,210,65,35,"4.jpg"));
		blocks.add(new Block((i*70)+15,250,65,35,"5.jpg"));
	}
	
	for(i=1;i<18;i++)
	{
		block_count+=1;
		blocks.add(new Block((i*70),290,65,35,"7.jpg"));
	}

	for(i=2;i<17;i++)
	{
		block_count+=1;
		blocks.add(new Block((i*70),330,65,35,"8.jpg"));
	}
	for(i=3;i<16;i++)
	{
		block_count+=1;
		blocks.add(new Block((i*70),370,65,35,"12.jpg"));
	}
	
	Random random = new Random();
	   
	blocks.get(random.nextInt(40)).powerups=true;
	blocks.get(random.nextInt(40)).powerups=true;
	blocks.get(random.nextInt(40)).powerups=true;
	blocks.get(random.nextInt(40)).powerups=true;
	
	
	
	
	ball.add(new Block(630,565,30,30,"b2.png"));

	addKeyListener(this);
	setFocusable(true);
	addMouseMotionListener(this);
	addMouseListener(this);

	
}

public void paintComponent(Graphics g)

{
	  //for erasing the screen and repaint
	super.paintComponent(g);
	
	for(Block b : blocks)
	 b.draw(g, this);
	
	paddle.draw(g,this);
	
	for(Block b : ball)
		 b.draw(g, this);
	
	for(Block p : powerups)
		 p.draw(g, this);
}


public void update() 
{
	
	for(Block p:powerups){
		
		p.y+=2;
		
		if(p.intersects(paddle) && !p.destroyed)    /*  this block control the power ups and create
	                                                 new ball for every power ups taken by paddle /*                                                                     paddle*/
		{
			 p.destroyed=true;
			 
		     ball.add(new Block(paddle.x+75,410,32,32,"b3.jpg"));
		     p.ballx*=3;
		     p.bally*=-3;
		     
			score+=30;	
		   lab.setText(" " + score);
			
		}
		
		
		
	}
	for(Block ba : ball)
	{
		ba.x+=ba.ballx;
		
		 // this block contain ball in  inside the frame
		if(ba.x>(getWidth()-size)  && ba.ballx>0|| ba.x<0)
			ba.ballx*=-1;
		
		//change y coordinate when  ball intersect with paddle 
		if(ba.y<0 || ba.intersects(paddle))
		{  
			ba.bally*=-1;
		
		}  // end if
		
		
		
		 // Game Over condition
		 if( ba.y>paddle.y+10  && ba.getHeight()==30 && ba.getWidth()==30 )
		 {
			ba.bally*=1;
//			ba.destroyed=true;
			
			
			if(count1==0 )
			{
				
			 String ObjButtons1[] = {"Yes","NO"};
			 
		    int PromptResult1 = JOptionPane.showOptionDialog(null, 
		       " Your Score is:"+score +"\n "+ "Do You Want To continue ", "Game Over!", 
		        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
		        ObjButtons1,ObjButtons1[0]);
		    
		    
		     
		     if(PromptResult1==JOptionPane.OK_OPTION )
		     {
		    	 count1=1;
		    	 ba.setBounds(123, 500, 30, 30);
		    	 ba.bally *=-1;
		    	 thread.resume();
		    	 
		     }    // end if
		     else
		     {
		    	System.exit(0);
			   
		     }  // end else loop
		     
			}
			
			
			     
				
		
		     
		 } //end if loop
		ba.y-=ba.bally;  // return the ball 
		
	
	for(Block b :blocks)
	{    
		   // when block intersect with the ball and block destroyed
		if( (b.left.intersects(ba) || b.right.intersects(ba))&& !b.destroyed )
		{
			ba.ballx*=-1;
			b.destroyed=true;
			block_count-=1;
			score+=10;
			lab.setText(" Score  :" + score);
			
			if(b.powerups)
			 {
				powerups.add(new Block(b.x,b.y,30,30,"power.png"));
			    ba.bally*=-1;
			 } //end if
		  } //end if
		
		else if(ba.intersects(b) && !b.destroyed )
		 {
			b.destroyed=true;
			ba.bally*=-1;
			block_count-=1;
			score+=10;
			lab.setText("Score :" + score);
			
			if(b.powerups)
			 {
			    powerups.add(new Block(b.x,b.y,30,30,"power.png"));
				score+=30;
			    lab.setText("Score : " + score);
			 }  // end if
			
	}   // end else if
			
	}   //end for for block
	
	}	// end for for ball
	
	repaint(); 
	

	   //  Winning condition when all the block are destroyed
	if(block_count==1)
	{
	    String ObjButtons[] = {" OK"};
	    int PromptResult = JOptionPane.showOptionDialog(null, "Congrats!! "+"\n"
	    		+ " You Won!!" + " \n " +" Your Score is :"+score, "Atari BreakOut", 
	            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
	            ObjButtons,ObjButtons[0]);
	    
	    if(PromptResult==0)
         {
            System.exit(0);          
         } // end if
      else
       {
 	     System.exit(0); 
       }  // end else
	      	
	}    // end if
	
}  // end update

@Override
public void mouseDragged(MouseEvent m) {
	// TODO Auto-generated method stub	
}
@Override
public void mouseMoved(MouseEvent m) {
	// TODO Auto-generated method stub
	paddle.x=m.getX(); //when the mouse is moved this will changed the position of the paddle
	if(paddle.x>(getWidth()-paddle.width))
	{
	paddle.x-=paddle.width+2;
	}
}
@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if(e.getKeyCode()==KeyEvent.VK_LEFT && paddle.x>0)
	 {
		paddle.x-=15;  //shift paddle left through key pressed
	 }
	if(e.getKeyCode()==KeyEvent.VK_RIGHT && paddle.x<(getWidth()-paddle.width))
	 {
		paddle.x+=15;  //shift paddle right through key pressed
	 }
	
} // end key pressed ()

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	if(e.getKeyCode()==KeyEvent.VK_ENTER && cont==1 )
	 {
		animate=new Animate(this);
		thread=new Thread(animate);
		thread.start();	
		cont=0;
	 }
	if(e.getKeyCode()==KeyEvent.VK_LEFT && paddle.x>0)
	 {
		paddle.x-=30;
	 }
	if(e.getKeyCode()==KeyEvent.VK_RIGHT && paddle.x<(getWidth()-paddle.width))
	 {
		paddle.x+=30;
	 }
     } // end keyReleased()

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseClicked(MouseEvent m) {
	// TODO Auto-generated method stub

		if(cont==1)
		{
	
			animate=new Animate(this);
			thread=new Thread(animate);
			thread.start();	
			cont=0;
		}
	
}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

}
