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

public class UserBook_Class {

	AmazonSimpleDBClient sdbClient;
	String nextToken;
	protected int count;
	private static final String REG_DOMAIN = "BOOKHOOK_USERBOOK";

	public UserBook_Class() {
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

	public void BookService(String sphn, String ework, String eid, String empphn, String empex, String empname, String empadd, String empemail, String sbdate, String rnum) {
		// TODO Auto-generated method stub
		ReplaceableAttribute name = new ReplaceableAttribute("EMPLOYEE_NAME", empname, Boolean.TRUE);
		ReplaceableAttribute id = new ReplaceableAttribute("EMP_ID", eid, Boolean.TRUE);
		ReplaceableAttribute phone = new ReplaceableAttribute("EMP_PHONE",empphn, Boolean.TRUE);
		ReplaceableAttribute email = new ReplaceableAttribute("EMP_EMAIL",empemail, Boolean.TRUE);
		ReplaceableAttribute address = new ReplaceableAttribute("EMP_ADDRESS",empadd, Boolean.TRUE);
		ReplaceableAttribute work = new ReplaceableAttribute("EMP_WORK",ework.trim(), Boolean.TRUE);
		ReplaceableAttribute exp = new ReplaceableAttribute("EMP_EXPERIENCE",empex, Boolean.TRUE);
		ReplaceableAttribute usernum=new ReplaceableAttribute("USER_NUMBER",sphn,Boolean.TRUE);
		ReplaceableAttribute accept=new ReplaceableAttribute("ACCEPT","pending",Boolean.TRUE);
		ReplaceableAttribute bookdate=new ReplaceableAttribute("BOOK_DATE",sbdate,Boolean.TRUE);
		ReplaceableAttribute random=new ReplaceableAttribute("SNO",rnum,Boolean.TRUE);

		List<ReplaceableAttribute> attrs = new ArrayList<ReplaceableAttribute>(
				2);
		attrs.add(name);
		attrs.add(id);
		attrs.add(phone);
		attrs.add(email);
		attrs.add(address);
		attrs.add(work);
		attrs.add(exp);
		attrs.add(usernum);
		attrs.add(accept);
		attrs.add(bookdate);
		attrs.add(random);

		PutAttributesRequest par = new PutAttributesRequest(REG_DOMAIN, rnum,
				attrs);
		try {
			sdbClient.putAttributes(par);
		} catch (Exception exception) {
			System.out.println("EXCEPTION = " + exception);
		}
	}
	public List<Others> getdetails(String sphn) {
		// TODO Auto-generated method stub
		SelectRequest selectRequest = new SelectRequest(
				"select BOOK_DATE,EMP_WORK from BOOKHOOK_USERBOOK where USER_NUMBER='"+sphn+"'").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);

		List<Item> ls= response.getItems();

		return this.valuesGetting1(response.getItems());


	}


	private List<Others> valuesGetting1(List<Item> items) {
		// TODO Auto-generated method stub
		ArrayList<Others> alldata = new ArrayList<Others>(items.size());

		for (Item item : items) {
			alldata.add(this.individulaData1(item));
		}

		System.out.println("all data size        " + alldata.size());
		for (int i = 0; i < alldata.size(); i++) {
			System.out.println(" name  " + alldata.get(i));
		}
		return alldata;
	}


	private Others individulaData1(Item item) {
		// TODO Auto-generated method stub
		return new Others(this.getimagenames1(item));
	}


	private String getimagenames1(Item item) {
		// TODO Auto-generated method stub
		String date=getAllStringAttribute1("BOOK_DATE", item.getAttributes());
		String work=getAllStringAttribute1("EMP_WORK", item.getAttributes());
		return date+"                                  "+work;
	}


	private String getAllStringAttribute1(String usernameAttribute,
										  List<Attribute> list) {
		// TODO Auto-generated method stub
		for (Attribute attrib : list) {
			if (attrib.getName().equals(usernameAttribute)) {
				return attrib.getValue();
			}
		}

		return "";
	}
	public List<Others> getnum(String sempwork) {
		// TODO Auto-generated method stub
		SelectRequest selectRequest = new SelectRequest(
				"select USER_NUMBER,SNO from BOOKHOOK_USERBOOK where ACCEPT='pending' and EMP_WORK='"+sempwork+"'").withConsistentRead(true);
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
		String aa=getAllStringAttribute("USER_NUMBER", item.getAttributes());
		String bb=getAllStringAttribute("SNO", item.getAttributes());
		return aa+"			"+bb;
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
	public void UpdateRequest(String ssno) {
		// TODO Auto-generated method stub
		ReplaceableAttribute random=new ReplaceableAttribute("SNO",ssno,Boolean.TRUE);
		ReplaceableAttribute accept=new ReplaceableAttribute("ACCEPT","accepted",Boolean.TRUE);

		List<ReplaceableAttribute> attrs = new ArrayList<ReplaceableAttribute>(2);
		attrs.add(random);
		attrs.add(accept);
		PutAttributesRequest par = new PutAttributesRequest(REG_DOMAIN, ssno,
				attrs);
		try {
			sdbClient.putAttributes(par);
		} catch (Exception exception) {
			System.out.println("EXCEPTION = " + exception);
		}
	}
	public String getAllValues(String date, String sphn, String swork) {
		// TODO Auto-generated method stub
		SelectRequest selectRequest = new SelectRequest(
				"select * from BOOKHOOK_USERBOOK where BOOK_DATE='"+date+"' and EMP_WORK='"+swork+"' and USER_NUMBER='"+sphn+"'").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);

			/* List<String> ls= response.getItems(); */

			/* return this.valuesGetting(response.getItems()); */
		// System.out.println("image names            "+valuesGetting(response.getItems()));
		System.out.println("hello          " + response.getItems().toString());
		return response.getItems().toString();
	}
}