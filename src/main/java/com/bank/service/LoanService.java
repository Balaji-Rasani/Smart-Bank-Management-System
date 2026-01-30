package com.bank.service;

import com.bank.dao.LoanApplicationDao;
import com.bank.model.Loan_Applications;
import com.bank.model.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoanService {

    @Autowired
    private LoanApplicationDao loanDao;

    private final List<Reports> reports = new ArrayList<>();

    public Loan_Applications applyLoan(String accountNo) {
        Loan_Applications loan = new Loan_Applications();
        loan.setAccountNo(accountNo);
        loan.setStatus("Applied");
        loan.setApplied_on(LocalDateTime.now());
        loanDao.save(loan);
        return loan;
    }

    public boolean submitLoanApplication(Loan_Applications loan) {
        if (loan == null || loan.getAccountNo() == null || loan.getAmount() == null || loan.getDuration() == null) {
            return false;
        }

        loan.setStatus("Applied");
        loan.setApplied_on(LocalDateTime.now());
        return loanDao.save(loan);
    }

    public Loan_Applications getLoanStatus(Long loanId) {
        return loanDao.findById(loanId);
    }

    public boolean updateLoanStatus(Long loanId, String status) {
        Loan_Applications loan = loanDao.findById(loanId);
        if (loan != null) {
            loan.setStatus(status);
            loanDao.update(loan);
            return true;
        }
        return false;
    }

    public List<Loan_Applications> getPendingLoans() {
        return loanDao.findByStatus("Applied");
    }

    public Reports generateCustomerReport(String accountNo) {
        Reports report = new Reports();
        report.setAccountNo(accountNo);
        report.setReport_type("Customer Loan Report");
        report.setStatus("Generated");

        reports.add(report);
        return report;
    }

    public List<Loan_Applications> getAllLoans() {
        return loanDao.findAll();
    }

    public List<Reports> getAllReports() {
        return new ArrayList<>(reports);
    }

    public List<Loan_Applications> getLoansByAccountNo(String accountNo) {
        if (accountNo == null) return new ArrayList<>();
        return loanDao.findByAccountNo(accountNo);
    }
}
