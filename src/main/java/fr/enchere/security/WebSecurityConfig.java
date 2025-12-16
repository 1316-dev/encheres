package fr.enchere.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/encheres","/inscription ","/css/*", "/images/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/","/encheres").permitAll()
                       // .requestMatchers( "/profil","/vendre","/").hasRole("ADMIN")
                        //.anyRequest().authenticated()
                        .anyRequest().permitAll()
                )
                //.formLogin(Customizer.withDefaults())
                .formLogin((form) -> form.loginPage("/connexion")
                .defaultSuccessUrl("/encheres", true)
                .loginProcessingUrl("/login")
                .permitAll() )

                .logout((logout) -> logout
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/encheres")
                        .permitAll());


        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}