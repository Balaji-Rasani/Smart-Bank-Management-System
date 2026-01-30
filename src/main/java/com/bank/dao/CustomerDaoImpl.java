package com.bank.dao;

import com.bank.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map ResultSet to Customer object
    private RowMapper<Customers> customerRowMapper = new RowMapper<Customers>() {
        @Override
        public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customers c = new Customers();
            c.setId(rs.getLong("id"));
            c.setAccount_no(rs.getString("account_no"));
            c.setPassword(rs.getString("password"));
            c.setEmail(rs.getString("email"));
            c.setRegistered(rs.getBoolean("registered"));
            return c;
        }
    };

    @Override
    public void save(Customers customer) {
        String sql = "INSERT INTO customers (account_no, password, email, registered) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getAccount_no(), customer.getPassword(), customer.getEmail(), customer.getRegistered());
    }

    @Override
    public Customers findById(Long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, customerRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Customers findByEmail(String email) {
        String sql = "SELECT * FROM customers WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, customerRowMapper, email);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Customers findByAccountNo(String accountNo) {
        String sql = "SELECT * FROM customers WHERE account_no = ?";
        try {
            return jdbcTemplate.queryForObject(sql, customerRowMapper, accountNo);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void update(Customers customer) {
        String sql = "UPDATE customers SET password = ?, email = ?, registered = ? WHERE id = ?";
        jdbcTemplate.update(sql, customer.getPassword(), customer.getEmail(), customer.getRegistered(), customer.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Customers> findAll() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, customerRowMapper);
    }
}
