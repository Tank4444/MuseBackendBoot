package ru.chuikov.MuseBackendBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.chuikov.MuseBackendBoot.entity.User;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select b from User b where b.id = :id")
    Optional<User> findById(@Param("id") long id);

    @Query("select b from User b where b.username = :name")
    User findByUsername(@Param("name")String name);

    @Query("select b from User b where b.email = :email")
    User findByEmail(@Param("email")String email);

    @Query("select b from User b where b.username = :name or b.email = :name")
    User findByUsernameOrEmail(@Param("name")String name);
}
