/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapplication1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WelcomePage extends HttpServlet{
    private String n;
	public void processRequest(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
    n=request.getParameter("username");  
    out.print("Welcome "+n);  
    
    
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
         
      out.println(docType +
         "<html>\n" +
            
            "<body bgcolor = \"#f0f0f0\">\n" +
               "<ul>\n" +
                  "<form action=\"UserContribution\" method=\"post\">"+
                       "<button onclick=\"myFunction(n)\">My Contribution</button>"+
              "<p id=\"demo\"></p>"+
                 "</form>"+
                 "<form action=\"TotalAccount\" method=\"post\">"+
                       "<button style=\"width:auto;\">Total account</button>"+
               "</ul>\n" +
            "</body>"+
         "</html>"
      );
      
    out.close();  
}
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
        