package foundation_project.models;

import java.util.Objects;

public class Reimbursement {

    private int ticket_id;

    private int amount;

    private String description;

    private String status;

    private int em_id;


    public Reimbursement(int ticket_id, int amount, String description, String status, int em_id) {
        this.ticket_id = ticket_id;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.em_id = em_id;
    }

    public Reimbursement(int ticket_id, int em_id) {
        this.ticket_id = ticket_id;
        this.em_id = em_id;
    }

    public Reimbursement() {
    }

//    public Reimbursement(String changeStatus, boolean status) {
//    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEm_id() {
        return em_id;
    }

    public void setEm_id(int em_id) {
        this.em_id = em_id;
    }

    @Override
    public String toString() {


        return


                "Reimbursement:" +
                "Your ticket Id: " + ticket_id +
                ", Amount: " + amount +
                ", Description: '" + description + '\'' +
                ", Status: " + status +
                ", Employee Id: " + em_id
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return ticket_id == that.ticket_id && amount == that.amount && status == that.status && em_id == that.em_id && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket_id, amount, description, status, em_id);
    }
}
