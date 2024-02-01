package com.panda.UserAuth.securityConfig;

import com.panda.UserAuth.model.UserEntity;
import com.panda.UserAuth.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        security.csrf((csrf)-> csrf.disable())
                .authorizeHttpRequests((e) ->
                e.requestMatchers("/add").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .formLogin();

        return security.build();
    }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationProvider authenticationProvider(){

            DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
            daoAuthenticationProvider.setUserDetailsService(userDetailsService());
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            return daoAuthenticationProvider;
        }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<UserEntity> user = userRepository.findByUserName(username);

                if (user.isPresent()) {
                    return user.get();
                }
                return (UserDetails) new UsernameNotFoundException("UserName Not Found");
            }
        };
    }


    }









