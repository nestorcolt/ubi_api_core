package com.ubisoft.hotel.profileapi.repository;
import com.ubisoft.hotel.profileapi.vo.Profile;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ArquillianExtension.class)
public class ProfileRepositoryTest {

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClass(Profile.class)
                .addClass(ProfileRepository.class)
                .addAsResource("META-INF/persistence.xml");

        System.out.println(war.toString(true));
        return war;
    }

    @Test
    public void helloWorld() {
        System.out.println("This is a test");
        Assertions.assertEquals(0, 0);
    }

}