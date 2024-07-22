package eminozkan.transaction.service.user;

import eminozkan.transaction.model.User;
import eminozkan.transaction.repository.UserRepository;
import eminozkan.transaction.service.request.ChangePasswordServiceRequest;
import eminozkan.transaction.service.request.UserProfileUpdateServiceRequest;
import eminozkan.transaction.util.result.CrudResult;
import eminozkan.transaction.util.result.OperationFailureReason;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public CrudResult updateUserProfile(String userId, UserProfileUpdateServiceRequest request) {
        var userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()){
            log.error("Non Registered User logged in system, {}", userId);
            return CrudResult.failed(OperationFailureReason.INTERNAL_SERVER_ERROR,"Internal Server Error");
        }

        var userFromDb = userOptional.get();
        changeFields(userFromDb, request);
        userRepository.save(userFromDb);
        log.info("User {} updated his profile", userId);
        return CrudResult.success("Profile has been updated");
    }

    private void changeFields(User userFromDb, UserProfileUpdateServiceRequest request) {
        if (request.getBirthDate() != null){
            userFromDb.setBirthDate(request.getBirthDate());
        }

        if (request.getGender() != null){
            userFromDb.setGender(request.getGender());
        }

        if (request.getFullName() != null){
            userFromDb.setFullName(request.getFullName());
        }

        if (request.getIdNumber() != null){
            userFromDb.setIdNumber(request.getIdNumber());
        }
        if (request.getPhoneNumber()!= null){
            userFromDb.setPhoneNumber(request.getPhoneNumber());
        }
    }

    @Override
    public User getProfile(String userId) {
        var userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()){
            log.error("Non Registered User logged in system, {}", userId);
            return null;
        }

        return userOptional.get();
    }

    @Override
    public void deleteProfile(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public CrudResult changePassword(String userId, ChangePasswordServiceRequest request) {
        var userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()){
            log.error("Non Registered User logged in system, {}", userId);
            return CrudResult.failed(OperationFailureReason.INTERNAL_SERVER_ERROR,"Internal Server Error");
        }

        var user = userOptional.get();
        if (!encoder.matches(request.getOldPassword(),user.getPassword())){
            log.info("Change Password Failed for user {} Reason: Wrong old password", userId);
            return CrudResult.failed(OperationFailureReason.PRECONDITION_FAILED,"Wrong Old Password");
        }

        if (!request.getNewPassword().equals(request.getNewPasswordAgain())){
            log.info("Change Password Failed for user {} Reason: New Passwords Not Same", userId);
            return CrudResult.failed(OperationFailureReason.PRECONDITION_FAILED,"New Passwords Not Same");
        }

        var hashedPassword = encoder.encode(request.getNewPassword());
        user.setPasswordHash(hashedPassword);
        userRepository.save(user);
        log.info("User {} has changed password", userId);
        return CrudResult.success("Password has been changed successfuly");
    }
}
