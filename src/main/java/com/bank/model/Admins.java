package com.bank.model;

import java.time.LocalDateTime;

public class Admins {
    private Long admin_id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Admins() {}

    public Admins(Long admin_id, String username, String password, String email, LocalDateTime created_at, LocalDateTime updated_at) {
        this.admin_id = admin_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getAdmin_id() { return admin_id; }
    public void setAdmin_id(Long admin_id) { this.admin_id = admin_id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getCreated_at() { return created_at; }
    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }

    public LocalDateTime getUpdated_at() { return updated_at; }
    public void setUpdated_at(LocalDateTime updated_at) { this.updated_at = updated_at; }

    @Override
    public String toString() {
        return "admins{" +
                "admin_id=" + admin_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
