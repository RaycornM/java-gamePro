package bird;

/*定义一个英雄接口，抽象出鸟的手动控制，撞墙，撞地功能
 * 供船也能使用
 * **/
public interface Hero {
	
	 void moveHandle();
	 void moveHandleLeft();
	 void moveHandleRight();
	 void moveHandleDown();
	 
	 boolean hit(FlyingObject w);
	 boolean isGround();
}
