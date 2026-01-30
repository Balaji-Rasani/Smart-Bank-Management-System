package com.bank.dao;

import com.bank.model.Otp_Codes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class OtpDaoImpl implements OtpDao {

    private final JdbcTemplate jdbcTemplate;

    // Constructor injection ensures jdbcTemplate is never null
    public OtpDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to convert ResultSet to Otp_Codes object
    private final RowMapper<Otp_Codes> otpRowMapper = new RowMapper<Otp_Codes>() {
        @Override
        public Otp_Codes mapRow(ResultSet rs, int rowNum) throws SQLException {
            Otp_Codes otp = new Otp_Codes();
            otp.setOtp_id(rs.getLong("otp_id"));
            otp.setCustomer_id(rs.getLong("customer_id"));
            otp.setOtp_code(rs.getString("otp_code"));
            if (rs.getTimestamp("expiry") != null) {
                otp.setExpiry(rs.getTimestamp("expiry").toLocalDateTime());
            }
            if (rs.getTimestamp("created_on") != null) {
                otp.setCreated_on(rs.getTimestamp("created_on").toLocalDateTime());
            }
            return otp;
        }
    };

    @Override
    public void save(Otp_Codes otp) {
        String sql = "INSERT INTO Otp_Codes (customer_id, otp_code, expiry, created_on) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                otp.getCustomer_id(),
                otp.getOtp_code(),
                otp.getExpiry() != null ? java.sql.Timestamp.valueOf(otp.getExpiry()) : null,
                otp.getCreated_on() != null ? java.sql.Timestamp.valueOf(otp.getCreated_on()) : null
        );
    }

    @Override
    public Otp_Codes findByCustomerId(Long customerId) {
        String sql = "SELECT * FROM Otp_Codes WHERE customer_id = ? ORDER BY created_on DESC LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, otpRowMapper, customerId);
        } catch (Exception e) {
            return null;
        }
    }
}
