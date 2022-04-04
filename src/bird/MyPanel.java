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
	
	//重写父类的paint方法
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		back.paintSelf(g);
		//back2.paintSelf(g);
		bird.paintSelf(g);
//		wall.paintSelf(g);
		for(FlyingObject w:objs) {
			w.paintSelf(g); //方法名是否存在取决于变量的类型 
			//方法体的内容取决于引用对象内部重写的方法体 这种现象叫多态
			//多态体现在哪？ 1.调用父类引用的方法的时候，指向了子类的重写方法体
						  //2.调用同一个类的重载方法也叫多态
						  //3.父类引用，引用一个子类对象  FlyingObject a = new GoldBox();
			//引用是什么意思？ 引用类型的一个变量，简称引用
			//引用类型是什么意思？ 除了基本数据类型以外的类型，全是引用类型
		}
		b.paintSelf(g);
		//bird2.paintSelf(g);
//		for(Bird b:bs) {
//			b.paintSelf(g);
//		}
		}
	//空指针异常  “.”运算符前是一个null
	
	/*自定义一个方法，移动引擎*/
	public void moveAction() {
		
		
		new Thread() {
			public void run() {
				
				for(;;) {
					//检测宝箱是否越界
					//检测墙是否越界
					FlyingObject[] tmp = new FlyingObject[0]; //保存显示的墙
					for(FlyingObject w:objs) {
						if(!w.isOut()) {
							tmp = Arrays.copyOf(tmp,tmp.length+1);
							tmp[tmp.length-1] = w;
						}
					}
					objs = tmp;
					//检测小鸟是否死亡
					if(bird.getStatus()== Config.BIRD_DEATH) {
						JOptionPane.showMessageDialog(null, "寄");
						return;
					}
					
//					wall.move();
					//检测小鸟是否撞地
					if(bird.hitGround()) {
						bird.setStatus(Config.BIRD_DEATH);
					}
					//检测小鸟是否撞墙\宝箱
					for(FlyingObject w:objs) {
						if(bird.hit(w)) {
							if(w instanceof Enemy) { //判断对象的具体类型
								bird.setStatus(Config.BIRD_DYING);
								}else if(w instanceof Award) {
									w.setStatus(Config.FLYINGOBJECT_DEATH);
							}
						}
					}
					//小鸟移动、背景移动  墙移动
					bird.move();
					back.move();
					b.move();
					for(FlyingObject w:objs) {
						if(w != null) {
							w.move();
						}
					}
					//back2.move();
					repaint(); //调用MyPanel的repaint
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
	
	//自定义方法，手动控制引擎
		public void moveHandle() {
			KeyAdapter l = new KeyAdapter() {
				//当有键盘按键按下的时候，就会自动调用
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_UP && bird.getStatus()== Config.BIRD_LIVING) { //按上
						bird.moveup();
						repaint();
					}
					if(e.getKeyCode()==KeyEvent.VK_DOWN && bird.getStatus()== Config.BIRD_LIVING) { //按上
						bird.movedown();
						repaint();
					}
					if(e.getKeyCode()==KeyEvent.VK_LEFT && bird.getStatus()== Config.BIRD_LIVING) { //按上
						bird.moveleft();
						repaint();
					}
					if(e.getKeyCode()==KeyEvent.VK_RIGHT && bird.getStatus()== Config.BIRD_LIVING) { //按上
						bird.moveright();
						repaint();
					}
				}
			}; //键盘监听线程
			this.setFocusable(true); //光标聚焦
			this.addKeyListener(l); //键盘监听线程启动，直接加给panel
		}
		
		/*创建障碍物墙的引擎*/
		public void createWallAction() {
			new Thread() {
				public void run() {
					for(;;) {
						//扩容数组前，判断新生成的墙和已有的墙进行比较是否离得太远
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
								isOK = false; //该w不合格
								break; //只要有一个元素离新墙很近，就结束这内循环
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
		
		//建立鼠标移动引擎
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

