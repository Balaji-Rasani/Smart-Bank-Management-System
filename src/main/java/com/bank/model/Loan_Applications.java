package com.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Loan_Applications {

    private Long loan_id;
    private String account_no;      // <-- updated field
    private BigDecimal amount;
    private Integer duration;       // in months
    private String status;
    private LocalDateTime applied_on;

    public Loan_Applications() {}

    public Loan_Applications(Long loan_id, String account_no, BigDecimal amount, Integer duration,
                             String status, LocalDateTime applied_on) {
        this.loan_id = loan_id;
        this.account_no = account_no;
        this.amount = amount;
        this.duration = duration;
        this.status = status;
        this.applied_on = applied_on;
    }

    public Long getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(Long loan_id) {
        this.loan_id = loan_id;
    }

    public String getAccountNo() {
        return account_no;
    }

    public void setAccountNo(String account_no) {
        this.account_no = account_no;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getApplied_on() {
        return applied_on;
    }

    public void setApplied_on(LocalDateTime applied_on) {
        this.applied_on = applied_on;
    }

    @Override
    public String toString() {
        return "Loan_Applications{" +
                "loan_id=" + loan_id +
                ", account_no='" + account_no + '\'' +
                ", amount=" + amount +
                ", duration=" + duration +
                ", status='" + status + '\'' +
                ", applied_on=" + applied_on +
                '}';
    }
}
