package com.tonyqiu.vspider;

public class ContentColumn {
	public String name;
	public String cssSelector;
	public String type;
	
	public String value;
	boolean needImageExtracted;
	public String imageSelector;
	
	public String parentUrl;
	public String imageSrcBaseUrl;
	public String imageTargetBaseUrl;
	public String localImageFolder;
	
	public ContentColumn(String parentUrl, String name, String cssSelector) {
		super();
		this.parentUrl = parentUrl;
		this.name = name;
		this.cssSelector = cssSelector;
		this.type = ColumnType.TEXT;
	}

	public ContentColumn(String parentUrl, String name, String cssSelector, String type) {
		super();
		this.parentUrl = parentUrl;
		this.name = name;
		this.cssSelector = cssSelector;
		this.type = type;
	}
	public ContentColumn(String parentUrl, String name, String cssSelector, String type, boolean needImageExtracted, String imageSelector) {
		super();
		this.parentUrl = parentUrl;
		this.name = name;
		this.cssSelector = cssSelector;
		this.type = type;
		this.needImageExtracted = needImageExtracted;
		this.imageSelector = imageSelector;
	}

	
	public ContentColumn() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCssSelector() {
		return cssSelector;
	}

	public void setCssSelector(String cssSelector) {
		this.cssSelector = cssSelector;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isNeedImageExtracted() {
		return needImageExtracted;
	}

	public void setNeedImageExtracted(boolean needImageExtracted) {
		this.needImageExtracted = needImageExtracted;
	}

	public String getImageSelector() {
		return imageSelector;
	}

	public void setImageSelector(String imageSelector) {
		this.imageSelector = imageSelector;
	}

	public String getParentUrl() {
		return parentUrl;
	}

	public void setParentUrl(String parentUrl) {
		this.parentUrl = parentUrl;
	}

	public String getImageSrcBaseUrl() {
		return imageSrcBaseUrl;
	}

	public void setImageSrcBaseUrl(String imageSrcBaseUrl) {
		this.imageSrcBaseUrl = imageSrcBaseUrl;
	}

	public String getImageTargetBaseUrl() {
		return imageTargetBaseUrl;
	}

	public void setImageTargetBaseUrl(String imageTargetBaseUrl) {
		this.imageTargetBaseUrl = imageTargetBaseUrl;
	}

	public String getLocalImageFolder() {
		return localImageFolder;
	}

	public void setLocalImageFolder(String localImageFolder) {
		this.localImageFolder = localImageFolder;
	}

	@Override
	public String toString() {
		return "ContentColumn [name=" + name + ", cssSelector=" + cssSelector
				+ ", type=" + type + ", value=" + value + "]";
	}
	
	
	
}
