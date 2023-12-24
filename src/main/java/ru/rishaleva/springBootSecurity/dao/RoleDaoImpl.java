package ru.rishaleva.springBootSecurity.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rishaleva.springBootSecurity.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findByName(String name) {
        Query query = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name");
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }
    @Transactional
    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

}
