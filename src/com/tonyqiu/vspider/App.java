package com.tonyqiu.vspider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.beanutils.BeanUtils;

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
	
	public static JobConfig jobConfig = new JobConfig();
	
	public static void main(String[] args) {
		new App();
	}
	
	/**
	 * 程序初始化
	 */
	public App() {
		
		initJobConfig();
		
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
	
	private void initJobConfig() {
		try {
			List<Map<String, Object>> configs = DbHelper.getJobConfig();
			Map<String, Object> map = configs.get(0);
			
			
			for(String key : map.keySet()) {
				Object value = map.get(key);
				key = StringHelper.makeAllWordFirstLetterUpperCase(key);
				key = StringHelper.uncapitalize(key);
				BeanUtils.setProperty(App.jobConfig, key, value);
			}
			
			//初始化列
			List<Map<String, Object>> columnConfigList = DbHelper.getJobColumnConfig(App.jobConfig.jobId);
			for(Map<String, Object> columnConfigMap : columnConfigList) {
				ContentColumn contentColumn = new ContentColumn();
				for(String key : columnConfigMap.keySet()) {
					Object value = columnConfigMap.get(key);
					key = StringHelper.makeAllWordFirstLetterUpperCase(key);
					key = StringHelper.uncapitalize(key);
					BeanUtils.setProperty(contentColumn, key, value);
				}
				App.jobConfig.columns.add(contentColumn);
			}
			System.out.println("初始化成功:"+App.jobConfig);
		}catch(Exception e) { //TODO 处理异常
			e.printStackTrace();
		}
	}
 
}