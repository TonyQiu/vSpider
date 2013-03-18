package com.tonyqiu.vspider;

public class Image extends SpiderUrl{

	
	public String url;
	public String myUrl;
	public String myDiskUrl;
	
	
	public Image(String originalUrl, String myUrl, String myDiskUrl) {
		super(originalUrl);
		this.url = originalUrl;
		this.myUrl = myUrl;
		this.myDiskUrl = myDiskUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMyUrl() {
		return myUrl;
	}

	public void setMyUrl(String myUrl) {
		this.myUrl = myUrl;
	}

	public String getMyDiskUrl() {
		return myDiskUrl;
	}

	public void setMyDiskUrl(String myDiskUrl) {
		this.myDiskUrl = myDiskUrl;
	}

	@Override
	public String toString() {
		return "Image [originalUrl=" + url + ", myUrl=" + myUrl
				+ ", myDiskUrl=" + myDiskUrl + "]";
	}

}
