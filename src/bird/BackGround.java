package bird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackGround {
	private int x;
	private int y;
	private int width;
	private int height;
	private static Image img;
	private static Image img2;
	
	private int x2=-Config.JFRAME_WIDTH;
	
	static{
		try {
			img = ImageIO.read(new File("skin/bee/back.png"));
			img2 = ImageIO.read(new File("skin/bee/back2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BackGround(int x,int y) {
		this.x=x;
		this.y=y;
		this.width=Config.JFRAME_WIDTH;
		this.height=Config.JFRAME_HEIGHT;
	}
	
	public void paintSelf(Graphics g){
		g.drawImage(img, x, y, width, height, null);
		g.drawImage(img2, x2, y, width, height, null);
	}
	
	
	public void move(){
		this.x++;
		this.x2++;
		if(this.x>=Config.JFRAME_WIDTH){
			this.x=-Config.JFRAME_WIDTH;
		}
		if(this.x2>=Config.JFRAME_WIDTH){
			this.x2=-Config.JFRAME_WIDTH;
		}
	}
	
}