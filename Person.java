import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;

public class Person extends JLabel implements Runnable{
	Lane lane = new Lane();	
	JButton sun, rain, snow;
	JLabel point = new JLabel();
	
	int x, y;
	int px, py;
	static int rank1, rank2;
	String name;
	static String[] rank = new String[4];
	
	Random rand = new Random();
	int num = rand.nextInt(20) + 10;
	int speed  =num;
	static int flag1 =0, flag2 =0;
	private Monitor m;
	
	public Person(ImageIcon name, ImageIcon p, Monitor s) {
	
		m = s;		
		lane.setLayout(null);
		lane.getIMG(new ImageIcon("bg.jpg").getImage());
		Color clr = new Color(0xEBFBFB);
		
		//날씨변화에 따른 속도변화 (속도 speed는 sleep에 영향을 주므로 작을수록 빨라짐)
		sun = new JButton(new ImageIcon("sun.jpeg"));
	    rain = new JButton(new ImageIcon("rain.jpg"));
	    snow = new JButton(new ImageIcon("snow.jpg"));
	   
	    sun.setOpaque(false);
	    rain.setOpaque(false);
	    snow.setOpaque(false);
	    
	    sun.setBorderPainted(false);
	    rain.setBorderPainted(false);
	    snow.setBorderPainted(false);
		
	    sun.setBackground(clr);
		rain.setBackground(clr);
		snow.setBackground(clr);
		
		sun.addActionListener((e) ->{
			if(this.name =="짱구") {
				while(speed > 3) {
					this.speed--;
				}
			} else {
				speed = num;
				lane.getIMG(new ImageIcon("bg.jpg").getImage());
				SwingUtilities.updateComponentTreeUI(lane);
			}
		});
		snow.addActionListener((e) ->{
			speed = 50;
			lane.getIMG(new ImageIcon("bg2.png").getImage());
			SwingUtilities.updateComponentTreeUI(lane);
		});
		rain.addActionListener((e) ->{
			speed = 35;
			lane.getIMG(new ImageIcon("bg3.png").getImage());
			SwingUtilities.updateComponentTreeUI(lane);
		});
		
		//날씨 패널 추가
		JPanel wth = new JPanel();
		wth.setLayout(new GridLayout(3,1));
		wth.add(sun);
		wth.add(rain);
		wth.add(snow);
		
		//달리는 이미지 추가
		this.setIcon(name);
		if(name.toString() == "person1.png") {
			this.name = "짱구";
		}
		else if(name.toString() == "person2.png") {
			this.name = "짱구 아빠";
		}
		else if(name.toString() == "person3.png") {
			this.name = "짱구 엄마";
		}
		else {
			this.name = "짱아";
		}
		
		//말생성
		point.setIcon(p);
		point.setBounds(30, 30, 100, 100);
		
		wth.setBounds(0, 10, 80, 130);
		lane.add(wth);
		lane.add(this);
	}
	
	public void speedDown() {
		while(speed < 20) {
			this.speed++;
		}
	}

	public void run() {
		//TODO x,y 값을 받아서 바꿔줘야 됨
		//Car가 달리는 부분, 끝나는 조건이 있어야함
		if(this.name != "짱구") {
			for(int i=0; i< 1800; i++) {
				x = i;
				
				py = i * 650 / 1800;
				point.setBounds(30, py, 100, 100);
				lane.getVarX(x);
				lane.updateUI();
				
				if(x == 600) {
					flag1++;
					m.monitor(flag1);
				}
				
				if(x == 1200) {
					flag2++;
					m.monitor(flag2);
				}
				
				//Person 움직이게 하는 부분
				if(x < 300) {
					this.setBounds(x, 45, 130, 180);
				}
				else if(x >= 300 && x < 1700){
					this.setBounds(300, 45, 130, 180);
				}
				else {
					this.setBounds(i-1300, 45, 130, 180);
				}
				
				try {
					Thread.sleep((int)(Math.random()*speed));
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			for(int i=0; i< 1800; i++) {
				
				x = i;
				System.out.println(speed);
				
				if(i % 20 == 0) {
					speedDown();
				}
				py = i * 650 / 1800;
				point.setBounds(30, py, 100, 100);
				lane.getVarX(x);
				lane.updateUI();
				
				if(x == 600) {
					flag1++;
					m.monitor(flag1);
				}
				
				if(x == 1200) {
					flag2++;
					m.monitor(flag2);
				}
				
				//Person 움직이게 하는 부분
				if(x < 300) {
					this.setBounds(x, 45, 130, 180);
				}
				else if(x >= 300 && x < 1700){
					this.setBounds(300, 45, 130, 180);
				}
				else {
					this.setBounds(i-1300, 45, 130, 180);
				}
				
				try {
					Thread.sleep((int)(Math.random()*speed));
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
			
		
		rank1++;
		rank2 = rank1;
		System.out.println(name);
		rank[rank2-1] = name;
		
		flag1 = 0;
		flag2 = 0;
		
		if(rank2 ==4) {
			JFrame f =new JFrame();
			f.setSize(300, 500);
			JOptionPane.showMessageDialog(f, rank[0]+ ": 1위 \n"+ rank[1]+ ": 2위 \n"+ rank[2]+ ": 3위 \n"+rank[3]+ ": 4위 \n");
		}
	}
}