package api.specifications;

import api.entity.Order;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderSpecifications {

    public static Specification<Order> hasOrderNumber(String orderNumber) {
        return (root, query, criteriaBuilder) ->
                orderNumber == null ? null : criteriaBuilder.equal(root.get("orderNumber"), orderNumber);
    }

    public static Specification<Order> hasCustomerName(String customerName) {
        return (root, query, criteriaBuilder) ->
                customerName == null ? null : criteriaBuilder.like(root.get("customerName"), "%" + customerName + "%");
    }

    public static Specification<Order> hasStatus(String status) {
        return (root, query, criteriaBuilder) ->
                status == null ? null : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Order> hasMinAmount(BigDecimal minAmount) {
        return (root, query, criteriaBuilder) ->
                minAmount == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("totalAmount"), minAmount);
    }

    public static Specification<Order> hasMaxAmount(BigDecimal maxAmount) {
        return (root, query, criteriaBuilder) ->
                maxAmount == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("totalAmount"), maxAmount);
    }

    public static Specification<Order> hasAmountBetween(BigDecimal minAmount, BigDecimal maxAmount) {
        return (root, query, criteriaBuilder) -> {
            if (minAmount != null && maxAmount != null) {
                return criteriaBuilder.between(root.get("totalAmount"), minAmount, maxAmount);
            } else if (minAmount != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("totalAmount"), minAmount);
            } else if (maxAmount != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("totalAmount"), maxAmount);
            } else {
                return null;
            }
        };
    }

    public static Specification<Order> hasOrderDateAfter(LocalDate startDate) {
        return (root, query, criteriaBuilder) ->
                startDate == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("orderDate"), startDate);
    }

    public static Specification<Order> hasOrderDateBefore(LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                endDate == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("orderDate"), endDate);
    }

    public static Specification<Order> hasOrderDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate != null && endDate != null) {
                return criteriaBuilder.between(root.get("orderDate"), startDate, endDate);
            } else if (startDate != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("orderDate"), startDate);
            } else if (endDate != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("orderDate"), endDate);
            } else {
                return null;
            }
        };
    }
}
