/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.Controller;

import MinhT.MyConstants;
import MinhT.dao.RequestDAO;
import MinhT.dto.RequestDTO;
import MinhT.dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class DeleteReqsFromUser extends HttpServlet {

    private String ERROR = "login.jsp";
    private String SUCCESS = "LoadlListBookingControll";
    private String ERROR_UPDATE = "LoadlListBookingControll";
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
            throws ServletException, IOException, SQLException{
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("acc");
        String requestID = request.getParameter("requestID");
        RequestDAO requestDAO = new RequestDAO();
        String url = "";
        if (user == null) {
            url = ERROR;
        } else {
            try {
                RequestDTO requestDetail = requestDAO.getDetailRequest(Integer.parseInt(requestID));
                if (requestDetail.getStatusReqID() == MyConstants.STATUS_REQUEST_NEW) {
                    boolean updateStatus = requestDAO.updateStatusRequest(requestDetail.getRequestID(), MyConstants.STATUS_REQUEST_DELETE);
                    if (updateStatus) {
                        request.setAttribute("successDelete", "request of user deleted successfully");
                    }
                } else {
                    url = ERROR_UPDATE;
                    request.setAttribute("errorDelete", "please load list request");
                }
            } catch (NamingException ex) {
                Logger.getLogger(DeleteReqsFromUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DeleteReqsFromUser.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                 request.getRequestDispatcher(url).forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteReqsFromUser.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeleteReqsFromUser.class.getName()).log(Level.SEVERE, null, ex);
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
