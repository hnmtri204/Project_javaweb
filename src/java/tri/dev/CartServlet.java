package tri.dev;

import tri.dev.data.model.OrderItem;
import tri.dev.data.model.Order;
import tri.dev.util.Helper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class CartServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<OrderItem>();
        }

        List<Order> orders = (List<Order>) session.getAttribute("orders");
        if (orders == null) {
            orders = new ArrayList<Order>(); // Hoặc lấy từ cơ sở dữ liệu nếu cần
        }

        request.setAttribute("cart", cart);
        double totalAmount = Helper.total(cart);
        request.setAttribute("total", totalAmount);
        request.setAttribute("orders", orders); // Truyền đơn hàng tới JSP

        // Tạo mã QR ngay trong doGet
        String qrCodeBase64 = generateQRCodeBase64("Total Amount: $" + totalAmount);
        request.setAttribute("qrCodeBase64", qrCodeBase64);

        // Ghi lại thông tin giỏ hàng vào JSP
        request.getRequestDispatcher("cart.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createOrder(request);
                break;
            case "update":
                updateOrder(request);
                break;
            case "delete":
                deleteOrder(request);
                break;
            case "pay":
                // Xử lý logic thanh toán nếu cần
                break;
            default:
                throw new AssertionError();
        }

        response.sendRedirect("CartServlet");
    }

    // Phương thức để tạo mã QR dưới dạng chuỗi Base64
    private String generateQRCodeBase64(String paymentInfo) throws IOException {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(paymentInfo, BarcodeFormat.QR_CODE, 250, 250);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            byte[] qrCodeBytes = outputStream.toByteArray();
            
            return Base64.getEncoder().encodeToString(qrCodeBytes);
        } catch (WriterException e) {
            e.printStackTrace();
            throw new IOException("Error generating QR code: " + e.getMessage());
        }
    }

    private void createOrder(HttpServletRequest request) {
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        double price = Double.parseDouble(request.getParameter("price"));

        OrderItem orderItem = new OrderItem(quantity, price, 0, productId);
        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        } else {
            for (OrderItem ord : cart) {
                if (ord.getProductId() == productId) {
                    ord.setQuantity(ord.getQuantity() + quantity);
                    session.setAttribute("cart", cart);
                    return;
                }
            }
        }
        cart.add(orderItem);
        session.setAttribute("cart", cart);
    }

    private void updateOrder(HttpServletRequest request) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart != null) {
            for (OrderItem ord : cart) {
                if (ord.getProductId() == productId) {
                    ord.setQuantity(quantity);
                    break;
                }
            }
        }
        session.setAttribute("cart", cart);
    }

    private void deleteOrder(HttpServletRequest request) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(ord -> ord.getProductId() == productId);
        }
        session.setAttribute("cart", cart);
    }
}
