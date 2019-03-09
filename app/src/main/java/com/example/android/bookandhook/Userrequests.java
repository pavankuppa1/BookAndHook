package com.example.android.bookandhook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Userrequests extends Activity {
	ListView lv;
	String mynumber,sno;
	TextView empwork;
	ArrayList<String> arraylist = new ArrayList<String>();
	public static final String ACCESS_KEY_ID = "AKIAJYTBALIAMS7Y772A";
	public static final String SECRET_KEY = "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";
	AmazonSimpleDBClient sdbClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_first_page);
		Bundle bb=getIntent().getExtras();
		final String seid=bb.getString("eid").trim();
		empwork=(TextView)findViewById(R.id.textView2);
		lv = (ListView) findViewById(R.id.listView1);
		BasicAWSCredentials bs = new BasicAWSCredentials(ACCESS_KEY_ID,
				SECRET_KEY);
		AmazonSimpleDBClient as = new AmazonSimpleDBClient(bs);
		SelectResult selectResult = null;
		String sNextToken = null;
		String query = null;
		do {

			query = "select EMP_WORK from BOOKHOOK_USERBOOK where EMP_ID='"+seid+"'";

			SelectRequest selectRequest = new SelectRequest(query);
			selectRequest.setNextToken(sNextToken);
			selectResult = as.select(selectRequest);
			sNextToken = selectResult.getNextToken();
			List<Item> list2 = selectResult.getItems();

			for (Item item : list2) {
				item.getName(); // itemName
				List<Attribute> listAttribute = item.getAttributes();
				for (Attribute attribute : listAttribute) {
					attribute.getName(); // Attribute Name
					attribute.getValue(); // Attribute value;

					//arraylist.add(attribute.getValue());
					empwork.setText(attribute.getValue().trim());

				}
			}
		} while (sNextToken != null);


		final String sempwork=empwork.getText().toString().trim();

		AWSCredentials credentials = new BasicAWSCredentials(
				ACCESS_KEY_ID, SECRET_KEY);
		sdbClient = new AmazonSimpleDBClient(credentials);
		UserBook_Class afruit = new UserBook_Class();
		afruit.createDomain();
		List<Others> list = afruit.getnum(sempwork);
		arraylist.clear();
		int len = list.size();
		if (len == 0) {
			Toast.makeText(getApplicationContext(), "Not Available", 90)
					.show();
		} else {
			for (int i = 0; i < len; i++) {

				Others oo = list.get(i);
				String imagename = oo.getName();

				arraylist.add(imagename);
				String s[] = imagename.split("			");
				mynumber = s[0];
				sno = s[1];

				System.out.println("image names       " + oo.getName()
						+ "===========");
			}
			lv = (ListView) findViewById(R.id.listView1);
			ArrayAdapter<String> adp = new ArrayAdapter<String>(Userrequests.this,
					android.R.layout.simple_list_item_1, arraylist);
			lv.setAdapter(adp);

		}


		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
									long arg3) {
				// TODO Auto-generated method stub
				String data=lv.getItemAtPosition(position).toString().trim();
				String s[] = data.split("			");
				String snum = s[0];
				String sno = s[1];
				System.out.println("!!!!!!!!!!!!!!!!!!!1              "+seid+sempwork+snum+sno);
				Intent it=new Intent(Userrequests.this,UserRequestDetails
						.class);
				it.putExtra("eid", seid);
				it.putExtra("ework", sempwork);
				it.putExtra("uphn", snum);
				it.putExtra("sno", sno);
				startActivity(it);
				Toast.makeText(getApplicationContext(), mynumber, Toast.LENGTH_SHORT).show();
			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_user_first_page, menu);
		return true;
	}

}
