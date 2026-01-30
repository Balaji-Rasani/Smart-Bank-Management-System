package com.bank.dao;

import com.bank.model.Transactions;
import java.util.List;

public interface TransactionDao {
    void save(Transactions txn);
    List<Transactions> findLastN(String accountNo, int limit);
	List<Transactions> findLastN(Long accountId, int limit);
}
