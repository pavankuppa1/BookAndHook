package com.example.android.bookandhook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FirstActivity extends Activity {
	EditText ID,pass;
	private Boolean exit = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		ID=(EditText)findViewById(R.id.editText1);
		pass=(EditText)findViewById(R.id.editText2);
		final Spinner s=(Spinner)findViewById(R.id.spinner1);
		ArrayList<String> a1=new ArrayList<String>();
		a1.add("select");
		a1.add("Service");
		a1.add("User");

		ArrayAdapter<String> ar=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,a1);
		s.setAdapter(ar);

		Button bt=(Button)findViewById(R.id.button1);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String aaa=s.getSelectedItem().toString();
				String SID=ID.getText().toString().trim();
				String spass=pass.getText().toString().trim();
				if(aaa.equals("User")){

					if (!SID.equals("") && (!spass.equals(""))) {
						if (ID.length()!=10) {
							ID.setError("invalid phn no");
							ID.requestFocus();

						}else{

							User_Registerclass rlist=new User_Registerclass();
							String ver=rlist.loginVerify(SID,spass);
							if (ver.equals("Sorry")) {
								Toast.makeText(getApplicationContext(),"Invalid Credentials", Toast.LENGTH_SHORT).show();
							} else {
								System.out.println("@@@@@@@@@@@@@##$$$" + ver);
								Intent it = new Intent(FirstActivity.this,UserMain_Page.class);
								it.putExtra("sphn", SID);
								startActivity(it);
							}
						}
					}else {
						Toast.makeText(getBaseContext(),"Enter all Fields", Toast.LENGTH_SHORT).show();
					}
				}
				else {
					if (aaa.equals("Service")) {

						if (!SID.equals("") && (!spass.equals(""))) {
							/*if (ID.length()!=10) {
								ID.setError("invalid phn no");
								ID.requestFocus();

							}else{*/

							ServicePerson_class rlist=new ServicePerson_class();
							String ver=rlist.loginVerify(SID,spass);
							if (ver.equals("Sorry")) {
								Toast.makeText(getApplicationContext(),"Invalid Credentials", Toast.LENGTH_SHORT).show();
							} else {
								System.out.println("@@@@@@@@@@@@@##$$$" + ver);
								Intent it = new Intent(FirstActivity.this,Service_ChooseOption.class);
								it.putExtra("eid", SID);
								startActivity(it);
							}
							//			}
						}else {
							Toast.makeText(getBaseContext(),"Enter all Fields", Toast.LENGTH_SHORT).show();
						}

					}
				}

			}
		});
		Button reg=(Button)findViewById(R.id.button2);
		reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String aaa=s.getSelectedItem().toString();
				if (aaa.equals("User")) {
					Intent it=new Intent(FirstActivity.this,UserRegisterPage.class);
					startActivity(it);
				}else if (aaa.equals("Service")) {

					Intent it=new Intent(FirstActivity.this,ServiceRegisterPage.class);
					startActivity(it);
				}else {
					Toast.makeText(getApplicationContext(), "select any one", Toast.LENGTH_SHORT).show();
				}

			}
		});


	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (!exit) {
			Toast.makeText(getApplicationContext(), "press button again to exit", Toast.LENGTH_SHORT).show();
			exit=true;
		}else {
			super.onBackPressed();
		}
		new CountDownTimer(3000,1000) {

			@Override
			public void onTick(long arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				exit=false;
			}
		}.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_first, menu);
		return true;
	}

}
