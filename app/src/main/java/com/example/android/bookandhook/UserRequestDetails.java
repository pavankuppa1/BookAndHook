package com.example.android.bookandhook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserRequestDetails extends Activity {
TextView tv1,tv2,tv3,tv4,tv5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_request_details);
		Bundle bb=getIntent().getExtras();
		final String seid=bb.getString("eid").trim();
		final String sework=bb.getString("ework").trim();
		final String suphn=bb.getString("uphn").trim();
		final String ssno=bb.getString("sno").trim();
		tv1=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.textView2);
		tv3=(TextView)findViewById(R.id.textView3);
		tv4=(TextView)findViewById(R.id.textView4);
		tv1.setText(seid);
		tv2.setText(sework);
		tv3.setText(suphn);
		tv4.setText(ssno);
		Button accept=(Button)findViewById(R.id.button1);
		accept.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			  	UserBook_Class ulist = new UserBook_Class();
				ulist.createDomain();
				ulist.UpdateRequest(ssno);
				
				Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_request_details, menu);
		return true;
	}

}
