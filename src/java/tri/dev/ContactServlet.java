/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package tri.dev;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import tri.dev.data.dao.CategoryDao;
import tri.dev.data.dao.DatabaseDao;
import tri.dev.data.model.Category;

/**
 *
 * @author MSI
 */
public class ContactServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Truy vấn danh sách danh mục từ database
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        List<Category> categoryList = categoryDao.findAll();

        // Truyền danh sách danh mục cho JSP
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("pageTitle", "Contact");
        request.getRequestDispatcher("contact.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
