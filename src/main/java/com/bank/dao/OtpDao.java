package com.bank.dao;

import com.bank.model.Otp_Codes;

public interface OtpDao {
    void save(Otp_Codes otp);
    Otp_Codes findByCustomerId(Long customerId);
}
