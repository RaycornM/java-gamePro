package bird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Wall extends FlyingObject implements Enemy{
	private int dir; //������
	
//	private static Image img2;
	
	
//	static {
//		try {
//			//this.img = ImageIO.read(new File("skin/bee/bee0.png")); 
//			//��仰�Ǵ���ģ���Ϊstatic�Ǽ������ʱ�򣬴�������֮ǰִ�еģ��ǻỹû��this
//			img_wall = ImageIO.read(new File("Asoul/bella.png"));
////			img2 = ImageIO.read(new File("skin/bee/wall.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public Wall() {
		Random ran=new Random();
		//����ȸ߶ȸ�ֵ
		this.width=ran.nextInt(200)+100; // 20-120
		this.height=200+ran.nextInt(100);  //200-300
		
		//dir�������ϻ���������ǽ
		dir=ran.nextInt(2);// 0 1
		switch (dir) {
			case 0: this.y=0;break;// ǽ����
			case 1: this.y=Config.JFRAME_HEIGHT-this.height-20;break;//ǽ����
		}
		
		this.x=0;// ������������
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
	
	/*�õ�ǰǽ�͸���ǽ���бȽϣ��Ƿ����̫����
	 * �õ�ǰǽ��Ϊ��������������������150�����ұ�����150�����Լ���������Χ
	 * ��ǰ��������Ĳ���������һ��ǽ��������Ͳ�����������򷵻�true*/
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
