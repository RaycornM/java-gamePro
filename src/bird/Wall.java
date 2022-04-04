package bird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Wall extends FlyingObject implements Enemy{
	private int dir; //代表方向
	
//	private static Image img2;
	
	
//	static {
//		try {
//			//this.img = ImageIO.read(new File("skin/bee/bee0.png")); 
//			//这句话是错误的，因为static是加载类的时候，创建对象之前执行的，那会还没有this
//			img_wall = ImageIO.read(new File("Asoul/bella.png"));
////			img2 = ImageIO.read(new File("skin/bee/wall.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public Wall() {
		Random ran=new Random();
		//给宽度高度赋值
		this.width=ran.nextInt(200)+100; // 20-120
		this.height=200+ran.nextInt(100);  //200-300
		
		//dir决定在上还是下生成墙
		dir=ran.nextInt(2);// 0 1
		switch (dir) {
			case 0: this.y=0;break;// 墙在上
			case 1: this.y=Config.JFRAME_HEIGHT-this.height-20;break;//墙在下
		}
		
		this.x=0;// 在鸟的左侧生成
//		this.x2=ran.nextInt(400);
	}
	
	public void paintSelf(Graphics g) {
		g.drawImage(img_wall, x, y,width, height, null);
//		g.drawImage(img2, x2, y,width, height, null);
	}
	
	public void move() {
		this.x+=15;
//		this.x2+=5;
//		Random ran = new Random();
//		if(this.x>=800) {
//			this.x=ran.nextInt(400);
//		}
//		if(this.x2>=800) {
//			this.x2=ran.nextInt(400);
//		}
	}
	
	/*拿当前墙和给定墙进行比较，是否离得太近，
	 * 拿当前墙作为参照物，参照物最左边往左150，最右边往右150，是自己的势力范围
	 * 当前方法传入的参数，是另一面墙，如果它和参照物相近，则返回true*/
	public boolean isClosed(FlyingObject w) {
		if(this.x-350 < w.x && w.x < this.x+this.width+350) {
			return true;
		}
		return false;
	}
	
	public boolean isOut() {
		if(this.x+this.width > Config.JFRAME_WIDTH) {
			return true;
		}
		return false;
	}
	
}
