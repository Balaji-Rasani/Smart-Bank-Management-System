package com.bank.model;

import java.time.LocalDateTime;

public class Otp_Codes {
    private Long otp_id;
    private Long customer_id;
    private String otp_code;
    private LocalDateTime expiry;
    private LocalDateTime created_on;

    public Otp_Codes() {}

    public Otp_Codes(Long otp_id, Long customer_id, String otp_code, LocalDateTime expiry, LocalDateTime created_on) {
        this.otp_id = otp_id;
        this.customer_id = customer_id;
        this.otp_code = otp_code;
        this.expiry = expiry;
        this.created_on = created_on;
    }

    public Long getOtp_id() { return otp_id; }
    public void setOtp_id(Long otp_id) { this.otp_id = otp_id; }

    public Long getCustomer_id() { return customer_id; }
    public void setCustomer_id(Long customer_id) { this.customer_id = customer_id; }

    public String getOtp_code() { return otp_code; }
    public void setOtp_code(String otp_code) { this.otp_code = otp_code; }

    public LocalDateTime getExpiry() { return expiry; }
    public void setExpiry(LocalDateTime expiry) { this.expiry = expiry; }

    public LocalDateTime getCreated_on() { return created_on; }
    public void setCreated_on(LocalDateTime created_on) { this.created_on = created_on; }

    @Override
    public String toString() {
        return "otp_codes{" +
                "otp_id=" + otp_id +
                ", customer_id=" + customer_id +
                ", otp_code='" + otp_code + '\'' +
                ", expiry=" + expiry +
                ", created_on=" + created_on +
                '}';
    }
}
