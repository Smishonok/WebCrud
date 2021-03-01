package org.valentinenikolaev.webcrud.repository;

import org.valentinenikolaev.webcrud.models.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User,Long> {
    boolean isExistUserWithLogin(String login);

    Optional<User> change(User user);
}
