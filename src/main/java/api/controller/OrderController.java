package api.controller;

import api.entity.Order;
import api.repository.OrderRepository;
import api.specifications.OrderSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> searchOrders(
            @RequestParam(required = false) String orderNumber,
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

        Specification<Order> spec = Specification
                .where(OrderSpecifications.hasOrderNumber(orderNumber))
                .and(OrderSpecifications.hasCustomerName(customerName))
                .and(OrderSpecifications.hasStatus(status))
                .and(OrderSpecifications.hasAmountBetween(minAmount, maxAmount))
                .and(OrderSpecifications.hasOrderDateBetween(startDate, endDate));

        return orderRepository.findAll(spec);
    }
}
