<%-- 
    Document   : delete
    Created on : 7 Oct, 2018, 10:28:33 AM
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
        <% String transId = request.getParameter("id"); %>
       <%try  {
            /* TODO output your page here. You may use following sample code. */
           Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","common_db","knn");
		CallableStatement cstmt = con.prepareCall(
		"{call common_dml.Dml_contribution(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                 cstmt.setString(1,transId);
                 cstmt.setString(2,null);
                 cstmt.setString(3,null);
                 cstmt.setString(4,null);
                 cstmt.setString(5,null);
                 cstmt.setString(6,null);
                 cstmt.setString(7,"Delete");
                 
                 cstmt.registerOutParameter(8,java.sql.Types.INTEGER);

                 cstmt.registerOutParameter(9,java.sql.Types.VARCHAR);
                 cstmt.executeUpdate();
                 int err_code=cstmt.getInt(8);
                String err_msg=cstmt.getString(9);
                
                if(err_code==0){
           out.println("You have successfully deleted your transaction");
                           out.println("<a href=\"LogoutProcess.jsp\">Logout</a>|");
                           out.println("<a href=\"ViewContribution.jsp\">Check updated records</a>");

             con.close();

    }
        }catch (Exception e) {
     //   LOGGER.error("Error extracting ", e);
        out.println( "<h1>exception: "+e.getMessage()+"</h1>" );
    }%>
    </body>
</html>
