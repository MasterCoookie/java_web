package pl.pols.lab.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import pl.polsl.lab.model.Tab;

@WebServlet(name = "TabServlet", urlPatterns = {"/tab"})
public class TabServlet extends HttpServlet {

    private Tab tab;
    
    @Override
    public void init() {
        tab = new Tab();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        String _username = request.getParameter("username");
        String _contact = request.getParameter("contact");
        
        if(_username == null || _contact == null || _username.length() == 0 || _contact.length() == 0){
            response.sendError(response.SC_BAD_REQUEST, "Wymagane są dwa parametry!!!");
        } else {
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for(Cookie cookie : cookies) {
                    
                }
            }
            this.tab.setUsername(_username);
            try{
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>TAB</title>"); 
                out.println("<link rel=\"stylesheet\" href=\"css/main.css\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hello " + this.tab.getUsername() + ". Welcome to TAB</h1>");
                out.println("<div style=\"display: grid; grid-template-columns: repeat(3, minmax(0, auto));\">");
                out.println("<div>Title</div><div>Price</div><div>Author</div>");
                for(int i = 0; i < this.tab.getListings().size(); ++i) {
                    var listing = this.tab.getListings().get(i);
                    out.print("<a href=\"listing?username=" + this.tab.getUsername() + "&index=" + i + "\">");
                    out.print("<div>"+ listing.getTitle() +"</div>");
                    out.print("<div>"+ listing.getPrice() +"</div>");
                    out.print("<div>"+ listing.getAuthorUname() +"</div></a>");
                };
                
                out.println("</div>");
                out.println("<a href=\"create?username=" + _username + "\"><button>Create</button></a>");
                out.println("</body>");
                out.println("</html>");
            } catch(Exception ex) {
                response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
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
