///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package tri.dev.admin.product;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.Part;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//import tri.dev.admin.BaseAdminServlet;
//import tri.dev.data.dao.CategoryDao;
//import tri.dev.data.dao.DatabaseDao;
//import tri.dev.data.dao.ProductDao;
//import tri.dev.data.model.Category;
//import tri.dev.data.model.Product;
//
//@WebServlet("/CreateProduct")
//@MultipartConfig(
//        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
//        maxFileSize = 1024 * 1024 * 10, // 10 MB
//        maxRequestSize = 1024 * 1024 * 100 // 100 MB
//)
//public class CreateProductServlet extends BaseAdminServlet {
//
//    private static final String UPLOAD_DIR = "uploads/product";
//    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        if (session.getAttribute("user") == null) {
//            response.sendRedirect("LoginServlet");
//            return;
//        }
//        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
//        List<Category> categoryList = categoryDao.findAll();
//
//        request.setAttribute("categoryList", categoryList);
//        request.getRequestDispatcher("admin/product/create.jsp").include(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String name = request.getParameter("name");
//        String description = request.getParameter("description");
//        double price = Double.parseDouble(request.getParameter("price"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
//
//        // Handle file upload
//        Part filePart = request.getPart("thumbnail");
//        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//        String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
//
//        // Ensure the upload directory exists
//        Files.createDirectories(Paths.get(uploadPath));
//
//        // Save the file on the server
//        String filePath = uploadPath + File.separator + fileName;
//        filePart.write(filePath);
//
//        // Set the thumbnail path relative to the application context
//        String thumbnail = UPLOAD_DIR + "/" + fileName;
//
//        // Create and insert the product
//        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
//        Product product = new Product(name, description, thumbnail, price, quantity, categoryId);
//        productDao.insert(product);
//
//        // Redirect to product list
//        response.sendRedirect("IndexProductServlet");
//    }
//}
package tri.dev.admin.product;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import tri.dev.admin.BaseAdminServlet;
import tri.dev.data.dao.CategoryDao;
import tri.dev.data.dao.DatabaseDao;
import tri.dev.data.dao.ProductDao;
import tri.dev.data.model.Category;
import tri.dev.data.model.Product;

@WebServlet("/CreateProduct")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class CreateProductServlet extends BaseAdminServlet {

    private static final String UPLOAD_DIR = "uploads/product";
    private static final String QR_UPLOAD_DIR = "uploads/QRCodeGenerator";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("LoginServlet");
            return;
        }
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        List<Category> categoryList = categoryDao.findAll();

        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("admin/product/create.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        // Handle file upload
        Part filePart = request.getPart("thumbnail");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

        Files.createDirectories(Paths.get(uploadPath));

        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        String thumbnail = UPLOAD_DIR + "/" + fileName;

        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        Product product = new Product(name, description, thumbnail, price, quantity, categoryId);
        productDao.insert(product);

        generateQRCode(request, response, name, description, price, quantity, product.getId());

        response.sendRedirect("IndexProductServlet");
    }

    private void generateQRCode(HttpServletRequest request, HttpServletResponse response,
            String name, String description, double price, int quantity, int productId)
            throws IOException {
        // QR code data
        String qrData = String.format("%d,%s,%s,%.2f,%d", productId, name, description, price, quantity);

        // Path for saving QR code
        String qrUploadPath = request.getServletContext().getRealPath("") + File.separator + QR_UPLOAD_DIR;
        Files.createDirectories(Paths.get(qrUploadPath));

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            // Generate QR code matrix
            BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, 200, 200);

            // Remove special characters from the product name for a safe file name
            String sanitizedFileName = name.replaceAll("[^a-zA-Z0-9\\-_]", "_");

            // Save QR code image file with the product name
            String qrFileName = sanitizedFileName + ".png";
            Path qrFilePath = Paths.get(qrUploadPath, qrFileName);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrFilePath);

            // Optionally, you can send the QR code directly in the response
            response.setContentType("image/png");
            OutputStream out = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);
            out.flush();

            // Debug log for saved QR code path
            System.out.println("QR Code saved at: " + qrFilePath.toString());
        } catch (WriterException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating QR code");
        }
    }
}
