package be.ucll.vaadin.layout;

import be.ucll.vaadin.LoginTracker;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
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

        //main settings
        getStyle().set("background-color","WhiteSmoke");
        setSizeFull();

        //Header
        HorizontalLayout header = new HorizontalLayout();
        header.getStyle().set("width","100%");
        header.getStyle().set("margin","auto");
        header.getStyle().set("padding","10px");

                //image Logo
                Image logo = new Image("images/logo.png", "Xtofs order manager logo");
                    logo.getStyle().set("width","20%");
                    logo.getStyle().set("min-width","180px");
                    logo.getStyle().set("max-width","300px");
                    logo.getStyle().set("height","auto");
                    logo.getStyle().set("margin-left","-3px");


                header.add(logo);

                //logout button
                Button btnLogout = new Button("Log out");
                    btnLogout.getStyle().set("margin-left", "auto");
                    btnLogout.getStyle().set("margin-right", "20px");
                    btnLogout.getStyle().set("margin-top", "20px");
                    btnLogout.getStyle().set("background-color", "#c1b2ff");
                    btnLogout.getStyle().set("color", "white");

                btnLogout.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
                    @Override
                    public void onComponentEvent(ClickEvent<Button> event) {
                        LoginTracker.logInCheck = false;
                        UI.getCurrent().navigate("Login");

                    }
                });

                header.add(btnLogout);

        //add header to screen
        add(header);

        //------------------
        //add Body to screen
        add(mainContainer);


        //------------------
        //Footer

            //horizontal layout
            HorizontalLayout footer = new HorizontalLayout();
            footer.setSizeFull();
            footer.setAlignItems(Alignment.CENTER);

            //disclaimer
            Paragraph disclaimer = new Paragraph("All rights reserved by ©Xtofdesign.com");
                disclaimer.getStyle().set("text-align", "center");
                disclaimer.getStyle().set("font-style", "italic");
                //disclaimer.getStyle().set("font-weight", "bold");
                disclaimer.getStyle().set("margin", "auto");
            //add disclaimer to footer
            footer.add(disclaimer);

        //add footer to screen
        add(footer);

    }

    @Override
    public void showRouterLayoutContent(HasElement content){
        mainContainer.getElement().appendChild(content.getElement());
    }




}
