/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForumLets;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.File;
import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author michaelcavey (Adapted to kdastrup file system)
 */
@WebServlet(name = "ForumIn", urlPatterns = {"/ForumIn"})
public class ForumIn extends HttpServlet {
    
//    public ForumUp(){
//       super();
//    }

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
        
/**     (WAS) before post-assignment corrections

        // obviously these belong in a DB or something...
        String correctName = "Guest";
        String correctPassword = "post";

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        if (username != null && password != null
                && username.equals(correctName) && password.equals(correctPassword)) {

            // correct username and password!
            request.getSession().setAttribute("username", username);
            response.sendRedirect("/FirstProject/forum/welcome.jsp");
        } else {
            response.sendRedirect("/FirstProject/forum/badLogin.jsp");
        }
    }
*/

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        request.getSession().setAttribute("username", username);
        
        //loop through file to find match
        String temp;
        boolean found = false;
        String path = getServletContext().getRealPath("/") + "forum/members.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        while ((temp = bufferedReader.readLine()) != null) {
        if((username + password).equals(temp)) {
        request.setAttribute("username", username);
        request.getSession().setAttribute("username", username);
        request.getRequestDispatcher("/forum/welcome.jsp").forward(request, response);
        found = true;
        break;
        }
        }
        
        if (found == false){
            request.getRequestDispatcher("/forum/badLogin.jsp").forward(request, response);
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
