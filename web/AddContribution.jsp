<%-- 
    Document   : AddContribution
    Created on : 4 Oct, 2018, 9:46:08 PM
    Author     : kannan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 
    <body>
        <form action="AddContribution" method="POST">
            Purpose     : <input type="text" name="Purpose" required/><br/><br/>
            Description : <input type="text" name="Description" /><br/><br/>
            Price       : <input type="text" name="Price" required/><br/><br/>
            <button style="width:auto;">Submit</button>
        </form>
    </body>
</html>
