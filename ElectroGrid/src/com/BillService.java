package com;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Bill;

@Path("/Bills")
public class BillService {
Bill bill = new Bill();
	
	//test
	/*@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello()
	 {
	 return "Hello world.";
	 }*/
	
	@GET	
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItem(){
		return bill.readBills();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertData(@FormParam("UserID") String UserID,
			@FormParam("PastunitRead") String PastunitRead,
			@FormParam("NewunitRead") String NewunitRead,
			@FormParam("NoofUnits") String NoofUnits,
			@FormParam("Unitprice") String Unitprice,
			@FormParam("Totalbill") String Totalbill) {
		String output = bill.insertBills(UserID, PastunitRead, NewunitRead, NoofUnits, Unitprice, Totalbill);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String BillData) {
		
		JsonObject fundObject = new JsonParser().parse(BillData).getAsJsonObject();
		
		String BillID = fundObject.get("bid").getAsString();
		String UserID = fundObject.get("uid").getAsString();
		String PastunitRead = fundObject.get("punitread").getAsString();
		String NewunitRead = fundObject.get("nunitread").getAsString();
		String NoofUnits = fundObject.get("noofunits").getAsString();
		String Unitprice = fundObject.get("unitprice").getAsString();
		String Totalbill = fundObject.get("totalbill").getAsString();
		
		String output = bill.updateBills(BillID, UserID, PastunitRead, NewunitRead, NoofUnits, Unitprice, Totalbill);

		return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFunds(String billData) {
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

		//Read the value from the element <ID>
		 String bid = doc.select("bid").text();
		 String output = bill.deleteBill(bid);
		return output;
	}
	
}


