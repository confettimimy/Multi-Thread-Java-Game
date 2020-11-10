import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;

class ManualPanel extends JPanel
{
	//기본 생성자
    public ManualPanel() {    
    }
   
    ImageIcon backgroundImg = new ImageIcon("manual.png");

   
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImg.getImage(), 0, 0, 500, 760, null);
        setOpaque(false);//그림을 표시하게 설정,투명하게 조절

        super.paintComponent(g);
   }


}
