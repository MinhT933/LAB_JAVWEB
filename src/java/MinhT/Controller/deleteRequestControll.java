/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.Controller;

import MinhT.dao.RequestDAO;
import MinhT.dao.SourceDAO;
import MinhT.dao.StatusRequestsDAO;
import MinhT.dto.RequestDTO;
import MinhT.dto.SourceDTO;
import MinhT.dto.StatusRequestDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MinhT
 */
@WebServlet(name = "deleteRequestControll", urlPatterns = {"/deleteRequestControll"})
public class deleteRequestControll extends HttpServlet {

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
        String url = "";
        try {
            response.setContentType("text/html;charset=UTF-8");
            String id = request.getParameter("id");
            RequestDAO dao = new RequestDAO();
            dao.deleteRequestByid(id);
            RequestDAO requestDAO = new RequestDAO();
            SourceDAO resourceDAO = new SourceDAO();
            StatusRequestsDAO statusRequestDAO = new StatusRequestsDAO();
            List<RequestDTO> listRequestBooking = new ArrayList<>();
            List<SourceDTO> listResources = new ArrayList<>();
            ArrayList<StatusRequestDTO> listStatusRequest = new ArrayList<>();
            listRequestBooking= requestDAO.loadReqsData();
            listResources=resourceDAO.loadData();
            try {
                listStatusRequest= statusRequestDAO.getAllListStatusRequest();
            } catch (NamingException ex) {
                Logger.getLogger(deleteRequestControll.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(deleteRequestControll.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("listStatusRequest", listStatusRequest);
            
            request.setAttribute("listResouces", listResources);
            request.setAttribute("listRequestBooking", listRequestBooking);
            url="ViewListBooking.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(deleteRequestControll.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
