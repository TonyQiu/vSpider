package com.tonyqiu.vspider;

public class Reporter implements Runnable{
	
	@Override
	public void run() {
		System.out.println("列表线程数\t详细线程数\t详细页剩余数量\t图片线程数\t图片剩余数量\t详细页产出\t数据库保存线程\t系统状态");
		while(true) {
			try {
				
				int detailRunning = 0;
				for(Thread t: App.detailThreadList) {
					
					if(t.isAlive()) {
						detailRunning +=1;
					}
				}
				
				int imageRunning = 0;
				for(Thread t: App.imageThreadList) {
					
					if(t.isAlive()) {
						imageRunning +=1;
					}
				}
				
				System.out.printf("%s\t\t%s/%s\t\t%s\t\t%s/%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
						1,
						App.detailThreadList.size(),detailRunning,
						App.detailUrlQueue.size(),
						App.imageThreadList.size(),imageRunning,
						App.imageQueue.size(),
						App.columnsList.size(), App.contentDbSaverThreadList.size(),
						App.STATUS
						);
				Thread.sleep(1000l);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
