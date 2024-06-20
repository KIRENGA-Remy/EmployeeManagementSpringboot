package rw.ac.app.employeeservice.serviceimpl;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import rw.ac.app.employeeservice.model.Employee;
import rw.ac.app.employeeservice.repositories.EmployeeRepository;
import rw.ac.app.employeeservice.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() throws Exception {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Employee getEmployeeById(Long id) throws Exception {
        try {
            return employeeRepository.findById(id).orElseThrow(() -> new Exception(String.format("Employee %d not found!", id)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Employee createEmployee(Employee employee) throws Exception {
        try {
            if (employee.getFirstName() == null || employee.getLastName() == null || employee.getInstitution() == null || employee.getEmPosition() == null) {
                throw new BadRequestException("All employee details are required!");
            } else {
                return employeeRepository.save(employee);
            }
        } catch (BadRequestException e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) throws Exception {
        try {
            Employee e = employeeRepository.findById(id).orElseThrow(() -> new Exception(String.format("Employee %d not found!", id)));
            String fname = employee.getFirstName() == null ? e.getFirstName() : employee.getFirstName();
            String lname = employee.getLastName() == null ? e.getLastName() : employee.getLastName();
            String emPosition = employee.getEmPosition() == null ? e.getEmPosition() : employee.getEmPosition();
            String institution = employee.getInstitution() == null ? e.getInstitution() : employee.getInstitution();
            e.setLastName(lname);
            e.setFirstName(fname);
            e.setEmPosition(emPosition);
            e.setInstitution(institution);
            return employeeRepository.save(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(Long id) throws Exception {
        try {
            Employee e = employeeRepository.findById(id).orElseThrow(() -> new Exception(String.format("Employee %d not found!", id)));
            employeeRepository.delete(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
