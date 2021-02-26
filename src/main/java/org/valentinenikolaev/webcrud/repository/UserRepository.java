package org.valentinenikolaev.webcrud.repository;

import org.valentinenikolaev.webcrud.models.User;

public interface UserRepository extends GenericRepository<User,Long> {
    boolean isExistUserWithLogin(String login);
}
