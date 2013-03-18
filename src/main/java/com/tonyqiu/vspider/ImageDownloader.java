package com.tonyqiu.vspider;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FileUtils;

public class ImageDownloader implements Runnable{

	
	public ImageDownloader() {
	}
	
	/**
	 * TODO 添加功能，图片获取失败自动重试
	 */
	@Override
	public void run() {
		
		while(true) {
			try {
					
				//如果图片队列为空,并且详细线程池已经停止,那么图片下载线程应该停止
				if(App.imageQueue.isEmpty()
						&& (!App.STATUS.isDetailPageThreadRunning())){
//					System.out.printf("imageQueue:%s, AppStatus:%s\n",App.imageQueue.size(),App.STATUS);
					break;
				}else if(App.imageQueue.isEmpty()){
					Thread.sleep(1000l);
					continue;
				}
				
				Image image = App.imageQueue.poll();
				if(image == null) {
					continue;
				}
				File file = new File(image.getMyDiskUrl());
				if(file.exists()) {
					continue;
				}
				file.getParentFile().mkdirs();
				URLConnection con = new URL(image.getUrl()).openConnection();
				con.setReadTimeout(10 * 1000);
				FileUtils.copyInputStreamToFile(con.getInputStream(), file);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
