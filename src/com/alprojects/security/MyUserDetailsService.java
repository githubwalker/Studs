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
                        "7110eda4d09e062aa5e4a390b0a572ac0d2c0220",
                        roles);

        return userDetails;
    }

}

