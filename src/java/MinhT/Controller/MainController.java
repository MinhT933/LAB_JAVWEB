/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MinhT
 */
public class MainController extends HttpServlet {
    private String ERROR= "error.jsp";
    private String HOME= "login.jsp";
    private String LOGIN_SUCCESS= "LoginController";
    private String LOGOUT="LogoutController";
    private String RESET= "LoadSourceControll";
    private String SEARCH="SearchController";
    private String REGISTER_SERVLET = "RegisterController";
    private String BOOKING= "BookingController";
    private String LOAD_LIST_BOOKING= "LoadlListBookingControll";
    private String SUBMIT_CODE="VerifyCodeServlet";
    private String DELETE_REQUEST="deleteRequestControll";

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
        String url= ERROR;
        String namebutton = request.getParameter("btnAction");
        try {
            if(namebutton==null){
                url=HOME;
            }if(namebutton.equals("login")){
                url=LOGIN_SUCCESS;
            }if(namebutton.equals("logout")){
                url=LOGOUT;
            }if(namebutton.equals("search")){
                url=SEARCH;
            }if(namebutton.equals("create")){
                url = REGISTER_SERVLET;
            }if(namebutton.equals("Booking")){
                url = BOOKING;
            }if(namebutton.equals("Viewlist")){
                url=LOAD_LIST_BOOKING;
            }if(namebutton.equals("reset")){
                url= RESET;
            }if(namebutton.equals("submit")){
                url=SUBMIT_CODE;
            }if(namebutton.equals("delete")){
                url = DELETE_REQUEST;
            }
        } catch (Exception e){
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
