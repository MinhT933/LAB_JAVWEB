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
import MinhT.dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MinhT
 */
public class ResetController extends HttpServlet {
    private String  ERROR = "error.jsp";
    private String LOAD_SUCCESS="loadRequetsControll";

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
        response.setContentType("text/html;charset=UTF-8");
      String url = ERROR;
        SourceDAO dao = new SourceDAO();
        RequestDAO dao1= new RequestDAO();
        try {
            List<SourceDTO> list = dao.loadData();
            HttpSession session = request.getSession();
            session.setAttribute("src", list);
            List<RequestDTO> listReq = dao1.loadReqsData();
            request.setAttribute("listReq", listReq);
            UserDTO user = (UserDTO) session.getAttribute("user");
            RequestDAO requestDAO = new RequestDAO();
            SourceDAO resourceDAO = new SourceDAO();
            StatusRequestsDAO statusRequestDAO = new StatusRequestsDAO();
            List<RequestDTO> listRequestBooking = new ArrayList<>();
            List<SourceDTO> listResources = new ArrayList<>();
            ArrayList<StatusRequestDTO> listStatusRequest = new ArrayList<>();
            listRequestBooking= requestDAO.loadReqsData();
            listResources=resourceDAO.loadData();
            listStatusRequest= statusRequestDAO.getAllListStatusRequest();
            request.setAttribute("listStatusRequest", listStatusRequest);
            request.setAttribute("listResouces", listResources);
            request.setAttribute("listRequestBooking", listRequestBooking);
            url=LOAD_SUCCESS;
        } catch (Exception e) {
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
