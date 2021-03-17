package org.duke.sa.service;

import org.duke.sa.entity.AppUser;
import org.duke.sa.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<AppUser> appUser = appUserRepository.findById(userName);

        if (!appUser.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(appUser.get().getUsername())
                .password(appUser.get().getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}