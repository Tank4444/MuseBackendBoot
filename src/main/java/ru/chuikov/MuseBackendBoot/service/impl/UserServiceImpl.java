package ru.chuikov.MuseBackendBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.chuikov.MuseBackendBoot.entity.OauthClientDetails;
import ru.chuikov.MuseBackendBoot.entity.Role;
import ru.chuikov.MuseBackendBoot.entity.User;
import ru.chuikov.MuseBackendBoot.repository.OauthClientRepository;
import ru.chuikov.MuseBackendBoot.repository.UserRepository;
import ru.chuikov.MuseBackendBoot.service.UserService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OauthClientRepository oauthClientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User get(String name) {
        return userRepository.findByUsernameOrEmail(name);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        new AccountStatusUserDetailsChecker().check(user);
        return user;
    }
//"{bcrypt}"
    @PostConstruct
    private void setupDefaultUser() {
        List<Role> Roles=new ArrayList<>();
        Roles.add(new Role("user"));
        Roles.add(new Role("admin"));

        User user=new User(
                "admin",
                passwordEncoder.encode("admin"),
                "admin@mail.ru",
                Roles
        );

        create(user);


        OauthClientDetails oauthClientDetails=new OauthClientDetails(
                "USER_CLIENT_APP",
                passwordEncoder.encode("password"),
                "USER_CLIENT_RESOURCE,USER_ADMIN_RESOURCE",
                "read,write",
                "authorization_code,password,refresh_token,implicit",
                null,
                "ROLE_USER,ROLE_ADMIN",
                900,3600,
                "{}",null);
        oauthClientRepository.saveAndFlush(oauthClientDetails);

    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByNicknameOrEmail(String name) {
        return userRepository.findByUsernameOrEmail(name);
    }
}
