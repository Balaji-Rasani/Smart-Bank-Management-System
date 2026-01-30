package com.bank.service;

import com.bank.dao.OtpDao;
import com.bank.model.Otp_Codes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    private final OtpDao otpDao;

    @Autowired
    public OtpService(OtpDao otpDao) {
        this.otpDao = otpDao;
    }

    // Generate OTP for a customer
    public Otp_Codes generateOtp(Long customerId) {
        Otp_Codes otp = new Otp_Codes();
        otp.setCustomer_id(customerId);
        otp.setOtp_code(generateRandomOtp());
        otp.setCreated_on(LocalDateTime.now());
        otp.setExpiry(LocalDateTime.now().plusMinutes(5)); // 5 min expiry
        otpDao.save(otp);
        return otp;
    }

    // Validate OTP
    public boolean validateOtp(Long customerId, String otpCode) {
        Otp_Codes lastOtp = otpDao.findByCustomerId(customerId);
        if (lastOtp != null && lastOtp.getOtp_code().equals(otpCode)) {
            // Check if OTP is expired
            return lastOtp.getExpiry() != null && LocalDateTime.now().isBefore(lastOtp.getExpiry());
        }
        return false;
    }

    // Helper to generate 6-digit OTP
    private String generateRandomOtp() {
        Random random = new Random();
        int otpInt = 100000 + random.nextInt(900000);
        return String.valueOf(otpInt);
    }
}
