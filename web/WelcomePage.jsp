<%-- 
    Document   : newjsp
    Created on : 30 Sep, 2018, 3:50:58 PM
    Author     : kannan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        
        <form action="Handler.jsp">
            <center>
<h1>Welcome <%=request.getParameter("username")%></h1>
  <select name="Select list">
    <option value="1">View My Contribution</option>
    <option value="2">View Total contribution</option>
    <option value="3">Add Contribution</option>
  </select>
  <input type="submit" value="Submit">
</form>
    </body>
</html>
