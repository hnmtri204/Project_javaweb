package tri.dev;

import tri.dev.data.dao.DatabaseDao;
import tri.dev.data.dao.ProductDao;
import tri.dev.data.model.Product;
import tri.dev.util.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import tri.dev.data.dao.CategoryDao;
import tri.dev.data.model.Category;

public class ShopServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();

        // Check if a specific product ID is requested
        String productId = request.getParameter("id");
        if (productId != null) {
            // Fetch the specific product
            Product product = productDao.find(Integer.parseInt(productId));
            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("product-detail.jsp").forward(request, response);
                return;
            }
        }

        // If no specific product is requested, continue with the product list logic
        int totalProducts = productDao.countAll();
        int page = 1;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                // Keep default as 1 if parameter is invalid
            }
        }

        // Filter products
        String property = request.getParameter("property");
        String order = request.getParameter("order");

        List<Product> productList = new ArrayList<>();

        if (property != null && order != null) {
            if (property.equals("name") || property.equals("price") || property.equals("createdAt")) {
                if (order.equals("asc") || order.equals("desc")) {
                    productList = productDao.filterProducts(property, order, (page - 1) * Constants.PER_PAGE, Constants.PER_PAGE);
                } else {
                    productList = productDao.getProducts((page - 1) * Constants.PER_PAGE, Constants.PER_PAGE);
                }
            } else {
                productList = productDao.getProducts((page - 1) * Constants.PER_PAGE, Constants.PER_PAGE);
            }
        } else {
            productList = productDao.getProducts((page - 1) * Constants.PER_PAGE, Constants.PER_PAGE);
        }

        int numberPage = (int) Math.ceil((double) totalProducts / Constants.PER_PAGE);

        request.setAttribute("page", page);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("productList", productList);

        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        List<Category> categoryList = categoryDao.findAll();
        request.setAttribute("categoryList", categoryList);

        request.setAttribute("pageTitle", "Shop");
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}