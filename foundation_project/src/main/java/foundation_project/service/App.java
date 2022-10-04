package foundation_project.service;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        System.out.println("Welcome to the Reimbursement Center");
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to login or 2 to register");
        Scanner input = new Scanner(System.in);
        String logreg = input.nextLine();
        System.out.println(logreg);
    }

}
