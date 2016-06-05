package com.alprojects.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andrew on 22.05.2016.
 */
public class MyUserDetailsService implements UserDetailsService
{
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        "andrew",
                        "$2a$04$4/SfeIicq61A/09wDbu5SesLPDAjv363EenU0zYXi9Xlri6bSZaIa", // 1234
                        // "$2a$04$hG1oXZ0dhkY4T2Q1elrjwO5OVml7094BMitcduNcMF63.HLk6/4Bm",
                        roles);

        return userDetails;
    }

}

