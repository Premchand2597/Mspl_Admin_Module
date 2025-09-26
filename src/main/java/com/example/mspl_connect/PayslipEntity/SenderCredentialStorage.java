package com.example.mspl_connect.PayslipEntity;

import org.springframework.stereotype.Component;

@Component
public class SenderCredentialStorage {
    private String email;
    private String password;

    public synchronized void setCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public synchronized String getEmail() {
        return email;
    }

    public synchronized String getPassword() {
        return password;
    }

    public synchronized void clear() {
        this.email = null;
        this.password = null;
    }
}

