package com.webApp.HRdatabase.service;

import com.webApp.HRdatabase.data.Employee;
import com.webApp.HRdatabase.repository.EmployeeRepository;
import com.webApp.HRdatabase.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ValidationService validationService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           ValidationService validationService) {
        this.employeeRepository = employeeRepository;
        this.validationService = validationService;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee getEmployee(Long id) {
        validationService.validateId(id);
        return employeeRepository.getEmployee(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("No such employee."));
    }

    public Employee addEmployee(Employee employee) {
        validationService.validateEmployee(employee);
        if (employeeRepository.findByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Employee with such email already exists.");
        }
        return employeeRepository.addEmployee(employee);
    }

    public Employee editEmployee(Employee employee, Long id) {
        getEmployee(id);
        validationService.validateEmployee(employee);
        return employeeRepository.editEmployee(employee, id);
    }
}
