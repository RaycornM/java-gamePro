package bird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 自定义一个鸟类
 * 
 * 一个类型是一种对象的模型
 * 
 * 一个对象具备属性和行为两种东西
 * 所以定义该对象的类时，一般有属性（全局变量）和行为（方法）
 * 
 * 给属性赋值，首先想到的是构造方法
 * 
 * 静态属性和静态代码块只执行一次，并且是最先执行
 * 
 * 非静态属性、非静态代码块、构造方法，每创建一个对象就执行一次，并且是后于静态的执行
 * 
 * 非静态代码块和构造方法的作用一样，是用来给属性赋值的
 * @author RaycornM
 *
 */
public class Bird {
	private int x;
	private int y;
	private int width;
	private int height;
	
	private static Image[] imgs = new Image[7];
	
	private int status; //0 活着 1 在死中 2 寄
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	static {
		try {//7张照片，前五张是飞的样子，第六张是寄了，第七张黑脸
			for(int i=0;i<imgs.length;i++) {
//				imgs[i] = ImageIO.read(new File("skin/bee/bee"+i+".png"));
				imgs[i] = ImageIO.read(new File("Asoul/datou"+i+".png"));
			}
			//this.img = ImageIO.read(new File("skin/bee/bee0.png")); 
			//这句话是错误的，因为static是加载类的时候，创建对象之前执行的，那会还没有this
			//img = ImageIO.read(new File("skin/bee/bee0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//非静态代码块，和构造方法一起运行，作用是给属性赋值，和构造方法作用一样，因此不常用

	public Bird(int x,int y) { //构造方法有什么用？ 1.给属性赋值 2.通过new关键字创建对象
		super();
		this.x = x;
		this.y = y;
		this.width = Config.BIRD_WIDTH;
		this.height = Config.BIRD_HEIGHT;
		
	}
	
	//这是一个自定义的方法，被panel的paint调用
	int index = 0;
	public void paintSelf(Graphics g) {
		if(this.status==Config.BIRD_LIVING) {
			g.drawImage(imgs[index++], x, y,width, height, null);
			if(index>=5) {
				index = 0;
			}
		}else if(this.status==Config.BIRD_DYING){
			g.drawImage(imgs[5], x, y,width, height, null);
		}else {
			g.drawImage(imgs[6], x, y,width, height, null);
		}
	}
	
	public void move() {
		if(status == Config.BIRD_LIVING) {
			this.y++;
		}else if(status == Config.BIRD_DYING){
			this.y += 30;
		}else if(status == Config.BIRD_DEATH) {
			this.x = 0;
			this.y = 0;
		}
	}
	
	public void move(int x, int y) {
		if(status == Config.BIRD_LIVING) {
			this.x = x;
			this.y = y;
		}else if(status == Config.BIRD_DYING){
			this.y += 30;
		}else if(status == Config.BIRD_DEATH) {
			this.x = 0;
			this.y = 0;
		}
	}
	
	public void moveup() {
		this.y-=25;
	}
	public void movedown() {
		this.y+=25;
	}
	public void moveleft() {
		this.x-=25;
	}
	public void moveright() {
		this.x+=25;
	}
	
	public boolean hit(FlyingObject w) {
		if(this.x < w.getX()-5 + w.getWidth()-5 && this.x + this.width>w.getX()-5) {
			if(this.y+this.height>w.getY() && this.y<w.getY()+w.getHeight()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hitGround() {
		if(this.y+this.height>Config.JFRAME_HEIGHT) {
			return true;
		}
		return false;
	}

}
