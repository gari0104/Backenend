package com.Pill.Popper.dao.service.impl;

import com.Pill.Popper.dao.entity.UserEntity;
import com.Pill.Popper.dao.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    
    public Long getLoggedInUserId(){
    	UsernamePasswordAuthenticationToken response = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
    	UserDetailsImpl userDetails = (UserDetailsImpl)response.getPrincipal();
    	return userDetails.getId();
    }
    
}
