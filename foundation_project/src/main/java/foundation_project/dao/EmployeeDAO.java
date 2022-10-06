package foundation_project.dao;

import foundation_project.models.Employee;

public interface EmployeeDAO {

        //This is where the methods I want to user go

    //first we need a create employee method

    Employee getByUsername(String username);

    Employee registerEmployee(String username, String password);


}
