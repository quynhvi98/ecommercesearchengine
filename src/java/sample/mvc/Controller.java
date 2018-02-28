/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.mvc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author viquy
 */
public class Controller extends HttpServlet {
    final private String errorPage = "fail.jsp";
    final private String homePage = "index.jsp";
    final private String welcomePage = "welcome.jsp";
    final private String registerPage = "register.jsp";
    final private String showPage ="show.jsp";

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
        PrintWriter out = response.getWriter();
        try{
            String action = request.getParameter("btAction");
            if(action.equals("Login")){
                String username = request.getParameter("txtUsername");
                String password = request.getParameter("txtPass");
                LoginBean login = new LoginBean();
                boolean result = login.checkLogin(username, password);
                String url = errorPage;
                if(result){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("USER",username);
                    url= welcomePage;
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }else if(action.equals("tryAgain")){
                RequestDispatcher rd = request.getRequestDispatcher(homePage);
                rd.forward(request, response);
            }else if(action.equals("Register")){
                String username = request.getParameter("txtUser");
                String password = request.getParameter("txtPass");
                String lastname = request.getParameter("txtLast");
                String admin = request.getParameter("chkAdmin");
                boolean roles = false;
                if(admin!=null){
                    roles = true;
                }
                RequestDispatcher rd = request.getRequestDispatcher(registerPage);
                rd.forward(request, response);
            }else if(action.equals("Search")){
                String name = request.getParameter("txtSearch");
                LoginBean login = new LoginBean();
                LoginBean[] result = login.searchLikeLastname(name);
                request.setAttribute("INFO", result);
                RequestDispatcher rd = request.getRequestDispatcher(showPage);
                rd.forward(request, response);
            }else if(action.equals("Delete")){
                String username = request.getParameter("username");
                String name = request.getParameter("txtSearch");
                LoginBean login = new LoginBean();
                login.setUsername(username);
                boolean result = login.deleteRecord();
                String url = "Controller?btAction=SearchtxtSearch="+name;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }else if(action.equals("Updae")){
                String username = request.getParameter("txtUsername");
                String lastname = request.getParameter("txtLast");
                String admin = request.getParameter("chkAdmin");
                boolean roles = false;
                if(admin!= null){
                    roles = true;
                }
                String name = request.getParameter("txtSearch");
                LoginBean login = new LoginBean(username, lastname, roles);
                boolean result = login.updateRecord();
                String url = "Controller?btAction=Search&txtSearch" + name;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }else if(action.equals("Back")){
                RequestDispatcher rd = request.getRequestDispatcher(welcomePage);
                rd.forward(request, response);
            }  
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            out.close();
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
