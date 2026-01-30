package com.bank.dao;

import com.bank.model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ✅ Map SQL ResultSet → Accounts object
    private final RowMapper<Accounts> accountRowMapper = new RowMapper<Accounts>() {
        @Override
        public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
            Accounts a = new Accounts();
            a.setId(rs.getLong("id"));
            a.setCustomer_id(rs.getLong("customer_id"));
            a.setAccount_no(rs.getString("account_no"));
            a.setBalance(rs.getBigDecimal("balance"));
            a.setCreated_at(rs.getTimestamp("created_at") != null
                    ? rs.getTimestamp("created_at").toLocalDateTime()
                    : null);
            a.setUpdated_at(rs.getTimestamp("updated_at") != null
                    ? rs.getTimestamp("updated_at").toLocalDateTime()
                    : null);
            return a;
        }
    };

    /* -------------------- CREATE -------------------- */
    @Override
    public void save(Accounts account) {
        // ✅ Fetch customer's account number from customers table if not already set
        String accountNo = account.getAccount_no();
        if (accountNo == null || accountNo.isEmpty()) {
            try {
                String fetchAccountNoSql = "SELECT account_no FROM customers WHERE id = ?";
                accountNo = jdbcTemplate.queryForObject(fetchAccountNoSql, String.class, account.getCustomer_id());
            } catch (Exception e) {
                throw new RuntimeException("❌ No account_no found in customers table for customer_id: "
                        + account.getCustomer_id());
            }
        }

        // ✅ Insert into accounts table using that account_no
        String sql = "INSERT INTO accounts (customer_id, account_no, balance, created_at, updated_at) " +
                     "VALUES (?, ?, ?, NOW(), NOW())";
        jdbcTemplate.update(sql,
                account.getCustomer_id(),
                accountNo,
                account.getBalance());
    }

    /* -------------------- READ BY ID -------------------- */
    @Override
    public Accounts findById(Long id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, accountRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    /* -------------------- READ BY ACCOUNT NUMBER -------------------- */
    @Override
    public Accounts findByAccountNo(String accountNo) {
        String sql = "SELECT * FROM accounts WHERE account_no = ?";
        try {
            return jdbcTemplate.queryForObject(sql, accountRowMapper, accountNo);
        } catch (Exception e) {
            return null;
        }
    }

    /* -------------------- READ BY CUSTOMER ID -------------------- */
    @Override
    public Accounts findByCustomerId(Long customerId) {
        String sql = "SELECT * FROM accounts WHERE customer_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, accountRowMapper, customerId);
        } catch (Exception e) {
            return null;
        }
    }

    /* -------------------- UPDATE -------------------- */
    @Override
    public void update(Accounts account) {
        String sql = "UPDATE accounts SET balance = ?, updated_at = NOW() WHERE id = ?";
        jdbcTemplate.update(sql, account.getBalance(), account.getId());
    }

    /* -------------------- DELETE -------------------- */
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
