package demo.thread;

public class Day1 {

	public static void main(String[] args) {
		Day1Thread t1 = new Day1Thread("tt1");
		Day1Thread t2 = new Day1Thread("tt2");
		Day1Thread t3 = new Day1Thread("tt3");
		Day1Thread t4 = new Day1Thread("tt4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}
}

class Day1Thread extends Thread {
	
	String name;
	
	public Day1Thread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(int j=0; j<30; j++) {
			for(int i=-999999999; i<999999999;i ++) {
				
			}
			System.out.println("this :"+name);
		}
	}
}