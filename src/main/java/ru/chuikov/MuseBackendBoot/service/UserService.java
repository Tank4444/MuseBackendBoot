package ru.chuikov.MuseBackendBoot.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.chuikov.MuseBackendBoot.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User create(User user);
    void delete(User user);
    Optional<User> get(Long id);
    User get(String email);
    List<User> getAll();
    User update(User user);
    User loadUserByUsername(String s) throws UsernameNotFoundException;
    Optional<User> findById(long id);
    User findByNicknameOrEmail(String name);
}
