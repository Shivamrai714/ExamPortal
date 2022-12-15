package com.exam.config;


import com.exam.services.impl_classes.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//STEP 14 a

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;   // used for Exceprtion Handling.

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return  super.authenticationManagerBean();
    }

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }

/*
    //Only for testing purpose
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
*/



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // we will do the authentication using the details of the DAtabase using userDetails

        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());  // passed the function itself which is bean to get the password   //Here to authnticate spring will intenally call the load userby username method of userDetailsServiceImpl
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //STEP 14 b :

        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers("/generate-token","/user/").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
