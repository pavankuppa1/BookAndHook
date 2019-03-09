package com.example.android.bookandhook;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;

public class ServicePerson_class {
	AmazonSimpleDBClient sdbClient;
	String nextToken;
	protected int count;
	private static final String REG_DOMAIN = "BOOKHOOK_SERVICE";

	public ServicePerson_class() {
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

	public void AddToTable1(String sname, String sid, String sphn, String semail, String saddr, String swork, String sexp, String dd, String hh, String spwd) {
		// TODO Auto-generated method stub
		ReplaceableAttribute name = new ReplaceableAttribute(
				"EMPLOYEE_NAME", sname, Boolean.TRUE);
		ReplaceableAttribute id = new ReplaceableAttribute(
				"EMP_ID", sid, Boolean.TRUE);
		ReplaceableAttribute phone = new ReplaceableAttribute("EMP_PHONE",
				sphn, Boolean.TRUE);
		ReplaceableAttribute email = new ReplaceableAttribute("EMP_EMAIL",
				semail, Boolean.TRUE);
		ReplaceableAttribute address = new ReplaceableAttribute("EMP_ADDRESS",
				saddr, Boolean.TRUE);
		ReplaceableAttribute work = new ReplaceableAttribute("EMP_WORK",
				swork, Boolean.TRUE);
		ReplaceableAttribute exp = new ReplaceableAttribute("EMP_EXPERIENCE",
				sexp, Boolean.TRUE);
		ReplaceableAttribute attributedate= new ReplaceableAttribute("REGISTER_DATE",dd, Boolean.TRUE);
		ReplaceableAttribute attributehour = new ReplaceableAttribute("REGISTER_TIME",hh, Boolean.TRUE);
		ReplaceableAttribute password = new ReplaceableAttribute("EMP_PASSWORD",spwd, Boolean.TRUE);

		List<ReplaceableAttribute> attrs = new ArrayList<ReplaceableAttribute>(
				2);
		attrs.add(name);
		attrs.add(id);
		attrs.add(phone);
		attrs.add(email);
		attrs.add(address);
		attrs.add(work);
		attrs.add(exp);
		attrs.add(attributedate);
		attrs.add(attributehour);
		attrs.add(password);

		PutAttributesRequest par = new PutAttributesRequest(REG_DOMAIN, sname,
				attrs);
		try {
			sdbClient.putAttributes(par);
		} catch (Exception exception) {
			System.out.println("EXCEPTION = " + exception);
		}
	}

	public String loginVerify(String sID, String spass) {
		// TODO Auto-generated method stub
		String flag ;
		SelectRequest selectRequest = new SelectRequest(
				"select * from BOOKHOOK_SERVICE where EMP_ID='" + sID
						+ "' and EMP_PASSWORD='" + spass + "'").withConsistentRead(true);
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

	public List<Others> getNAMES(String spinvalue) {
		// TODO Auto-generated method stub
		SelectRequest selectRequest = new SelectRequest(
				"select EMPLOYEE_NAME from BOOKHOOK_SERVICE where EMP_WORK='"+spinvalue+"'").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);

		/* List<String> ls= response.getItems(); */

		/* return this.valuesGetting(response.getItems()); */
		// System.out.println("image names            "+valuesGetting(response.getItems()));
		System.out.println("hello          " + response.getItems().toString());
		return valuesGetting(response.getItems());
	}
	private List<Others> valuesGetting(List<Item> items) {
		// TODO Auto-generated method stub
		ArrayList<Others> alldata = new ArrayList<Others>(items.size());

		for (Item item : items) {
			alldata.add(this.individulaData(item));
		}

		System.out.println("all data size        " + alldata.size());
		for (int i = 0; i < alldata.size(); i++) {
			System.out.println(" name  " + alldata.get(i));
		}
		return alldata;
	}


	private Others individulaData(Item item) {
		// TODO Auto-generated method stub
		return new Others(this.getimagenames(item));
	}


	private String getimagenames(Item item) {
		// TODO Auto-generated method stub
		return this.getAllStringAttribute("EMPLOYEE_NAME", item.getAttributes());
	}


	private String getAllStringAttribute(String usernameAttribute,
										 List<Attribute> list) {
		// TODO Auto-generated method stub
		for (Attribute attrib : list) {
			if (attrib.getName().equals(usernameAttribute)) {
				return attrib.getValue();
			}
		}

		return "";
	}

	public List<Item> getempnames(String sempname) {
		// TODO Auto-generated method stub
		SelectRequest selectRequest = new SelectRequest(
				"select * from BOOKHOOK_SERVICE where EMPLOYEE_NAME='"
						+ sempname + "'")
				.withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);
		System.out.println("hello          " + response.getItems().toString());
		return response.getItems();
	}

	public void UpdateToTable1(String sname, String sid, String sphn, String semail, String saddr, String swork, String sexp, String dd, String hh, String spwd) {
		// TODO Auto-generated method stub
		ReplaceableAttribute name = new ReplaceableAttribute(
				"EMPLOYEE_NAME", sname, Boolean.TRUE);
		ReplaceableAttribute id = new ReplaceableAttribute(
				"EMP_ID", sid, Boolean.TRUE);
		ReplaceableAttribute phone = new ReplaceableAttribute("EMP_PHONE",
				sphn, Boolean.TRUE);
		ReplaceableAttribute email = new ReplaceableAttribute("EMP_EMAIL",
				semail, Boolean.TRUE);
		ReplaceableAttribute address = new ReplaceableAttribute("EMP_ADDRESS",
				saddr, Boolean.TRUE);
		ReplaceableAttribute work = new ReplaceableAttribute("EMP_WORK",
				swork, Boolean.TRUE);
		ReplaceableAttribute exp = new ReplaceableAttribute("EMP_EXPERIENCE",
				sexp, Boolean.TRUE);
		ReplaceableAttribute attributedate = new ReplaceableAttribute("REGISTER_DATE", dd, Boolean.TRUE);
		ReplaceableAttribute attributehour = new ReplaceableAttribute("REGISTER_TIME", hh, Boolean.TRUE);
		ReplaceableAttribute password = new ReplaceableAttribute("EMP_PASSWORD", spwd, Boolean.TRUE);

		List<ReplaceableAttribute> attrs = new ArrayList<ReplaceableAttribute>(
				2);
		attrs.add(name);
		attrs.add(id);
		attrs.add(phone);
		attrs.add(email);
		attrs.add(address);
		attrs.add(work);
		attrs.add(exp);
		attrs.add(attributedate);
		attrs.add(attributehour);
		attrs.add(password);

		PutAttributesRequest par = new PutAttributesRequest(REG_DOMAIN, sname,
				attrs);
		try {
			sdbClient.putAttributes(par);
		} catch (Exception exception) {
			System.out.println("EXCEPTION = " + exception);
		}
	}
}
