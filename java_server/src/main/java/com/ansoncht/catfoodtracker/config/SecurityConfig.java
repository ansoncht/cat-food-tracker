//package com.ansoncht.catfoodtracker.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
//public class SecurityConfig {
//    private final UserDetailsService userDetailsService;
//
//    public SecurityConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            BCryptPasswordEncoder bCryptPasswordEncoder) {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
//
//        return new ProviderManager(daoAuthenticationProvider);
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(
//                (authorize) ->
//                        authorize
//                                .requestMatchers("/login")
//                                .permitAll()
//                                .anyRequest()
//                                .authenticated());
//
//        return http.build();
//    }
//}
