package com.example.android.bookandhook;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ImagevActivity extends Activity{
	
	protected Handler mHandler;
	protected TextView loadingText;
	protected ImageView bodyText;
	protected String bucketName;
	protected String objectName;
	protected InputStream objectData;
	
	private final Runnable postResults = new Runnable() {
		public void run(){
			updateUi();
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_imagev);
        Bundle extras = this.getIntent().getExtras();
        bucketName = extras.getString("bucketname");
        objectName = extras.getString("imagename").trim();
        mHandler = new Handler();
        loadingText = (TextView) findViewById(R.id.item_view_loading_text);
        bodyText = (ImageView) findViewById(R.id.ImageView01);
        startPopulateText();
    }
    
    private void startPopulateText(){
    	Thread t = new Thread() {
    		@Override
    		public void run(){
    			System.out.println("object Name is "+GetbucketName.bucketName);
    			objectData =S3.getImageForObject(GetbucketName.bucketName, GetbucketName.getFoldername()+"/"+objectName.trim());
    	        mHandler.post(postResults);
    		}
    	};
    	t.start();
    }
    
    private void updateUi(){
    	System.out.println("hai pavan");
    	try {
			byte[] byteArray = objectName.getBytes("UTF-16LE");
			System.out.println(byteArray.length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	loadingText.setText(objectName);
    	Bitmap  myBitmap = BitmapFactory.decodeStream(objectData);
    	
   /* BufferedInputStream  bis=new BufferedInputStream(objectData);
     ByteArrayBuffer baf=new ByteArrayBuffer(50);
     int current=0;
     try {
		while((current=bis.read())!=-1){
			 baf.append((byte)current);
		 }
		  long startTime = System.currentTimeMillis();
		FileOutputStream fout=new FileOutputStream(objectName);
		fout.write(baf.toByteArray());
		fout.close();
		   Log.d("ImageManager", "download ready in"
                   + ((System.currentTimeMillis() - startTime) / 1000)
                   + " sec");

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
     
    	bodyText.setImageBitmap(myBitmap);
    	loadingText.setTextSize(16);
    }
  		
}
