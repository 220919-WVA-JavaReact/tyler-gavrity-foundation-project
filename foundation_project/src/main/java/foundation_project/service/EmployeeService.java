package foundation_project.service;

import foundation_project.dao.EmployeeDAO;
import foundation_project.dao.EmployeeDaoPostgres;
import foundation_project.models.Employee;

import java.util.Scanner;

public class EmployeeService {

    public static ReimbursementService rembSer = new ReimbursementService();
    EmployeeDAO emDao = new EmployeeDaoPostgres();

    Scanner input = new Scanner(System.in);

    boolean repeat = true;

    Employee loggedInEmployee = null;

    public Employee login() {

        System.out.println("Please enter your username");
        String username = input.nextLine();
        System.out.println("Please enter your password");
        String password = input.nextLine();

        Employee em = emDao.getByUsername(username);

        if (em.getPassword().equals(password)) {
            System.out.println("Sign in successful");
            System.out.println("+------------------------+");
            System.out.println("");
            System.out.println("Welcome " + username);

            return em;

        } else {
            System.out.println("Please try again");
            return null;
        }

    }


    public Employee register() {
        System.out.println("Please enter your username");
        String user = input.nextLine();
        System.out.println("Please enter your password");
        String password = input.nextLine();
//        System.out.println("Are you an employee or manager?");
//        String isManager = input.nextLine();

        //now use the create method

        Employee em = emDao.registerEmployee(user, password);
        System.out.println("You have been registered");


        return em;

    }

//    public Employee looper() {
//        //Employee loggedInEmployee = null;
//
//        while (repeat) {
//
//            if (loggedInEmployee != null) {
//                //Another prompt since the employee has either logged in or registered
//                System.out.println("What would you like to do?");
//                System.out.println("Press 1 to create a ticket, 2 to view your tickets, or 3 to logout");
//                String choiceTwo = input.nextLine();
//
//                switch (choiceTwo) {
//                    case "1":
//                        rembSer.submitReimbursment(loggedInEmployee);
//                        break;
//                    case "2":
//                        rembSer.getReimbursementbyEmId(loggedInEmployee);
//                        break;
//                    case "3":
//
//                        repeat = false;
//                        break;
//
//                }
//
//            }
//        }
//        return null;
//    }
}



