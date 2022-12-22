package com.Service;

import com.Entity.User;
import com.Form.UserCreateForm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> findAll();

    void create(UserCreateForm form);

    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;

    UserDetails loadUserById(Long id);
}
