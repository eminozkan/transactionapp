package eminozkan.transaction.service;

import eminozkan.transaction.service.request.UserRegistrationServiceRequest;
import eminozkan.transaction.util.result.CrudResult;

public interface RegistrationService {
    CrudResult register(UserRegistrationServiceRequest request);
}
