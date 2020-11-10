import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Professor extends Thread{
	JLabel bullet, target, target2, stage, score;
	Student student;
	Student2 student2;
	int y, speed;
	static int flag = 1;
	static int flag2 = 0;
	int flag3 = 0;
	int i;
	int size;
	
	ImageIcon stdnt, stdnt2;
	ImageIcon[] img =  {
			new ImageIcon("song4.png"),
			new ImageIcon("song2.png"),
			new ImageIcon("song3.png"),
			new ImageIcon("song.png"),
			new ImageIcon("song5.png"),
			new ImageIcon("song6.png")
	};
	
	public Professor(JLabel bullet, JLabel target, JLabel target2,
		Student student, Student2 student2,  JLabel stage, JLabel score) {
		this.bullet=bullet;
        this.target=target;
        this.target2=target2;
        this.student = student;
        this.student2 = student2;
        this.stage= stage;
        this.score = score;
        i=0;
	}

	public void run() {
		while(true) {
			flag3++;
			if(hit()) {
				flag2++;
				score.setText("SCORE: "+Integer.toString(flag2));
				countStage(flag);
				
				student.interrupt();
				student2.interrupt();
				
				student.getChanged(y, speed);
				if(y < 200) {
					student2.getChanged(y+100, speed);
				} else {
					student2.getChanged(y-100, speed);
				}
				
				if(flag2>1 && flag2%3==0) {
					flag++;
					stage.setText("STAGE: "+ Integer.toString(flag));
					JOptionPane.showMessageDialog(null, "STAGE : "+flag+"\n´ÙÀ½ stage·Î ³Ñ¾î°¡½Ã°Ú½À´Ï±î?");
				} else {
					bullet.setLocation(bullet.getParent().getWidth()/2-5, bullet.getParent().getHeight()-60);
				}
				
				if(flag2>1 && flag2%3==1) {
					target2.setVisible(true);
				}
				else {
					target2.setVisible(false);
				}
				
				i = (int)(Math.random()*6)+0;
				int j = (int)(Math.random()*6)+0;
				
				Image originImg = img[i].getImage();
				Image img = originImg.getScaledInstance(size, size, Image.SCALE_SMOOTH);
				
				stdnt = new ImageIcon(img);				
				target.setIcon(stdnt);		//ÇÐ»ý»ý¼º
				target.setSize(stdnt.getIconWidth(), stdnt.getIconHeight());
				
				Image originImg2 = this.img[j].getImage();
				Image img2 = originImg2.getScaledInstance(size, size, Image.SCALE_SMOOTH);
				
				stdnt2 = new ImageIcon(img2);				
				target2.setIcon(stdnt2);		//ÇÐ»ý»ý¼º
				target2.setSize(stdnt2.getIconWidth(), stdnt2.getIconHeight());
			}
			else {
				int x = bullet.getX();
				int y = bullet.getY()-5;
				
				if(y<0) {
					bullet.setLocation(bullet.getParent().getWidth()/2-5, bullet.getParent().getHeight()-60);
					return;
				}
				else {
					bullet.setLocation(x, y);
				}
				
				//0.01ÃÊ¸¶´Ù 5ÇÈ¼¿ ÀÌµ¿
				try{
	                sleep(3);
	            }   catch(Exception e){}
			}
			
		}
		
	}
	
	private boolean hit(){
        int x=bullet.getX();
        int y=bullet.getY();
        
        int w=bullet.getWidth();
        int h=bullet.getHeight();
        
        if(targetContains(x,y) || targetContains(x+w-1,y)
        	||targetContains(x+w-1,y+h-1) || targetContains(x,y+h-1))
            return true;
        else
            return false;
    }
	
	private boolean targetContains(int x, int y){
        //Å¸°ÙÀÇ xÁÂÇ¥°¡ ÃÑ¾Ë xÁÂÇ¥º¸´Ù ÀÛ°Å³ª °°À¸¸ç ÃÑ¾Ë xÁÂÇ¥º¸´Ù Å¸°Ù xÁÂÇ¥ + Å¸°ÙÀÇ °¡·Î ±æÀÌ°¡ Å©°í 
        if(((target.getX()<=x)&&(x<target.getX()+target.getWidth()))   
             //Å¸°ÙÀÇ yÁÂÇ¥°¡ ÃÑ¾Ë yÁÂÇ¥º¸´Ù ÀÛ°Å³ª °°À¸¸ç ÃÑ¾Ë yÁÂÇ¥º¸´Ù Å¸°Ù yÁÂÇ¥ + Å¸°ÙÀÇ ¼¼·Î ±æÀÌ°¡ Å©¸é
             &&((target.getY()<=y)&&(y<target.getY()+target.getHeight())))
            return true;
        
        else
            return false;
    }
	
	public void countStage(int f) {
		Random rand = new Random();
		int num;
		if(flag2 < 7) {
			num = rand.nextInt(15) + 13;
		}	else if(flag2 > 7 && flag2 < 16){
			num = rand.nextInt(13) + 10;
		}	else {
			num = rand.nextInt(9) + 5;
		}
		speed  = num;
		System.out.println("speed: "+speed);
		y = rand.nextInt(300) + 100;
		System.out.println("y: "+y);
		
		if(flag2 < 50) {
			size = rand.nextInt(100) + 50;
		} else {
			size = 45;
		}
	}
}
