package rw.ac.app.employeeservice.service;

import rw.ac.app.employeeservice.model.Employee;

import java.util.List;

public interface EmployeeService {
        List<Employee> getAllEmployees()throws Exception;
        Employee getEmployeeById(Long id)throws Exception;
        Employee createEmployee(Employee employee)throws Exception;
        Employee updateEmployee(long id, Employee employee)throws Exception;
        void deleteEmployee(Long id)throws Exception;
    }

