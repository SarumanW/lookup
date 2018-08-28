package com.lookup.service;

import com.lookup.controller.UserController;
import com.lookup.domain.User;
import com.lookup.domain.UserPrincipal;
import com.lookup.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MongoUserDetailsService implements UserDetailsService {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByName(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        log.debug(user.toString());
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("USER"));

        return new UserPrincipal(user, authorities);
    }
}
