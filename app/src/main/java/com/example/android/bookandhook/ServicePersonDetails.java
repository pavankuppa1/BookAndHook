package com.example.android.bookandhook;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;

public class ServicePersonDetails extends Activity {
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,clouddate;
	Button book, bdate;
	public static final String ACCESS_KEY_ID = "AKIAJYTBALIAMS7Y772A";
	public static final String SECRET_KEY = "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";
	AmazonSimpleDBClient sdbClient;
	ListView lv;
	String eid, displaydate;
	ArrayList<String> arraylist = new ArrayList<String>();

	private int year;
	private int month;
	private int day;

	static final int DATE_PICKER_ID = 1111;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_person_details);
		Bundle bb=getIntent().getExtras();
		final String sphn=bb.getString("sphn");
		final String sempname=bb.getString("empname");
		tv1=(TextView)findViewById(R.id.textView2);
		tv2=(TextView)findViewById(R.id.textView3);
		tv3=(TextView)findViewById(R.id.textView4);
		tv4=(TextView)findViewById(R.id.textView5);
		tv5=(TextView)findViewById(R.id.textView6);
		tv6=(TextView)findViewById(R.id.textView7);
		tv7=(TextView)findViewById(R.id.textView8);
		clouddate=(TextView)findViewById(R.id.textView9);
		book = (Button) findViewById(R.id.button1);
		lv = (ListView) findViewById(R.id.listView1);
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY_ID,
				SECRET_KEY);
		sdbClient = new AmazonSimpleDBClient(credentials);
		ServicePerson_class ulist = new ServicePerson_class();
		ulist.createDomain();

		List<Item> list = ulist.getempnames(sempname);

		int len = list.size();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@    "+list.size());
		/*
		 * if (len == 0) { Toast.makeText(getApplicationContext(),
		 * "Not Avialable", 90) .show(); }else {
		 */
		System.out.println("devaaaaaaaaaa" + list);
		String data1 = list.toString();
		String[] a1 = data1.split(":");

		String p1 = a1[4];
		String p2 = a1[12];
		String p3 = a1[14];
		String p4 = a1[16];
		String p5 = a1[18];
		String p6 = a1[22];
		String p7 = a1[24];

		String[] s1 = p1.split(",");
		String[] s2 = p2.split(",");
		String[] s3 = p3.split(",");
		String[] s4 = p4.split(",");
		String[] s5 = p5.split(",");
		String[] s6 = p6.split(",");
		String[] s7 = p7.split(",");

		final String ework = s1[0].trim();;
		final String eid = s2[0].trim();
		final String empphn = s3[0].trim();;
		final String empex = s4[0].trim();;
		final String empname = s5[0].trim();
		final String empadd = s6[0].trim();;
		final String empemail = s7[0].trim();

		tv1.setText("Employee Work	      :   " + ework);
		tv2.setText("Employee ID	      :   " + eid);
		tv3.setText("Employee Phone       :   " + empphn);
		tv4.setText("Employee Experience  :   " + empex);
		tv5.setText("Employee Name        :   " + empname);
		tv6.setText("Employee Address     :   " + empadd);
		tv7.setText("Employee Email       :   " + empemail);


		BasicAWSCredentials bs = new BasicAWSCredentials(ACCESS_KEY_ID,
				SECRET_KEY);
		AmazonSimpleDBClient as = new AmazonSimpleDBClient(bs);
		SelectResult selectResult = null;
		String sNextToken = null;
		String query = null;
		// ArrayList<String> al = new ArrayList<String>();



		book.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int max=100;
				int min= 1;
				Random randomnum=new Random();
				int show=min+randomnum.nextInt(max);
				System.out.println("**********************  "+show);
				String rnum=Integer.toString(show);
				String sbdate=bdate.getText().toString().trim();
				String scdate=clouddate.getText().toString().trim();
				System.out.println("@@@@@@@@@@@@@@@@222222222222222   "+displaydate+"   "+scdate);
				if (sbdate.equals(scdate)) {
					Toast.makeText(getApplicationContext(), "Service person already booked", Toast.LENGTH_SHORT).show();
				}else {
					UserBook_Class ulist=new UserBook_Class();
					ulist.createDomain();
					ulist.BookService(sphn,ework,eid,empphn,empex,empname,empadd,empemail,sbdate,rnum);

					Toast.makeText(getApplicationContext(),
							"Booked Successfully", Toast.LENGTH_SHORT).show();
				}

			}
		});

		final Calendar c = Calendar.getInstance();
		year  = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day   = c.get(Calendar.DAY_OF_MONTH);
		bdate=(Button)findViewById(R.id.button2);

		bdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub


				showDialog(DATE_PICKER_ID);
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case DATE_PICKER_ID:

				// open datepicker dialog.
				// set date picker for current date
				// add pickerListener listner to date picker
				return new DatePickerDialog(this, pickerListener, year, month,day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		@Override
		public void onDateSet(DatePicker view, int selectedYear,
							  int selectedMonth, int selectedDay) {

			year  = selectedYear;
			month = selectedMonth;
			day   = selectedDay;

			// Show selected date

			displaydate=new StringBuilder().append(day)
					.append("-").append(month + 1).append("-").append(year)
					.append(" ").toString().trim();
			bdate.setText(displaydate);

			BasicAWSCredentials bs = new BasicAWSCredentials(ACCESS_KEY_ID,
					SECRET_KEY);
			AmazonSimpleDBClient as = new AmazonSimpleDBClient(bs);
			SelectResult selectResult = null;
			String sNextToken = null;
			String query = null;

			do {

				query = "select BOOK_DATE from BOOKHOOK_USERBOOK where BOOK_DATE='"+displaydate+"' and EMP_ID='"+eid+"'";

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
						clouddate.setText(attribute.getValue().trim());

					}
				}
			} while (sNextToken != null);



		}
	};




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_service_person_details, menu);
		return true;
	}

}
