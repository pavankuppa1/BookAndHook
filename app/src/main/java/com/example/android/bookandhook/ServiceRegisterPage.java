package com.example.android.bookandhook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ServiceRegisterPage extends Activity {
	EditText name,id,phn,email,addr,pass;
	Spinner work,exp;
	Button add;
	String sname,sid,sphn,semail,saddr,swork,sexp,spwd;
	ArrayList<String> al1=new ArrayList<String>();
	ArrayList<String> al2=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_service_register_page);
		name=(EditText)findViewById(R.id.name);
		id=(EditText)findViewById(R.id.editText1);
		phn=(EditText)findViewById(R.id.editText2);
		email=(EditText)findViewById(R.id.editText3);
		addr=(EditText)findViewById(R.id.editText4);
		pass=(EditText)findViewById(R.id.editText5);
		work=(Spinner)findViewById(R.id.spinner1);
		al1.add("Photographer");
		al1.add("Designer");
		al1.add("Hairstylist");
		al1.add("Beautician");
		al1.add("Makeup Artist");
		al1.add("Travels");
		al1.add("Fitness Trainer");
		ArrayAdapter<String> adp1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,al1);
		work.setAdapter(adp1);
		exp=(Spinner)findViewById(R.id.spinner2);
		al2.add("1year");
		al2.add("2year");
		al2.add("3year");
		ArrayAdapter<String> adp2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,al2);
		exp.setAdapter(adp2);
		add=(Button)findViewById(R.id.button1);
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Date dNow = new Date( );
				SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
				Date dNow1 = new Date( );
				SimpleDateFormat ft1 = new SimpleDateFormat ("HH:mm:ss");
				String dd= ft.format(dNow);
				String hh= ft1.format(dNow1);

				sname=name.getText().toString().trim();
				sid=id.getText().toString().trim();
				sphn=phn.getText().toString().trim();
				semail=email.getText().toString().trim();
				saddr=addr.getText().toString().trim();
				swork=work.getSelectedItem().toString();
				sexp=exp.getSelectedItem().toString();
				spwd=pass.getText().toString().trim();
				ServicePerson_class ulist=new ServicePerson_class();
				ulist.createDomain();
				ulist.AddToTable1(sname,sid,sphn,semail,saddr,swork,sexp,dd,hh,spwd);
				Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_service_register_page, menu);
		return true;
	}

}