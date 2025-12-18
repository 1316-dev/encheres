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
                        .requestMatchers("/", "/encheres","/inscription","/css/*", "/images/*","/encheresCategorie").permitAll()
                        .requestMatchers(HttpMethod.GET, "/","/encheres").permitAll()
                       // .requestMatchers( "/profil","/vendre","/").hasRole("ADMIN")
                        .anyRequest().authenticated()
                        // .anyRequest().permitAll()
                )
                //.formLogin(Customizer.withDefaults())
                .formLogin((form) -> form.loginPage("/connexion")
                .defaultSuccessUrl("/encheres", true)
                        //.failureUrl("/login?error") // redirect to error page
                .loginProcessingUrl("/login")
                        .usernameParameter("login")
                .permitAll() )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                );
        /*
                .logout(logout -> logout
                        .logoutUrl("/logout")      // URL de déconnexion
                        .logoutSuccessUrl("/")         // URL après déconnexion
                        .invalidateHttpSession(true)   // invalide la session
                        .clearAuthentication(true)     // nettoie l'authentification
                        .deleteCookies("JSESSIONID")); // supprime le cookie de session
          */

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}