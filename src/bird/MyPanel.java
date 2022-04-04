package bird;

import java.applet.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	
	private File f;
	private URI uri;
	private URL url;
	
	Image img2;
	
	BackGround back = new BackGround(0,0);
	//BackGround back2 = new BackGround(-800,0);
	Bird bird = new Bird(600,200);
	FlyingObject[] objs = new FlyingObject[0];
	Wall[] walls = new Wall[0];
	GoldBox b = new GoldBox();
	//Bird bird2 = new Bird(400,200);
	
	//Bird[] bs = new Bird[10];
	
	public MyPanel() {
//		Random ran = new Random();
//		for(int i=0;i<bs.length;i++) {
//			bs[i] = new Bird(ran.nextInt(800),ran.nextInt(380));
//		}
		
	}
	
	//��д�����paint����
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		back.paintSelf(g);
		//back2.paintSelf(g);
		bird.paintSelf(g);
//		wall.paintSelf(g);
		for(FlyingObject w:objs) {
			w.paintSelf(g); //�������Ƿ����ȡ���ڱ��������� 
			//�����������ȡ�������ö����ڲ���д�ķ����� ��������ж�̬
			//��̬�������ģ� 1.���ø������õķ�����ʱ��ָ�����������д������
						  //2.����ͬһ��������ط���Ҳ�ж�̬
						  //3.�������ã�����һ���������  FlyingObject a = new GoldBox();
			//������ʲô��˼�� �������͵�һ���������������
			//����������ʲô��˼�� ���˻�������������������ͣ�ȫ����������
		}
		b.paintSelf(g);
		//bird2.paintSelf(g);
//		for(Bird b:bs) {
//			b.paintSelf(g);
//		}
		}
	//��ָ���쳣  ��.�������ǰ��һ��null
	
	/*�Զ���һ���������ƶ�����*/
	public void moveAction() {
		
		
		new Thread() {
			public void run() {
				
				for(;;) {
					//��ⱦ���Ƿ�Խ��
					//���ǽ�Ƿ�Խ��
					FlyingObject[] tmp = new FlyingObject[0]; //������ʾ��ǽ
					for(FlyingObject w:objs) {
						if(!w.isOut()) {
							tmp = Arrays.copyOf(tmp,tmp.length+1);
							tmp[tmp.length-1] = w;
						}
					}
					objs = tmp;
					//���С���Ƿ�����
					if(bird.getStatus()== Config.BIRD_DEATH) {
						JOptionPane.showMessageDialog(null, "��");
						return;
					}
					
//					wall.move();
					//���С���Ƿ�ײ��
					if(bird.hitGround()) {
						bird.setStatus(Config.BIRD_DEATH);
					}
					//���С���Ƿ�ײǽ\����
					for(FlyingObject w:objs) {
						if(bird.hit(w)) {
							if(w instanceof Enemy) { //�ж϶���ľ�������
								bird.setStatus(Config.BIRD_DYING);
								}else if(w instanceof Award) {
									w.setStatus(Config.FLYINGOBJECT_DEATH);
							}
						}
					}
					//С���ƶ��������ƶ�  ǽ�ƶ�
					bird.move();
					back.move();
					b.move();
					for(FlyingObject w:objs) {
						if(w != null) {
							w.move();
						}
					}
					//back2.move();
					repaint(); //����MyPanel��repaint
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			};
		}.start();
	
	}
	
	//�Զ��巽�����ֶ���������
		public void moveHandle() {
			KeyAdapter l = new KeyAdapter() {
				//���м��̰������µ�ʱ�򣬾ͻ��Զ�����
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_UP && bird.getStatus()== Config.BIRD_LIVING) { //����
						bird.moveup();
						repaint();
					}
					if(e.getKeyCode()==KeyEvent.VK_DOWN && bird.getStatus()== Config.BIRD_LIVING) { //����
						bird.movedown();
						repaint();
					}
					if(e.getKeyCode()==KeyEvent.VK_LEFT && bird.getStatus()== Config.BIRD_LIVING) { //����
						bird.moveleft();
						repaint();
					}
					if(e.getKeyCode()==KeyEvent.VK_RIGHT && bird.getStatus()== Config.BIRD_LIVING) { //����
						bird.moveright();
						repaint();
					}
				}
			}; //���̼����߳�
			this.setFocusable(true); //���۽�
			this.addKeyListener(l); //���̼����߳�������ֱ�ӼӸ�panel
		}
		
		/*�����ϰ���ǽ������*/
		public void createWallAction() {
			new Thread() {
				public void run() {
					for(;;) {
						//��������ǰ���ж������ɵ�ǽ�����е�ǽ���бȽ��Ƿ����̫Զ
						Random r = new Random();
						int n = r.nextInt(2);
						
						FlyingObject w = null;
						
						switch(n) {
						case 0:w = new Wall();break;
						case 1:w = new GoldBox();break;
						}
						boolean isOK = true;
						for(FlyingObject tmp:objs) {
							if(tmp.isClosed(w)) {
								isOK = false; //��w���ϸ�
								break; //ֻҪ��һ��Ԫ������ǽ�ܽ����ͽ�������ѭ��
							}
						}
						if(!isOK) {
							continue;
						}
//						for(Wall t:walls) {
//							if(t.isClosed(w)) {
//								continue;
//							}
//						}
						objs = Arrays.copyOf(objs, objs.length+1);
						objs[objs.length-1] = w;
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				};
			}.start();
			
		}
		
		//��������ƶ�����
//		public void mouseMoveAction() {
//			MouseMotionAdapter m = new MouseMotionAdapter() {
//				public void mouseMoved(MouseEvent e) {
//					int x = e.getX();
//					int y = e.getY();
//					bird.move(x,y);
//					repaint();
//				}
//			};
//			this.setFocusable(true);
//			this.addMouseMotionListener(m);
//		}
		
} 

