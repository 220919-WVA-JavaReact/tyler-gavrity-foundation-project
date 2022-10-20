package foundation_project.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import foundation_project.models.Employee;
import foundation_project.models.Reimbursement;
import foundation_project.service.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PendingServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    private ReimbursementService reimb = new ReimbursementService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);
        HttpSession session = req.getSession(false);

        if(session != null) {
            Employee loggedIn = (Employee) session.getAttribute("auth_employee");
            // int em_id = (Integer) credentials.get("em_id");
            if (loggedIn.getisManager().equals("manager")) {
                List<Reimbursement> reimbursement = reimb.getReimbursementByPending();
                String payload = mapper.writeValueAsString(reimbursement);
                if (!payload.equals("null")) {
                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.getWriter().write("All pending tickets are displayed");
                    resp.getWriter().write(mapper.writeValueAsString(reimbursement));
                } else {
                    resp.getWriter().write("Error");
                    resp.setStatus(400);
                }

            }else{
                resp.getWriter().write("MANAGERS ONLY");
                resp.setStatus(400);
            }
        }

    }
}
