import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame{

	JPanel contentPane;
	JPanel menuPanel;
	JPanel centerPanel;
	View viewPanel = new View();
	JButton startBt, stopBt, pauseBt, suspendBt, resumeBt;
	Thread[] ths;
	Monitor monitor = new Monitor();
	Person[] psns = new Person[] {
			new Person(new ImageIcon("person1.png"),new ImageIcon("point1.png"),monitor),
			new Person(new ImageIcon("person2.png"),new ImageIcon("point2.png"),monitor),
			new Person(new ImageIcon("person3.png"),new ImageIcon("point3.png"),monitor),
			new Person(new ImageIcon("person4.png"),new ImageIcon("point4.png"),monitor),
	};
	
	public Frame() {
		super("Runnable test");
		ths = new Thread[psns.length];
		
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
         	public void windowClosing(WindowEvent e) {
            	setVisible(false);
            dispose();
         }
		});
		
		setBounds(10,10,800,800);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
		
		//------------------------------------------------------
		menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(1,3,5,5));	//1행 3열 5개로 나눈 위치
		startBt = new JButton("start");
		suspendBt = new JButton("suspend");
		resumeBt = new JButton("resume");
		
        //메인 메뉴바 버튼색들 색상, 폰트 변경
		startBt.setBackground( new Color(153, 102, 255) );
		suspendBt.setBackground( new Color(204, 153, 255) );
		resumeBt.setBackground( new Color(255, 204, 255) );
		startBt.setFont(new  Font("Dialog",  Font.PLAIN,  25));
		suspendBt.setFont(new  Font("Dialog",  Font.PLAIN,  25));
		resumeBt.setFont(new  Font("Dialog",  Font.PLAIN,  25));
		
		menuPanel.add(startBt);
		menuPanel.add(suspendBt);
		menuPanel.add(resumeBt);
		contentPane.add("North",menuPanel);
		
		//------------------------------------------------------
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(psns.length,1,5,5));
		contentPane.add("Center", centerPanel);
		
		//------------------------------------------------------
		
		contentPane.add("West", viewPanel);
		for(int i=0; i< psns.length;i++) {
			viewPanel.add(psns[i].point);
		}
		//------------------------------------------------------
		startBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i=0; i< psns.length;i++) {
					centerPanel.add(psns[i].lane);
				}
				centerPanel.updateUI();
				viewPanel.updateUI();
				
				Person.rank1 = 0;
				if((ths[0] == null && ths[1] == null && ths[2] == null && ths[3] == null)||
						(!ths[0].isAlive() && !ths[1].isAlive() && !ths[2].isAlive() && !ths[3].isAlive() )) {
					game_play();
				}
			}
		});
		
		resumeBt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				for(Thread t:ths) {
					t.resume();
				}
			}	
		});
		
		suspendBt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				for(Thread t:ths) {
					t.suspend();
				}
			}	
		});

	}
	
	public void game_play() {
		makeThread();
		
		for(int i = 0; i < psns.length; i++) {
			ths[i].start();
		}
		
		
	}
	private void makeThread() {
		for(int i= 0 ; i < psns.length; i++) {
			ths[i] = new Thread(psns[i]);			
		}
	}
	
	
}