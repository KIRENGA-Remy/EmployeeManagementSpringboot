package rw.ac.app.employeeservice.controllers;



import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.app.employeeservice.handlers.ResponseHandler;
import rw.ac.app.employeeservice.model.Employee;
import rw.ac.app.employeeservice.responses.ApiResponse;
import rw.ac.app.employeeservice.serviceimpl.EmployeeServiceImpl;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    EmployeeController(EmployeeServiceImpl employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees()throws Exception {
        try{
            List<Employee> es = employeeService.getAllEmployees();
            return ResponseHandler.success(es);
        }catch(Exception e){
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable("id") long id)throws Exception{
        try{
            Employee e = employeeService.getEmployeeById(id);
            return ResponseHandler.success(e);
        }catch (Exception e){
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody Employee employee)throws Exception{
        try{
            Employee e = employeeService.createEmployee(employee);
            return ResponseHandler.success(e);
        }catch (Exception e){
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee)throws Exception{
        try{
            Employee e = employeeService.updateEmployee(id, employee);
            return ResponseHandler.success(e);
        }catch (Exception e){
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable("id") Long id)throws Exception{
        try{
            employeeService.deleteEmployee(id);
            return ResponseHandler.success(String.format("Employee %d deleted successfully...", id));
        }catch(Exception e){
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
