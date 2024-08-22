package api.specifications;

import api.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeSpecifications {

    public static Specification<Employee> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName == null ? null : criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
    }

    public static Specification<Employee> hasDepartment(String department) {
        return (root, query, criteriaBuilder) ->
                department == null ? null : criteriaBuilder.equal(root.get("department"), department);
    }

    public static Specification<Employee> hasMinSalary(BigDecimal minSalary) {
        return (root, query, criteriaBuilder) ->
                minSalary == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("salary"), minSalary);
    }

    public static Specification<Employee> hasHireDateAfter(LocalDate hiredAfter) {
        return (root, query, criteriaBuilder) ->
                hiredAfter == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("hireDate"), hiredAfter);
    }

    public static Specification<Employee> hasHireDateBefore(LocalDate hiredBefore) {
        return (root, query, criteriaBuilder) ->
                hiredBefore == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("hireDate"), hiredBefore);
    }

    public static Specification<Employee> hasHireDateBetween(LocalDate hiredAfter, LocalDate hiredBefore) {
        return (root, query, criteriaBuilder) -> {
            if (hiredAfter != null && hiredBefore != null) {
                return criteriaBuilder.between(root.get("hireDate"), hiredAfter, hiredBefore);
            } else if (hiredAfter != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("hireDate"), hiredAfter);
            } else if (hiredBefore != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("hireDate"), hiredBefore);
            } else {
                return null;
            }
        };
    }

    public static Specification<Employee> isActive(Boolean active) {
        return (root, query, criteriaBuilder) ->
                active == null ? null : criteriaBuilder.equal(root.get("active"), active);
    }
}

