/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package tri.dev.admin.user;

import tri.dev.BaseServlet;
import tri.dev.data.dao.DatabaseDao;
import tri.dev.data.dao.UserDAO;
import tri.dev.data.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
/**
 *
 * @author Welcome
 */
public class CreateUserServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("LoginServlet");
            return;
        }
        request.getRequestDispatcher("admin/user/create.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPass = request.getParameter("repassword");
        String role = request.getParameter("role");
        UserDAO userDao = DatabaseDao.getInstance().getUserDao();
        User user = userDao.find(email);
        HttpSession session = request.getSession();
        
        if (email.isEmpty() || password.isEmpty() || repeatPass.isEmpty() || role.isEmpty()) {

            session.setAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin đăng ký");
            request.getRequestDispatcher("admin/user/create.jsp").forward(request, response);
        } else if (user != null) {
            session.setAttribute("errorMessage", "Email trùng");
            request.getRequestDispatcher("admin/user/create.jsp").forward(request, response);
        } else if (!password.equals(repeatPass)) {
            session.setAttribute("errorMessage", "Mật khẩu nhập lại không trùng");
            request.getRequestDispatcher("admin/user/create.jsp").forward(request, response);
        } else {
            user = new User(email, password, role);
            userDao.insert(user);
            response.sendRedirect("IndexUserServlet");
        }
    }

}