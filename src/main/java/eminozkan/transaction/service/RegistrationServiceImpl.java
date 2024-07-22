package eminozkan.transaction.service;

import eminozkan.transaction.model.User;
import eminozkan.transaction.repository.UserRepository;
import eminozkan.transaction.service.request.UserRegistrationServiceRequest;
import eminozkan.transaction.util.result.CrudResult;
import eminozkan.transaction.util.result.OperationFailureReason;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final Logger log = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public RegistrationServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public CrudResult register(UserRegistrationServiceRequest request) {
        var userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isPresent()){
            log.info("User registration failed. Reason : User {} has already registered.", request.getUsername());
            return CrudResult.failed(OperationFailureReason.CONFLICT,"User has already registered.");
        }

        var user = new User()
                .setIdNumber(request.getIdNumber())
                .setUsername(request.getUsername())
                .setPasswordHash(encoder.encode(request.getRawPassword()))
                .setFullName(request.getFullName())
                .setBirthDate(request.getBirthDate())
                .setPhoneNumber(request.getPhoneNumber())
                .setGender(request.getGender());

        userRepository.save(user);
        log.info("User has registered successfully");
        return CrudResult.success("User has registered successfully");
    }
}
