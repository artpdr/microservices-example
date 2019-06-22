package io.example.dao;

import io.example.dto.UserDTO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

public class PostgresqlTest {
    private final static DBAccess dbAccess = new DBAccess();

    @BeforeClass
    public static void setupDB() throws ClassNotFoundException, SQLException {
        Postgresql.DBSetup dbSetup = new Postgresql.DBSetup();
        dbSetup.createUsersDB();
        dbSetup.createUsersTable();
    }

    @Test
    public void testCreateUser() throws SQLException {
        String username = "test";
        UserDTO userToInsert = new UserDTO.Builder()
                .setUsername(username)
                .setFirstName("Artur")
                .setLastName("Pedroso")
                .setEmail("exampleapp@yahoo.com")
                .setPasswordHash("12345")
                .build();
        Postgresql postgresqlDao = Postgresql.getInstance();
        Assert.assertTrue(postgresqlDao.createUser(userToInsert));
        dbAccess.deleteUser(username);
    }

    @Test
    public void testCreateUserWithSameUsername() throws SQLException {
        String username = "test";
        UserDTO userToInsert = new UserDTO.Builder()
                .setUsername(username)
                .setFirstName("Artur")
                .setLastName("Pedroso")
                .setEmail("exampleapp@yahoo.com")
                .setPasswordHash("12345")
                .build();
        Postgresql postgresqlDao = Postgresql.getInstance();
        Assert.assertTrue(postgresqlDao.createUser(userToInsert));
        boolean excThrown = false;
        try{
            UserDTO userToInsertRepeated = new UserDTO.Builder()
                    .setUsername(username)
                    .setFirstName("Artur")
                    .setLastName("Pedroso")
                    .setEmail("yellow@yahoo.com")
                    .setPasswordHash("12345")
                    .build();
            postgresqlDao.createUser(userToInsertRepeated);
        }catch (SQLException e){
            Assert.assertTrue(e.getSQLState().equals(Postgresql.UNIQUE_VIOLATION));
            excThrown = true;
        }finally {
            Assert.assertTrue(excThrown);
        }
        dbAccess.deleteUser(username);
    }


    @Test
    public void testReadUser() throws SQLException {
        String username = "test";
        UserDTO userMock = new UserDTO.Builder()
                .setUsername(username)
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("username@mail.com")
                .setPasswordHash("hash")
                .build();
        dbAccess.createUser(userMock);
        Postgresql postgresqlDao = Postgresql.getInstance();
        UserDTO userDTO = postgresqlDao.readUser(userMock.getUsername());
        assert userMock.getUsername().equals(userDTO.getUsername())
                && userMock.getFirstName().equals(userDTO.getFirstName())
                && userMock.getLastName().equals(userDTO.getLastName())
                && userMock.getEmail().equals(userDTO.getEmail())
                && userMock.getPasswordHash().equals(userDTO.getPasswordHash())
                && !userDTO.isConfirmed() && !userDTO.isDeleted();
        dbAccess.deleteUser(userMock.getUsername());
    }


    @Test
    public void testUpdateUser() throws SQLException {
        String username = "test";
        UserDTO userMock = new UserDTO.Builder()
                .setUsername(username)
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("username@mail.com")
                .setPasswordHash("hash")
                .build();
        dbAccess.createUser(userMock);
        UserDTO userToUpdate = new UserDTO.Builder()
                .setUsername(username)
                .setFirstName("Artur")
                .setLastName("Pedroso")
                .setEmail("apedroso@outlook.pt")
                .setPasswordHash("qwerty")
                .build();
        Postgresql postgresqlDao = Postgresql.getInstance();
        Assert.assertTrue(postgresqlDao.updateUser(userToUpdate));
        UserDTO updatedUser = dbAccess.readUser(userMock.getUsername());
        assert userToUpdate.getUsername().equals(updatedUser.getUsername())
                && userToUpdate.getFirstName().equals(updatedUser.getFirstName())
                && userToUpdate.getLastName().equals(updatedUser.getLastName())
                && userToUpdate.getEmail().equals(updatedUser.getEmail())
                && userToUpdate.getPasswordHash().equals(updatedUser.getPasswordHash());
        dbAccess.deleteUser(userMock.getUsername());
    }

