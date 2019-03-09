package com.example.android.bookandhook;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class User_MyBookings extends Activity {
	
	ListView lv;
	ArrayList<String> arraylist = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user__my_bookings);
		Bundle bb = getIntent().getExtras();
		final String sphn = bb.getString("sphn");
		lv = (ListView) findViewById(R.id.listView1);

		UserBook_Class ab = new UserBook_Class();
		ab.createDomain();
		List<Others> list = ab.getdetails(sphn);
		int len = list.size();

		for (int i = 0; i < len; i++) {

			Others oo = list.get(i);
			String imagename = oo.getName();


			arraylist.add(imagename);
			String s[] = imagename.split("                                  ");
			String num = s[0];
			String name = s[1];
			System.out.println("image names       " + oo.getName()
					+ "===========");
			System.out.println("#####################################      " + num + "   " + name);
		}

		ArrayAdapter<String> adp = new ArrayAdapter<String>(User_MyBookings.this,
				android.R.layout.simple_list_item_1, arraylist);
		lv.setAdapter(adp);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user__my_bookings, menu);
		return true;
	}
}
