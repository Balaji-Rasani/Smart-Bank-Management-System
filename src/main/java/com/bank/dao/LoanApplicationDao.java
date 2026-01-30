package com.bank.dao;

import com.bank.model.Loan_Applications;
import java.util.List;

public interface LoanApplicationDao {
    boolean save(Loan_Applications loan);
    Loan_Applications findById(Long id);
    void update(Loan_Applications loan);
    List<Loan_Applications> findByStatus(String status);
    List<Loan_Applications> findAll();
    List<Loan_Applications> findByCustomerId(Long customerId);

    // ⭐ new method
    List<Loan_Applications> findByAccountNo(String accountNo);
}
