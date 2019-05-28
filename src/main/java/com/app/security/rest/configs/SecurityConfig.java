package com.app.security.rest.configs;

import com.app.security.rest.services.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
        //auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ADMIN");
        //auth.inMemoryAuthentication().withUser("dba").password("{noop}password").roles("DBA");

        auth.userDetailsService(appUserService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/db/**").access("hasRole('DBA')")
                .and().rememberMe().key("uniqueAndSecret").rememberMeCookieName("javasampleapproach-remember-me").tokenValiditySeconds(24 * 60 * 60)
                .and().logout().deleteCookies("JSESSIONID")
                .and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
                .usernameParameter("userName").passwordParameter("password")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
}
