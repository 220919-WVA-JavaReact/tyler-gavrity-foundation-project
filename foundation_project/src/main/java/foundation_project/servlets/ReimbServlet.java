package foundation_project.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import foundation_project.models.Employee;
import foundation_project.models.Reimbursement;
import foundation_project.service.EmployeeService;
import foundation_project.service.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReimbServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    private ReimbursementService reimb = new ReimbursementService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);
        //super.doPut(req, resp);
        HttpSession session = req.getSession(false);
        if(session != null){
            Employee loggedIn = (Employee) session.getAttribute("auth_employee");
       // int em_id = (Integer) credentials.get("em_id");

       List<Reimbursement> reimbursement = reimb.getReimbursementbyEmId(loggedIn.getEm_id());
       String payload = mapper.writeValueAsString(reimbursement);
       if(!payload.equals("null")) {
           resp.setStatus(200);
           resp.setContentType("application/json");
           resp.getWriter().write("Here are your tickets");
           resp.getWriter().write(mapper.writeValueAsString(reimbursement));
       }else{
           resp.getWriter().write("Error");
           resp.setStatus(400);
       }

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);
        HttpSession session = req.getSession(false);

        String providedStatus = (String) credentials.get("status");
        int providedTicketId = (Integer) credentials.get("ticket_id");
        Reimbursement checkerReim = reimb.getReimbursementById(providedTicketId);


        if(checkerReim.getStatus().equals("pending")) {
            Reimbursement reimbursements = reimb.updateReimbursment(providedStatus, providedTicketId);
            Employee loggedIn = (Employee) session.getAttribute("auth_employee");
            // int em_id = (Integer) credentials.get("em_id");
            if (loggedIn.getisManager().equals("manager")) {
                if (session != null) {
                    Reimbursement reimbursement = reimb.updateReimbursment(providedStatus, providedTicketId);
                    String payload = mapper.writeValueAsString(reimbursement);
                    if (!payload.equals("null")) {
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write("You updated the ticket");
                        resp.getWriter().write(mapper.writeValueAsString(reimbursements));
                    } else {
                        resp.getWriter().write("Error");
                        resp.setStatus(400);
                    }

                }
            } else {
                resp.getWriter().write("MANAGERS ONLY");
                resp.setStatus(400);
            }
        }else{
            resp.setStatus(400);
            resp.getWriter().write("This ticket has already been changed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);
        HttpSession session = req.getSession(false);

        int providedAmount = (Integer) credentials.get("amount");
        String providedDescription = (String) credentials.get("description");


        // Reimbursement reimbursements = reimb.submitReimbursment(providedAmount, providedDescription, loggedIn.getEm_id());
        if(!providedDescription.equals("")){
            if(providedAmount >= 1) {
                if (session != null) {
                    Employee loggedIn = (Employee) session.getAttribute("auth_employee");
                    // int em_id = (Integer) credentials.get("em_id");
                    if (loggedIn.getisManager().equals("employee")) {
                        Reimbursement reimbursement = reimb.submitReimbursment(providedAmount, providedDescription, loggedIn.getEm_id());
                        String payload = mapper.writeValueAsString(reimbursement);
                        if (!payload.equals("null")) {
                            resp.setStatus(200);
                            resp.setContentType("application/json");
                            resp.getWriter().write("Ticket Created");
                            resp.getWriter().write(mapper.writeValueAsString(reimbursement));
                        } else {
                            resp.getWriter().write("Error");
                            resp.setStatus(400);
                        }

                    }
                }
            }else{resp.setStatus(400);}
    }else{resp.setStatus(400);}
    }
}
