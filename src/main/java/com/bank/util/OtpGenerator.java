package com.bank.util;

import java.security.SecureRandom;

public class OtpGenerator {

    private static final String NUMBERS = "0123456789";
    private static final int OTP_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    // Generates a 6-digit numeric OTP
    public static String generateOtp() {
        StringBuilder sb = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = RANDOM.nextInt(NUMBERS.length());
            sb.append(NUMBERS.charAt(index));
        }
        return sb.toString();
    }
}
