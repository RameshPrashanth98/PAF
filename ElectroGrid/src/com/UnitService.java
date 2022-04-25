package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Unit;

@Path("/Unit")
public class UnitService {
	
	Unit UnitObj = new Unit();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUnit() {
		return UnitObj.readUnit();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUnit(@FormParam("uAccNo") String uAccNo,			
	 @FormParam("uDate") String uDate,
	 @FormParam("UnitAmount") String UnitAmount,
	 @FormParam("PriceForPerUnit") String PriceForPerUnit,
	 @FormParam("Total") String Total)
	{
	 String output = UnitObj.insertUnit (uAccNo, uDate, UnitAmount, PriceForPerUnit, Total);
	return output;
	
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUnit(String unitData)
	{
	//Convert the input string to a JSON object
	 JsonObject unitObject = new JsonParser().parse(unitData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String uID = unitObject.get("uID").getAsString();
	 String uAccNo = unitObject.get("uAccNo").getAsString();
	 String uDate = unitObject.get("uDate").getAsString();
	 String UnitAmount = unitObject.get("UnitAmount").getAsString();
	 String PriceForPerUnit = unitObject.get("PriceForPerUnit").getAsString();
	 String Total = unitObject.get("Total").getAsString();
	 String output = UnitObj.updateUnit(uID, uAccNo, uDate, UnitAmount, PriceForPerUnit, Total);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUnit(String unitData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(unitData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String uID = doc.select("uID").text();
	 String output = UnitObj.deleteUnit(uID);
	return output;
	}
}
