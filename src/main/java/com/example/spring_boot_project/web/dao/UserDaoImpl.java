package com.example.spring_boot_project.web.dao;



import com.example.spring_boot_project.web.exeptions.UserNotFoundException;
import com.example.spring_boot_project.web.model.User;



import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.springframework.stereotype.Component;


import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        try {
            if (user.getId() == null) {
                Session session = sessionFactory.getCurrentSession();
                session.save(user);
            } else {
                Session session = sessionFactory.getCurrentSession();
                session.update(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query;
        try {
            query = (TypedQuery<User>) sessionFactory.getCurrentSession().createQuery("from User");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public User getUser(long id) throws UserNotFoundException {
        Optional<User> user = Optional.empty();
        TypedQuery<User> query;
        try {
            String hql = "from User u where u.id =: id";
            query = (TypedQuery<User>) sessionFactory.getCurrentSession().createQuery(hql, User.class);
            query.setParameter("id", id);
            user = Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.orElseThrow(() ->
                new UserNotFoundException(String.format("Пользователь с таки id - %d не найден", id)));
    }

    @Override
    public void removeUser(long id) throws UserNotFoundException {
        String getUser = "from User u where u.id =: id";
        TypedQuery<User> query = (TypedQuery<User>) sessionFactory.getCurrentSession().createQuery(getUser, User.class);
        query.setParameter("id", id);
        Optional<User> user = Optional.ofNullable(query.getSingleResult());
        if (user.isPresent()) {
            sessionFactory.getCurrentSession().delete(user.get());
        } else {
            throw new UserNotFoundException(String.format("Пользователь с таки id - %d не найден", id));
        }
    }
}
