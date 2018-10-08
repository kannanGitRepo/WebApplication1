<%-- 
    Document   : Handler
    Created on : 3 Oct, 2018, 8:21:28 PM
    Author     : kannan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      
        <%String param=request.getParameter("Select list");%>
        <%if(param.equals("1")){
        response.sendRedirect("ViewContribution.jsp");

     }else if(param.equals("2")){
                       response.sendRedirect("TotalAccount");

}else if(param.equals("3")){
    
                     response.sendRedirect("AddContribution.jsp");
   
}%>
             
    </body>
</html>
