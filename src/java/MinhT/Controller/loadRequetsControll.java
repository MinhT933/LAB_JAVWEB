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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MinhT
 */
public class loadRequetsControll extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            String statusRequest = request.getParameter("StatusRequest");
            RequestDAO requestDaos = new RequestDAO();
            StatusRequestsDAO statusRequestDAO = new StatusRequestsDAO();
            SourceDAO reourceDAO = new SourceDAO();
            
            List<SourceDTO> listResources = reourceDAO.loadData();
            ArrayList<StatusRequestDTO> listStatusRequest = statusRequestDAO.getAllListStatusRequest();
            List<RequestDTO> ListRequest = new ArrayList<>();
            ListRequest=requestDaos.loadReqsData();
            for (int i = 0; i < listStatusRequest.size(); i++) {
                if (listStatusRequest.get(i).getStatusReqName().equalsIgnoreCase("inactive")) {
                    listStatusRequest.remove(i);
                }
            }
            request.setAttribute("arrayListRequest", ListRequest);
            request.setAttribute("StatusRequest", statusRequest);
            request.setAttribute("listStatusRequest", listStatusRequest);
            request.setAttribute("listResource", listResources);
            
        } catch (NamingException ex) {
            Logger.getLogger(loadRequetsControll.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(loadRequetsControll.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            request.getRequestDispatcher("loadData.jsp").forward(request, response);
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
            Logger.getLogger(loadRequetsControll.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loadRequetsControll.class.getName()).log(Level.SEVERE, null, ex);
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
