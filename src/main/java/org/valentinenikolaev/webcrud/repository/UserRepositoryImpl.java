package org.valentinenikolaev.webcrud.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.valentinenikolaev.webcrud.models.User;


@Component
@Transactional(propagation = Propagation.REQUIRED)
public class UserRepositoryImpl implements UserRepository{

    private SessionFactory sessionFactory;

    public UserRepositoryImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(user);

    }

    @Override
    public User get(Long ID) {
        return null;
    }

    @Override
    public boolean remove(User user) {
        return false;
    }
}
