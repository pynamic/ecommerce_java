/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDao;
import dao.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.Order;

/**
 *
 * @author TUAN
 */
@WebServlet(name = "OrderController", urlPatterns = {"/Order"})
public class OrderController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        CartDao cartDao=new CartDao();
        
        String suid=request.getParameter("uid");
        int uid=Integer.parseInt(suid);
        List<Cart> list=cartDao.getCartByUserId(uid);
        String description=request.getParameter("description");
        String address=request.getParameter("address");
        String phone=request.getParameter("phone");
        BigDecimal total=BigDecimal.ZERO;
        for(Cart c : list)
        {
           total=total.add(c.getSubTotal());
        }
        Order order=new Order();
        order.setAddress(address);
        order.setDescription(description);
        order.setIsFinished(0);
        order.setPhone(phone);
        order.setTotal(total);
        order.setUserId(uid);
        OrderDao orderDao=new OrderDao();
        orderDao.createOrder(order);//tu them vao order-detail roi
        cartDao.cleanCart(uid);//order xong xoa cart
        request.setAttribute("uid", uid);//gui lai id de giu login
        request.getRequestDispatcher("jsp/home.jsp").forward(request, response);//quay ve home
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
