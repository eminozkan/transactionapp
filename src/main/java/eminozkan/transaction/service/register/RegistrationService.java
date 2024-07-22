package eminozkan.transaction.service.register;

import eminozkan.transaction.service.request.UserRegistrationServiceRequest;
import eminozkan.transaction.util.result.CrudResult;

public interface RegistrationService {
    CrudResult register(UserRegistrationServiceRequest request);
}
