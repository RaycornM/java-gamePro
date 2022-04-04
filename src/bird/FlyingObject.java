package bird;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * �ڽ�һϵ�����͹���Ϊһ�������ʱ����ѭ��ԭ��
 * 
 * 1.�������๲�е�һ�����Զ��鵽����
 * 2.��������ľ�̬���Զ��鵽����
 * @author RaycornM
 *
 */
public abstract class FlyingObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected static Image img_wall;
	protected static Image img_box;
	protected static Image img_fire;
	protected static Image img_money;
	protected static Image img_stone;
	
	private File f;
	private URI uri;
	private URL url;
	
	protected int status;
	
	

	static {
		try {
			img_wall = ImageIO.read(new File("Asoul/acao.gif"));
			img_box = ImageIO.read(new File("Asoul/ˮĸ.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public abstract void paintSelf(Graphics g);
	
	public abstract boolean isOut();
	
	public abstract void move();
	
	public abstract boolean isClosed(FlyingObject w);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
