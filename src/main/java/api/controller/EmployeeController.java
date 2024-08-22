package api.controller;

import api.entity.Employee;
import api.repository.EmployeeRepository;
import api.specifications.EmployeeSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> searchEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) BigDecimal minSalary,
            @RequestParam(required = false) BigDecimal maxSalary,
            @RequestParam(required = false) LocalDate hiredAfter,
            @RequestParam(required = false) LocalDate hiredBefore,
            @RequestParam(required = false) Boolean active) {

        Specification<Employee> spec = Specification
                .where(EmployeeSpecifications.hasFirstName(firstName))
                .and(EmployeeSpecifications.hasDepartment(department))
                .and(EmployeeSpecifications.hasHireDateBetween(hiredAfter, hiredBefore))
                .and(EmployeeSpecifications.isActive(active));

        return employeeRepository.findAll(spec);
    }
}
