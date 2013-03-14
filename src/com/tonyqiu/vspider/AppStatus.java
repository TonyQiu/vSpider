package com.tonyqiu.vspider;


class AppStatus {
//	public static int ALL_THREAD_STOP= 0x0;
//	public static int LIST_PAGE_THREAD_RUNNING = 0x1;
//	public static int DETAIL_PAGE_THREAD_RUNNING = 0x2;
//	public static int IMAGE_DOWNLOAD_THREAD_RUNNING = 0x4;
	
	private Integer listPageThreadRunning = 0;
	
	private Integer detailPageThreadRunning = 0;
	
	private Integer imageDownloadThreadRunning = 0;
	
	private Integer contentDbSaverRunning = 0;
	
	public synchronized boolean isListPageThreadRunning() {
		listPageThreadRunning = 0;
		for(Thread t: App.listThreadList) {
			if(t.isAlive()) {
				listPageThreadRunning +=1;
			}
		}
		return listPageThreadRunning!=0;
	}
	public synchronized boolean isDetailPageThreadRunning() {
		detailPageThreadRunning = 0;
		for(Thread t: App.detailThreadList) {
			if(t.isAlive()) {
				detailPageThreadRunning +=1;
			}
		}
		return detailPageThreadRunning != 0;
	}
	public synchronized boolean isImageDownloadThreadRunning() {
		imageDownloadThreadRunning = 0;
		for(Thread t: App.imageThreadList) {
			if(t.isAlive()) {
				imageDownloadThreadRunning +=1;
			}
		}
		return imageDownloadThreadRunning !=0;
	}
	public synchronized boolean isContentDbSaverRunning() {
		contentDbSaverRunning = 0;
		for(Thread t: App.contentDbSaverThreadList) {
			if(t.isAlive()) {
				contentDbSaverRunning +=1;
			}
		}
		return contentDbSaverRunning !=0;
	}
	public boolean isAllThreadStop() {
		if(listPageThreadRunning == 0
				&& detailPageThreadRunning == 0
				&& imageDownloadThreadRunning == 0
				&& contentDbSaverRunning == 0
				) {
			return true;
		}
		return false;
			
	}
	
	public String toString() {
		return String.format("L:%s(%s),D:%s(%s),I:%s(%s),D:%s(%s)", 
				isListPageThreadRunning(),listPageThreadRunning,
				isDetailPageThreadRunning(),detailPageThreadRunning,
				isImageDownloadThreadRunning(),imageDownloadThreadRunning,
				isContentDbSaverRunning(), contentDbSaverRunning
				);
	}
}