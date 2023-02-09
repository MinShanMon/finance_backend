package com.personalfinance.backend.security;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.personalfinance.backend.filter.AuthenticationCustom;
import com.personalfinance.backend.filter.AuthorizationCustom;

import lombok.RequiredArgsConstructor;


@Deprecated
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
@SpringBootApplication
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        AuthenticationCustom customAuthenticationFilter = new AuthenticationCustom(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/custom/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/custom/login/**").permitAll();
        http.authorizeRequests().antMatchers("/api/user/loginWithFb/**").permitAll();
        http.authorizeRequests().antMatchers("/api/customer/review/**").permitAll();
        http.authorizeRequests().antMatchers("/api/customer/rate/**").permitAll();
        http.authorizeRequests().antMatchers("/api/user/register/**").permitAll();
        http.authorizeRequests().antMatchers("/api/user/sentOTPByEmail/**").permitAll();
        http.authorizeRequests().antMatchers("/api/user/addSessionAdmin/**").hasAnyAuthority("Admin");
        http.authorizeRequests().anyRequest().authenticated();        
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new AuthorizationCustom(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // TODO Auto-generated method stub
        return super.authenticationManagerBean();
    }

    // @Bean
    // @Override
    // public AuthenticationManager authenticationManagerBean() throws Exception{
    //     return super.authenticationManagerBean();
    // }
    
}
