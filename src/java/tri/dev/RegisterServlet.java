package tri.dev;

import tri.dev.data.dao.DatabaseDao;
import tri.dev.data.dao.UserDAO;
import tri.dev.data.model.User;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import tri.dev.data.dao.CategoryDao;
import tri.dev.data.model.Category;

public class RegisterServlet extends BaseServlet {

    private static final String SMTP_SERVER = "smtp.gmail.com"; 
    private static final int SMTP_PORT = 587; 
        private static final String USERNAME = "hoangngocminhtri.k22@gmail.com"; 
    private static final String PASSWORD = "zghoksjhbnufxeuh"; 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        List<Category> categoryList = categoryDao.findAll();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("register.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        // Kiểm tra hợp lệ
        if (!email.endsWith("@gmail.com")) {
            session.setAttribute("error", "Email must be a Gmail address (ending with @gmail.com)");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!password.matches("\\d{8}")) {
            session.setAttribute("error", "Password must be exactly 8 digits long and contain only numbers");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(repeatPassword)) {
            session.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        UserDAO userDao = DatabaseDao.getInstance().getUserDao();
        User existingUser = userDao.find(email);

        if (existingUser != null) {
            session.setAttribute("error", "Email already exists");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            String hashedPassword = hashPassword(password);
            User newUser = new User(email, hashedPassword, "user");
            userDao.insert(newUser);

            sendEmail(email);

            response.sendRedirect("HomeServlet");
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendEmail(String toEmail) {
        try {
            String urlStr = "https://api.sendgrid.com/v3/mail/send";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set up connection
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", "");

            String subject = "Chúc mừng bạn đã đăng ký thành công!";
            String content = String.format("Xin chào,\\n\\nCảm ơn bạn đã đăng ký tài khoản. Chúng tôi rất vui khi có bạn tham gia.\\n\\nTrân trọng,\\nĐội ngũ hỗ trợ.");

            String jsonInputString = "{"
                    + "\"personalizations\": [{\"to\": [{\"email\": \"" + toEmail + "\"}]}],"
                    + "\"from\": {\"email\": \"hoangngocminhtri.k22@gmail.com\"},"
                    + "\"subject\": \"" + subject + "\","
                    + "\"content\": [{\"type\": \"text/plain\", \"value\": \"" + content + "\"}]"
                    + "}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Check response
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
            System.out.println("SendGrid API Request Body: " + jsonInputString);

            for (String headerName : conn.getHeaderFields().keySet()) {
                if (headerName != null) {
                    System.out.println(headerName + ": " + conn.getHeaderField(headerName));
                }
            }

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

