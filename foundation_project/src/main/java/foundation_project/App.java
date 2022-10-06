package foundation_project;

import foundation_project.models.Employee;
import foundation_project.models.Reimbursement;
import foundation_project.service.EmployeeService;
import foundation_project.service.ReimbursementService;

import java.util.Scanner;

public class App {


    public static EmployeeService emSer = new EmployeeService();

    public static ReimbursementService rembSer = new ReimbursementService();

    public static void main(String[] args) {

        System.out.println("Welcome to the Reimbursement Center");
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to login or 2 to register");
        Scanner input = new Scanner(System.in);
        String logreg = input.nextLine();
        //create options for the user and once logged in be able to create a ticket
        Employee loggedInEmployee = null;
        switch(logreg){

            case "1":

                System.out.println("Login");
                loggedInEmployee = emSer.login();
                break;


            case "2":

                System.out.println("Register");
                loggedInEmployee = emSer.register();
                break;
        }

        if(loggedInEmployee != null){
            //Another prompt since the employee has either logged in or registered
            System.out.println("What would you like to do?");
            System.out.println("Press 1 to create a ticket or 2 to view your tickets");
            String choiceTwo = input.nextLine();

            switch (choiceTwo){
                case "1":
                    rembSer.submitReimbursment(loggedInEmployee);
                    break;
                case "2":
                    rembSer.getReimbursementbyEmId(loggedInEmployee);
            }

        }
    }

}
