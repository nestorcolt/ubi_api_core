package com.ubisoft.hotel.profileapi.repository;

import com.ubisoft.hotel.profileapi.JAXRSConfiguration;
import com.ubisoft.hotel.profileapi.controllers.ProfileController;
import com.ubisoft.hotel.profileapi.vo.Profile;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.net.URL;


@ExtendWith(ArquillianExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProfileControllerTest {

    @Inject
    ProfileController profileController;

    private final Client client = ClientBuilder.newClient();

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClass(ProfileController.class)
                .addClass(Logger.class)
                .addClass(ProfileRepository.class)
                .addClass(Profile.class)
                .addAsResource("META-INF/persistence.xml");

        System.out.println(war.toString(true));
        return war;
    }

    @Test
    public void testFindAll() {
        System.out.println("test executed");
        // Test your REST service
//        WebTarget target = client.target(deploymentUrl.toURI()).path("/profile");

    }
}

