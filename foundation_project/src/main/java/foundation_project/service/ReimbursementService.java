package foundation_project.service;

import foundation_project.dao.ReimbursementDAO;
import foundation_project.dao.ReimbursementDAOImpl;
import foundation_project.dao.ReimbursementDaoPostgres;
import foundation_project.models.Employee;
import foundation_project.models.Reimbursement;

import java.util.List;
import java.util.Scanner;

public class ReimbursementService {

    Scanner input = new Scanner(System.in);

    ReimbursementDAO check = new ReimbursementDaoPostgres();

    public void submitReimbursment(Employee loggedIn){

        System.out.println("Please enter your amount for reimbursement");
        int amount = Integer.parseInt(input.nextLine());

        System.out.println("Please enter the description of the reimbursement");
        String description = input.nextLine();

//        System.out.println("Please enter your employee ID");
//        int em_id = Integer.parseInt(input.nextLine());


        //This method makes sure that the info gets sent to the database
        check.submitReimbursement(amount, description, loggedIn.getEm_id());
    }

    public List<Reimbursement> getReimbursementbyEmId(int em_id){
//        System.out.println("Please enter your Employee Id");
//        int em_id = input.nextInt();

        //List<Reimbursement> theList = check.getReimbursementbyEmId(em_id);
        return check.getReimbursementbyEmId(em_id);
        //return theList;
    }



    //Now we have to create a method that creates the reimbursment ticket
    public Reimbursement updateReimbursment(String status, int ticket_id){
//        System.out.println("You would like to update a ticket");
//        System.out.println("+---------------------------------------------+");
//        System.out.println("Which ticket number would you like to update?");
//        int updNum = Integer.parseInt(input.nextLine());
//        System.out.println("What would you like to do? approve or deny");
//        String changeStat = input.nextLine();
        return check.updateReimbursement(status, ticket_id);
        //This will be used by the manager to change the pending to approved
        //System.out.println("Has this Reimbursment ticket been approved? ");

    }

    public List<Reimbursement> getReimbursementByPending(){
         return check.getReimbursementByPending();
    }
}
