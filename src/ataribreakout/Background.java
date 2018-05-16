package ataribreakout;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Background extends Main{
	
	public Main game;
	public Image image;
	public Background(Main game){
	
	this.game=game;
		image=(new ImageIcon("power.png")).getImage();
		
		
	}
	
	
	
	

}
