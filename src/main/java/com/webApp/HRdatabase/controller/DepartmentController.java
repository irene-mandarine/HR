package com.webApp.HRdatabase.controller;

import com.webApp.HRdatabase.data.Department;
import com.webApp.HRdatabase.service.DepartmentService;
import com.webApp.HRdatabase.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "api/hr/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public @ResponseBody List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("{id}")
    public @ResponseBody Department getDepartment(@PathVariable Long id) {
        return departmentService.getDepartment(id);
    }

    @PostMapping("/")
    public @ResponseBody Department addNewDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @PutMapping("{id}")
    public @ResponseBody Department editDepartment(@RequestBody Department department,
                                                   @PathVariable Long id) {
        return departmentService.editDepartment(department, id);
    }
}
