/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.Controller;

import MinhT.dao.RequestDAO;
import MinhT.dao.SourceDAO;
import MinhT.dto.RequestDTO;
import MinhT.dto.SourceDTO;
import MinhT.dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MinhT
 */
public class BookingController extends HttpServlet {

    private String ERROR = "error.jsp";
    private String LOAD_SUCCESS = "loadData.jsp";

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String productID = request.getParameter("id");
        RequestDAO RequestDAO = new RequestDAO();
        SourceDAO SourceDAO = new SourceDAO();
        RequestDTO requestBooking = null;
        SourceDTO resource = RequestDAO.getSourceID(productID);
        HttpSession sesion = request.getSession();
        UserDTO user = (UserDTO) sesion.getAttribute("acc");
        int quanlity = Integer.parseInt(resource.getQuanlity());
        if (quanlity >= 1) {
            try {
                if (user != null) {
                    requestBooking = new RequestDTO(0, Date.valueOf(java.time.LocalDate.now()), 1, user.getEmail(), productID);
                }
                boolean isBooking = RequestDAO.bookingResource(requestBooking);
                if (isBooking) {
                    request.setAttribute("bookingSuccess", "Booking SUCCESS");
                } else {
                    request.setAttribute("bookingFail", "Booking FAIL");
                }
            } catch (NamingException ex) {
                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("errBooking", "Please booking another resource");
            request.setAttribute("idProductOutOfNumber", resource.getProductID());
        }
        request.getRequestDispatcher("LoadlListBookingControll").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
