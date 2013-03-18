package demo.jsoup;

import java.net.URL;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HelloWorld {

	public static void main(String[] args) {
		try {
//			Long s = new Date().getTime();
//			Document d = Jsoup.parse(new URL("http://sports.163.com/13/0107/21/8KL6L20F00052UUC.html#p=8KHNASDV538T0005"), 5000);
//			Long e = new Date().getTime();
//			System.out.println(d);
//			System.out.println(e-s);
			int a = 0x1;
			int b = 0x2;
			System.out.println(a | b);
		}catch(Exception e) {
			e.printStackTrace();
		}
	} 
}
