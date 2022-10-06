package foundation_project.service;

import foundation_project.dao.EmployeeDAO;
import foundation_project.dao.EmployeeDaoPostgres;
import foundation_project.models.Employee;

import java.util.Scanner;

public class EmployeeService {


    EmployeeDAO emDao = new EmployeeDaoPostgres();

    Scanner input = new Scanner(System.in);

    public Employee login(){

        System.out.println("Please enter your username");
        String username = input.nextLine();
        System.out.println("Please enter your password");
        String password = input.nextLine();

        Employee em = emDao.getByUsername(username);

        if(em.getPassword().equals(password)){
            System.out.println("Sign in successful");
            System.out.println("+------------------------+");
            System.out.println("");
            System.out.println("Welcome " + username);

            return em;

        } else{
            System.out.println("Please try again");
            return null;
        }

    }


    public Employee register(){
        System.out.println("Please enter your username");
        String user = input.nextLine();
        System.out.println("Please enter your password");
        String password = input.nextLine();

        //now use the create method

        Employee em = emDao.registerEmployee(user, password);
        System.out.println("You have been registered. Please try logging in");


        return em;

    }


}
