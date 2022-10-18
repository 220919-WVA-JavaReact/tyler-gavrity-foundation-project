package foundation_project.dao;

import foundation_project.models.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {
    Reimbursement submitReimbursement(int amount, String description, int em_id);

     //This function will hopefully retrieve all reimbursement tickets for the manager
    List<Reimbursement> getAllReimbursments();
    //This function will display the reimbursement
    List<Reimbursement> getReimbursementbyEmId(int em_id);

    Reimbursement updateReimbursement(String status, int ticket_id);

    List<Reimbursement> getReimbursementByPending();

//    void submitReimbursement(int amount, String description, int em_id);
    //This will be used to change the status of the ticket from pending to confirmed
}
