package be.ucll.vaadin.layout;

import be.ucll.vaadin.LoginTracker;
import be.ucll.vaadin.model.*;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Route(value="Search", layout = ParentView.class)
public class SearchView extends VerticalLayout {
    public SearchView(){

        //check if user is logged in
        if(LoginTracker.logInCheck)
        {
            //maincontainer
            VerticalLayout mainContainer = new VerticalLayout();
            mainContainer.getStyle().set("width", "100%");
            mainContainer.getStyle().set("margin","auto");

            //Object declaration
            OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria();
            OrderService orderService = new OrderServiceImpl();

            //binder
            Binder<OrderSearchCriteria> binder = new Binder<>(OrderSearchCriteria.class);

            //Horizontal wrapper
            FormLayout wrapper = new FormLayout();
            wrapper.getStyle().set("width", "100%");
            wrapper.getStyle().set("margin", "auto");
            wrapper.getStyle().set("border", "solid");
            wrapper.getStyle().set("border-radius", "5px");
            wrapper.getStyle().set("border-color", "Gainsboro");
            wrapper.getStyle().set("padding", "10px");
            wrapper.getStyle().set("background-color", "white");
            wrapper.getStyle().set("box-sizing", "border-box"); //to contain padding by 100%


            //Title
            H2 title = new H2("Search Orders:");
                title.getStyle().set("color","Gainsboro");
                title.getStyle().set("margin-top","-20px");

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

                EmailField emailAddress = new EmailField("Email Address");
                    binder.forField(emailAddress)
                            .withNullRepresentation("") //to set as not required
                            .withValidator(new EmailValidator(
                                    "This doesn't look like a valid email address"))
                            .bind(OrderSearchCriteria::getEmail,OrderSearchCriteria::setEmail);

                //add to col2
                col2.add(maximumAmount);
                col2.add(Delivered);
                col2.add(emailAddress);

                    //Error message if nothing entered in searchbox
                    Paragraph mesg = new Paragraph("Please enter some search info!!");
                    mesg.getStyle().set("color","red");
                    mesg.setVisible(false);

                //clear and search buttons
                HorizontalLayout hlButtons = new HorizontalLayout();

                    //create result list
                                final List<Order>[] results = new List[]{new ArrayList<Order>()};

                    //create grid
                    Grid<Order> grid = new Grid<>(Order.class, false);

                    //create subtitle1
                    H3 title1 = new H3("Search results:");
                    title1.getStyle().set("color","Gainsboro");
                    title1.setVisible(false);

                    //button clear
                    Button btnClear = new Button("Clear");
                    btnClear.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
                       @Override
                       public void onComponentEvent(ClickEvent<Button> event) {

                           //clear form fields
                           binder.getFields().forEach(f -> f.clear());

                           //hide subtitle1
                           title1.setVisible(false);

                           //clear grid
                           grid.setItems();
                           grid.removeAllColumns();
                       }
                    });

                    //button Search
                    Button btnSearch  = new Button("Search");
                        btnSearch.getStyle().set("background-color", "#c1b2ff");
                        btnSearch.getStyle().set("color", "white");

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
                                //clear grid
                                grid.setItems();
                                grid.removeAllColumns();

                                //show subtitle1
                                title1.setVisible(true);

                                //hide error mesg
                                mesg.setVisible(false);

                                //fill grid
                                grid.setItems(results[0]);

                                grid.addColumn(Order::getOrderId).setHeader("OrderId");
                                grid.addColumn(Order::getCustomerId).setHeader("CustomerId");
                                grid.addColumn(Order -> Order.getProducts().size()).setHeader("#Products");
                                grid.addColumn(Order::isDelivered).setHeader("Delivered?");
                                grid.addColumn(Order::getDeliveryDays).setHeader("Delivery days");
                                grid.addColumn(Order::getTotalOrderPrice).setHeader("Total price");
                                grid.setAllRowsVisible(true); //only interesting for small results this eliminates the scrollbar
                                grid.addThemeVariants(GridVariant.LUMO_COMPACT);
                                grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

                                final Order[] order = {new Order()};

                                //Disabling the selection mode  but still getting item-click events.
                                grid.setSelectionMode(Grid.SelectionMode.NONE);

                                //get data
                                grid.addCellFocusListener(ev -> {
                                                order[0] = ev.getItem().get();
                                        });

                                //track if button clicked and go to detail page
                                grid.addComponentColumn(Order -> {
                                    Button btnDetail = new Button("Detail");
                                    btnDetail.addClickListener(e -> {
                                        ComponentUtil.setData(UI.getCurrent(), Order.class, order[0]);
                                        getUI().get().navigate("Details");
                                    });
                                    return btnDetail;}).setWidth("150px").setFlexGrow(0);

                                grid.getStyle().set("margin-top","5px");

                                //add to screen
                                wrapper.setColspan(grid, 2);
                                wrapper.add(grid);
                            }
                            else
                            {
                                //show error mesg
                                mesg.setVisible(true);
                                //hide subtitle1
                                title1.setVisible(false);
                            }

                        }
                    });

                //add to horizontal view
                hlButtons.add(btnClear);
                hlButtons.add(btnSearch);
                hlButtons.add(mesg);

                //add to col2
                col2.add(hlButtons);

            //add to wrapper
            wrapper.add(col1);
            wrapper.add(col2);
            wrapper.add(title1);

            //add to mainContainer
            mainContainer.add(title);
            mainContainer.add(wrapper);

            //add to screen
            add(mainContainer);

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
