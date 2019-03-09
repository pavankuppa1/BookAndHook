package com.example.android.bookandhook;


import android.os.Bundle;
import android.app.Activity;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends Activity {
	EditText num,msg;
	Button send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		Bundle bb=getIntent().getExtras();
		final String empnumber=bb.getString("empphn").trim();
		num=(EditText)findViewById(R.id.editText1);
		num.setText(empnumber);
		msg=(EditText)findViewById(R.id.editText2);
		send=(Button)findViewById(R.id.button1);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String smsg=msg.getText().toString().trim();
				SmsManager sms=SmsManager.getDefault();
				sms.sendTextMessage(empnumber, null, smsg, null, null);
				Toast.makeText(getApplicationContext(), "sent", Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_message, menu);
		return true;
	}

}
