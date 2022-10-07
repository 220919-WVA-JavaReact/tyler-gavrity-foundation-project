package foundation_project.dao;

import foundation_project.models.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {
    Reimbursement submitReimbursement(int amount, String description, int em_id);

     //This function will hopefully retrieve all reimbursement tickets for the manager
    List<Reimbursement> getAllReimbursments();
    //This function will display the reimbursement
    List<Reimbursement> getReimbursementbyEmId(int em_id);

    boolean updateReimbursement(Reimbursement status);

//    void submitReimbursement(int amount, String description, int em_id);
    //This will be used to change the status of the ticket from pending to confirmed
}
