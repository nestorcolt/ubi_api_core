package com.ubisoft.hotel.profileapi.controllers;

import com.ubisoft.hotel.profileapi.repository.ProfileRepository;
import com.ubisoft.hotel.profileapi.vo.Profile;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProfileControllerTest {
    /*
    Happy path test suite for the API profile controller
     */

    @Mock
    private ProfileController profileControllerMock;

    @Mock
    private ProfileRepository profileRepositoryMock;

    @Test
    @DisplayName("UNIT - Get all profiles >>")
    void getAllProfiles() {
        // Arrange
        List<Profile> profiles = profileRepositoryMock.findAll();

        // Act
        lenient().when(profileControllerMock.getAllProfiles()).thenReturn(profiles);

        // Assert
        Assertions.assertEquals(profileControllerMock.getAllProfiles(), profiles);
    }

    @Test
    @DisplayName("UNIT - Get profile by id >>")
    void assertGetProfileById() {
        // Arrange
        long mockId = 1;
        Profile profileMock = Mockito.mock(Profile.class);
        profileMock.setId(mockId);

        Response response =  Optional.of(profileMock)
                .map(result -> Response.status(Response.Status.OK).entity(profileMock).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());

        // Act
        lenient().when(profileControllerMock.getProfile(mockId)).thenReturn(response);

        // Assert
        Assertions.assertEquals(profileControllerMock.getProfile(mockId), response);
    }

}