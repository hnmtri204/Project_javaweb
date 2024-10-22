///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package tri.dev;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//
//@WebServlet("/QRCodeServlet")
//public class QRCodeServlet extends BaseServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String productId = request.getParameter("productId");
//        String productName = request.getParameter("productName");
//        String productPrice = request.getParameter("productPrice");
//
//        // Dữ liệu cho mã QR (thông tin sản phẩm)
//        String qrCodeData = "Product ID: " + productId + "\nName: " + productName + "\nPrice: $" + productPrice;
//
//        int size = 250;
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix;
//        try {
//            bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, size, size);
//        } catch (WriterException e) {
//            throw new ServletException("Error generating QR Code", e);
//        }
//
//        response.setContentType("image/png");
//        OutputStream outputStream = response.getOutputStream();
//        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
//        outputStream.close();
//    }
//}
