package be.ucll.vaadin.layout;

import be.ucll.vaadin.LoginTracker;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

public class ParentView extends VerticalLayout implements RouterLayout {
    //wrapper nest main container to center the view
    //main container --> this is the part that changes when you clik links

    FormLayout mainContainer  = new FormLayout();

    public ParentView()
    {
        //main container --> set only 1 column for main container and other position settings

        mainContainer.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));




        //Header
        HorizontalLayout header = new HorizontalLayout();
                //Title
                H1 heading = new H1("Xtofs Order Manager");
                header.add(heading);
                //image Logo

                //logout button
                Button btnLogout = new Button("Log out");
                btnLogout.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
                    @Override
                    public void onComponentEvent(ClickEvent<Button> event) {
                        LoginTracker.logInCheck = false;
                        UI.getCurrent().navigate("Login");

                    }
                });
                header.add(btnLogout);
        add(header);

        //------------------
        //Body
        add(mainContainer);


        //------------------
        //Footer
            //disclaimer
             add("All rights reserved by ©Xtofdesign.com");
    }

    @Override
    public void showRouterLayoutContent(HasElement content){
        mainContainer.getElement().appendChild(content.getElement());
    }




}
