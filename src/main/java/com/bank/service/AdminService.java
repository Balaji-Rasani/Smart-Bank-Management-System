package com.bank.service;

import com.bank.dao.AdminDao;
import com.bank.model.Admins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao; // Spring injects AdminDaoImpl automatically

    // Admin login
    public Admins login(String username, String password) {
        Admins admin = adminDao.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    // Create new admin
    public boolean register(Admins admin) {
        if (adminDao.findByUsername(admin.getUsername()) == null) {
            adminDao.save(admin);
            return true;
        }
        return false;
    }
}
