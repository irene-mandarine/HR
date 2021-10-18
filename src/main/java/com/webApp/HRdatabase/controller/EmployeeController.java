package com.webApp.HRdatabase.controller;

import com.webApp.HRdatabase.data.Employee;
import com.webApp.HRdatabase.service.EmployeeService;
import com.webApp.HRdatabase.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/hr/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ValidationService validationService;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              ValidationService validationService) {
        this.employeeService = employeeService;
        this.validationService = validationService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("{id}")
    public Employee getEmployee(@PathVariable Long id){
        validationService.validateId(id);
        return employeeService.getEmployee(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        validationService.validateEmployee(employee);
        return employeeService.addEmployee(employee);
    }

    @PutMapping("{id}")
    public Employee editEmployee(@RequestBody Employee employee,
                                 @PathVariable Long id){
        validationService.validateId(id);
        validationService.validateEmployee(employee);
        return employeeService.editEmployee(employee, id);
    }
}
