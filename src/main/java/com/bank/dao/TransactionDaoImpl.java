package com.bank.dao;

import com.bank.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Transactions> transactionRowMapper = (rs, rowNum) -> {
        Transactions t = new Transactions();
        t.setTxn_id(rs.getLong("txn_id"));
        t.setFrom_account_no(rs.getString("from_account_no"));
        t.setTo_account_no(rs.getString("to_account_no"));
        t.setAmount(rs.getBigDecimal("amount"));

        if (rs.getTimestamp("timestamp") != null) {
            t.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
        }
        return t;
    };

    @Override
    public void save(Transactions txn) {
        String sql = "INSERT INTO transactions (from_account_no, to_account_no, amount, timestamp) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                txn.getFrom_account_no(),
                txn.getTo_account_no(),
                txn.getAmount(),
                txn.getTimestamp() != null ? java.sql.Timestamp.valueOf(txn.getTimestamp()) : null
        );
    }

    @Override
    public List<Transactions> findLastN(String accountNo, int limit) {
        String sql = "SELECT * FROM transactions WHERE from_account_no = ? OR to_account_no = ? ORDER BY timestamp DESC LIMIT ?";
        return jdbcTemplate.query(sql, transactionRowMapper, accountNo, accountNo, limit);
    }

    /**
     * Optional: Remove this method if you always query by accountNo.
     * If you want to keep it, convert accountId -> accountNo first in service.
     */
    @Override
    public List<Transactions> findLastN(Long accountId, int limit) {
        // Fetch the account number first
        String accountNo = jdbcTemplate.queryForObject(
                "SELECT account_no FROM accounts WHERE account_id = ?",
                String.class,
                accountId
        );

        return findLastN(accountNo, limit);
    }
}
