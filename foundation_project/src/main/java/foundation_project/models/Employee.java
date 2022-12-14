package foundation_project.models;

import java.util.Objects;

public class Employee {

        private int em_id;

        private String username;

        private String password;

        private String isManager;

    public Employee(int em_id, String username, String password, String isManager) {
        this.em_id = em_id;
        this.username = username;
        this.password = password;
        this.isManager = isManager;
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee(int em_id, String username, String password) {
        this.em_id = em_id;
        this.username = username;
        this.password = password;
    }

    public Employee(int em_id) {
        this.em_id = em_id;
    }

    public Employee() {
    }

    public int getEm_id() {
        return em_id;
    }

    public void setEm_id(int em_id) {
        this.em_id = em_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getisManager() {
        return isManager;
    }

    public void setisManager(String is_Manager) {
        this.isManager = isManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return em_id == employee.em_id && username.equals(employee.username) && password.equals(employee.password) && isManager.equals(employee.isManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(em_id, username, password, isManager);
    }
}


