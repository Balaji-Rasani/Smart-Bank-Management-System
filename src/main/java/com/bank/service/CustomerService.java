package com.bank.service;

import com.bank.dao.CustomerDao;
import com.bank.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /*** Login ***/
    public Customers login(String accountNo, String rawPassword) {
        if (accountNo == null || rawPassword == null) return null;

        Customers customer = customerDao.findByAccountNo(accountNo.trim());
        if (customer != null && passwordEncoder.matches(rawPassword.trim(), customer.getPassword())) {
            return customer;
        }
        return null;
    }

    /*** Register ***/
    public boolean register(Customers customer) {
        if (customerDao.findByAccountNo(customer.getAccount_no()) != null ||
            customerDao.findByEmail(customer.getEmail()) != null) {
            return false;
        }

        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashedPassword);
        customer.setRegistered(true);
        customer.setFirstLogin(true);

        customerDao.save(customer);
        return true;
    }

    /*** Reset Password (Used After Registration First Login) ***/
    public boolean resetPassword(Long customerId, String newPassword) {
        Customers c = customerDao.findById(customerId);
        if (c != null) {
            c.setPassword(passwordEncoder.encode(newPassword));
            c.setFirstLogin(false);
            customerDao.update(c);
            return true;
        }
        return false;
    }

    /*** Update Password (Used in Settings) ***/
    public boolean updatePassword(Long customerId, String newPassword) {
        Customers customer = customerDao.findById(customerId);
        if (customer != null) {
            String encodedPassword = passwordEncoder.encode(newPassword.trim());
            customer.setPassword(encodedPassword);
            customer.setFirstLogin(false);
            customerDao.update(customer);
            return true;
        }
        return false;
    }

    /*** Find by ID ***/
    public Customers findById(Long id) {
        return customerDao.findById(id);
    }

    /*** Update customer ***/
    public boolean updateCustomer(Customers customer) {
        if (customer == null || customer.getId() == null) return false;
        try {
            customerDao.update(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*** Get all customers ***/
    public List<Customers> getAllCustomers(Long customerId) {
        return List.of(customerDao.findById(customerId));
    }

    public Customers findByAccountNo(String accountNo) {
        if (accountNo == null || accountNo.trim().isEmpty()) return null;
        return customerDao.findByAccountNo(accountNo.trim());
    }
}
