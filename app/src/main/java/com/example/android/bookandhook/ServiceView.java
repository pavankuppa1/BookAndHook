package com.example.android.bookandhook;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;

public class ServiceView extends Activity {
	Spinner sp;
	ListView lv;
	ArrayList<String> arraylist = new ArrayList<String>();
	ArrayList<String> spinnerlist = new ArrayList<String>();
	public static final String ACCESS_KEY_ID = "AKIAJYTBALIAMS7Y772A";
	public static final String SECRET_KEY = "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";
	AmazonSimpleDBClient sdbClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_service_view);
		Bundle bb=getIntent().getExtras();
		final String sphn=bb.getString("sphn");
		lv = (ListView) findViewById(R.id.listView1);
		sp = (Spinner) findViewById(R.id.spinner1);
		spinnerlist.add("Photographer");
		spinnerlist.add("Designer");
		spinnerlist.add("Hairstylist");
		spinnerlist.add("Beautician");
		spinnerlist.add("Makeup Artist");
		spinnerlist.add("Travels");
		spinnerlist.add("Fitness Trainer");
		ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, spinnerlist);
		sp.setAdapter(adp1);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String spinvalue=sp.getSelectedItem().toString().trim();
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@      "+spinvalue);
				AWSCredentials credentials = new BasicAWSCredentials(
						ACCESS_KEY_ID, SECRET_KEY);
				sdbClient = new AmazonSimpleDBClient(credentials);
				ServicePerson_class afruit = new ServicePerson_class();
				afruit.createDomain();
				List<Others> list = afruit.getNAMES(spinvalue);
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

						System.out.println("image names       " + oo.getName()
								+ "===========");
					}
					lv = (ListView) findViewById(R.id.listView1);
					ArrayAdapter<String> adp = new ArrayAdapter<String>(ServiceView.this,
							android.R.layout.simple_list_item_1, arraylist);
					lv.setAdapter(adp);
					
			}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/*BasicAWSCredentials bs = new BasicAWSCredentials(ACCESS_KEY_ID,
				SECRET_KEY);
		AmazonSimpleDBClient as = new AmazonSimpleDBClient(bs);
		SelectResult selectResult = null;
		String sNextToken = null;
		String query = null;
		
		
		do {

			query = "select PLACE from TRAVELLOG_ADDPLACE";
			SelectRequest selectRequest = new SelectRequest(query);
			selectRequest.setNextToken(sNextToken);
			selectResult = as.select(selectRequest);
			sNextToken = selectResult.getNextToken();
			List<Item> list = selectResult.getItems();

			for (Item item : list) {
				item.getName(); // itemName
				List<Attribute> listAttribute = item.getAttributes();
				for (Attribute attribute : listAttribute) {
					attribute.getName(); // Attribute Name
					attribute.getValue(); // Attribute value;

					arraylist.add(attribute.getValue());
				}
			}
		} while (sNextToken != null);*/
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				String ename=lv.getItemAtPosition(position)
						.toString();
				Intent it=new Intent(ServiceView.this,ServicePersonDetails.class);
				it.putExtra("sphn", sphn);
				it.putExtra("empname", ename);
				startActivity(it);
				Toast.makeText(getApplicationContext(), ename, Toast.LENGTH_SHORT).show();
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_service_view, menu);
		return true;
	}

}
