import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.w3c.dom.events.MouseEvent;

public class StartFrame extends JFrame implements ActionListener{
	private JButton btn1;
	private JButton btn2;
	JLabel header;
	ImageIcon top2;
	ImageIcon b_jjang, b_ppap;
	ImageIcon c_jjang, c_ppap;
	int width;
	int height;
	
	public StartFrame() {
		setTitle("Best2");
		setSize(1000, 610);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel;
		ImageIcon icon = new ImageIcon("board88.png");
		panel = new JPanel(){
        	public void paintComponent(Graphics g) {
        		g.drawImage(icon.getImage(),0,0,null);
        		setOpaque(false);
        		super.paintComponent(g);
        	}
        };
		panel.setLayout(new GridLayout(0, 2,2,2));
		
		
		top2 = new ImageIcon("header.png");
		b_jjang = new ImageIcon("b_jjang.png");
		b_ppap = new ImageIcon("b_ppap.png");
		c_jjang = new ImageIcon("c_jjang.png");
		c_ppap = new ImageIcon("c_ppap.png");
		header = new JLabel(top2);
		btn1 = new JButton(b_jjang);
		btn1.setRolloverIcon(c_jjang);
		btn2 = new JButton(b_ppap);
		btn2.setRolloverIcon(c_ppap);
		
		btn1.setBorderPainted(false);
		btn2.setBorderPainted(false);
		//btn1.setText("Â¯±¸ÀÇ ´Þ¸®±â ½ÃÇÕ!");
		//btn2.setText("±³¼ö´ÔÀÇ »ç°ÝÈÆ·Ã!");
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		btn1.setContentAreaFilled(false);
		btn2.setContentAreaFilled(false);
		
		btn1.setFocusPainted(false);
		btn2.setFocusPainted(false);
		
		btn1.setOpaque(false);
		btn2.setOpaque(false);
		

		
		//panel.add(header);
		panel.add(btn1);
		panel.add(btn2);
		add(header,BorderLayout.NORTH);
		add(panel, BorderLayout.SOUTH);
		setVisible(true);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn1) {
			EventQueue.invokeLater(new Runnable(){
				public void run() {
					// TODO Auto-generated method stub
					try {
						Frame frame = new Frame();
						frame.setVisible(true);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		else if(e.getSource()==btn2) {
			EventQueue.invokeLater(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				try {
					Frame2 frame2 = new Frame2();
					frame2.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		}
	}
}

