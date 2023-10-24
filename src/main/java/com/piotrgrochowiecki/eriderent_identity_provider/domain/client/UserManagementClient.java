package com.piotrgrochowiecki.eriderent_identity_provider.domain.client;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.User;
import org.springframework.lang.Nullable;

public interface UserManagementClient {

    String USER_MANAGEMENT_URL = "http://localhost:8081/api/v1/internal/user/";

    User getByEmail(@Nullable String email);

}
