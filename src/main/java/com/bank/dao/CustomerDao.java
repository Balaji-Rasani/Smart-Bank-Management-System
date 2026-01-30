package com.bank.dao;

import java.util.List;

import com.bank.model.Customers;

public interface CustomerDao {
    void save(Customers customer);
    Customers findById(Long id);
    Customers findByEmail(String email);
    Customers findByAccountNo(String accountNo); // added method
    void update(Customers customer);
    void delete(Long id);
	List<Customers> findAll();
}
