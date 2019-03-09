package com.example.android.bookandhook;

public class GetbucketName {
	
	static String bucketName="AndroidAmazon",date,size,foldername="BandH";
   static boolean flag=true;
  // static boolean listflag=true;
	public String getBucketName() {
		return bucketName;
	}
	public static void setBucketName(String bucktname) {	
		GetbucketName.bucketName=bucktname;
	}
	public static String getDate() {
		return date;
	}
	public static void setDate(String date) {
		GetbucketName.date = date;
	}
	public static String getSize() {
		return size;
	}
	public static void setSize(String size) {
		GetbucketName.size = size;
	}
	public static String getFoldername() {
		return foldername;
	}
	public static void setFoldername(String foldername) {
		GetbucketName.foldername = foldername;
	}
	
	

}
