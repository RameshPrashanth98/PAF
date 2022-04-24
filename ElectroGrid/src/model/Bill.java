package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
	//Create connection
		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return con;
		}
		
		//Read Bills
		public String readBills() {
			String output = "";
			try {
				Connection con = connect();
				if(con == null) {
					return "Error while connecting to the database for reading";
				}
				output = "<table border='1'><tr><th>Bill ID</th><th>User ID</th>"
						+ "<th>Previous Unit Read</th><th>New Unit Read</th>"
						+ "<th>No of Units</th><th>Unit Price</th><th>Total Bill</th></tr>";
				String query= "select* from bills";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while(rs.next()) {
					String BillID = Integer.toString(rs.getInt("bid"));
					String UserID = rs.getString("uid");
					String PastunitRead	 = Integer.toString(rs.getInt("punitread"));
					String NewunitRead	 = Integer.toString(rs.getInt("nunitread"));
					String NoofUnits	 = Integer.toString(rs.getInt("noofunits"));
					String Unitprice	 = Integer.toString(rs.getInt("unitprice"));
					String Totalbill = Double.toString(rs.getDouble("totalbill"));
					
					
					output += "<tr><td>"+BillID+"</td>";
					output += "<td>"+UserID+"</td>";
					output += "<td>"+PastunitRead+"</td>";
					output += "<td>"+NewunitRead+"</td>";
					output += "<td>"+NoofUnits+"</td>";
					output += "<td>"+Unitprice+"</td>";
					output += "<td>"+Totalbill+"</td>";
					
				}
				con.close();
				
				output += "</table>";
			}catch(Exception e) {
				output = "Error while reading the info";
				System.err.println(e.getMessage());
				System.out.println(e);
			}
			return output;
		}
}
