package java_web.demo;
import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "userServlet", value = "/userServlet")
public class userServlet extends HttpServlet {
    private String name;
    private int age;

    public void init() {
        name = "Vuong";
        age = 12;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("name", name);
        request.setAttribute("age", age);

        request.getRequestDispatcher("/userInfo.jsp").forward(request,response);
    }

    public void destroy() {
    }
}