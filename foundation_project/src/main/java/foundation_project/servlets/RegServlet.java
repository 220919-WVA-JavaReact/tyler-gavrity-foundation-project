package foundation_project.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import foundation_project.models.Employee;
import foundation_project.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class RegServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    private EmployeeService es = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);
        //super.doPut(req, resp);

        String providedUsername = (String) credentials.get("username");
        String providedPassword = (String) credentials.get("password");

        Employee employee = es.register(providedUsername, providedPassword);

        if (employee.getEm_id() != -1) {
            if (employee != null) {
                //I need to get the employee to throw the else statement if that username is already in use
                //.getPassword().equals(providedPassword)
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.getWriter().write("Employee created");
                resp.getWriter().write(mapper.writeValueAsString(employee));
            } else {
                resp.setStatus(404);
                resp.getWriter().write("Couldn't register user");
            }

        }else{
            resp.setStatus(404);
            resp.getWriter().write("Username already taken");
        }
    }

}
