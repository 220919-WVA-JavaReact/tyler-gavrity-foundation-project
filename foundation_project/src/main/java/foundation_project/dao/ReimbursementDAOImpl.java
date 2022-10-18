package foundation_project.dao;

import foundation_project.models.Reimbursement;

import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO{


//    @Override
//    public Reimbursement submitReimbursement(int amount, String description, int em_id) {
//        System.out.println("Reimbursement submitted");
//    return null;
//    }

    @Override
    public Reimbursement submitReimbursement(int amount, String description, int em_id) {
        return null;
    }

    @Override
    public List<Reimbursement> getAllReimbursments() {
        System.out.println("Return List of Reimbursements");

        return null;
    }

    @Override
    public List<Reimbursement> getReimbursementbyEmId(int em_id) {
        System.out.println("Return list of reimbursements by employee ID");
        return null;
    }

    @Override
    public Reimbursement updateReimbursement(String status, int ticket_id) {
        System.out.println("This will return when status changes from pending to approved");
        return null;
    }

    @Override
    public List<Reimbursement> getReimbursementByPending() {
        return null;
    }

//    @Override
//    public void submitReimbursement(int amount, String description, int em_id) {
//        return null;
//    }
}
