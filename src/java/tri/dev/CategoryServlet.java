/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package tri.dev;

import tri.dev.data.dao.CategoryDao;
import tri.dev.data.dao.DatabaseDao;
import tri.dev.data.dao.ProductDao;
import tri.dev.data.model.Category;
import tri.dev.data.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Welcome
 */
public class CategoryServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);

        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        List<Product> productList = new ArrayList<>();

        int page = 1; // Mặc định là trang 1
        int pageSize = 4; // Số sản phẩm mỗi trang

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        if (request.getParameter("property") != null && request.getParameter("order") != null) {
            String property = request.getParameter("property");
            String order = request.getParameter("order");
            productList = productDao.filter(categoryId, property, order);
        } else {
            productList = productDao.findByCategory(categoryId, page, pageSize);
        }

        // Tính số trang
        int totalProducts = productDao.countByCategory(categoryId);
        int numberPage = (int) Math.ceil((double) totalProducts / pageSize);

        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        Category category = categoryDao.find(categoryId);

        request.setAttribute("productList", productList);
        request.setAttribute("category", category);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("page", page);

        request.setAttribute("pageTitle", "Categories");
        request.getRequestDispatcher("category.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
