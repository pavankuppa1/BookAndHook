package com.example.android.bookandhook;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;

public class User_Registerclass {
	AmazonSimpleDBClient sdbClient;
	String nextToken;
	protected int count;
	private static final String REG_DOMAIN = "BOOKHOOK_USER";

	public User_Registerclass() {
		// TODO Auto-generated constructor stub

		AWSCredentials credentials = new BasicAWSCredentials(
				Constants.ACCESS_KEY_ID, Constants.SECRET_KEY);
		sdbClient = new AmazonSimpleDBClient(credentials);
		this.nextToken = null;
		this.count = -1;
	}

	public void createDomain() {
		// TODO Auto-generated method stub

		CreateDomainRequest cr = new CreateDomainRequest(REG_DOMAIN);
		sdbClient.createDomain(cr);

	}

	public void AddToTable1(String sname, String spass, String sage, String sphn) {
		// TODO Auto-generated method stub
		ReplaceableAttribute name = new ReplaceableAttribute(
				"USERNAME", sname, Boolean.TRUE);
		ReplaceableAttribute pass = new ReplaceableAttribute(
				"PASSWORD", spass, Boolean.TRUE);
		ReplaceableAttribute age = new ReplaceableAttribute("AGE",
				sage, Boolean.TRUE);
		ReplaceableAttribute phone = new ReplaceableAttribute("PHONE",
				sphn, Boolean.TRUE);


		List<ReplaceableAttribute> attrs = new ArrayList<ReplaceableAttribute>(
				2);
		attrs.add(name);
		attrs.add(pass);
		attrs.add(age);
		attrs.add(phone);

		PutAttributesRequest par = new PutAttributesRequest(REG_DOMAIN, sname,
				attrs);
		try {
			sdbClient.putAttributes(par);
		} catch (Exception exception) {
			System.out.println("EXCEPTION = " + exception);
		}
	}

	public String loginVerify(String SID, String spass) {
		// TODO Auto-generated method stub
		String flag ;
		SelectRequest selectRequest = new SelectRequest(
				"select * from BOOKHOOK_USER where PHONE='" + SID
						+ "' and PASSWORD='" + spass + "'").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);
		SelectResult response = this.sdbClient.select(selectRequest);
		List<Item> ls = response.getItems();
		System.out.println("@@" + ls.size());
		System.out.println("@@" + response.getItems());
		this.nextToken = response.getNextToken();
		System.out.println("@@" + this.nextToken);


		String[] a1 =  response.getItems().toString().split(",");

		String branch = a1[0];
		System.out.println("QQQQQQQQQ@@@@@@@@@!!!!!!!!!!!" + branch);
		String[] na = branch.split(":");
		String naam = na[0];

		String hall = a1[0];
		System.out.println("QQQQQQQQQ@@@@@@@@@!!!!!!!!!!!" + hall);
		String[] desc = hall.split(":");
		String dec1 = desc[0];

		String year = a1[0];
		System.out.println("QQQQQQQQQ@@@@@@@@@#######" + year);
		String[] addr1 = year.split(":");
		final String addr2 = addr1[0];

		String sem = a1[0];
		System.out.println("QQQQQQQQQ@@@@@@@@@" + sem);
		String[] phn2 = sem.split(":");
		String co1 = phn2[0];
		String s=naam+"@"+dec1+"@"+addr2;
		System.out.println("@#$$%%"+s);
		if (ls.size() > 0) {
			flag = s;
		} else {
			flag = "Sorry";
		}
		return flag;
	}

}
