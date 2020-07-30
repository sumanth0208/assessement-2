import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class servlet3
 */
@WebServlet("/servlet3")
public class servlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		out.println("<h1 style='text-align:center;background-color:skyblue;color:black;'>BANK STATEMENT</h1>");
		out.println("<table><tr><th>Recent Five Transactions</th></tr>");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment2","list","list");
			String id1 =  request.getParameter("number");
			String amount =  request.getParameter("send");
			PreparedStatement amountps = con.prepareStatement("insert into usecase(transaction_number,transaction_amount)values('"+id1+"' ,'"+amount+"')");  
			amountps.executeUpdate();
		}catch(Exception p){
			System.out.println(p);
			
		}
		out.println("<table >");
		out.println("<tr>");
		out.println("<td>Transaction No</td>");
		out.println("<td>Transaction Amount</td>");

		out.println("</tr>");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment2","list","list");
		    
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM (SELECT * FROM usecase ORDER BY transaction_number DESC LIMIT 5)sub ORDER BY transaction_number ASC ");
			while(rs.next())
			{
				out.println("<tr><td>");
				out.print(rs.getInt(1));
				out.println("</td>");
				out.println("<td>");
				out.print(rs.getInt(2));
				out.println("</td>");
				out.println("<tr>");
			}
					
		}catch(Exception p){
			System.out.println(p);
			
		}
		out.println("</table>");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment2","list","list");
			String amount = request.getParameter("send");
			PreparedStatement Ps = con.prepareStatement("update balance set account_balance= (account_balance-'"+amount+"') where id=1 ");  
			Ps.executeUpdate();
			
			Statement balanceStmt = con.createStatement();
			ResultSet rs = balanceStmt.executeQuery("SELECT * FROM balance ");
			while(rs.next())
			{
				out.println("<center>");
				out.println("<h2 style='color:black ;background-color:white ; width:250px'>Latest Balance:</h2>");
				out.println("<h1 style='color:#1d1145 ;background-color:#ff8928 ;width:250px;color:white;'>");
				out.print(rs.getInt(2));
				out.println("</h1>");
				out.println("</center>");
			}
		}catch(Exception p){
			System.out.println(p);
			
		}
		
		out.print("</table");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
