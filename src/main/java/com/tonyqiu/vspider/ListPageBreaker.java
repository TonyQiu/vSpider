package com.tonyqiu.vspider;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ListPageBreaker implements Runnable{

	
	
	private Document d = null;
	private boolean recursive = true;
	
	public ListPageBreaker() {
		try {
			List<String> historyUrlInDb = DbHelper.getHitoryUrl();
			App.historyUrls.addAll(historyUrlInDb);
			d = Jsoup.parse(new URL(App.jobConfig.listPageUrl),App.HTTP_TIME_OUT);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			try {
				extractDetailUrlFromListPage(d, recursive);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void extractDetailUrlFromListPage(Document listPageDoc, boolean recursive) throws IOException{
		Elements els =listPageDoc.select(App.jobConfig.detailAnchorSelector);
		for(Element e : els) {
			String href =e.attr("href");
			if(!App.historyUrls.contains(href)) {
				App.detailUrlQueue.add(new SpiderUrl(href));
				App.historyUrls.add(href);
			}
		}
		Elements nextPageEls = listPageDoc.select(App.jobConfig.nextPageSelector);
		if(recursive == false  
				|| nextPageEls.isEmpty()
				|| ! nextPageEls.first().attr("href").startsWith("http://")
				) {
			return;
		}else {
			Document nextD = Jsoup.parse(new URL(nextPageEls.first().attr("href")), 5000);
			extractDetailUrlFromListPage(nextD, recursive);
			return;
		}
	}
}
