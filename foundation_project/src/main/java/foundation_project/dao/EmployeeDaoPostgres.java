package foundation_project.dao;

import foundation_project.models.Employee;
import foundation_project.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoPostgres implements EmployeeDAO {
    @Override
    public Employee getByUsername(String username) {
        //first create an object of employee
        Employee logEm = new Employee();

        try (Connection conn = ConnectionUtil.getConnection()) {
            //Write a sql statement, we are going to need a prepared statement to protect against sql injection

            String sql = "Select * from employee where em_username = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            //Set the individual values for the question marks
            stmt.setString(1, username);
            ResultSet rs;

            //This is how to only expect one result
            if ((rs = stmt.executeQuery()) != null) {
                //if the result set is not blank then we found our value

                //Move the cursor forward
                rs.next();

                //Now we can pull the information out and store it in the teacher object
                int em_id = rs.getInt("em_id");
                String dataUser = rs.getString("em_username");
                String dataPassword = rs.getString("em_password");

                //Now that I have my fields we create a teacher object

                logEm = new Employee(em_id, dataUser, dataPassword);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logEm;
    }


    @Override
    public Employee registerEmployee(String username, String password) {
        //Need to register an employee now that you can log in

        Employee em = new Employee();

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "Insert into employee (\"em_username\", \"em_password\") Values (?,?) returning *";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null) {
                //if you've gotten this far then that means the query went through

                rs.next();
                String gUsername = rs.getString("em_username");
                String gPassword = rs.getString("em_password");
                int id = rs.getInt("em_id");
                em = new Employee(id, gUsername, gPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't register user");

        }
        return em;
    }
}