package be.ucll.spring.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl implements OrderService, Serializable {

    private static final String FIXED_CUSTOMER = "john";
    private static final long serialVersionUID = 1L;
    private static AtomicLong idGenerator = new AtomicLong(0);

    private final Map<String, List<Order>> map = new HashMap<>();

    public OrderServiceImpl() {
        createTestData();
    }

    @Override
    public List<String> getMatchingProductNamesWithinAllOrders(String productName) {
        if (StringUtils.isEmpty(productName)) {
            return new ArrayList<String>();
        }

        List<String> productNames = new ArrayList<>();
        List<Order> orders = map.get(FIXED_CUSTOMER);

        for (Order order : orders) {
            for (Product product : order.getProducts()) {
                if (product.getProductName().toLowerCase().startsWith(productName.toLowerCase())) {
                    productNames.add(product.getProductName());
                }
            }
        }
        return productNames;
    }

    @Override
    public List<Order> searchOrders(OrderSearchCriteria orderSearchCriteria) {
        List<Order> matchedOrders = new ArrayList<Order>();
        List<Order> orders = map.get(FIXED_CUSTOMER);

        for (Order order : orders) {
            boolean shouldAdd = false;

            if (orderSearchCriteria.hasPriceRange()) {
                if (orderSearchCriteria.isWithinRange(order.getTotalOrderPrice())) {
                    shouldAdd = true;
                }
            }

            if (!shouldAdd && StringUtils.isNotBlank(orderSearchCriteria.getProductName())) {
                for (Product product : order.getProducts()) {
                    if (product.getProductName().toLowerCase().startsWith(orderSearchCriteria.getProductName().toLowerCase())) {
                        shouldAdd = true;
                        break;
                    }
                }
            }

            // TODO add code for filtering orders based on the criteria
            // 'numberOfProducts' and 'delivered'

            if (shouldAdd) {
                matchedOrders.add(order);
            }
        }

        return matchedOrders;
    }

    @Override
    public Long createOrder(Order order) {
        order.setId(idGenerator.getAndIncrement());
        order.getProducts().forEach(product -> product.setId(idGenerator.getAndIncrement()));
        map.get(FIXED_CUSTOMER).add(order);
        return order.getId();
    }

    @Override
    public Order getOrder(Long orderId) {
        Optional<Order> orderOptional = map.get(FIXED_CUSTOMER).stream().filter(order -> order.getId().equals(orderId)).findFirst();
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        }
        throw new IllegalArgumentException("Order with id " + orderId + " not found");
    }

    @Override
    public List<Order> getAllOrdersForCustomer() {
        return map.get(FIXED_CUSTOMER);
    }

    @SuppressWarnings("boxing")
    private void createTestData() {
        List<Order> orders = new ArrayList<Order>();

        // Order1
        Product lcdTv = new Product(idGenerator.getAndIncrement(), "P1", "Samsung 60\" LCD", "Samsung UN60F6300", new BigDecimal("1200.33"));
        Product smartphone = new Product(idGenerator.getAndIncrement(), "P2", "Samsung galaxy S4", "Samsung android smartphone", new BigDecimal("500.50"));
        Product tabled = new Product(idGenerator.getAndIncrement(), "P3", "HP Elitepad", "HP Elitepad 900", new BigDecimal("999.99"));
        Order order = new Order(idGenerator.getAndIncrement(), "Order1", FIXED_CUSTOMER, true, 3, lcdTv, smartphone, tabled);
        orders.add(order);

        // Order2
        Product elitebook = new Product(idGenerator.getAndIncrement(), "P4", "HP Elitebook", "HP Elitebook 8570w LY556EA Azerty", new BigDecimal("2350.45"));
        order = new Order(idGenerator.getAndIncrement(), "Order2", FIXED_CUSTOMER, false, 1, elitebook);
        orders.add(order);
        map.put(FIXED_CUSTOMER, orders);
    }
}
