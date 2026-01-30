package com.bank.dao;

import com.bank.model.Admins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Repository
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Admins> adminRowMapper = new RowMapper<Admins>() {
        @Override
        public Admins mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admins admin = new Admins();
            admin.setAdmin_id(rs.getLong("admin_id"));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
            admin.setEmail(rs.getString("email"));
            admin.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
            admin.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
            return admin;
        }
    };

    @Override
    public Admins findByUsername(String username) {
        String sql = "SELECT * FROM admins WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, adminRowMapper, username);
        } catch (Exception e) {
            return null; // return null if no admin found
        }
    }

    @Override
    public void save(Admins admin) {
        String sql = "INSERT INTO admins (username, password, email, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        LocalDateTime now = LocalDateTime.now();
        admin.setCreated_at(now);
        admin.setUpdated_at(now);
        jdbcTemplate.update(sql, admin.getUsername(), admin.getPassword(), admin.getEmail(),
                java.sql.Timestamp.valueOf(admin.getCreated_at()),
                java.sql.Timestamp.valueOf(admin.getUpdated_at()));
    }
}
