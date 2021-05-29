/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.Controller;

import MinhT.MyConstants;
import MinhT.dao.RequestDAO;
import MinhT.dao.SourceDAO;
import MinhT.dto.SourceDTO;
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

/**
 *
 * @author MinhT
 */
public class AcceptReqsController extends HttpServlet {

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
            throws ServletException, IOException, NamingException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String requestID = request.getParameter("requestID");
            String isConfirm = request.getParameter("flag");
            String productID = request.getParameter("productID");
            RequestDAO requestDAO = new RequestDAO();
            SourceDAO resourceDAO = new SourceDAO();
            SourceDTO resource = resourceDAO.getDetailResource(productID);
            int quanlity = Integer.parseInt(resource.getQuanlity());
            if(Boolean.parseBoolean(isConfirm)){
                boolean isStatusActive = requestDAO.updateStatusRequest(Integer.parseInt(requestID), MyConstants.STATUS_REQUEST_ACTIVE);
                boolean updateQuanity = resourceDAO.updateQuanityResource(productID, quanlity - 1);
                if(updateQuanity&& isStatusActive){
                    request.setAttribute("successConfirm", "Confirm successfully");
                }
                
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(AcceptReqsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcceptReqsController.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
             request.getRequestDispatcher("loadRequetsControll").forward(request, response);
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
        } catch (NamingException ex) {
            Logger.getLogger(AcceptReqsController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NamingException ex) {
            Logger.getLogger(AcceptReqsController.class.getName()).log(Level.SEVERE, null, ex);
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
