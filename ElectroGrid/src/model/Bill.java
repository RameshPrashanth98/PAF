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
		
		//Insert bill
		public String insertBills(String UserID, String PastunitRead, String NewunitRead, String NoofUnits,String Unitprice,String Totalbill) {
			String output = "" ;
			try {
				Connection con = connect();
				if(con == null) {
					return "Error while connecting to the database for inserting. "; 
				}
				String query = "insert into bills (bid,uid,	punitread,nunitread	,noofunits,unitprice,totalbill	) values(?,?,?,?,?) ";
				PreparedStatement pst = con.prepareStatement(query);
				
				pst.setInt(1, 0);
				pst.setString(2, UserID);
				pst.setInt(3,Integer.parseInt(PastunitRead));
				pst.setInt(4,Integer.parseInt(NewunitRead));
				pst.setInt(5,Integer.parseInt(NoofUnits));
				pst.setInt(6,Integer.parseInt(Unitprice));
				pst.setDouble(7, Double.parseDouble(Totalbill));
				
				pst.executeUpdate();
				con.setAutoCommit(false);
				con.commit();
				output = "Inserted Successfully.";
			}catch(Exception e) {
				output ="Error while inserting the Bill.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		//update bills
		public String updateBills(String BillID,String UserID, String PastunitRead, String NewunitRead, String NoofUnits,String Unitprice,String Totalbill) {
			String output = "";
			Connection con = connect();
			
			try {
				
				if(con == null) {
					return "Error while connecting to the database for updating.";
				}
				
				String query = "update bills set uid=?, punitread=?, noofunits=?, unitprice=?, totalbill=?"
						+ " where bid=?";
				
				PreparedStatement pdstmt = con.prepareStatement(query);
				
				pdstmt.setString(1, UserID);
				pdstmt.setInt(2,Integer.parseInt(PastunitRead));
				pdstmt.setInt(3,Integer.parseInt(NewunitRead));
				pdstmt.setInt(4,Integer.parseInt(NoofUnits));
				pdstmt.setInt(5,Integer.parseInt(Unitprice));
				pdstmt.setInt(6,Integer.parseInt(BillID));
				pdstmt.executeUpdate();
				con.setAutoCommit(false);
				con.commit();
				output = "Updated successfully.";
				
				
				
			}catch(Exception e) {
				output = "Error while updating the Bill.";
				System.err.print(e.getMessage());
			}
			
			return output;
		}
		
		
		
		//Delete bill
		public String deleteBill(String BillID) {
			String output = "";
			try {
				Connection con = connect();
				if(con==null) {
					return "Error while connecting to database for deleting.";
				}
				
				String query = "delete from bills where id=?";
				
				PreparedStatement pdstmt = con.prepareStatement(query);
				
				pdstmt.setInt(1, Integer.parseInt(BillID));
				
				pdstmt.executeUpdate();
				con.setAutoCommit(false);
				con.commit();
				output = "Deleted Successfully";
				
			}catch(Exception e) {
				output = "Error while deleting the Bill.";
				System.err.print(e.getMessage());
			}
			
			return output;
		}
}
