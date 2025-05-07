package java_web.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

import static java_web.demo.RegisterTicket.users;

@WebServlet(name = "ListStudentServlet", value = "/ListStudentServlet")
public class ListStudentServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Check if users list is null or empty
            if (users == null || users.isEmpty()) {
                throw new IllegalStateException("Danh sách người dùng trống hoặc không tồn tại!");
            }

            // Validate license plates
            for (Object obj : users) {
                if (!(obj instanceof User)) {
                    throw new IllegalStateException("Dữ liệu người dùng không đúng định dạng!");
                }
                User user = (User) obj;
                if (!user.getLicensePlate().matches("[0-9A-Z-]{6,10}")) {
                    throw new IllegalArgumentException("Biển số xe không hợp lệ cho người dùng " + user.getName() + ": " + user.getLicensePlate());
                }
            }

            request.setAttribute("users", users);
            request.getRequestDispatcher("/ListStudent.jsp").forward(request, response);

        } catch (IllegalArgumentException | IllegalStateException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle other errors
            request.setAttribute("errorMessage", "Đã xảy ra lỗi không mong muốn: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}