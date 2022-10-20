package foundation_project.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import foundation_project.models.Employee;
import foundation_project.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class AuthServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    private EmployeeService es = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);

        String providedUsername = (String) credentials.get("username");
        String providedPassword = (String) credentials.get("password");

        Employee employee = es.login(providedUsername, providedPassword);

        if(employee != null){
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(employee));
            HttpSession session = req.getSession();
            session.setAttribute("auth_employee", employee);
        }else {
            resp.setStatus(404);
            resp.getWriter().write("Invalid credentials");
        }
    }


}
