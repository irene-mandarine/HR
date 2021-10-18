package com.webApp.HRdatabase.service;

import com.webApp.HRdatabase.data.Department;
import com.webApp.HRdatabase.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow((() ->
                        new IllegalArgumentException("No such department.")));
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department editDepartment(Department department, Long id) {
        getDepartment(id);
        department.setDepartmentId(id);
        return departmentRepository.save(department);
    }
}
