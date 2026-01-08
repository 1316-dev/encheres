package fr.enchere.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/","/connexion", "/encheres","/inscription","/css/*", "/images/*","/encheresCategorie").permitAll()
                        .requestMatchers(HttpMethod.GET, "/","/encheres").permitAll()
                       // .requestMatchers( "/profil","/vendre","/").hasRole("ADMIN")
                        .anyRequest().authenticated()
                        // .anyRequest().permitAll()
                )
                .formLogin((form) -> form.loginPage("/connexion")
                .defaultSuccessUrl("/gestion-encheres", true)

                .loginProcessingUrl("/login")
                        .usernameParameter("login")
                .permitAll() )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID","remember-me")
                        )
                .sessionManagement(session -> session
                        .invalidSessionUrl("/connexion?expired=true")
                        .maximumSessions(1)
                )
                .rememberMe((remember) -> remember
                        .key("vfgr17hjMJ18lkdrtgDFG")
                        .tokenValiditySeconds(86400) // 1 jour
                        .userDetailsService(userDetailsService) // Indispensable pour que Spring retrouve l'utilisateur
                        .rememberMeParameter("remember-me")    // Doit correspondre au 'name' de votre checkbox HTML
                );


        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}