    @Test
    public void testUpdateSpecificFieldsOfUser() throws SQLException {
        String username = "test";
        UserDTO userMock = new UserDTO.Builder()
                .setUsername(username)
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("username@mail.com")
                .setPasswordHash("hash")
                .build();
        dbAccess.createUser(userMock);
        UserDTO userToUpdate = new UserDTO.Builder()
                .setUsername(username)
                .setLastName("Pedroso")
                .setEmail("apedroso@outlook.pt")
                .setPasswordHash("qwerty")
                .build();
        Postgresql postgresqlDao = Postgresql.getInstance();
        Assert.assertTrue(postgresqlDao.updateUser(userToUpdate));
        UserDTO updatedUser = dbAccess.readUser(userMock.getUsername());
        assert userToUpdate.getUsername().equals(updatedUser.getUsername())
                && userMock.getFirstName().equals(updatedUser.getFirstName())
                && userToUpdate.getLastName().equals(updatedUser.getLastName())
                && userToUpdate.getEmail().equals(updatedUser.getEmail())
                && userToUpdate.getPasswordHash().equals(updatedUser.getPasswordHash());
        dbAccess.deleteUser(userMock.getUsername());
    }


    @Test
    public void testDeleteUser() throws SQLException {
        String username = "test";
        UserDTO userMock = new UserDTO.Builder()
                .setUsername(username)
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("username@mail.com")
                .setPasswordHash("hash")
                .build();
        dbAccess.createUser(userMock);
        Postgresql postgresqlDao = Postgresql.getInstance();
        Assert.assertTrue(postgresqlDao.deleteUser(username));
        UserDTO delUser = dbAccess.readUser(userMock.getUsername());
        assert userMock.getUsername().equals(delUser.getUsername())
                && userMock.getFirstName().equals(delUser.getFirstName())
                && userMock.getLastName().equals(delUser.getLastName())
                && userMock.getEmail().equals(delUser.getEmail())
                && userMock.getPasswordHash().equals(delUser.getPasswordHash())
                && delUser.isDeleted();
        dbAccess.deleteUser(userMock.getUsername());
    }

    @Test
    public void testReadEmail() throws SQLException {
        String username = "test";
        UserDTO userMock = new UserDTO.Builder()
                .setUsername(username)
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("username@mail.com")
                .setPasswordHash("hash")
                .build();
        dbAccess.createUser(userMock);
        Postgresql postgresqlDao = Postgresql.getInstance();
        Assert.assertEquals(postgresqlDao.readEmail(userMock.getUsername()), userMock.getEmail());
        dbAccess.deleteUser(userMock.getUsername());
    }

    @Test
    public void testUpdateConfirmedFlag() throws SQLException {
        String username = "test";
        UserDTO userMock = new UserDTO.Builder()
                .setUsername(username)
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("username@mail.com")
                .setPasswordHash("hash")
                .build();
        dbAccess.createUser(userMock);
        Postgresql postgresqlDao = Postgresql.getInstance();
        Assert.assertTrue(postgresqlDao.updateConfirmedUserFlag(userMock.getUsername(), true));
        UserDTO userConfirmed = dbAccess.readUser(userMock.getUsername());
        Assert.assertTrue(userConfirmed.isConfirmed());
        dbAccess.deleteUser(userMock.getUsername());
    }

    @Test
    public void testUpdateUserPassword() throws SQLException {
        String username = "test";
        UserDTO userMock = new UserDTO.Builder()
                .setUsername(username)
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("username@mail.com")
                .setPasswordHash("hash")
                .build();
        dbAccess.createUser(userMock);
        Postgresql postgresqlDao = Postgresql.getInstance();
        String newPassword = "new_password";
        Assert.assertTrue(postgresqlDao.updateUserPassword(userMock.getUsername(), newPassword));
        UserDTO userWithPasswordUpdated = dbAccess.readUser(userMock.getUsername());
        Assert.assertEquals(newPassword, userWithPasswordUpdated.getPasswordHash());
        dbAccess.deleteUser(userMock.getUsername());
    }
}
