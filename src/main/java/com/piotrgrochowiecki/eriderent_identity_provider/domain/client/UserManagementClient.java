package com.piotrgrochowiecki.eriderent_identity_provider.domain.client;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.User;

import java.util.Optional;

public interface UserManagementClient {

    Optional<User> getByEmail(String email);

}
