package be.ucll.vaadin.layout;

import be.ucll.vaadin.LoginTracker;
import be.ucll.vaadin.model.*;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.router.Route;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Route(value="Search", layout = ParentView.class)
public class SearchView extends VerticalLayout {
    public SearchView(){

        //check if user is logged in
        if(LoginTracker.logInCheck)
        {
            //Object declaration
            OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria();
            OrderService orderService = new OrderServiceImpl();

            //binder
            Binder<OrderSearchCriteria> binder = new Binder<>(OrderSearchCriteria.class);

            //Horizontal wrapper
            FormLayout wrapper = new FormLayout();


            //vertical column1--> and use one column
            FormLayout col1 = new FormLayout();
            col1.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

                //components
                BigDecimalField minimumAmount = new BigDecimalField("Minimum amount");
                    binder.forField(minimumAmount)
                            .bind(OrderSearchCriteria::getMinAmount,OrderSearchCriteria::setMinAmount);

                IntegerField numberOfProducts = new IntegerField("Number of Products");
                    binder.forField(numberOfProducts)
                            .bind(OrderSearchCriteria::getNumberOfProducts,OrderSearchCriteria::setNumberOfProducts);

                ComboBox<String> productName = new ComboBox<String>("ProductName");
                    List<Order> orders = orderService.getAllOrdersForCustomer();
                    List<String> productNames = new ArrayList<>();
                    for (Order order : orders) {
                        for (Product product : order.getProducts()) {
                            productNames.add(product.getProductName());
                        }
                    }
                    productName.setItems(new CallbackDataProvider.FetchCallback() {
                        @Override
                        public Stream fetch(Query query) {
                            query.getFilter();
                            query.getLimit();
                            query.getOffset();
                            query.getPage();
                            if (query.getFilter().isPresent() && query.getFilter().get().toString().length() >= 1) {
                                return productNames.stream().filter(pNames -> pNames.toLowerCase().contains(query.getFilter().get().toString().toLowerCase())).skip(query.getOffset()).limit(query.getLimit());
                            }
                            return Stream.empty();
                        }
                    });
                    binder.forField(productName)
                            .bind(OrderSearchCriteria::getProductName,OrderSearchCriteria::setProductName);

            //add to col1
                col1.add(minimumAmount);
                col1.add(numberOfProducts);
                col1.add(productName);

            //vertical column2 --> and use one column
            FormLayout col2 = new FormLayout();
            col2.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

                //components
                BigDecimalField maximumAmount = new BigDecimalField("Maximum amount");
                    binder.forField(maximumAmount)
                            .bind(OrderSearchCriteria::getMaxAmount,OrderSearchCriteria::setMaxAmount);

                Checkbox Delivered = new Checkbox("Delivered");
                    binder.forField(Delivered)
                            .bind(OrderSearchCriteria::getDelivered,OrderSearchCriteria::setDelivered);

                /*TextField emailAddress = new TextField("Email Address");
                    binder.forField(emailAddress)
                            .withValidator(new EmailValidator(
                                    "This doesn't look like a valid email address"))
                            .bind(OrderSearchCriteria::getEmail,OrderSearchCriteria::setEmail);*/

                //add to col2
                col2.add(maximumAmount);

                col2.add(Delivered);
                //col2.add(emailAddress);

                //clear and search buttons
                HorizontalLayout hlButtons = new HorizontalLayout();
                    //create result list
                                final List<Order>[] results = new List[]{new ArrayList<Order>()};

                    //button clear
                    Button btnClear = new Button("Clear");
                    btnClear.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
                       @Override
                       public void onComponentEvent(ClickEvent<Button> event) {

                           binder.readBean(orderSearchCriteria);
                           results[0] = null;
                       }
                    });


                    //button Search
                    Button btnSearch  = new Button("Search");
                    btnSearch.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
                        @Override
                        public void onComponentEvent(ClickEvent<Button> event) {
                            try {
                                binder.writeBean(orderSearchCriteria);
                            } catch (ValidationException e) {
                                e.printStackTrace();
                            }
                            results[0] = orderService.searchOrders(orderSearchCriteria);

                            //show grid only after search with valid data
                            if(!results[0].isEmpty())
                            {
                                Grid<Order> grid = new Grid<>(Order.class, false);

                                grid.setItems(results[0]);

                                grid.addColumn(Order::getOrderId).setHeader("OrderId");
                                grid.addColumn(Order::getCustomerId).setHeader("CustomerId");
                                grid.addColumn(Order::getProducts).setHeader("#Producten");
                                grid.addColumn(Order::isDelivered).setHeader("Delivered?");
                                grid.addColumn(Order::getDeliveryDays).setHeader("Deliverydays");
                                grid.addColumn(Order::getTotalOrderPrice).setHeader("Total price");
                                grid.addComponentColumn(Order -> {
                                    Button btnDetail = new Button("Detail");
                                    btnDetail.addClickListener(e -> {
                                        //todo detailsscreen
                                    });
                                    return btnDetail;
                                }).setWidth("150px").setFlexGrow(0);

                                //add to screen
                                add(grid);
                            }
                        }
                    });


                //add to horizontal view
                hlButtons.add(btnClear);
                hlButtons.add(btnSearch);

                //add to col2
                col2.add(hlButtons);



            //add to wrapper
            wrapper.add(col1);
            wrapper.add(col2);

            //add to screen
            add(wrapper);


        }
        else
        {
            UI.getCurrent().navigate("Login");
        }

    }


   /* //methods
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }*/

}
