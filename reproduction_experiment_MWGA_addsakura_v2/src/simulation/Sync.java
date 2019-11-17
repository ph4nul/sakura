package simulation;

public class Sync extends Thread{
	int cnt;
	
	public Sync() {
		cnt=0;
	}
	
	public synchronized void addSync() {
		cnt++;
	}
	
	public synchronized void delSync() {
		cnt--;
		try {
			notify();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public synchronized void waitSync() {
	    while (cnt > 0) {
	      try {
	        wait();
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }
	    }
	  }
}
