import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class MyLabel extends JLabel{
    int barSize=0;//바의 크기
    int maxBarSize;
    MyLabel(int maxBarSize){
        this.maxBarSize=maxBarSize;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.magenta);
        int width =(int)(((double)(this.getWidth()))/maxBarSize*barSize);
        if(width==0) return;//크기가 0이면 바를 그릴 필요 없음
        g.fillRect(0,0,width,this.getHeight());
    }
    synchronized void fill(){
        if(barSize==maxBarSize){
            try{
                this.wait();//바의 크기가 최대이면, ConsumerThread에 의해 바의 크기가 줄어들때까지 대기
            }
            catch(Exception e){
                return;
            }
        }
        barSize++;
        this.repaint();//바 다시그리기
        this.notify();//기다리는 ConsumerThread 스레드 깨우기
    }
    synchronized void consume(){
        if(barSize==0){
            try{
                this.wait();//바의 크기가 0이면 바의 크기가 0보다 커질때까지 대기
            }
            catch(Exception e){
                return;
            }
        }
        barSize--;
        this.repaint();//바 다시 그리기
        this.notify();//기다리는 이벤트 스레드 깨우기
    }
}

class ConsumerThread extends Thread{
    MyLabel con;
    ConsumerThread(MyLabel con){
        this.con=con;
    }
    public void run(){
        while(true){
            try{
                sleep(200);
                con.consume();//0.2초마다 바를 1씩 줄인다.
            }
            catch(Exception e){
                return;
            }
        }
    }
}

class TabAndThreadEx extends JFrame{
	static JLabel la;
    MyLabel bar = new MyLabel(100);//바의 최대 크기를 100으로 지정
    TabAndThreadEx(){
        this.setTitle("바 채우기");
		addWindowListener(new WindowAdapter() {
         	public void windowClosing(WindowEvent e) {
            	setVisible(false);
            dispose();
         }
      });
        this.setLayout(null);
        
        bar.setBackground(Color.orange);
        bar.setOpaque(true);
        bar.setLocation(20, 50);
        bar.setSize(300,20);
        this.add(bar);
        //키 리스너 등록
        this.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                bar.fill();//키를 누를때마다 바가 1씩 증가
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
            
        });
        
        setLayout(new GridLayout(2, 1));
        MyThread thread = new MyThread();
        la=new JLabel();
        la.setFont(new Font("Gothic", Font.ITALIC, 80));
        this.add(la);
        
        this.setLocationRelativeTo(null);
        this.setSize(350,200);
        this.setVisible(true);
        this.requestFocus();//키 처리권 부여
        ConsumerThread th = new ConsumerThread(bar);//스레드 생성
        th.start();//스레드 시작
        thread.start();
    }
    
    class MyThread extends Thread{
        public void run(){
            int n=60;
            while(true){
                la.setText(Integer.toString(n--));
                try{
                    sleep(1000);
                }
                catch(Exception e){
                    return;
                }
            }
        }
    }

}









