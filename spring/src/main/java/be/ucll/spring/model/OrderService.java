package be.ucll.spring.model;

import java.util.List;

public interface OrderService {

    List<String> getMatchingProductNamesWithinAllOrders(String productName);

    List<Order> searchOrders(OrderSearchCriteria orderSearchCriteria);

    List<Order> getAllOrdersForCustomer();

    Long createOrder(Order order);

    Order getOrder(Long orderId);

}
