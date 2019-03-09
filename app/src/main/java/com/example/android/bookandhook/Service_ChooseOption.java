package com.example.android.bookandhook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Service_ChooseOption extends Activity {
	Button upimage, viewreq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service__choose_option);
		Bundle bb=getIntent().getExtras();
		final String seid=bb.getString("eid");
		upimage = (Button) findViewById(R.id.button1);
		viewreq = (Button) findViewById(R.id.button2);
		upimage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Service_ChooseOption.this,UpdateProfDetails.class);
				it.putExtra("eid", seid);
				startActivity(it);
			}
		});

		viewreq.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Service_ChooseOption.this,Userrequests.class);
				it.putExtra("eid", seid);
				startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.service__choose_option, menu);
		return true;
	}

}
