import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;

class ManualPanel extends JPanel
{
	//�⺻ ������
    public ManualPanel() {    
    }
   
    ImageIcon backgroundImg = new ImageIcon("manual.png");

   
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImg.getImage(), 0, 0, 500, 760, null);
        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ����

        super.paintComponent(g);
   }


}
