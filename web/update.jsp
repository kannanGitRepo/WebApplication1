<%-- 
    Document   : update
    Created on : 6 Oct, 2018, 10:08:29 PM
    Author     : kannan
--%>

<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String transId = request.getParameter("id");
            HttpSession hi = request.getSession();
        hi.setAttribute("transId",transId);%>
      <form action="UpdateContribution" method="POST">
            Purpose     : <input type="text" name="Purpose" /><br/><br/>
            Description : <input type="text" name="Description" /><br/><br/>
            Price       : <input type="text" name="Price" /><br/><br/>
            <button style="width:auto;">Update</button>
        </form>

    </body>
</html>
