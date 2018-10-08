/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapplication1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kannan
 */
public class LoginForm extends HttpServlet {

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
         // Set response content type
      response.setContentType("text/html");
 
      PrintWriter out = response.getWriter();

      String n=request.getParameter("username");
      String p=request.getParameter("password");
      String r=request.getParameter("role");
int result=LoginCheck.validate(n,p,r);
        
                  HttpSession session=request.getSession();  
        session.setAttribute("uname",n);  
        if (result==0){
         RequestDispatcher rd=request.getRequestDispatcher("WelcomePage.jsp");
         rd.forward(request,response);

      }else if(result==1){
          out.println("You dont have admin previlages, Please select user role");
      }
      else{
         out.println("Sorry Wrong username or password or role");
         

      }
      out.close(); 

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
