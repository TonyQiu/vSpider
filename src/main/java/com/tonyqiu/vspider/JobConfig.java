package com.tonyqiu.vspider;

import java.util.ArrayList;
import java.util.List;

public class JobConfig {
	
	public String name;
	public Integer jobId;
	public String listPageUrl;
	public String detailAnchorSelector;
	public String nextPageSelector;
	public int oneBatchColumnsToSaveDb;
	
	public List<ContentColumn> columns = new ArrayList<ContentColumn>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getListPageUrl() {
		return listPageUrl;
	}

	public void setListPageUrl(String listPageUrl) {
		this.listPageUrl = listPageUrl;
	}

	public String getDetailAnchorSelector() {
		return detailAnchorSelector;
	}

	public void setDetailAnchorSelector(String detailAnchorSelector) {
		this.detailAnchorSelector = detailAnchorSelector;
	}

	public String getNextPageSelector() {
		return nextPageSelector;
	}

	public void setNextPageSelector(String nextPageSelector) {
		this.nextPageSelector = nextPageSelector;
	}

	public int getOneBatchColumnsToSaveDb() {
		return oneBatchColumnsToSaveDb;
	}

	public void setOneBatchColumnsToSaveDb(int oneBatchColumnsToSaveDb) {
		this.oneBatchColumnsToSaveDb = oneBatchColumnsToSaveDb;
	}

	public List<ContentColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<ContentColumn> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "JobConfig [name=" + name + ", jobId=" + jobId
				+ ", listPageUrl=" + listPageUrl + ", detailAnchorSelector="
				+ detailAnchorSelector + ", nextPageSelector="
				+ nextPageSelector + ", oneBatchColumnsToSaveDb="
				+ oneBatchColumnsToSaveDb + ", columns=" + columns + "]";
	}

}
