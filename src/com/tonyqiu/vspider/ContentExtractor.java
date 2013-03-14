package com.tonyqiu.vspider;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ContentExtractor implements Runnable{

	
	private List<ContentColumn> columns ;
	
	public ContentExtractor() {
	}
	
	@Override
	public void run() {
		while(true) {
				
				if(App.detailUrlQueue.isEmpty()
						&& (!App.STATUS.isListPageThreadRunning())){
					break;
				}
			
			
			SpiderUrl url = null;
			
			try {
				if(App.detailUrlQueue.isEmpty()){
					Thread.sleep(1000l);
					continue;
				}
				url = App.detailUrlQueue.poll();
				
				Document detailDoc = Jsoup.parse(new URL(url.url),App.HTTP_TIME_OUT);
				
				columns = new ArrayList<ContentColumn>();
				columns.add(new ContentColumn(url.url,"title", "h1#h1title"));
				columns.add(new ContentColumn(url.url,"time", "div.endContent span.info span",ColumnType.TIMESTAMP));
				columns.add(new ContentColumn(url.url,"summury", "div.endContent p.summary"));
				columns.add(new ContentColumn(url.url,"content", "div.ep-content-main div#endText",ColumnType.HTML,true,"img"));
				for(ContentColumn col : columns) {
					//是否要剥离图片
					if(col.needImageExtracted) {
						Elements imageEles = detailDoc.select(col.imageSelector);
						if(imageEles.isEmpty() == false) {
							for(Element imageEle : imageEles) {
								Image image = ImageUtils.reverImage(imageEle.attr("src"));
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
				}
				//加锁，以免不同步
				synchronized (App.columnsList) {
					App.columnsList.add(columns);
				}
			}catch(SocketTimeoutException ste) {
				url.tryTimes += 1;
				App.detailUrlQueue.add(url);
			}catch(Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
			
	}
}
