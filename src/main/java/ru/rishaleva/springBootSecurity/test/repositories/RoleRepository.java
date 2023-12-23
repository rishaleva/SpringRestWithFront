package ru.rishaleva.springBootSecurity.test.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.rishaleva.springBootSecurity.model.Role;

@Repository
@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Long> {

}

