package com.example.mspl_connect.PayslipController;

public class NumberToWordsConverter {

    private static final String[] units = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] thousands = {"", "Thousand", "Lakh", "Crore"};

    public static String convert(double n) {
        if (n < 0) {
            return "Minus " + convert(-n);
        }

        long wholePart = (long) n;

        return convert(wholePart);
    }

    private static String convert(long n) {
        if (n < 20) {
            return units[(int) n];
        }

        if (n < 100) {
            return tens[(int) (n / 10)] + ((n % 10 != 0) ? " " + units[(int) (n % 10)] : "");
        }

        if (n < 1000) {
            return units[(int) (n / 100)] + " Hundred" + ((n % 100 != 0) ? " " + convert(n % 100) : "");
        }

        if (n < 100000) { // Less than one lakh
            return convert(n / 1000) + " Thousand" + ((n % 1000 != 0) ? " " + convert(n % 1000) : "");
        }

        if (n < 10000000) { // Less than one crore
            return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " + convert(n % 100000) : "");
        }
        // One crore and above
        return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " + convert(n % 10000000) : "");
    }
}



