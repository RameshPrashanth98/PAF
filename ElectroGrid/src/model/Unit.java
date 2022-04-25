package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Unit {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			
			// Provide the correct details: DBSevr/DBName, username, pw
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electricity?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public String insertUnit(String uAccNo, String uDate, String UnitAmount, String PriceForPerUnit, String Total) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			
			// create prepared statmnt
			String query = " insert into unit1(`uID`,`uAccNo`,`uDate`,`UnitAmount`,`PriceForPerUnit`,`Total`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, uAccNo);
			preparedStmt.setString(3, uDate);
			preparedStmt.setString(4, UnitAmount);
			preparedStmt.setString(5, PriceForPerUnit);
			preparedStmt.setString(6, Total);
			
			// execute the statmnt
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the unit.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUnit() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
		
			
			
			// Prepare html table to be displayed
			output = "<table border=\"1\"><tr><th>Unit ID</th><th>Account No</th><th>Date</th><th>Unit Amount</th><th>Price for PerUnit</th><th>Total Amount</th></tr>";
			String query = "select * from unit1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			
			
			// iterate through the rows in th result set
			while (rs.next()) {
				String uID = Integer.toString(rs.getInt("uID"));
				String uAccNo = rs.getString("uAccNo");
				String uDate = rs.getString("uDate");
				String UnitAmount = rs.getString("UnitAmount");
				String PriceForPerUnit = rs.getString("PriceForPerUnit");
				String Total = rs.getString("Total");
				
				
				// Add into html table
				output += "<tr><td>" + uID + "</td>";
				output += "<td>" + uAccNo + "</td>";
				output += "<td>" + uDate + "</td>";
				output += "<td>" + UnitAmount + "</td>";
				output += "<td>" + PriceForPerUnit + "</td>";
				output += "<td>" + Total + "</td>";
				
			}
			con.close();
			
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the unit.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateUnit(String uID, String uAccNo, String uDate, String UnitAmount, String PriceForPerUnit, String Total) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			
			
			// create prepared statmnt
			String query = "UPDATE unit1 SET uAccNo=?,uDate=?,UnitAmount=?,PriceForPerUnit=?,Total=?" + "WHERE uID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, uAccNo);
			preparedStmt.setString(2, uDate);
			preparedStmt.setString(3, UnitAmount);
			preparedStmt.setString(4, PriceForPerUnit);
			preparedStmt.setString(5, Total);
			preparedStmt.setInt(6, Integer.parseInt(uID));

			
			// execute the statmnt
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the unit.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteUnit(String uID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			
			// create a prepared statmnt
			String query = "delete from unit1 where uID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(uID));

			
			// execute the statmnt
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the unit.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
