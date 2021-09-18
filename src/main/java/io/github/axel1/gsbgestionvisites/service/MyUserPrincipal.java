package io.github.axel1.gsbgestionvisites.service;

import io.github.axel1.gsbgestionvisites.entity.Visiteur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserPrincipal implements UserDetails {
    private final Visiteur visiteur;

    public MyUserPrincipal(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_VISITEUR"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.visiteur.getMdp();
    }

    @Override
    public String getUsername() {
        return this.visiteur.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
