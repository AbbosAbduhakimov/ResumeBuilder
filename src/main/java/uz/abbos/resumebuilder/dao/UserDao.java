package uz.abbos.resumebuilder.dao;

import org.springframework.stereotype.Repository;
import uz.abbos.resumebuilder.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    public User findUser(Long id) {
        return entityManager.find(User.class,id);
    }

    public String saveUser(User user) {
        entityManager.persist(user);
        return "User created";
    }

    public String updateUser(User user) {
        entityManager.merge(user);
        return "User updated";
    }

    public String deleteUser(User user) {
        entityManager.remove(user);
        return "User deleted";
    }
}
