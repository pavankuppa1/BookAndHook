package com.example.android.bookandhook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserMain_Page extends Activity {
	Button bt, booking;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_main__page);
		Bundle bb=getIntent().getExtras();
		final String sphn=bb.getString("sphn");

		bt=(Button)findViewById(R.id.button1);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it=new Intent(UserMain_Page.this,ServiceView.class);
				it.putExtra("sphn", sphn);
				startActivity(it);

			}
		});

		booking=(Button)findViewById(R.id.button3);
		booking.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it=new Intent(UserMain_Page.this,User_MyBookings.class);
				it.putExtra("sphn", sphn);
				startActivity(it);

			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_user_main__page, menu);
		return true;
	}

}
