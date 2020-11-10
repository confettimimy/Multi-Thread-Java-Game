import java.awt.*;
import javax.swing.*;

public class View extends JPanel{
	private Image img = new ImageIcon("view.png").getImage();
	
	public View() {		
		Dimension size = new Dimension(150, 800);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
