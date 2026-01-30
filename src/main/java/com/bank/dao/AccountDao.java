package com.bank.dao;

import com.bank.model.Accounts;

public interface AccountDao {
    void save(Accounts account);
    Accounts findById(Long id);
    void update(Accounts account);
    void delete(Long id);
	Accounts findByCustomerId(Long customerId);
	Accounts findByAccountNo(String fromAccountNo);
}
