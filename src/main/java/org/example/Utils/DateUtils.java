package org.example.Utils;

import java.time.LocalDate;

public class DateUtils {

    public static String getTomorrowsDate() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return tomorrow.toString();
    }
}