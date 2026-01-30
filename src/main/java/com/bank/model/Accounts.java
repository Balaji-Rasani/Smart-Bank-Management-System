package com.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Accounts {

    private Long id; // Primary key (account ID)
    private Long customer_id;
    private String account_no; // ✅ Added account number field
    private BigDecimal balance;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    // -------------------- Constructors --------------------
    public Accounts() {}

    public Accounts(Long id, Long customer_id, String account_no, BigDecimal balance,
                    LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.customer_id = customer_id;
        this.account_no = account_no;
        this.balance = balance;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // -------------------- Getters & Setters --------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    // ✅ Helper method to return the account's primary key
    public Long getAccountId() {
        return this.id;
    }

    // ✅ Helper alias for consistency (matches naming in AccountService)
    public String getAccountNo() {
        return this.account_no;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", account_no='" + account_no + '\'' +
                ", balance=" + balance +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
