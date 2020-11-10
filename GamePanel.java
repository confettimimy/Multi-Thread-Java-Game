import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel{

	JLabel base = new JLabel();		//교수님 사진
	JLabel bullet = new JLabel();	//분필..
	JLabel target = new JLabel();	//학생
	JLabel target2 = new JLabel();
	JLabel stage = new JLabel();
	JLabel score = new JLabel();
	Student [] student = new Student[] {
			new Student(target, 400, 20, "song.png"),
			new Student(target, 400, 20, "song1.png"),
			new Student(target, 400, 20, "song2.png"),
			new Student(target, 400, 20, "song3.png")};
	
	Student2 student2 = new Student2(target2, 400, 20, "song.png");
	
	GamePanel(){
		this.setLayout(null);
		this.setSize(500, 550);
		
		Font font = new Font("HY헤드라인M", Font.PLAIN, 30);
		
		ImageIcon prof = new ImageIcon("001.png");
		ImageIcon stdnt = student[0].getIcon();
		ImageIcon stdnt2 = student2.getIcon();
		
		base.setIcon(prof);			//교수님 생성
		base.setSize(prof.getIconWidth(),prof.getIconHeight());
		
		target.setIcon(stdnt);		//학생생성
		target.setSize(stdnt.getIconWidth(), stdnt.getIconHeight());
		target2.setIcon(stdnt2);		//학생2생성
		target2.setSize(stdnt2.getIconWidth(), stdnt2.getIconHeight());
		
		
		bullet.setSize(10,50);			//분필 생성
		bullet.setOpaque(true);	
		bullet.setBackground(Color.white);
				
		stage.setSize(200,50);
		stage.setFont(font);
		stage.setText("STAGE: 1");
		
		score.setSize(200,50);
		score.setFont(font);
		score.setText("SCORE : 0");
		
		this.add(base);
		this.add(target);
		this.add(target2);
		this.add(bullet);
		this.add(stage);
		this.add(score);
	}
	
	public void startGame() {
		base.setLocation(this.getWidth()/2- (base.getWidth()/2), this.getHeight()-(base.getHeight()+10));
		bullet.setLocation(this.getWidth()/2-5, this.getHeight()-60);
		target.setLocation(20, 200);
		target2.setLocation(380, 300);
		target2.setVisible(false);
		stage.setLocation(170, 20);
		score.setLocation(170, 60);
		
		//타겟을 움직이는 스레드
		student[0].start();		
		student2.start();
        
		base.setFocusable(true);
        base.requestFocusInWindow();
        base.addKeyListener(new KeyListener() {
        	Professor professor;	
        	public void keyPressed(KeyEvent ke) {
				if(ke.getKeyChar() == KeyEvent.VK_ENTER || ke.getKeyChar() == KeyEvent.VK_SPACE) {
					if(professor == null || !professor.isAlive()) {						
						professor = new Professor(bullet, target, target2, student[0], student2, stage, score);
	                    professor.start();
					}
				}
			}
			public void keyTyped(KeyEvent ke) {      }
			public void keyReleased(KeyEvent ke) {		}
        });
	}
}