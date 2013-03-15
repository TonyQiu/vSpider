package com.tonyqiu.vspider;

import org.apache.commons.lang3.StringUtils;

public class ImageUtils {

	private static String allowImageExt=",bmp,gif,jpg,jpeg,jpe,png,tif,";
	
	public static Image reverImage(String originalImgUrl, 
			String imageSrcBaseUrl, 
			String imageTargetBaseUrl,
			String localImageFolder
			) {
		String ext = "jpg";
		if(StringUtils.isBlank(originalImgUrl)) {
			return null;
		}
		//setep1. get extendsion
		String[] arrs=StringUtils.split(originalImgUrl,".");
		if(arrs != null) {
			if(StringUtils.contains(allowImageExt, arrs[arrs.length-1])) {
				ext = arrs[arrs.length-1];
			}
		}
		
		if(!StringUtils.startsWithAny(originalImgUrl, "http://","https://")) {
			originalImgUrl = imageSrcBaseUrl + originalImgUrl;
		}
		
		//get file url
		String md5Str = MD5Util.toMd5(new String(originalImgUrl).getBytes());
		String fstDir = md5Str.substring(30, 32);
		String secDir = md5Str.substring(28, 30);
		String thrDir = md5Str.substring(26, 28);
		
		String targetUrl = fstDir + "/" + secDir +"/" + thrDir + "/" + md5Str + "." + ext;
		
		
		return new Image(originalImgUrl, imageTargetBaseUrl + targetUrl, localImageFolder + targetUrl);
	}
}
