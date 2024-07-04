package com.example.mirror.service;

import com.example.mirror.entity.Users;
import com.example.mirror.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户未找到");
        }
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
