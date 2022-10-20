package foundation_project.dao;

import foundation_project.models.Employee;
import foundation_project.models.Reimbursement;
import foundation_project.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoPostgres implements ReimbursementDAO{


    @Override
    public Reimbursement submitReimbursement(int amount, String description, int em_id) {

        //Hopefully this adds a reimbursement ticket to the database

        Reimbursement ticket = new Reimbursement();

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "Insert into reimbursement (\"amount\", \"description\", \"em_id\") values (?,?,?) returning *";
            //These two lines interact by creating a sql statement and making it safe enough to add to the database
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, amount);
            stmt.setString(2,description);
//            stmt.setString(3, status);
            stmt.setInt(3, em_id);

            ResultSet rs;
            if ((rs = stmt.executeQuery()) != null ){
                //if the statement is not null aka the query went through

                while(rs.next()) {

                    int receivedTicket_id = rs.getInt("ticket_id");
                    int receivedAmount = rs.getInt("amount");
                    String receivedDescription = rs.getString("description");
                    String receivedStatus = rs.getString("status");
                    int receivedEmId = rs.getInt("em_id");

                    System.out.println("Your ticket has been submitted");

                    //call the new object??
                    ticket = new Reimbursement(receivedTicket_id, receivedAmount, receivedDescription, receivedStatus, receivedEmId);

                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't create ticket");
        }

        return ticket;
    }

    @Override
    public List<Reimbursement> getAllReimbursments() {
        return null;
    }

    @Override
    public List<Reimbursement> getReimbursementbyEmId(int em_id) {

        //Connection conn = ConnectionUtil.getConnection();

        List<Reimbursement> tickets = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()){
            //Statement stmt = conn.createStatement();
            //What do you want the string to get?

            String sql = "select * from reimbursement where em_id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, em_id);

            ResultSet rs;
            if((rs = stmt.executeQuery()) != null) {
                while (rs.next()) {
                    int ticketId = rs.getInt("ticket_id");
                    int amount = rs.getInt("amount");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    int emId = rs.getInt("em_id");


                    Reimbursement ticket = new Reimbursement(ticketId, amount, description, status, emId);
                    System.out.println(ticket.toString());

                    //To add the item to the list of teachers

                    tickets.add(ticket);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't get employee's tickets");
            return null;
        }


        return tickets;
    }

    @Override
    public Reimbursement updateReimbursement(String status, int ticket_id) {
        //Connection conn = ConnectionUtil.getConnection();

        List<Reimbursement> tickets = new ArrayList<>();

        Reimbursement ticket = null;
        try (Connection conn = ConnectionUtil.getConnection()) {
            //Statement stmt = conn.createStatement();
            //What do you want the string to get?

            String sql = "update reimbursement set status = ? where ticket_id = ? returning *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, status);
            stmt.setInt(2, ticket_id);

            ResultSet rs;
            if ((rs = stmt.executeQuery()) != null) {
                while (rs.next()) {
                    int updTicketId = rs.getInt("ticket_id");
                    int updAmount = rs.getInt("amount");
                    String updDescription = rs.getString("description");
                    String updStatus = rs.getString("status");
                    int updEmId = rs.getInt("em_id");


                    ticket = new Reimbursement(updTicketId, updAmount, updDescription, updStatus, updEmId);
                    //System.out.println(ticket.toString());

                    //To add the item to the list of teachers
                    System.out.println("Ticket updated");
                    tickets.add(ticket);
                    //return ticket;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't update ticket");
        }
        return ticket;
    }

    @Override
    public List<Reimbursement> getReimbursementByPending() {
        Connection conn = ConnectionUtil.getConnection();

        List<Reimbursement> tickets = new ArrayList<>();

        try{
            //Statement stmt = conn.createStatement();
            //What do you want the string to get?

            String sql = "select * from reimbursement where status = 'pending'";

            PreparedStatement stmt = conn.prepareStatement(sql);

            //stmt.setInt(1, em_id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                int viewTicketId = rs.getInt("ticket_id");
                int viewAmount = rs.getInt("amount");
                String viewDescription = rs.getString("description");
                String viewStatus = rs.getString("status");
                int viewEmId = rs.getInt("em_id");


                Reimbursement ticket = new Reimbursement(viewTicketId, viewAmount, viewDescription, viewStatus, viewEmId);
                System.out.println(ticket.toString());

                //To add the item to the list of teachers

                tickets.add(ticket);
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't get employee's tickets");
        }


        return null;
    }

//    @Override
//    public void submitReimbursement(int amount, String description, int em_id) {
//
//    }
}
