package eminozkan.transaction.service.register;

import eminozkan.transaction.model.Gender;
import eminozkan.transaction.model.User;
import eminozkan.transaction.repository.UserRepository;
import eminozkan.transaction.service.register.RegistrationServiceImpl;
import eminozkan.transaction.service.request.UserRegistrationServiceRequest;
import eminozkan.transaction.util.result.OperationFailureReason;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder encoder;

    RegistrationServiceImpl service;

    @BeforeEach
    void setUp(){
        service = new RegistrationServiceImpl(userRepository,encoder);
    }

    @Nested
    class RegistrationTests{

        @Captor
        ArgumentCaptor<User> userCaptor;

        @Test
        @DisplayName("User has already registered")
        void alreadyRegistered(){
            var user = new User()
                    .setUserId("userid")
                    .setUsername("eminozkan")
                    .setFullName("Muhammet Emin Özkan")
                    .setIdNumber("25512962530")
                    .setPasswordHash("hashedPassword")
                    .setGender(Gender.MALE)
                    .setPhoneNumber("+905350624914")
                    .setBirthDate(LocalDate.of(2001,10,2));

            Mockito.doReturn(Optional.of(user))
                    .when(userRepository)
                    .findByUsername(Mockito.eq("eminozkan"));

            var request = new UserRegistrationServiceRequest()
                    .setUsername("eminozkan")
                    .setRawPassword("password");


            var result = service.register(request);

            assertFalse(result.isSuccess());
            assertEquals(OperationFailureReason.CONFLICT,result.getReason());
            Mockito.verifyNoMoreInteractions(userRepository);
        }

        @Test
        @DisplayName("Success")
        void success(){
            Mockito.doReturn(Optional.empty())
                    .when(userRepository)
                    .findByUsername(Mockito.eq("eminozkan"));

            var request = new UserRegistrationServiceRequest()
                    .setUsername("eminozkan")
                    .setRawPassword("password")
                    .setUsername("eminozkan")
                    .setFullName("Muhammet Emin Özkan")
                    .setIdNumber("25512962530")
                    .setGender(Gender.MALE)
                    .setPhoneNumber("+905350624914")
                    .setBirthDate(LocalDate.of(2001,10,2));

            var result = service.register(request);

            Mockito.verify(userRepository)
                    .save(userCaptor.capture());

            assertTrue(result.isSuccess());

            var capturedUser = userCaptor.getValue();
            assertEquals(request.getUsername(), capturedUser.getUsername());
            assertEquals(request.getGender(),capturedUser.getGender());
            assertEquals(request.getBirthDate(),capturedUser.getBirthDate());
            assertEquals(request.getPhoneNumber(),capturedUser.getPhoneNumber());
            assertEquals(request.getFullName(), capturedUser.getFullName());
            assertEquals(request.getIdNumber(), capturedUser.getIdNumber());
        }

    }

}