/*package com;
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

import model.Payment;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;

@Path("/Payment")

public class PaymentService {
	Payment inquiryObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments() {
		return inquiryObj.readPayments();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insert(@FormParam("accountNo ") int accountNo , 
			@FormParam("customerName") String customerName,
			@FormParam("unitsConsumed") String unitsConsumed,
			@FormParam("chargeForUnits") String chargeForUnits, 
			@FormParam("adjustments") String adjustments,
			@FormParam("totalAmount") String totalAmount)

	{
		String output = inquiryObj.insertPayment(accountNo, customerName, unitsConsumed, chargeForUnits, adjustments, totalAmount);
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String inquiryData) 
	{
		// Convert the input string to a JSON object
		JsonObject inquiryObject = new JsonParser().parse(inquiryData).getAsJsonObject();
		// Read the values from the JSON object
		
		int accountNo = inquiryObject.get("accountNo ").getAsInt();
		String customerName = inquiryObject.get("customerName").getAsString();
		String unitsConsumed = inquiryObject.get("unitsConsumed").getAsString();
		String chargeForUnits = inquiryObject.get("chargeForUnits").getAsString();
		String adjustments = inquiryObject.get("adjustments").getAsString();
		String totalAmount = inquiryObject.get("totalAmount").getAsString();
		
		
		String output = inquiryObj.updatePayment(accountNo, customerName, unitsConsumed, chargeForUnits, adjustments, totalAmount);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePaymenr(String inquiryData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());
		//Read the value from the element <itemID>
		String accountNo = doc.select("accountNo").text();
		String output = inquiryObj.deletePayment(accountNo);
		return output;
	}

}*/



