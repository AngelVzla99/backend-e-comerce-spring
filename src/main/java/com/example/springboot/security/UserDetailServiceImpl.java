package com.example.springboot.security;

import com.example.springboot.model.UserEntity;
import com.example.springboot.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository
                .findOneByEmail(email)
                .orElseThrow( () -> new UsernameNotFoundException("The user with email "+email+" does not exist") );
        return new UserDetailsImpl(user);
    }
}
