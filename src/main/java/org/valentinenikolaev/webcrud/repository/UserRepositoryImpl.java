package org.valentinenikolaev.webcrud.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.valentinenikolaev.webcrud.models.User;

import java.util.List;
import java.util.Optional;


@Component
@Transactional(propagation = Propagation.REQUIRED)
public class UserRepositoryImpl implements UserRepository{

    private SessionFactory sessionFactory;

    public UserRepositoryImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<User> add(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> get(Long Id) {
        return Optional.empty();
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> change(User user) {
        return Optional.empty();
    }

    @Override
    public boolean isExistUserWithLogin(String login) {
        return true;
    }
}
