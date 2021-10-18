package com.webApp.HRdatabase.controller;

import com.webApp.HRdatabase.data.Department;
import com.webApp.HRdatabase.service.DepartmentService;
import com.webApp.HRdatabase.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/hr/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final ValidationService validationService;

    @Autowired
    public DepartmentController(DepartmentService departmentService,
                                ValidationService validationService) {
        this.departmentService = departmentService;
        this.validationService = validationService;
    }

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("{id}")
    public Department getDepartment(@PathVariable Long id) {
        validationService.validateId(id);
        return departmentService.getDepartment(id);
    }

    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        validationService.validateDepartment(department);
        return departmentService.addDepartment(department);
    }

    @PutMapping("{id}")
    public Department editDepartment(@RequestBody Department department,
                                     @PathVariable Long id) {
        validationService.validateId(id);
        validationService.validateDepartment(department);
        return departmentService.editDepartment(department, id);
    }

}
