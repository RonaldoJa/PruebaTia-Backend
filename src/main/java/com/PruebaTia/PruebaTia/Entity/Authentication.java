package com.PruebaTia.PruebaTia.Entity;

import org.springframework.security.core.GrantedAuthority;

public class Authentication implements GrantedAuthority {
    private String authority;

    public Authentication(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
