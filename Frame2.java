import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Frame2 extends JFrame{
	//ImageIcon icon;
	
	public Frame2() {

		this.setLayout(new GridLayout(0,2));

		this.setTitle("PPAP");
		addWindowListener(new WindowAdapter() {
	         	public void windowClosing(WindowEvent e) {
	            	setVisible(false);
	            dispose();
	         }
	      });
		
		ImageIcon icon = new ImageIcon("board5.png");
		GamePanel p = new GamePanel(){
      	public void paintComponent(Graphics g) {
  
      		g.drawImage(icon.getImage(),0,0,null);
      		setOpaque(false);
      		super.paintComponent(g);
      	}
      };

		ManualPanel p0 = new ManualPanel();
		this.add(p0);
		this.add(p);
      

      this.setLocationRelativeTo(null);
		this.setSize(1000,800);
      this.setResizable(false);
      this.setVisible(true);
      p.startGame();
	}

}