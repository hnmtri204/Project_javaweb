/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package tri.dev;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import tri.dev.data.dao.CategoryDao;
import tri.dev.data.dao.DatabaseDao;
import tri.dev.data.dao.ProductDao;
import tri.dev.data.model.Category;
import tri.dev.data.model.Product;
import tri.dev.util.Constants;

public class ProductServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        Product product = productDao.find(productId);

        if ("getQRCode".equals(action)) {
            // Generate QR code content with product details
            String qrContent = "Product ID: " + productId + "\n"
                    + "Name: " + product.getName() + "\n"
                    + "Description: " + product.getDescription() + "\n"
                    + "Price: $" + product.getPrice();
            BufferedImage qrImage = generateQRCodeImage(qrContent);
            // Send the generated QR code image in the response
            response.setContentType("image/png");
            ServletOutputStream outputStream = response.getOutputStream();
            if (qrImage != null) {
                javax.imageio.ImageIO.write(qrImage, "PNG", outputStream);
            }
            outputStream.close();
        } else {
            // Load product details and forward to detail.jsp
            List<Product> newsProductList = productDao.news(Constants.NUMBER_LIMIT);
            CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
            List<Category> categoryList = categoryDao.findAll();
            List<Product> relatedProducts = productDao.findRelatedProducts(product.getCategoryId(), productId);

            request.setAttribute("newsProductList", newsProductList);
            request.setAttribute("product", product);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("relatedProducts", relatedProducts);
            request.getRequestDispatcher("detail.jsp").forward(request, response);
        }
    }

    private BufferedImage generateQRCodeImage(String content) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 200;
        int height = 200;
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
