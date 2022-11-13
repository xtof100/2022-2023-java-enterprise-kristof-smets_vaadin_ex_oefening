package be.ucll.vaadin.layout;

import be.ucll.vaadin.LoginTracker;
import be.ucll.vaadin.model.Order;
import be.ucll.vaadin.model.Product;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;

@Route(value="Details", layout = ParentView.class)
public class DetailsView extends VerticalLayout {

    public DetailsView() {

        //retrieve data
        Order order= ComponentUtil.getData(UI.getCurrent(), Order.class);

        //check if user is logged in
        if(LoginTracker.logInCheck) {

            //Title
            H2 title1 = new H2("Order Detail:");
            title1.getStyle().set("color","Gainsboro");
            title1.getStyle().set("margin-top","-20px");

            //Objects

            List<Product> products = order.getProducts();

            //create formlayout to keep it dynamic
            FormLayout orderInfoWrapper = new FormLayout();
                orderInfoWrapper.getStyle().set("border","solid");
                orderInfoWrapper.getStyle().set("border-color","Gainsboro");
                orderInfoWrapper.getStyle().set("border-radius","5px");
                orderInfoWrapper.getStyle().set("padding","10px");
                orderInfoWrapper.getStyle().set("width","50%");
                orderInfoWrapper.getStyle().set("background-color", "white");

            //col1
            FormLayout col1 = new FormLayout();
            col1.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

                //Components col1
                HorizontalLayout hl1 = new HorizontalLayout();
                Label orderIdTitle = new Label("OrderId: ");
                    orderIdTitle.getStyle().set("font-weight","bold");
                Label orderId = new Label(order.getOrderId());
                hl1.add(orderIdTitle,orderId);

                HorizontalLayout hl2 = new HorizontalLayout();
                Label labelProductsTitle = new Label("#Products: ");
                    labelProductsTitle.getStyle().set("font-weight","bold");
                Label labelProducts = new Label(String.valueOf(order.getProducts().size()));
                hl2.add(labelProductsTitle,labelProducts);

                HorizontalLayout hl3 = new HorizontalLayout();
                Label deliveryDaysTitle = new Label("DeliveryDays: ");
                    deliveryDaysTitle.getStyle().set("font-weight","bold");
                Label deliveryDays = new Label(String.valueOf(order.getDeliveryDays()));
                hl3.add(deliveryDaysTitle,deliveryDays);

                //add to colum1
                col1.add(hl1,hl2,hl3);

            FormLayout col2 = new FormLayout();
            col2.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

                //components col2
                HorizontalLayout hl4 = new HorizontalLayout();
                Label customerIdTitle = new Label("CustomerID: ");
                    customerIdTitle.getStyle().set("font-weight","bold");
                Label customerId = new Label(order.getCustomerId());
                hl4.add(customerIdTitle,customerId);

                HorizontalLayout hl5 = new HorizontalLayout();
                Label deliveredTitle = new Label("Delivered: ");
                    deliveredTitle.getStyle().set("font-weight","bold");
                Label delivered = new Label(String.valueOf(order.isDelivered()));
                    delivered.getStyle().set("color","blue");
                hl5.add(deliveredTitle,delivered);

                HorizontalLayout hl6 = new HorizontalLayout();
                Label totalPriceTitle = new Label("Total Price: ");
                    totalPriceTitle.getStyle().set("font-weight","bold");
                Label totalPrice = new Label(String.valueOf(order.getTotalOrderPrice()));
                    totalPrice.getStyle().set("font-weight","bold");
                    totalPrice.getStyle().set("color","red");
                hl6.add(totalPriceTitle,totalPrice);

                //add to colum1
                col2.add(hl4,hl5,hl6);

            //add to formlayout orderInfowrapper
            orderInfoWrapper.add(col1,col2);

            //Title
            H2 title2 = new H2("Product Detail:");
            title2.getStyle().set("color","Gainsboro");


            //grid layout
            VerticalLayout gridLayout = new VerticalLayout();
                gridLayout.getStyle().set("border","solid");
                gridLayout.getStyle().set("border-color","Gainsboro");
                gridLayout.getStyle().set("border-radius","5px");
                gridLayout.getStyle().set("padding","10px");
                //gridLayout.getStyle().set("width","50%");
                gridLayout.getStyle().set("background-color", "white");

            //productGrid
                //create grid
                Grid<Product> grid = new Grid<>(Product.class, false);

                //data for grid
                grid.setItems(products); //todo

                //set columns title + data
                grid.addColumn(Product::getProductId).setHeader("ProductId");
                grid.addColumn(Product::getProductName).setHeader("Name");
                grid.addColumn(Product::getProductDescription).setHeader("Description");
                grid.addColumn(Product::getProductPrice).setHeader("Price");
                grid.setAllRowsVisible(true); //only interesting for small results this eliminates the scrolbar
                grid.addThemeVariants(GridVariant.LUMO_COMPACT);
                grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

                //add grid to layout
                gridLayout.add(grid);

            //Btn back
            Button btnBack = new Button("Back");

                btnBack.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
                    @Override
                    public void onComponentEvent(ClickEvent<Button> event) {
                        getUI().get().navigate("Search");
                    }
                });

            //add to screen
            add(title1);
            add(orderInfoWrapper);
            add(title2);
            add(gridLayout);
            add(btnBack);

        }
    }
}
