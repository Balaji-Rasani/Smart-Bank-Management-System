package com.bank.dao;

import com.bank.model.Admins;

public interface AdminDao {

    // Find admin by username
    Admins findByUsername(String username);

    // Optional: add/save a new admin
    void save(Admins admin);
}
