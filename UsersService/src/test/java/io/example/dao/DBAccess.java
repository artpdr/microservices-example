package io.example.dao;

import io.example.dto.UserDTO;

import java.sql.*;
import java.util.Properties;

/**
 * Configure connection to db to access records created during tests
 */
public final class DBAccess {
    private final String url;
    private final Properties props;

    public DBAccess() {
        url = "jdbc:postgresql://[::1]:5432/users";
        props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","postgres");
        props.setProperty("ssl","false");
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, props);
    }

    public void deleteUser(String username) throws SQLException {
        try (Connection conn = getConnection()){
            conn.setAutoCommit(true);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE username=?");
            stmt.setString(1, username);
            stmt.execute();
        }
    }

    public void createUser(UserDTO user) throws SQLException {
        try (Connection conn = getConnection()){
            conn.setAutoCommit(true);
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users (username, first_name, last_name, email, password_h) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPasswordHash());
            stmt.execute();
        }
    }

    public UserDTO readUser(String username) throws SQLException {
        UserDTO.Builder userDTOBuilder = new UserDTO.Builder();
        try (Connection conn = getConnection()){
            conn.setAutoCommit(true);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username=?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                userDTOBuilder = userDTOBuilder
                        .setUsername(rs.getString(
                                UserDTO.ColumnNamesToNumbersMapping.USERNAME.getColNum()))
                        .setFirstName(rs.getString(
                                UserDTO.ColumnNamesToNumbersMapping.FIRST_NAME.getColNum()))
                        .setLastName(rs.getString(
                                UserDTO.ColumnNamesToNumbersMapping.LAST_NAME.getColNum()))
                        .setEmail(rs.getString(
                                UserDTO.ColumnNamesToNumbersMapping.EMAIL.getColNum()))
                        .setPasswordHash(rs.getString(
                                UserDTO.ColumnNamesToNumbersMapping.PASSWORD_HASH.getColNum()))
                        .setConfirmed(rs.getBoolean(
                                UserDTO.ColumnNamesToNumbersMapping.CONFIRMED.getColNum()))
                        .setDeleted(rs.getBoolean(
                                UserDTO.ColumnNamesToNumbersMapping.DELETED.getColNum()))
                        .setCreated(rs.getDate(
                                UserDTO.ColumnNamesToNumbersMapping.CREATED.getColNum()))
                        .setUpdated(rs.getDate(
                                UserDTO.ColumnNamesToNumbersMapping.UPDATED.getColNum()));
            }
        }
        return userDTOBuilder.build();
    }
}
