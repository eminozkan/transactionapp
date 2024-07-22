package eminozkan.transaction.service.auth;

import eminozkan.transaction.repository.UserRepository;
import eminozkan.transaction.service.jwt.JwtService;
import eminozkan.transaction.service.request.UserAuthServiceRequest;
import eminozkan.transaction.util.result.AuthResult;
import eminozkan.transaction.util.result.OperationFailureReason;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder encoder;

    public AuthenticationServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    @Override
    public AuthResult authenticate(UserAuthServiceRequest request) {
       var userOptional = userRepository.findByUsername(request.getUsername());
       if (userOptional.isEmpty()){
           log.info("User not exists with {}", request.getUsername());
           return AuthResult.failed(OperationFailureReason.UNAUTHORIZED, "Invalid Credentials");
       }

       var userFromDb = userOptional.get();
       if (!encoder.matches(request.getPassword(),userFromDb.getPassword())){
           log.info("User {} tried to login with wrong password", request.getUsername());
           return AuthResult.failed(OperationFailureReason.UNAUTHORIZED, "Invalid Credentials");
       }

       log.info("Jwt Token generated for user {}", request.getUsername());
       var jwtToken = jwtService.generateToken(userFromDb);
       return AuthResult.success("Success", jwtToken);
    }
}
