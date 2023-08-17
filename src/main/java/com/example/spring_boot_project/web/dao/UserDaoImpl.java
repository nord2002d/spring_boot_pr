package com.example.spring_boot_project.web.dao;

import com.example.spring_boot_project.web.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        if (user.getId() == null) {
            Session session = sessionFactory.getCurrentSession();
            session.save(user);
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.update(user);
        }
    }
    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.
                getCurrentSession().createQuery("from User");
        return query.getResultList();

    }
    @Override
    public User getUser(long id) {
        String hql = "from User u where u.id =: id";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void removeUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
