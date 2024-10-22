//package tri.dev;
//
//import tri.dev.data.model.Order;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderStatusServlet extends BaseServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        
//        // Giả sử danh sách đơn hàng được lưu trong session
//        List<Order> orders = (List<Order>) session.getAttribute("orders");
//        
//        // Nếu không có đơn hàng nào
//        if (orders == null) {
//            orders = new ArrayList<>();
//        }
//
//        request.setAttribute("orders", orders);
//        request.getRequestDispatcher("order_status.jsp").include(request, response);
//    }
//}


//package tri.dev;
//
//import tri.dev.data.dao.DatabaseDao;
//import tri.dev.data.dao.OrderDao;
//import tri.dev.data.model.Order;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//
//public class OrderStatusServlet extends BaseServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try (Connection connection = DatabaseDao.getInstance().getConnection()) {
////            OrderDao orderDao = new OrderDao(connection);
//            List<Order> orders = orderDao.findAll();
//            request.setAttribute("orders", orders);
//            request.getRequestDispatcher("order_status.jsp").include(request, response);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            request.setAttribute("error", "Could not retrieve orders.");
//            request.getRequestDispatcher("error.jsp").forward(request, response);
//        }
//    }
//}


