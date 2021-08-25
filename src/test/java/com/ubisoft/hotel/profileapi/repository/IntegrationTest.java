package com.ubisoft.hotel.profileapi.repository;

import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public class IntegrationTest {

    @ClassRule
    public static DockerComposeContainer container =
            new DockerComposeContainer(new File("docker-compose.yml"))
                    .withExposedService("crm_db", 3306)
                    .withExposedService("api-gradle", 8080)
                    .withLocalCompose(true);

    @BeforeAll
    public static void initContainers() {
        container.start();
    }

    @Test
    @DisplayName("ITest - Should validate connection on localhost")
    public void assert_connection_to_localhost() {

        String host = container.getServiceHost("crm_db", 3306);
        String host2 = container.getServiceHost("api-gradle", 8080);

        Assertions.assertEquals(host, "localhost");
        Assertions.assertEquals(host2, "localhost");
        Assertions.assertEquals(host2, "0");
    }

}




