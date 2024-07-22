package eminozkan.transaction.service.user;

import eminozkan.transaction.model.User;
import eminozkan.transaction.service.request.ChangePasswordServiceRequest;
import eminozkan.transaction.service.request.UserProfileUpdateServiceRequest;
import eminozkan.transaction.util.result.CrudResult;

public interface UserService {
    CrudResult updateUserProfile(String userId,UserProfileUpdateServiceRequest request);

    User getProfile(String userId);

    void deleteProfile(String userId);

    CrudResult changePassword(String userId, ChangePasswordServiceRequest request);

}
