/* package com.example.ams.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.ams.Entity.EmailStatus;

public class EmailStatusReader {

    private static final String FILE_NAME = "email_status.csv";

    public static List<EmailStatus> readFailedEmails() {
        List<EmailStatus> failedEmails = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && "NOT_SENT".equals(parts[3])) {
                    failedEmails.add(new EmailStatus(parts[1], parts[2], parts[3], parts[0]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return failedEmails;
    }
} */

