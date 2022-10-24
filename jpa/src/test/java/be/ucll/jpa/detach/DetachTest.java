package be.ucll.jpa.detach;

import be.ucll.jpa.entities.detach.DetachChild;
import be.ucll.jpa.entities.detach.DetachParent;
import be.ucll.jpa.entities.detach.DetachService;
import be.ucll.spring.JpaConfig;
import be.ucll.spring.MyAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@Test
@ContextConfiguration(classes = {JpaConfig.class, MyAppConfig.class})
@ActiveProfiles("jpa")
public class DetachTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DetachService detachService;

    public void test() {
        DetachParent detachParent = new DetachParent();
        detachParent.setParentText("ParentText");
        DetachChild detachChild = new DetachChild();
        detachChild.setChildText("ChildText");
        detachParent.getChildren().add(detachChild);
        detachService.saveDetach(detachParent);

        detachParent = detachService.getDetach();
        detachParent.setParentText("The new ParentText");
        detachParent.getChildren().iterator().next().setChildText("The new ChildText");
        detachService.saveDetach(detachParent);

        detachParent = detachService.getDetach();
        assertEquals(detachParent.getParentText(), "The new ParentText");
        assertEquals(detachParent.getChildren().iterator().next().getChildText(), "The new ChildText");
    }
}
