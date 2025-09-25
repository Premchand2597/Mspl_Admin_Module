package com.example.mspl_connect.DispatchController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component("dateUtils")
public class DateUtils {
    public long daysBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) return -1;
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}

