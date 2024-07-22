package eminozkan.transaction.service.user;

import eminozkan.transaction.model.Gender;
import eminozkan.transaction.model.User;
import eminozkan.transaction.repository.UserRepository;
import eminozkan.transaction.service.request.ChangePasswordServiceRequest;
import eminozkan.transaction.util.result.OperationFailureReason;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    UserServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(userRepository, passwordEncoder);
    }


    @Nested
    class GetUserTests {

        @Test
        @DisplayName("Get Non Exists User")
        void nonExistUser() {
            var userId = "userid";
            Mockito.doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(Mockito.eq("userid"));

            var user = service.getProfile("userid");

            assertNull(user);
        }

        @Test
        @DisplayName("Success")
        void getUser() {
            var user = new User()
                    .setUserId("userid")
                    .setUsername("username")
                    .setGender(Gender.MALE);

            Mockito.doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(Mockito.eq("userid"));

            var userFromService = service.getProfile("userid");

            assertNotNull(userFromService);
        }
    }

    @Nested
    class ChangePasswordTests{

        @Captor
        ArgumentCaptor<User> userCaptor;
        @Test
        @DisplayName("Wrong old password")
        void wrongPassword(){
            var user = new User().setPasswordHash("hashedPassword");

            Mockito.doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(Mockito.eq("userid"));

            var request = new ChangePasswordServiceRequest()
                    .setOldPassword("wrongPassword")
                    .setNewPassword("newPassword")
                    .setNewPasswordAgain("newPassword");

            Mockito.doReturn(false)
                    .when(passwordEncoder)
                    .matches(Mockito.eq("wrongPassword"),Mockito.eq("hashedPassword"));

            var result = service.changePassword("userid",request);
            assertFalse(result.isSuccess());
            assertEquals(OperationFailureReason.PRECONDITION_FAILED,result.getReason());
        }

        @Test
        @DisplayName("Not Matching New Passwords")
        void notMatchingNewPasswords(){
            var user = new User().setPasswordHash("hashedPassword");

            Mockito.doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(Mockito.eq("userid"));

            var request = new ChangePasswordServiceRequest()
                    .setOldPassword("hashedPassword")
                    .setNewPassword("newPassword")
                    .setNewPasswordAgain("newPassword2");

            Mockito.doReturn(true)
                    .when(passwordEncoder)
                    .matches(Mockito.eq("hashedPassword"),Mockito.eq("hashedPassword"));

            var result = service.changePassword("userid",request);
            assertFalse(result.isSuccess());
            assertEquals(OperationFailureReason.PRECONDITION_FAILED,result.getReason());
        }

        @Test
        @DisplayName("Success")
        void success(){
            var user = new User().setPasswordHash("hashedPassword");

            Mockito.doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(Mockito.eq("userid"));

            var request = new ChangePasswordServiceRequest()
                    .setOldPassword("hashedPassword")
                    .setNewPassword("newPassword")
                    .setNewPasswordAgain("newPassword");

            Mockito.doReturn(true)
                    .when(passwordEncoder)
                    .matches(Mockito.eq("hashedPassword"),Mockito.eq("hashedPassword"));


            Mockito.doReturn("hashedNewPassword")
                    .when(passwordEncoder)
                    .encode(Mockito.eq("newPassword"));

            var result = service.changePassword("userid",request);
            Mockito.verify(userRepository).save(userCaptor.capture());

            var userFromDb = userCaptor.getValue();

            assertTrue(result.isSuccess());
            assertEquals("hashedNewPassword",userFromDb.getPassword());
        }
    }


}