package bird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * �Զ���һ������
 * 
 * һ��������һ�ֶ����ģ��
 * 
 * һ������߱����Ժ���Ϊ���ֶ���
 * ���Զ���ö������ʱ��һ�������ԣ�ȫ�ֱ���������Ϊ��������
 * 
 * �����Ը�ֵ�������뵽���ǹ��췽��
 * 
 * ��̬���Ժ;�̬�����ִֻ��һ�Σ�����������ִ��
 * 
 * �Ǿ�̬���ԡ��Ǿ�̬����顢���췽����ÿ����һ�������ִ��һ�Σ������Ǻ��ھ�̬��ִ��
 * 
 * �Ǿ�̬�����͹��췽��������һ���������������Ը�ֵ��
 * @author RaycornM
 *
 */
public class Bird {
	private int x;
	private int y;
	private int width;
	private int height;
	
	private static Image[] imgs = new Image[7];
	
	private int status; //0 ���� 1 ������ 2 ��
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	static {
		try {//7����Ƭ��ǰ�����Ƿɵ����ӣ��������Ǽ��ˣ������ź���
			for(int i=0;i<imgs.length;i++) {
//				imgs[i] = ImageIO.read(new File("skin/bee/bee"+i+".png"));
				imgs[i] = ImageIO.read(new File("Asoul/datou"+i+".png"));
			}
			//this.img = ImageIO.read(new File("skin/bee/bee0.png")); 
			//��仰�Ǵ���ģ���Ϊstatic�Ǽ������ʱ�򣬴�������֮ǰִ�еģ��ǻỹû��this
			//img = ImageIO.read(new File("skin/bee/bee0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//�Ǿ�̬����飬�͹��췽��һ�����У������Ǹ����Ը�ֵ���͹��췽������һ������˲�����

	public Bird(int x,int y) { //���췽����ʲô�ã� 1.�����Ը�ֵ 2.ͨ��new�ؼ��ִ�������
		super();
		this.x = x;
		this.y = y;
		this.width = Config.BIRD_WIDTH;
		this.height = Config.BIRD_HEIGHT;
		
	}
	
	//����һ���Զ���ķ�������panel��paint����
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
