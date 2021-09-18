package io.github.axel1.gsbgestionvisites.service;

import io.github.axel1.gsbgestionvisites.entity.Visiteur;
import io.github.axel1.gsbgestionvisites.repository.VisiteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final VisiteurRepository visiteurRepository;

    @Autowired
    public MyUserDetailsService(VisiteurRepository visiteurRepository) {
        this.visiteurRepository = visiteurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Visiteur visiteur = visiteurRepository.findByLogin(username);
        if (visiteur == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(visiteur);
    }
}
