package java_web.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
    private static List<Task> tasks = new ArrayList<>();
    private static AtomicInteger idGenerator = new AtomicInteger(1);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String description = request.getParameter("description");
            if (description != null && !description.trim().isEmpty()) {
                tasks.add(new Task(idGenerator.getAndIncrement(), description));
                request.setAttribute("message", "Thêm công việc thành công!");
            } else {
                request.setAttribute("error", "Vui lòng nhập mô tả công việc!");
            }
        } else if ("complete".equals(action)) {
            String taskId = request.getParameter("taskId");
            try {
                int id = Integer.parseInt(taskId);
                tasks.stream()
                        .filter(task -> task.getId() == id)
                        .findFirst()
                        .ifPresent(task -> task.setCompleted(true));
                request.setAttribute("message", "Đánh dấu công việc hoàn thành!");
            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID công việc không hợp lệ!");
            }
        }

        // Luôn hiển thị danh sách công việc
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("/todoList.jsp").forward(request, response);
    }
}