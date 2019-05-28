package com.app.security.rest.daos;

import com.app.security.rest.models.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AppUserDAOImpl {

    private static List<AppUser> list = new ArrayList<>();

    private static final String PASSWORD_ENCODED = new BCryptPasswordEncoder().encode("password");

    static {
        list.add(new AppUser("user", PASSWORD_ENCODED, "USER", true));
        list.add(new AppUser("admin", PASSWORD_ENCODED, "ADMIN", true));
        list.add(new AppUser("dba", PASSWORD_ENCODED, "DBA", true));
    }

    public AppUser getAppUserByUserName(String userName) {
        Optional<AppUser> appUser = list.stream().filter(user -> user.getUserName().equalsIgnoreCase(userName)).findFirst();
        return appUser.isPresent() ? appUser.get() : null;
    }

    public AppUser addAppUser(AppUser appUser){
        list.add(appUser);
        return appUser;
    }
}
