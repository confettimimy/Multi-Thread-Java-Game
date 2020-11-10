import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

class Lane extends JPanel{
	private Image img;
	private int x = 0;
	
	public Lane() {
		Dimension size = new Dimension(700, 700);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void getIMG(Image img) {
		this.img = img;
	}
	
	public void getVarX(int x) {
		this.x = x;
	}
	
	public void paintComponent(Graphics g) {
		if(x < 1800) {
			g.drawImage(img, -x, 0, null);
		}
		else {
			g.drawImage(img, 1800, 0, null);
		}
	}
}