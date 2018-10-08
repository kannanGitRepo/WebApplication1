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
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kannan
 */
@WebServlet(name = "UpdateContribution", urlPatterns = {"/UpdateContribution"})
public class UpdateContribution extends HttpServlet {

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
                String transId=(String)session.getAttribute("transId"); 

        PrintWriter out = response.getWriter();

String purpose=request.getParameter("Purpose");
String description=request.getParameter("Description");
String price=request.getParameter("Price");


    
         out.print("<html><body>");

       try  {
           Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","common_db","knn");
		CallableStatement cstmt = con.prepareCall(
		"{call common_dml.Dml_contribution(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                 cstmt.setString(1,transId);
                 cstmt.setString(2,n);
                 cstmt.setString(3,null);
                 cstmt.setString(4,purpose);
                 cstmt.setString(5,description);
                 cstmt.setString(6,price);
                 cstmt.setString(7,"Update");
                 
                 cstmt.registerOutParameter(8,java.sql.Types.INTEGER);

                 cstmt.registerOutParameter(9,java.sql.Types.VARCHAR);
                 cstmt.executeUpdate();
                 int err_code=cstmt.getInt(8);
                String err_msg=cstmt.getString(9);
                
                if(err_code==0){
           out.println("You have successfully updated your transaction");
         out.println("<a href=\"LogoutProcess.jsp\">Logout</a>|");
             out.println("<a href=\"ViewContribution.jsp\">Check updated records</a>");
                out.println("</body></html>");
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
