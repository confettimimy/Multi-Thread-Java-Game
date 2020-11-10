public class Monitor {
	int flag;
	
	public synchronized void monitor(int flag) {
		
		if(flag == 4 || this.flag ==4) {
			this.flag = flag;
			notifyAll();
		}
		
		while(flag < 4 && this.flag !=4) {
			try {
				wait();
			} catch (InterruptedException e) {		}
		}
	}

}
