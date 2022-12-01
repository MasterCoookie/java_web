package pl.pols.lab.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import pl.polsl.lab.model.Listing;
import pl.polsl.lab.model.Tab;

@WebServlet(name = "InsertServlet", urlPatterns = {"/insert"})
public class InsertServlet extends HttpServlet {

    private Tab tab;
    
    @Override
    public void init() {
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String _title = request.getParameter("title");
        String _price = request.getParameter("price");
        String _desc = request.getParameter("desc");
        String _negotiable = request.getParameter("negotiable");
        
        HttpSession session = request.getSession();
        if(session.getAttribute("tabObject") == null) {
            response.sendRedirect(request.getContextPath() + "/create");
        } else {
            this.tab = (Tab)session.getAttribute("tabObject");
        }
        
        if(_title == null || _title.length() == 0 || _price == null || _price.length() == 0 || _desc == null || _desc.length() == 0){
            response.sendRedirect(request.getContextPath() + "/create?username=" + this.tab.getUsername() + "&msg=Missing%20tab%20parameters");
        } else {
            try {
                float price = Float.parseFloat(_price);
                //TODO
                var l = new Listing(_title, price, _desc, "on".equals(_negotiable), "JK", "123");
                this.tab.addListing(l, true);
                response.sendRedirect(request.getContextPath() + "/tab?username=" + this.tab.getUsername() + "&contact=" + this.tab.getContact() + "&inserted=" + _title);
            } catch(Exception ex) {
                response.sendRedirect(request.getContextPath() + "/create?username=" + this.tab.getUsername() + "&msg=Invalid%20tab%20parameters");
            }
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
