import javax.swing.*;
import java.awt.*;

public class Student extends Thread{
	JLabel student;
	int x, y;
	int size;
	int speed;
	String name;
	
	ImageIcon originIcon;
	Image originImg, song;
	ImageIcon stdnt;
	
	Student(JLabel student, int y, int speed, String name){
		this.student = student;
		student.setLocation(0, y);
		this.y = y;
		this.speed = speed;
		this.name = name;
		
		originIcon = new ImageIcon(this.name);  
		originImg = originIcon.getImage();
		song = originImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		stdnt = new ImageIcon(song);
	}
	
	public ImageIcon getIcon() {
		return stdnt;
	}
	
	public void getChanged(int y, int speed) {	
		this.y = y;
		this.speed = speed;
	}
	
	public void run() {
		//round trip
		boolean rt = true;
		while(true) {
			y = student.getY();
			
			if(rt == true) {
				x = student.getX() + 5;
			} else if (rt == false) {
				x = student.getX() - 5;
			}
			
			if(x == 380) {
				rt = false;
			} else if( x == 20 ) {
				rt = true;
			}
			
			student.setLocation(x, y);
			
			try {
				sleep(speed);
			} catch(Exception e) {
				student.setLocation(20, y);
				rt = true;
				try{
                    sleep(1000);                    
                }
                catch(Exception e2){}
			}
		}
	}

}