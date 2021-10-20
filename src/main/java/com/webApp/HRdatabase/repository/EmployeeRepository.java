package com.webApp.HRdatabase.repository;

import com.webApp.HRdatabase.data.Employee;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository extends BaseRepository {

    public List<Employee> getEmployees() {
        String query = "SELECT * FROM employee";
        List<Employee> list = null;
        try {
            list = getEmployeesList(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.close();
        return list;
    }

    public Optional<Employee> getEmployee(Long id) {
        String query = "SELECT * FROM employee WHERE id =" + id;
        Employee emp = null;
        try {
            emp = tempEmployee(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.close();
        return Optional.of(emp);
    }

    public Employee addEmployee(Employee employee) {
        String query = "INSERT INTO employee (firstName, lastName, department, email, phoneNumber, salary) VALUES ('"
                + employee.getFirstName() + "', '"
                + employee.getLastName() + "', '"
                + employee.getDepartment() + "', '"
                + employee.getEmail() + "', '"
                + employee.getPhoneNumber() + "', "
                + employee.getSalary() + ")";
        execute(query);
        String queryResponse = "SELECT * FROM employee WHERE email = '" + employee.getEmail() + "'";
        Employee emp = null;
        try {
            emp = tempEmployee(queryResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.close();
        return emp;
    }

    public Employee editEmployee(Employee employee, Long id) {
        String query = "UPDATE employee SET firstName = '"
                + employee.getFirstName() + "', lastName = '"
                + employee.getLastName() + "', department = '"
                + employee.getDepartment() + "', email = '"
                + employee.getEmail() + "', phoneNumber = '"
                + employee.getPhoneNumber() + "', salary = "
                + employee.getSalary() + " WHERE id ="
                + id;
        execute(query);
        employee.setId(id);
        databaseConnection.close();
        return employee;
    }

    public boolean findByEmail(String email) {
        String query = "SELECT * FROM employee WHERE email ='" + email + "'";
        boolean exists = false;
        try {
            exists = (executeQuery(query)).next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    private List<Employee> getEmployeesList(String query) throws SQLException {
        ResultSet rs = executeQuery(query);
        List<Employee> list = new ArrayList<>();
        while (rs.next()) {
            Long id = rs.getLong(1);
            String name = rs.getString(2);
            String surname = rs.getString(3);
            String d = rs.getString(4);
            String email = rs.getString(5);
            String phone = rs.getString(6);
            double salary = rs.getDouble(7);
            Employee e = new Employee(id, name, surname, d, email, phone, salary);
            list.add(e);
        }
        return list;
    }

    private Employee tempEmployee(String query) throws SQLException {
        ResultSet rs = executeQuery(query);
        Employee emp = new Employee();
        while (rs.next()) {
            emp.setId(rs.getLong(1));
            emp.setFirstName(rs.getString(2));
            emp.setLastName(rs.getString(3));
            emp.setDepartment(rs.getString(4));
            emp.setEmail(rs.getString(5));
            emp.setPhoneNumber(rs.getString(6));
            emp.setSalary(rs.getDouble(7));
        }
        return emp;
    }
}
