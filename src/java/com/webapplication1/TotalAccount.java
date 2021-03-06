/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapplication1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author kannan
 */
public class TotalAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
         
 HttpSession session=request.getSession(false);  
        String n=(String)session.getAttribute("uname"); 
        PrintWriter out = response.getWriter();
         out.println("<html><body>");
        try  {
            /* TODO output your page here. You may use following sample code. */
           Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","common_db","knn");
		CallableStatement cstmt = con.prepareCall(
		"{call common_dml.user_contribution(?, ?, ?, ?)}");
                 cstmt.setString(1,null);
                 
                 cstmt.registerOutParameter(2, OracleTypes.CURSOR);//Refcursor selects the row based upon query results provided in Package.
		 
                 cstmt.registerOutParameter(3,java.sql.Types.INTEGER);

                 cstmt.registerOutParameter(4,java.sql.Types.VARCHAR);

                 cstmt.executeUpdate();

                 int err_code=cstmt.getInt(3);
                String err_msg=cstmt.getString(4);
                
                ResultSet rset=(ResultSet) cstmt.getObject(2);
                if(err_code==0){
                     out.println("<table border=1>");
             out.println("<tr><th>Transaction ID</th><th>Purchasing Date</th><th>Purpose</th><th>Description(if any)</th><th>Price</th><tr>");
                while (rset.next()) {
                    int transId=rset.getInt(1);
                    Date datePurchased=rset.getDate(2);
                    String purpose=rset.getString(3);
                    String description=rset.getString(4);
                    float price=rset.getFloat(5);
             out.println("<tr><td>" + transId + "</td><td>"+ datePurchased + "</td><td>" + purpose+ "</td><td>" + description + "</td><td>" + price + "</td></tr>"); 
                }
                out.println("</table>");
                out.println("<a href=LogoutProcess.jsp>Logout</a>");
             out.println("</html></body>");
             con.close();
                }
           
        }catch (Exception e) {
     //   LOGGER.error("Error extracting ", e);
        out.println( "<h1>exception: "+e.getMessage()+"</h1>" );
    }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
