package web.userdao;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManagerBean;


    @Override
    @Transactional
    public void saveUser(User user) {
        entityManagerBean.persist(user);
        entityManagerBean.flush();
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return entityManagerBean.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        Query jpqlQuery = entityManagerBean.createQuery("SELECT u FROM User u WHERE u.id=:id");
        jpqlQuery.setParameter("id", id);
        User us = (User) jpqlQuery.getSingleResult();
        return us;
    }

    @Override
    @Transactional
    public void updateUserById(long id, User user) {
        User toUpdate = getUserById(id);
        toUpdate.setName(user.getName());
        toUpdate.setProfession(user.getProfession());
        entityManagerBean.merge(toUpdate);
        entityManagerBean.flush();
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        entityManagerBean.remove(getUserById(id));
        entityManagerBean.flush();
    }

}
