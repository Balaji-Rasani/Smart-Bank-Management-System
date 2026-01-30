package com.bank.dao;

import com.bank.model.Loan_Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LoanApplicationDaoImpl implements LoanApplicationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(Loan_Applications loan) {
        try {
            if (loan.getLoan_id() == null) {

                String sql = "INSERT INTO Loan_Applications (account_no, amount, duration, status) VALUES (?, ?, ?, ?)";
                KeyHolder keyHolder = new GeneratedKeyHolder();

                jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, loan.getAccountNo());
                    ps.setBigDecimal(2, loan.getAmount());
                    ps.setInt(3, loan.getDuration());
                    ps.setString(4, loan.getStatus());
                    return ps;
                }, keyHolder);

                if (keyHolder.getKey() != null) {
                    loan.setLoan_id(keyHolder.getKey().longValue());
                }

            } else {
                String sql = "UPDATE Loan_Applications SET account_no = ?, amount = ?, duration = ?, status = ? WHERE loan_id = ?";
                jdbcTemplate.update(sql,
                        loan.getAccountNo(),
                        loan.getAmount(),
                        loan.getDuration(),
                        loan.getStatus(),
                        loan.getLoan_id());
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Loan_Applications findById(Long loanId) {
        String sql = "SELECT * FROM Loan_Applications WHERE loan_id = ?";
        List<Loan_Applications> loans = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Loan_Applications.class),
                loanId
        );
        return loans.isEmpty() ? null : loans.get(0);
    }

    @Override
    public void update(Loan_Applications loan) {
        String sql = "UPDATE Loan_Applications SET account_no = ?, amount = ?, duration = ?, status = ? WHERE loan_id = ?";
        jdbcTemplate.update(sql,
                loan.getAccountNo(),
                loan.getAmount(),
                loan.getDuration(),
                loan.getStatus(),
                loan.getLoan_id());
    }

    @Override
    public List<Loan_Applications> findByStatus(String status) {
        String sql = "SELECT * FROM Loan_Applications WHERE LOWER(status) = LOWER(?)";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Loan_Applications.class),
                status);
    }

    @Override
    public List<Loan_Applications> findAll() {
        String sql = "SELECT * FROM Loan_Applications";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Loan_Applications.class));
    }

    @Override
    public List<Loan_Applications> findByAccountNo(String accountNo) {
        String sql = "SELECT * FROM Loan_Applications WHERE account_no = ?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Loan_Applications.class),
                accountNo);
    }

    @Override
    public List<Loan_Applications> findByCustomerId(Long customerId) {
        String sql = """
            SELECT l.*
            FROM Loan_Applications l
            JOIN Accounts a ON l.account_no = a.account_no
            WHERE a.customer_id = ?
        """;

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Loan_Applications.class),
                customerId
        );
    }
}
