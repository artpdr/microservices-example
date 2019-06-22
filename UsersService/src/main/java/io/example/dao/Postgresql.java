package io.example.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.example.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public final class Postgresql {
    public static final String UNIQUE_VIOLATION = "23505";
    private static final Postgresql INSTANCE = new Postgresql();

    private static HikariConfig dbConfig;

    static {
        // TODO: Read config from file
        dbConfig = new HikariConfig();
        dbConfig.setJdbcUrl("jdbc:postgresql://[::1]:5432/users");
        dbConfig.setUsername("postgres");
        dbConfig.setPassword("postgres");
        dbConfig.setAutoCommit(false);
    }

    private static HikariDataSource dataSource = new HikariDataSource(dbConfig);

    private Postgresql(){}

    public static Postgresql getInstance(){
        return INSTANCE;
    }

    /**
     * Insert user in database (The fields that are inserted are username, first name, last name, email, and hashed password)
     * @param user io.example.dto.UserDTO to insert in db
     * @return true if user is inserted. false otherwise.
     * @throws SQLException if there is an error in database access (e.g. repeated index)
     */
    public boolean createUser(UserDTO user) throws SQLException {
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users (username, first_name, last_name, email, password_h) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPasswordHash());
            int updatedRows = stmt.executeUpdate();
            if (updatedRows == 1){
                conn.commit();
                return true;
            }
            else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Read a user record according to a given username.
     * @param username is the username of the user record to read
     * @return io.example.dto.UserDTO with the fields filled with the values on the record
     * @throws SQLException if there is an error in database access (e.g. repeated index)
     */
    public UserDTO readUser(String username) throws SQLException {
        UserDTO.Builder userDTOBuilder = new UserDTO.Builder();
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE username = ?"
            );
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()){
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
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return userDTOBuilder.build();
    }

    /**
     * Retrieves the email from a given user stored in the db
     * @param username
     * @return The email or empty string if the user doesn't exist
     * @throws SQLException  if there is an error in database access
     */
    public String readEmail(String username) throws SQLException {
        try (Connection conn = dataSource.getConnection()){
            conn.setAutoCommit(true);
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT email FROM users WHERE username = ?"
            );
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getString(1);
                }
            }finally {
                conn.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return "";
    }

    /**
     * Update a user record with the fields that aren't null in the given userDTO. Only updated the fields username, first name, last name, email and password hash
     * @param user UserDTO that will be used to update the user record in the db.
     * @return true if the user is updated. false otherwise
     * @throws SQLException if there is any sql exception while contacting the db.
     */
    public boolean updateUser(UserDTO user) throws SQLException {
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement stmt = buildUpdateUsersQuery(user, conn);
            int updatedRows = stmt.executeUpdate();
            if (updatedRows == 1){
                conn.commit();
                return true;
            }
            else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Sets column deleted = true in a user record with the given username.
     * @param username is the username of the user record to delete (set deleted = true)
     * @return true if the user is deleted (set deleted = true). False otherwise
     * @throws SQLException if there is any sql exception while contacting the db.
     */
    public boolean deleteUser(String username) throws SQLException {
        try (Connection conn = dataSource.getConnection()){
            String sqlQuery = String.format("UPDATE users SET %s=? WHERE %s=?",
                    UserDTO.ColumnNamesToNumbersMapping.DELETED.getColName(),
                    UserDTO.ColumnNamesToNumbersMapping.USERNAME.getColName());
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setBoolean(1, true);
            stmt.setString(2, username);
            int updatedRows = stmt.executeUpdate();
            if (updatedRows == 1){
                conn.commit();
                return true;
            }
            else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Sets column confirmed = true or false in a user record with the given username.
     * @param username is the username of the user record to update
     * @param confirmed signals the confirmed status of the user
     * @return true if the user is updated. False otherwise
     * @throws SQLException if there is any sql exception while contacting the db.
     */
    public boolean updateConfirmedUserFlag(String username, boolean confirmed) throws SQLException {
        try (Connection conn = dataSource.getConnection()){
            String sqlQuery = String.format("UPDATE users SET %s=? WHERE %s=?",
                    UserDTO.ColumnNamesToNumbersMapping.CONFIRMED.getColName(),
                    UserDTO.ColumnNamesToNumbersMapping.USERNAME.getColName());
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setBoolean(1, confirmed);
            stmt.setString(2, username);
            int updatedRows = stmt.executeUpdate();
            if (updatedRows == 1){
                conn.commit();
                return true;
            }
            else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Updates password field in db.
     * @param username The username
     * @param password The password of the user, which must be HASHED and SALTED.
     * @return True if password field is updated. False if it can't be updated.
     * @throws SQLException if there is any sql exception while contacting the db.
     */
    public boolean updateUserPassword(String username, String password) throws SQLException {
        try (Connection conn = dataSource.getConnection()){
            String sqlQuery = String.format("UPDATE users SET %s=? WHERE %s=?",
                    UserDTO.ColumnNamesToNumbersMapping.PASSWORD_HASH.getColName(),
                    UserDTO.ColumnNamesToNumbersMapping.USERNAME.getColName());
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, password);
            stmt.setString(2, username);
            int updatedRows = stmt.executeUpdate();
            if (updatedRows == 1){
                conn.commit();
                return true;
            }
            else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Builds an update query to update a user record according to the information present in the given user.
     * @param user userDTO with info to update in fields different from null
     * @return PreparedStatement with the update query
     * @throws SQLException if there is any sql exception while contacting the db.
     */
    private PreparedStatement buildUpdateUsersQuery(UserDTO user, Connection conn) throws SQLException {
        StringBuilder sb = new StringBuilder("UPDATE users SET ");
        ArrayList<String> sqlQueryVals = new ArrayList<>();

        if (user.getFirstName() != null){
            sb.append(String.format("%s=?,",
                    UserDTO.ColumnNamesToNumbersMapping.FIRST_NAME.getColName()));
            sqlQueryVals.add(user.getFirstName());
        }
        if (user.getLastName() != null){
            sb.append(String.format("%s=?,",
                    UserDTO.ColumnNamesToNumbersMapping.LAST_NAME.getColName()));
            sqlQueryVals.add(user.getLastName());
        }
        if (user.getEmail() != null){
            sb.append(String.format("%s=?,",
                    UserDTO.ColumnNamesToNumbersMapping.EMAIL.getColName()));
            sqlQueryVals.add(user.getEmail());
        }
        if (user.getPasswordHash() != null){
            sb.append(String.format("%s=?,",
                    UserDTO.ColumnNamesToNumbersMapping.PASSWORD_HASH.getColName()));
            sqlQueryVals.add(user.getPasswordHash());
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(String.format(" WHERE %s=?", UserDTO.ColumnNamesToNumbersMapping.USERNAME.getColName()));
        sqlQueryVals.add(user.getUsername());

        String sqlQuery = sb.toString();
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);

        int i = 1;
        for (String sqlVal: sqlQueryVals){
            stmt.setString(i, sqlVal);
            i++;
        }

        return stmt;
    }

    public static final class DBSetup{
        // Duplicate database and table error codes: https://www.postgresql.org/docs/8.1/errcodes-appendix.html
        private final static String DUPLICATE_DATABASE_E_CODE = "42P04";
        private final static String DUPLICATE_TABLE_E_CODE = "42P07";
        private final Properties props;
        private final String dbUrl;
        public DBSetup() throws ClassNotFoundException {
            dbUrl = "jdbc:postgresql://localhost:5432/";
            props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","postgres");
            props.setProperty("ssl","false");
            Class.forName("org.postgresql.Driver");
        }

        public void createUsersDB() throws SQLException {
            try (Connection conn = DriverManager.getConnection(dbUrl, props)){
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE DATABASE users");
            } catch (SQLException e) {
                if(!e.getSQLState().equals(DUPLICATE_DATABASE_E_CODE)){
                    throw e;
                }
            }
        }

        public void createUsersTable() throws SQLException {
            String usersDbUrl = this.dbUrl + "users";
            try (Connection conn = DriverManager.getConnection(usersDbUrl, props)){
                Statement stmt = conn.createStatement();
                stmt.execute(
                        "CREATE TABLE users (" +
                                "username VARCHAR(20) PRIMARY KEY, " +
                                "first_name VARCHAR(20) NOT NULL, " +
                                "last_name VARCHAR(20) NOT NULL, " +
                                "email VARCHAR(60) NOT NULL, " +
                                "password_h VARCHAR(1024) NOT NULL, " +
                                "deleted BOOL DEFAULT 'f' NOT NULL, " +
                                "confirmed BOOL DEFAULT 'f' NOT NULL, " +
                                "created TIMESTAMP NOT NULL DEFAULT NOW(), " +
                                "updated TIMESTAMP NOT NULL DEFAULT NOW())"
                );
            } catch (SQLException e) {
                if(!e.getSQLState().equals(DUPLICATE_TABLE_E_CODE)){
                    throw e;
                }
            }
        }
    }
}
