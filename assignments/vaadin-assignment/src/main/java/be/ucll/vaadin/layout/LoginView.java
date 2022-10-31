package be.ucll.vaadin.layout;

import be.ucll.vaadin.LoginTracker;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value="Login", layout = ParentView.class)
public class LoginView extends VerticalLayout {
    public LoginView() {

        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setAlignItems(Alignment.CENTER);

        //login elements
        TextField txtUser = new TextField("Username");
        wrapper.add(txtUser);

        TextField txtPassword = new TextField("Password");
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
