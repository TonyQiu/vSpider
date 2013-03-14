package com.tonyqiu.vspider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class App {

	public static Set<String> historyUrls = new HashSet<String>();
	public static ConcurrentLinkedQueue<SpiderUrl> detailUrlQueue = new ConcurrentLinkedQueue<SpiderUrl>();
	public static ConcurrentLinkedQueue<Image> imageQueue = new ConcurrentLinkedQueue<Image>();
	public static List<Thread> listThreadList = new ArrayList<Thread>(); 
	public static List<Thread> detailThreadList = new ArrayList<Thread>(); 
	public static List<Thread> imageThreadList = new ArrayList<Thread>(); 
	public static List<Thread> contentDbSaverThreadList = new ArrayList<Thread>();
	public static int HTTP_TIME_OUT = 10000;
	public static volatile AppStatus STATUS = new AppStatus();
	
	public static List<List<ContentColumn>> columnsList = Collections.synchronizedList(new ArrayList<List<ContentColumn>>());
	
	public static void main(String[] args) {
		
		//初始化historyUrls，把历史记录都装载进来。
		
		
		//做法1：用java1.5以上自带的线程池 ExecuteService
		//list: http://gz.house.163.com/exclusive/
		//detail: http://gz.house.163.com/13/0109/17/8KPVIEV800873C6D.html
		//listPageExecutor.execute(new ListPageBreaker());
		
		//做法2：自己开线程
		ListPageBreaker lpb = new ListPageBreaker();
		for( int i=0; i<1; i++) {
			Thread t = new Thread(lpb);
			t.start();
			listThreadList.add(t);
		}
		
		ContentExtractor ce = new ContentExtractor();
		for(int i=0; i<=4; i++) {
			Thread t = new Thread( ce );
			t.start();
			detailThreadList.add(t);
		}
		
		ImageDownloader il = new ImageDownloader();
		for(int i=0; i<=4; i++) {
			Thread t = new Thread( il );
			t.start();
			imageThreadList.add(t);
		}
		
		ContentDbSaver cd = new ContentDbSaver();
		for (int i = 0; i < 1; i++) {
			Thread t = new Thread(cd);
			t.start();
			contentDbSaverThreadList.add(t);
		}
		//启动报表线程
		new Thread(new Reporter()).start();
	}
	
 
}