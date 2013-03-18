package demo.jsoup;


import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class News163Cba {

	
	
	public static Set<String> getDetailList(Document d, boolean recursive) throws IOException{
		Set<String> links = new HashSet<String>();
		
		Elements els =d.select("div.col2 ul.articleList span.articleTitle a");
		for(Element e : els) {
			System.out.println(e.attr("href"));
			links.add(e.attr("href"));
		}
		Elements nextPageEls = d.select("ol.pages li.next a");
		if(recursive == false  
				|| nextPageEls.isEmpty()
				|| ! nextPageEls.first().attr("href").startsWith("http://")
				) {
			return links;
		}else {
			Document nextD = Jsoup.parse(new URL(nextPageEls.first().attr("href")), 5000);
			links.addAll(getDetailList(nextD, recursive));
			return links;
		}
	}
	
	
	public static void main(String[] args) {
		try {
			TimeWatch timeWatch = TimeWatch.start();
			String url = "http://sports.163.com/special/0005314V/moregdhy.html";
			Document d = Jsoup.parse(new URL(url), 5000);
			Set<String> links = News163Cba.getDetailList(d, true);
			System.out.println(timeWatch.time());
			System.out.println(links.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
