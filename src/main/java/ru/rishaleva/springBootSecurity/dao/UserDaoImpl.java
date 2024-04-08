package ru.rishaleva.springBootSecurity.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rishaleva.springBootSecurity.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public User findByUserEmail(String email) {
        String query = "select distinct u from User AS u left join fetch u.roles where u.email = :email";
        User user = entityManager.createQuery(query, User.class).setParameter("email", email).getSingleResult();
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " not found");
        }
        return user;
    }

    @Transactional
    public Optional<User> getUser(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.of(Optional.ofNullable(user).orElseThrow(() -> new EntityNotFoundException("Not found")));
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        var user = getUser(id);
        if (user.isPresent()) {
            entityManager.remove(user.get());
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    @Override
    @Transactional
    public void updateUser(Long id, User user) {
        entityManager.merge(user);
    }

}
