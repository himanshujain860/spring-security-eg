package com.app.security.rest.services;

import com.app.security.rest.daos.AppUserDAOImpl;
import com.app.security.rest.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserDAOImpl appUserDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName){
        AppUser appUser = appUserDAO.getAppUserByUserName(userName);
        if(appUser==null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(appUser.getUserName(), appUser.getPassword(),
                appUser.isActive(), true, true, true, getGrantedAuthorities(appUser));
    }

    private List<GrantedAuthority> getGrantedAuthorities(AppUser user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        return authorities;
    }

    public AppUser addAppUser(AppUser appUser){
        passwordEncoder.encode(appUser.getPassword());
        return appUserDAO.addAppUser(appUser);
    }
}
