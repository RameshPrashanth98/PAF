package ElectroGrid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerModel {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ElectroGridDB?useCustomercode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertCustomer(String Name, String Email, String Address, String NIC, String AccountNumber, String ContactNumber) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into customer(`CusID`,`Name`,`Email`,`Address`,` NIC`,`AccountNumber` , `ContactNumber`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Name);
			preparedStmt.setString(3, Email);
			preparedStmt.setString(4, Address);
			preparedStmt.setString(5, NIC);
			preparedStmt.setString(6, AccountNumber);
			preparedStmt.setString(7, ContactNumber);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readCustomer() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Cus ID</th><th>Name</th><th>Email</th><th>Address</th><th>NIC</th><th>Account Number</th><th>Contact Number</th></tr>";
			String query = "select * from customer";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String CusID = Integer.toString(rs.getInt("CusID"));
				String Name = rs.getString("Name");
				String Email = rs.getString("Email");
				String Address = rs.getString("Address");
				String NIC = rs.getString("NIC");
				String AccountNumber = rs.getString("AccountNumber");
				String ContactNumber= rs.getString("ContactNumber");
				
				
				

				// Add into the html table
				output += "<tr><td>" + CusID + "</td>";
				output += "<td>" + Name + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + AccountNumber + "</td>";
				output += "<td>" + ContactNumber + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCustomer(String CusID, String Name, String Email, String Address, String NIC, String AccountNumber, String ContactNumber) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE customer SET Name=?,Email=?,Address=?,NIC=?,AccountNumber=?,ContactNumber=?" + "WHERE CusID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Name);
			preparedStmt.setString(2, Email);
			preparedStmt.setString(3, Address);
			preparedStmt.setString(4, NIC);
			preparedStmt.setString(5, AccountNumber);
			preparedStmt.setString(6, ContactNumber);
			preparedStmt.setInt(7, Integer.parseInt(CusID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteCustomer(String CusID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from unit1 where CusuID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(CusID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}






