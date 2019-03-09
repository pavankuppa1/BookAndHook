package com.example.android.bookandhook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegisterPage extends Activity {
	Button reg;
	EditText name, pass, age, phn;
	String sname, spass, sage, sphn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_register_page);
		name = (EditText) findViewById(R.id.editText1);
		pass = (EditText) findViewById(R.id.editText2);
		age = (EditText) findViewById(R.id.editText3);
		phn = (EditText) findViewById(R.id.editText4);

		reg = (Button) findViewById(R.id.button2);
		reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				sname = name.getText().toString().trim();
				spass = pass.getText().toString().trim();
				sage = age.getText().toString().trim();
				sphn = phn.getText().toString().trim();
				if (sname.equals("") || spass.equals("") || sage.equals("")
						|| sphn.equals("")) {

					Toast.makeText(getApplicationContext(),
							"required all fields", Toast.LENGTH_SHORT).show();

				} else {
					User_Registerclass ulist = new User_Registerclass();
					ulist.createDomain();
					ulist.AddToTable1(sname, spass, sage, sphn);

					Toast.makeText(getApplicationContext(),
							"Registered Successfully", Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_user_register_page, menu);
		return true;
	}

}
