package pl.pols.lab.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import pl.polsl.lab.model.Listing;
import pl.polsl.lab.model.Tab;

@WebServlet(name = "InsertServlet", urlPatterns = {"/insert"})
public class InsertServlet extends HttpServlet {

    private Tab tab;
    
    @Override
    public void init() {
        tab = new Tab();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String _username = request.getParameter("username");
        String _title = request.getParameter("title");
        String _price = request.getParameter("price");
        String _desc = request.getParameter("desc");
        String _negotiable = request.getParameter("negotiable");
        
        if(_title == null || _title.length() == 0){
            response.sendError(response.SC_BAD_REQUEST, "Invalid argument");
        } else {
            this.tab.setUsername(_username);
            try {
                float price = Float.parseFloat(_price);
                //TODO
                var l = new Listing(_title, price, _desc, "on".equals(_negotiable), "JK", "123");
                this.tab.addListing(l, true);
                //TODO
                response.sendRedirect(request.getContextPath() + "/tab?username=JK&contact=123");
            } catch(Exception ex) {
                //TODO - redirect back to create with msg
                response.sendRedirect(request.getContextPath() + "/create?username=JK&msg=Invalid%20tab%20parameters");
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
