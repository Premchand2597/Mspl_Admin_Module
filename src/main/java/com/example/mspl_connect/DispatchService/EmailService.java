package com.example.mspl_connect.DispatchService;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Store;


/*@Service
public class EmailService {

    public boolean validateCredentials(String email, String password) {
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");
            props.put("mail.imap.host", "imap.ionos.com");
            props.put("mail.imap.port", "993"); // IONOS SMTP Server
            props.put("mail.imap.ssl.enable", "true");
            
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });

            // Instead of Transport, use Store for IMAP authentication
            Store store = session.getStore("imap");
            store.connect("imap.ionos.com", email, password);
            store.close();

            return true; // Credentials are correct
            
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Invalid credentials
        }
    }
}*/