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

    public Employee login(String username, String password) {

//        System.out.println("Please enter your username");
//        String username = input.nextLine();
//        System.out.println("Please enter your password");
//        String password = input.nextLine();

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


    public Employee register(String username, String password) {
//        System.out.println("Please enter your username");
//        String user = input.nextLine();
//        System.out.println("Please enter your password");
//        String password = input.nextLine();
//        System.out.println("Are you an employee or manager?");
//        String isManager = input.nextLine();

        //now use the create method
        Employee checkName = emDao.getByUsername(username);
        if(checkName == null) {
            Employee em = emDao.registerEmployee(username, password);
            //System.out.println("You have been registered");
            return em;
        }else{
            checkName = new Employee(-1);
            return checkName;
        }

    }


}



