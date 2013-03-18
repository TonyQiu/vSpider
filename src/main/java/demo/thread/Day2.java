package demo.thread;

public class Day2 {

	public static void main(String[] args) {
		Day2Thread r = new Day2Thread();
		
		
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(r);
		Thread t4 = new Thread(r);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}
}

class Day2Thread implements Runnable {
	
	private Integer tickle = 100;
	Object lock = new Object();
	@Override
	public void run() {
		while(true) {
			synchronized (lock) {
				
				if(tickle > 0) {
					
					System.out.printf("Thread %s sell tickle:%s\n", Thread.currentThread().getName(),tickle);
					tickle = tickle -1;
				}else {
					break;
				}
			}
		}
	}
}