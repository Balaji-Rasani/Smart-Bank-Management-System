package com.bank.model;

import java.time.LocalDateTime;

public class Customers {
    private Long id;
    private String account_no;
    private String password;
    private String email;
    private Boolean registered;
    private Boolean firstLogin; // NEW FIELD
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Customers() {}

    public Customers(Long id, String account_no, String password, String email,
                     Boolean registered, Boolean firstLogin,
                     LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.account_no = account_no;
        this.password = password;
        this.email = email;
        this.registered = registered;
        this.firstLogin = firstLogin;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccount_no() { return account_no; }
    public void setAccount_no(String account_no) { this.account_no = account_no; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getRegistered() { return registered; }
    public void setRegistered(Boolean registered) { this.registered = registered; }

    public Boolean isFirstLogin() { return firstLogin; }
    public void setFirstLogin(Boolean firstLogin) { this.firstLogin = firstLogin; }

    public LocalDateTime getCreated_at() { return created_at; }
    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }

    public LocalDateTime getUpdated_at() { return updated_at; }
    public void setUpdated_at(LocalDateTime updated_at) { this.updated_at = updated_at; }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", account_no='" + account_no + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registered=" + registered +
                ", firstLogin=" + firstLogin +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
