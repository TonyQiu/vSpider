package demo;

public class TryDemo {

	public static void main(String[] args) {
		for(int j=0;j<=10;j++) {
			try {
				for(int i=1;i<10;i++) {
					if(i==j) {
						continue;
					}
				}
			}finally {
				System.out.printf("pritn finnally, j=%",j);
			}
		}
	}
}
