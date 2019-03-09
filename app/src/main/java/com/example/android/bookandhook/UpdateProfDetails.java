package com.example.android.bookandhook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kmurali on 31-05-2018.
 */

public class UpdateProfDetails extends AppCompatActivity {

    EditText name,id,phn,email,addr,pass;
    Spinner work,exp;
    Button button1;
    ArrayList<String> al1=new ArrayList<String>();
    ArrayList<String> al2=new ArrayList<String>();
    String sname,sid,sphn,semail,saddr,swork,sexp,spwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bb=getIntent().getExtras();
        final String sph=bb.getString("eid");
        setContentView(R.layout.activity_updateprofdetails);
        name=(EditText)findViewById(R.id.name);
        id=(EditText)findViewById(R.id.editText1);
        phn=(EditText)findViewById(R.id.editText2);
        email=(EditText)findViewById(R.id.editText3);
        addr=(EditText)findViewById(R.id.editText4);
        pass=(EditText)findViewById(R.id.editText5);
        work=(Spinner)findViewById(R.id.spinner1);
        exp=(Spinner)findViewById(R.id.spinner2);
        button1=(Button)findViewById(R.id.button1);
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

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uname = name.getText().toString().trim();
                sid=id.getText().toString().trim();
                sphn=phn.getText().toString().trim();
                semail=email.getText().toString().trim();
                saddr=addr.getText().toString().trim();
                swork=work.getSelectedItem().toString();
                sexp=exp.getSelectedItem().toString();
                spwd=pass.getText().toString().trim();

                Date dNow = new Date( );
                SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
                Date dNow1 = new Date( );
                SimpleDateFormat ft1 = new SimpleDateFormat ("HH:mm:ss");
                String dd= ft.format(dNow);
                String hh= ft1.format(dNow1);



                if (uname.equals("") || semail.equals("") || sphn.equals("")
                        || saddr.equals("")||pass.equals("")||swork.equals("")||sid.equals("")||spwd.equals("")) {

                    Toast.makeText(getApplicationContext(),
                            "required all fields", Toast.LENGTH_SHORT).show();

                } else {
                    //another class to upload data in cloud
                    ServicePerson_class s = new ServicePerson_class();
                    s.createDomain();
                    s.UpdateToTable1(uname,sid,sphn,semail,saddr,swork,sexp,dd,hh,spwd);

                    Toast.makeText(getApplicationContext(),
                            "Upload Successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });


        }
    }