package com.tonyqiu.vspider;

public class ContentColumn {
	String name;
	String cssSelector;
	ColumnType type;
	
	String value;
	boolean needImageExtracted;
	String imageSelector;
	
	String parentUrl;
	
	public ContentColumn(String parentUrl, String name, String cssSelector) {
		super();
		this.parentUrl = parentUrl;
		this.name = name;
		this.cssSelector = cssSelector;
		this.type = ColumnType.TEXT;
	}

	public ContentColumn(String parentUrl, String name, String cssSelector, ColumnType type) {
		super();
		this.parentUrl = parentUrl;
		this.name = name;
		this.cssSelector = cssSelector;
		this.type = type;
	}
	public ContentColumn(String parentUrl, String name, String cssSelector, ColumnType type, boolean needImageExtracted, String imageSelector) {
		super();
		this.parentUrl = parentUrl;
		this.name = name;
		this.cssSelector = cssSelector;
		this.type = type;
		this.needImageExtracted = needImageExtracted;
		this.imageSelector = imageSelector;
	}

	@Override
	public String toString() {
		return "ContentColumn [name=" + name + ", cssSelector=" + cssSelector
				+ ", type=" + type + ", value=" + value + "]";
	}
	
	
	
}
