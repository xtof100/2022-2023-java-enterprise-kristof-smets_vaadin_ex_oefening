package be.ucll.vaadin.layout;

import be.ucll.vaadin.LoginTracker;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value="Login", layout = ParentView.class)
public class LoginView extends VerticalLayout {
    public LoginView() {

        //layout
        VerticalLayout wrapper = new VerticalLayout();
            wrapper.getStyle().set("border-style", "solid");
            wrapper.getStyle().set("border-width", "2px");
            wrapper.getStyle().set("border-color", "Gainsboro");
            wrapper.getStyle().set("border-radius", "5px");
            wrapper.getStyle().set("width", "fit-content");
            wrapper.getStyle().set("margin", "auto");
            wrapper.getStyle().set("background-color", "white");
            wrapper.setAlignItems(Alignment.CENTER);

        //Titlebox
        HorizontalLayout titleBox = new HorizontalLayout();
            titleBox.getStyle().set("background-color", "#c1b2ff");
            titleBox.getStyle().set("border-radius", "5px");
            titleBox.setSizeFull();

            //icon
            Icon iconLock = new Icon(VaadinIcon.LOCK);
                iconLock.getStyle().set("color", "white");

            //title
            H1 title = new H1("Login");
                title.getStyle().set("color", "white");
                title.getStyle().set("margin", "auto");

        titleBox.add(title);

        //add to wrapper
        wrapper.add(titleBox);


        //login elements
        TextField txtUser = new TextField("Username");
        wrapper.add(txtUser);

        PasswordField txtPassword = new PasswordField("Password");
        wrapper.add(txtPassword);

        Button btnLogin = new Button("LogIn");
        wrapper.add(btnLogin);

        //add to screen
        add(wrapper);


        //login controle if true go to SearchView
        btnLogin.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                String userName = txtUser.getValue();
                String passWord = txtPassword.getValue();

                if(userName.equals("test") && passWord.equals("test"))
                {
                    LoginTracker.logInCheck = true;
                    UI.getCurrent().navigate("Search");
                }
                else
                {
                    add("Wrong password");
                }
            }
        });

    }
}
