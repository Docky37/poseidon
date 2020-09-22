package com.nnk.springboot.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User implements UserDetails {

    /**
     * Generated serial version ID.
     */
    private static final long serialVersionUID = 7561369736068013423L;

    /**
     * Id of the user (Primary Key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Username (mandatory to log in before using Poseidon).
     */
    @NotBlank(message = "Username is mandatory")
    private String username;

    /**
     * User's password (mandatory to log in before using Poseidon).
     */
    @NotBlank(message = "Password is mandatory")
    private String password;

    /**
     * User's full name.
     */
    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    /**
     * User's role.
     */
    @NotBlank(message = "Role is mandatory")
    private String role;

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);

        return authorities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

     /**
     * {@inheritDoc}
     */
   @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
