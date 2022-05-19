package com.makarproject.lessonsletscod.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users_s")
@Component
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "password")
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{7,20}$", message = "You must use 1 simbol (a-z),(A-Z),(0-9), size(7-20)")
    public String password;


    @Column(name = "username")
    @NotBlank(message = "username is required field!")
    @Size(min = 2, max = 50, message = "Size of username in diapazon: 2-50 simbols")
    public String username;

    @Column(name = "active")
    private boolean active;

    @Email(message = "Please, enter correct email!", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Column(name = "email")
    private String email;

//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{7,20}$", message = "You must use 1 simbol (a-z),(A-Z),(0-9), size(7-20)")
    private String confimPassword;

    public String getConfimPassword() {
        return confimPassword;
    }

    public void setConfimPassword(String confimPassword) {
        this.confimPassword = confimPassword;
    }

    private String activationCode;
    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) //фактически заменяет таблицу для хранения Enum
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))//данное поле будет храниться в отдельной таблице, которую мы не описывали в бд (мы создали табличку ролей, которая будет связываться с текущей табличкой через user_id
    @Enumerated(EnumType.STRING) // мы хотим хранить этот Enum в виде строки
    private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }


    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String actovationCode) {
        this.activationCode = actovationCode;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isActive() {
        return active;
    }


    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", active=" + active +
                ", email='" + email + '\'' +
                ", activationCode='" + activationCode + '\'' +
                ", roles=" + roles +
                '}';
    }
}


