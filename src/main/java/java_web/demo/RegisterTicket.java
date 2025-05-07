package java_web.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/RegisterTicket")
public class RegisterTicket extends HttpServlet{
    public static List<User> users = new ArrayList<User>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRegister(request, response);
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String className = request.getParameter("className");
        String type = request.getParameter("type");
        String licensePlate = request.getParameter("licensePlate");

        try {

            if (userName == null || userName.trim().isEmpty() ||
                    className == null || className.trim().isEmpty() ||
                    type == null || type.trim().isEmpty() ||
                    licensePlate == null || licensePlate.trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ thông tin!");
            }

            if (!licensePlate.matches("[0-9A-Z-]{6,10}")) {
                throw new IllegalArgumentException("Biển số xe không hợp lệ!");
            }


            users.add(new User(userName, className, type, licensePlate));
            request.setAttribute("message", "Đăng ký vé xe thành công cho " + userName + "!");


        } catch (IllegalArgumentException e) {

            request.setAttribute("message", "Đăng ký thất bại: " + e.getMessage());

        }

        request.getRequestDispatcher("/resultRegister.jsp").forward(request, response);

    }
}