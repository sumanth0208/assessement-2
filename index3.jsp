<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*"%>
<%
String id = request.getParameter("userid");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "assignment2";
String userid = "list";
String password = "list";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>
<head>
<h1 style="text-align:center;background-color:skyblue;color:black;">Bank Statement</h1>
</head>
<body>
<table border="4">
<tr>
<td>transaction no</td>
<td>transaction amount</td>

</tr>
<%
try{
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
String sql ="SELECT * FROM (SELECT * FROM usecase ORDER BY transaction_number DESC LIMIT 5)sub ORDER BY transaction_number ASC";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr>
<td><%=resultSet.getInt(1) %></td>
<td><%=resultSet.getInt(2) %></td>
</tr>
<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
<table border="1">
<tr>


</tr>
</table>
<center>
<td><b>Balance:</b></td>
<%
try{
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
String sql ="select * from balance";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr>
<td><%=resultSet.getInt(2) %></td>
</tr>
<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</center>

<%-- Using the HTML form tag to get the amount to be transfered from the balance --%>
<form action="servlet3" method="get">
<label for="number"><i>Transaction No </i></label>
<input type="number" id="number" name="number" min="1"><br>
<label for="send">Fund Transfer</label>
<input type="number" id="send" name="send" min="1"><br>
<input type="submit" value="send">
</form>
</body>
</html>