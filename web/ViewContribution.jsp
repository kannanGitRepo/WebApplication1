<%-- 
    Document   : ViewContribution
    Created on : 7 Oct, 2018, 4:11:08 PM
    Author     : kannan
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="oracle.jdbc.OracleTypes"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.CallableStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%String n=(String)session.getAttribute("uname"); %>
      <% try  {
            /* TODO output your page here. You may use following sample code. */
           Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","common_db","knn");
		CallableStatement cstmt = con.prepareCall(
		"{call common_dml.user_contribution(?, ?, ?, ?)}");
                 cstmt.setString(1,n);
                 
                 cstmt.registerOutParameter(2, OracleTypes.CURSOR);//Refcursor selects the row based upon query results provided in Package.
		 
                 cstmt.registerOutParameter(3,java.sql.Types.INTEGER);

                 cstmt.registerOutParameter(4,java.sql.Types.VARCHAR);

                 cstmt.executeUpdate();

                 int err_code=cstmt.getInt(3);
                String err_msg=cstmt.getString(4);
                
                ResultSet rset=(ResultSet) cstmt.getObject(2);
                if(err_code==0){%>
                    
                    
                    <TABLE BORDER="1">
        <TR>
            <TH>Transaction ID</TH>
            <TH>Purchasing Date</TH>
            <TH>Purpose</TH>
            <TH>Description(if any)</TH>
            <TH>Update</TH>
            <TH>Delete</TH>
        </TR>
        <% while(rset.next()){ %>

        <TR>
            <TD> <%= rset.getInt(1) %></td>
            <TD> <%= rset.getDate(2) %></TD>
            <TD> <%= rset.getString(3) %></TD>
            <TD> <%= rset.getString(4) %></TD>
            <TD> <a href="update.jsp?id=<%=rset.getString(1) %>">Update</a> </td>
            <TD> <a href="delete.jsp?id=<%=rset.getString(1) %>">Delete</a> </TD>
        </TR>
        <% } %>
    </TABLE>
               <% con.close();%>
               <a href=LogoutProcess.jsp>Logout</a>
               <% }
        }catch (Exception e) {
     //   LOGGER.error("Error extracting ", e);
        out.println( "<h1>exception: "+e.getMessage()+"</h1>" );
    }%>
    </body>
</html>
