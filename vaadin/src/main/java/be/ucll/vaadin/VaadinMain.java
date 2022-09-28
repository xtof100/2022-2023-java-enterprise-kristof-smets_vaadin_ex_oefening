package be.ucll.vaadin;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.spring.SpringBootAutoConfiguration;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Theme(themeClass = Lumo.class)
@SpringBootApplication
@Import(SpringBootAutoConfiguration.class)
public class VaadinMain extends SpringBootServletInitializer implements AppShellConfigurator {

}