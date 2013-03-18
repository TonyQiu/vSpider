package demo.networking;

import java.io.BufferedInputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;

public class ProxyDemo {

	public static void main(String[] args) throws Exception{
		//221.211.131.18:6666
		//127.0.0.1:58087
		SocketAddress addr = new InetSocketAddress("127.0.0.1", 58087);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
		URL url = new URL("http://www.baidu.com/");
		URLConnection conn = url.openConnection(proxy);
		byte[] bytes = new byte[200];
		new BufferedInputStream(conn.getInputStream()).read(bytes);
		System.out.println(new String(bytes));
		
	}
}
