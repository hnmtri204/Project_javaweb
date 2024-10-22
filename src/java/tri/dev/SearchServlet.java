package tri.dev;

import tri.dev.data.dao.DatabaseDao;
import tri.dev.data.dao.ProductDao;
import tri.dev.data.model.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * SearchServlet handles search requests from users.
 */
public class SearchServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the keyword from the request
        String keyword = request.getParameter("keyword");
        
        // Get ProductDao instance
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        
        // Fetch the product list based on the keyword
        List<Product> productList = productDao.findByName(keyword);
        
        // Set attributes for the request
        request.setAttribute("keyword", keyword);
        request.setAttribute("productList", productList);
        
        // Forward the request to search.jsp
        request.getRequestDispatcher("search.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Not implemented for search functionality
    }
}
