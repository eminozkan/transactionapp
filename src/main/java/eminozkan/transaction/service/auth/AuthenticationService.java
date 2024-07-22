package eminozkan.transaction.service.auth;

import eminozkan.transaction.service.request.UserAuthServiceRequest;
import eminozkan.transaction.util.result.AuthResult;
import eminozkan.transaction.util.result.CrudResult;

public interface AuthenticationService {
    AuthResult authenticate(UserAuthServiceRequest request);
}
