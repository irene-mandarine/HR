package com.webApp.HRdatabase.controller;

import com.webApp.HRdatabase.data.Employee;
import com.webApp.HRdatabase.service.EmployeeService;
import com.webApp.HRdatabase.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "api/hr/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public @ResponseBody List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("{id}")
    public @ResponseBody Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping("/")
    public @ResponseBody Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("{id}")
    public @ResponseBody Employee editEmployee(@RequestBody Employee employee,
                                 @PathVariable Long id) {
        return employeeService.editEmployee(employee, id);
    }
}
