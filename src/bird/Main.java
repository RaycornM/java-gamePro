package bird;

import javax.swing.JFrame; // ctrl shift o
import javax.swing.JPanel;

public class Main {

	//  alt /
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(Config.JFRAME_WIDTH, Config.JFRAME_HEIGHT);
		frame.setLocationRelativeTo(null);// ��Ļ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// �ٷ��ṩ��������ӷ�����ֻ�����������˵�����ť�����ȡ����ܻ�һֻ��
		//JPanel panel=new JPanel();
		
		MyPanel panel=new MyPanel();
		
		frame.setResizable(false);
		
		frame.add(panel);// �����������壬��ѭ��������panel��paint����
		
		panel.createWallAction(); //��������ǽ������
		panel.moveAction(); //�����ƶ�����
		panel.moveHandle(); //�����ֶ���������
//		panel.mouseMoveAction(); //��������������
		frame.setVisible(true);
	}
	
}
