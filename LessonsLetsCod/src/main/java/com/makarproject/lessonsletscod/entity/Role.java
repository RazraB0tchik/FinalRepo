package com.makarproject.lessonsletscod.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


public enum Role implements GrantedAuthority{
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

