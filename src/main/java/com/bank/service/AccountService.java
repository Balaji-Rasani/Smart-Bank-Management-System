package com.bank.service;

import com.bank.dao.AccountDao;
import com.bank.dao.TransactionDao;
import com.bank.model.Accounts;
import com.bank.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionDao transactionDao;

    private static final BigDecimal MIN_BALANCE = new BigDecimal("500");
    private static final BigDecimal INITIAL_BALANCE = new BigDecimal("600");

    /* ============================================================
       VALIDATION HELPERS
    ============================================================ */

    private boolean isValidAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean hasSufficientBalance(Accounts from, BigDecimal amount) {
        BigDecimal remaining = from.getBalance().subtract(amount);
        return remaining.compareTo(MIN_BALANCE) >= 0;
    }

    private boolean isOwner(Long customerId, Accounts account) {
        return account != null && account.getCustomer_id().equals(customerId);
    }

    /* ============================================================
       TRANSFER USING ACCOUNT NUMBER (MAIN)
    ============================================================ */

    @Transactional
    public boolean transferFundsByAccountNo(Long customerId,
                                            String fromAccountNo,
                                            String toAccountNo,
                                            BigDecimal amount) {

        Accounts from = accountDao.findByAccountNo(fromAccountNo);
        Accounts to = accountDao.findByAccountNo(toAccountNo);

        if (from == null || to == null) return false;
        if (fromAccountNo.equals(toAccountNo)) return false;
        if (!isValidAmount(amount)) return false;
        if (!isOwner(customerId, from)) return false;
        if (!hasSufficientBalance(from, amount)) return false;

        // update balances
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountDao.update(from);
        accountDao.update(to);

        // ✅ record transaction using ACCOUNT NUMBERS
        recordTransaction(
                from.getAccount_no(),
                to.getAccount_no(),
                amount,
                "Transfer"
        );

        return true;
    }

    /* ============================================================
       TRANSACTION RECORD (ACCOUNT NUMBER BASED)
    ============================================================ */

    private void recordTransaction(String fromAccountNo,
                                   String toAccountNo,
                                   BigDecimal amount,
                                   String description) {

        Transactions txn = new Transactions();
        txn.setFrom_account_no(fromAccountNo);
        txn.setTo_account_no(toAccountNo);
        txn.setAmount(amount);
        txn.setDescription(description);
        txn.setTimestamp(LocalDateTime.now());

        transactionDao.save(txn);
    }

    /* ============================================================
       ACCOUNT CREATION / FETCH
    ============================================================ */

    @Transactional
    public Long getAccountIdByCustomerId(Long customerId, String accountNo) {

        Accounts acc = accountDao.findByCustomerId(customerId);

        if (acc != null) {
            return acc.getAccountId();
        }

        Accounts newAcc = new Accounts();
        newAcc.setCustomer_id(customerId);
        newAcc.setAccount_no(accountNo);
        newAcc.setBalance(INITIAL_BALANCE);

        accountDao.save(newAcc);

        return newAcc.getAccountId();
    }

    /* ============================================================
       BALANCE & HISTORY
    ============================================================ */

    public BigDecimal getAccountBalance(Long accountId) {
        Accounts acc = accountDao.findById(accountId);
        return acc != null ? acc.getBalance() : BigDecimal.ZERO;
    }

    public List<Transactions> getLastTransactions(Long accountId, int limit) {
        return transactionDao.findLastN(accountId, limit);
    }

    public BigDecimal getMinimumBalance() {
        return MIN_BALANCE;
    }
}
