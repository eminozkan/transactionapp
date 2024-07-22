package eminozkan.transaction.service.auth;

import eminozkan.transaction.model.User;
import eminozkan.transaction.repository.UserRepository;
import eminozkan.transaction.service.jwt.JwtService;
import eminozkan.transaction.service.request.UserAuthServiceRequest;
import eminozkan.transaction.util.result.OperationFailureReason;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {
    @Mock
    UserRepository userRepository;

    @Mock
    JwtService jwtService;

    @Mock
    PasswordEncoder encoder;

    AuthenticationServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new AuthenticationServiceImpl(userRepository, jwtService, encoder);
    }


    @Test
    @DisplayName("Invalid credentials: Wrong username")
    void invalidCredentials() {
        Mockito.doReturn(Optional.empty())
                .when(userRepository)
                .findByUsername(Mockito.eq("eminozkan"));

        var request = new UserAuthServiceRequest()
                .setUsername("eminozkan")
                .setPassword("parola");

        var result = service.authenticate(request);
        assertFalse(result.isSuccess());
        assertEquals(OperationFailureReason.UNAUTHORIZED, result.getReason());
    }

    @Test
    @DisplayName("Invalid credentials: Wrong Password")
    void wrongPassword() {
        var user = new User()
                .setUserId("id")
                .setUsername("eminozkan")
                .setPasswordHash("hashedPassword");

        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findByUsername(Mockito.eq("eminozkan"));

        Mockito.doReturn(false)
                .when(encoder)
                .matches(Mockito.eq("wrongpassword"),Mockito.eq(user.getPassword()));

        var request = new UserAuthServiceRequest()
                .setUsername("eminozkan")
                .setPassword("wrongpassword");

        var result = service.authenticate(request);
        assertFalse(result.isSuccess());
        assertEquals(OperationFailureReason.UNAUTHORIZED, result.getReason());
    }

    @Test
    @DisplayName("Success")
    void success(){
        var user = new User()
                .setUserId("id")
                .setUsername("eminozkan")
                .setPasswordHash("hashedPassword");

        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findByUsername(Mockito.eq("eminozkan"));

        Mockito.doReturn(true)
                .when(encoder)
                .matches(Mockito.eq("password"),Mockito.eq(user.getPassword()));

        var request = new UserAuthServiceRequest()
                .setUsername("eminozkan")
                .setPassword("password");

        var result = service.authenticate(request);

        assertTrue(result.isSuccess());
        Mockito.verify(jwtService).generateToken(Mockito.any());
        assertThat(result.getToken()).isNotNull();
    }
}