package model;

import java.sql.*;
public class Payment {
	
		// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://localhost:3307/electricity", "root", " ");
				System.out.print("Successfully connected");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.print("Error connected");
			}
			return con;
		}

		
		//Insert payments method
		
		public String insertPayment(int accountno, String cname, String uconsumed, String cforunits, String ajustm, String total) {
			String output = "";
			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}

				// create a prepared statement
				String query = " insert into payment(`accountNo`, `customerName`,`unitsConsumed`,`chargeForUnits`,`adjustments`,`totalAmount`)"
						+ " values (?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, accountno);			
				preparedStmt.setString(2, cname);
				preparedStmt.setString(3, uconsumed);
				preparedStmt.setString(4, cforunits);
				preparedStmt.setString(5, ajustm);
				preparedStmt.setString(6, total);

				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Inserted successfully";
			} catch (Exception e) {
				output = "Error while inserting the inquiries.";
				System.err.println(e.getMessage());
			}

			return output;
		}	
		
		
	/*	//read payments method
		
		public String readPayments() {
			String output = "";

			try {

				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Account No</th>"
						+ "<th>Customer Name</th>" 
						+ "<th>Units Consumed</th>"
						+ "<th>Charge for Units</th>" 
						+ "<th>Adjustments</th>"
						+ "<th>Total Amount</th>"
						+ "<th>Update</th><th>Remove</th></tr>";

				String query = "select * from payment";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {
					String accountNo  = Integer.toString(rs.getInt("accountNo"));
					String customerName = rs.getString("customerName");
					String unitsConsumed = rs.getString("unitsConsumed");
					String chargeForUnits = rs.getString("chargeForUnits");
					String adjustments = rs.getString("adjustments");
					String totalAmount = rs.getString("totalAmount");
					

					// Add into the html table
					
					output += "<td>" + accountNo + "</td>";
					output += "<td>" + customerName + "</td>";
					output += "<td>" + unitsConsumed + "</td>";
					output += "<td>" + chargeForUnits + "</td>";
					output += "<td>" + adjustments + "</td>";
					output += "<td>" + totalAmount + "</td>";

					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='items.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='inquiryID' type='hidden' value='" + accountNo + "'>" + "</form></td></tr>";
				}
				con.close();

				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the inquiries.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		//update payment
		
		public String updatePayment(int accountno, String cname, String uconsumed, String cforunits, String ajustm, String total) {
			String output = "";
			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for updating.";
				}

				// create a prepared statement
				String query = "UPDATE payment SET customerName=?,unitsConsumed=?,chargeForUnits=?,adjustments=?,totalAmount=? " + 
							"WHERE accountNo=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
										
				preparedStmt.setInt(1, accountno);
				preparedStmt.setString(2, cname);
				preparedStmt.setString(3, uconsumed);
				preparedStmt.setString(4, cforunits);
				preparedStmt.setString(5, ajustm);
				preparedStmt.setString(6, total);

				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the payment.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		// delete payment
		
		public String deletePayment(String accountNo) {
			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				String query = "delete from payment where accountNo=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, Integer.parseInt(accountNo));

				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Deleted successfully";

			} catch (Exception e) {
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}

			return output;
		}*/
		
		
}
