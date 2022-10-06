package foundation_project.service;

import foundation_project.dao.ReimbursementDAO;
import foundation_project.dao.ReimbursementDAOImpl;
import foundation_project.dao.ReimbursementDaoPostgres;
import foundation_project.models.Employee;
import foundation_project.models.Reimbursement;

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

    public void getReimbursementbyEmId(Employee loggedIn){
//        System.out.println("Please enter your Employee Id");
//        int em_id = input.nextInt();


        check.getReimbursementbyEmId(loggedIn.getEm_id());
    }



    //Now we have to create a method that creates the reimbursment ticket
    public void updateStatus(){
        //This will be used by the manager to change the pending to approved
        System.out.println("Has this Reimbursment ticket been approved? ");

    }
}
