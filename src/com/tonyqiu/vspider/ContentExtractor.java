package com.tonyqiu.vspider;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.beanutils.BeanUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ContentExtractor implements Runnable{

	
	public ContentExtractor() {
	}
	
	@Override
	public void run() {
		while(true) {
				
				if(App.detailUrlQueue.isEmpty()
						&& (!App.STATUS.isListPageThreadRunning())){
					break;
				}
			
			
			SpiderUrl spiderUrl = null;
			
			try {
				if(App.detailUrlQueue.isEmpty()){
					Thread.sleep(1000l);
					continue;
				}
				spiderUrl = App.detailUrlQueue.poll();
				
				Document detailDoc = Jsoup.parse(new URL(spiderUrl.url),App.HTTP_TIME_OUT);
				
				List<ContentColumn> colList = new ArrayList<ContentColumn>();
				for(ContentColumn colConf : App.jobConfig.columns) {
					ContentColumn col = new ContentColumn();
					BeanUtils.copyProperties(col, colConf);
					col.setParentUrl(spiderUrl.url);
					//是否要剥离图片
					if(col.needImageExtracted) {
						Elements imageEles = detailDoc.select(col.imageSelector);
						if(imageEles.isEmpty() == false) {
							for(Element imageEle : imageEles) {
								Image image = ImageUtils.reverImage(imageEle.attr("src"),
										col.imageSrcBaseUrl,
										col.imageTargetBaseUrl,
										col.localImageFolder
										);
								if(image != null) {
									App.imageQueue.add(image);
									imageEle.attr("src", image.getMyUrl());
								}
							}
						}
					}
					
					Elements elements = detailDoc.select(col.cssSelector);
					if(!elements.isEmpty()) {
						if(col.type == ColumnType.HTML) {
							col.value = elements.first().html();
						}else {
							col.value = elements.first().text();
						}
						
						
					}
					colList.add(col);
				}
				//加锁，以免不同步
				synchronized (App.columnsList) {
					App.columnsList.add(colList);
				}
			}catch(SocketTimeoutException ste) {
				spiderUrl.tryTimes += 1;
				App.detailUrlQueue.add(spiderUrl);
			}catch(Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
			
	}
}
