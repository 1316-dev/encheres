package fr.enchere.security;

import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncheresUserDetailService implements UserDetailsService {


    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EncheresUserDetailService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur user =  utilisateurRepository.findUserByUsername(username);
        //verifier si l'utilisateur existe en BD
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur ou mot de passe invalide");
        }
        User.UserBuilder builder = User.withUsername(username)
                .password(user.getMotDePasse());
                //.roles(user.isAdmin() ? "ADMIN" : "USER");

        return builder.build();

    }
}
