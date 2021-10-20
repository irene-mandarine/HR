package com.webApp.HRdatabase.repository;

import com.webApp.HRdatabase.data.Department;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository extends BaseRepository {

    public List<Department> getDepartments() {
        String query = "SELECT * FROM department";
        List<Department> list = null;
        try {
            list = getDepartmentsList(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.close();
        return list;
    }

    public Optional<Department> getDepartment(Long id) {
        String query = "SELECT * FROM department WHERE departmentId =" + id;
        Department dep = null;
        try {
            dep = tempDepartment(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.close();
        return Optional.of(dep);
    }

    public Department addDepartment(Department d) {
        String query = "INSERT INTO department (name, location) VALUES ('"
                + d.getName() + "', '"
                + d.getLocation() + "')";
        execute(query);
        String queryResponse = "SELECT * FROM department WHERE name = '" + d.getName() + "'";
        Department dep = null;
        try {
            dep = tempDepartment(queryResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.close();
        return dep;
    }

    public Department editDepartment(Department d, Long id) {
        String query = "UPDATE department SET name = '"
                + d.getName() + "', location = '"
                + d.getLocation() + "' WHERE departmentId ="
                + id;
        execute(query);
        d.setDepartmentId(id);
        databaseConnection.close();
        return d;
    }

    private List<Department> getDepartmentsList(String query) throws SQLException {
        ResultSet rs = executeQuery(query);
        List<Department> list = new ArrayList<>();
        while (rs.next()) {
            Long id = rs.getLong(1);
            String name = rs.getString(2);
            String loc = rs.getString(3);
            Department d = new Department(id, name, loc);
            list.add(d);
        }
        return list;
    }

    private Department tempDepartment(String query) throws SQLException {
        ResultSet rs = executeQuery(query);
        Department dep = new Department();
        while (rs.next()) {
            dep.setDepartmentId(rs.getLong(1));
            dep.setName(rs.getString(2));
            dep.setLocation(rs.getString(3));
        }
        return dep;
    }
}
