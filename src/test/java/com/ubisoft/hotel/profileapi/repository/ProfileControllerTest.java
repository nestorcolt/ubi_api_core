package com.ubisoft.hotel.profileapi.repository;

import com.ubisoft.hotel.profileapi.controllers.ProfileController;
import com.ubisoft.hotel.profileapi.vo.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

import java.net.URISyntaxException;
import java.util.List;


@SpringBootApplication
public class ProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private Logger log;

    @Test
    public void testFindAll() throws URISyntaxException {
        // Arrange
        Profile employee1 = new Profile();
        long idMock1 = 1;
        employee1.setId(idMock1);

        Profile employee2 = new Profile();
        long idMock2 = 2;
        employee2.setId(idMock2);

//        profileController.createProfile(employee1);
//        profileController.createProfile(employee2);

//        List<Profile> profiles = profileRepository.findAll();
//        when(profileController.getAllProfiles()).thenReturn(profiles);
//
//        // Assert
//        Assertions.assertEquals(2, profiles.size());
    }
}
