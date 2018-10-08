/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapplication1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author kannan
 */
public class Registration extends HttpServlet {

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
        response.setContentType("text/html");
        String username=request.getParameter("username");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String task="ADD";
       try{ 
           PrintWriter out = response.getWriter();
Class.forName("oracle.jdbc.driver.OracleDriver");  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:xe","common_db","knn");   
CallableStatement cstmt = con.prepareCall(
		"{call common_auth.registration(?, ?, ?, ?, ?, ?)}");
                cstmt.setString(1,username);
		cstmt.setString(2,password);
                cstmt.setString(3,email);
                cstmt.setString(4,task);
                
                cstmt.registerOutParameter(5,java.sql.Types.INTEGER);
		cstmt.registerOutParameter(6,java.sql.Types.VARCHAR);
		
                cstmt.executeUpdate();
                
                int err_code=cstmt.getInt(5);
                String err_msg=cstmt.getString(6);
               
                if (err_code==0){
                    out.println("Request has been done successfully");
                }else if (err_code==1){
                    out.println(err_msg);
                }
out.close(); 
        }catch(Exception e){
		System.out.println(e);
        }
    }

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
