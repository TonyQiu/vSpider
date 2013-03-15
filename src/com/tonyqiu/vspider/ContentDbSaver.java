package com.tonyqiu.vspider;

import java.util.ArrayList;
import java.util.List;

public class ContentDbSaver implements Runnable{

	public void run() {
		List<List<ContentColumn>> listToSaved = null;
		while(App.columnsList.isEmpty() == false || App.STATUS.isDetailPageThreadRunning()) {
			if(App.columnsList.size() < App.jobConfig.oneBatchColumnsToSaveDb
					) {
				if(App.STATUS.isDetailPageThreadRunning()) {
					try {
						Thread.sleep(2000l);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else {
					//TODO 全部存到数据库
					listToSaved = new ArrayList<List<ContentColumn>>(App.columnsList);
					App.columnsList.clear();
					try {
						DbHelper.saveColumns(listToSaved);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}else {
				
				//加锁,以免不同步
				synchronized (App.columnsList) {
					List<List<ContentColumn>> tmpList = App.columnsList.subList(0, App.jobConfig.oneBatchColumnsToSaveDb);
					listToSaved = new ArrayList<List<ContentColumn>>(tmpList);
					tmpList.clear();
				}
				//TODO 保存到数据库
				try {
					DbHelper.saveColumns(listToSaved);
//				System.out.println(listToSaved+"1");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
