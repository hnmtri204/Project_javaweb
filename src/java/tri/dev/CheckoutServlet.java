package tri.dev;

import tri.dev.data.dao.DatabaseDao;
import tri.dev.data.dao.OrderDao;
import tri.dev.data.dao.OrderItemDao;
import tri.dev.data.model.Order;
import tri.dev.data.model.OrderItem;
import tri.dev.data.model.User;
import tri.dev.util.StringHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckoutServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(CheckoutServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("LoginServlet");
        } else {
            try {
                processCheckout(request, user);
                session.setAttribute("message", "Checkout Success");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Checkout failed", e);
                session.setAttribute("error", "Checkout failed, please try again.");
            }
            response.sendRedirect("CartServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void processCheckout(HttpServletRequest request, User user) {
        OrderDao orderDao = DatabaseDao.getInstance().getOrderDao();
        String code = StringHelper.randomString(10);
        Order order = new Order(code, "pending", user.getId());

        orderDao.insert(order);
        order = orderDao.findByCode(code);
        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
        OrderItemDao orderItemDao = DatabaseDao.getInstance().getOrderItemDao();

        if (cart != null) {
            for (OrderItem orderItem : cart) {
                orderItem.setOrderId(order.getId());
                orderItemDao.insert(orderItem);
            }
        }

        sendCheckoutEmail(user.getEmail(), code);
        session.removeAttribute("cart");
    }

    private void sendCheckoutEmail(String toEmail, String orderCode) {
        try {
            String urlStr = "https://api.sendgrid.com/v3/mail/send";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set up connection
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", "");

            String subject = "Đặt Hàng Thành Công";
            String content = String.format("Cảm ơn bạn đã đặt hàng! Mã đơn hàng của bạn là: %s", orderCode);

            String jsonInputString = String.format("{"
                    + "\"personalizations\": [{\"to\": [{\"email\": \"%s\"}]}],"
                    + "\"from\": {\"email\": \"hoangngocminhtri.k22@gmail.com\"},"
                    + "\"subject\": \"%s\","
                    + "\"content\": [{\"type\": \"text/plain\", \"value\": \"%s\"}]"
                    + "}", toEmail, subject, content);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(responseCode >= 300 ? conn.getErrorStream() : conn.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            System.out.println("SendGrid API Response Code: " + responseCode);
            System.out.println("SendGrid API Response Body: " + response.toString());

            if (responseCode >= 200 && responseCode < 300) {
                System.out.println("Email sent successfully to: " + toEmail);
                System.out.println("Message ID: " + conn.getHeaderField("X-Message-Id"));
            } else {
                System.out.println("Failed to send email. Response Code: " + responseCode + ", Message: " + response.toString());
            }
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
