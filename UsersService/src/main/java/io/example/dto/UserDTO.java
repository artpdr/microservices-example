package io.example.dto;

import java.util.Date;
import java.util.Objects;

public class UserDTO {
    public enum ColumnNamesToNumbersMapping{
        USERNAME("username", 1),
        FIRST_NAME("first_name", 2),
        LAST_NAME("last_name", 3),
        EMAIL("email", 4),
        PASSWORD_HASH("password_h", 5),
        DELETED("deleted", 6),
        CONFIRMED("confirmed", 7),
        CREATED("created", 8),
        UPDATED("updated", 9);

        private int colNum;
        private String colName;
        private ColumnNamesToNumbersMapping(String colName, int colNum){
            this.colName = colName;
            this.colNum = colNum;
        }

        public int getColNum() {
            return colNum;
        }

        public String getColName() {
            return colName;
        }
    }
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String passwordHash;
    private final boolean deleted;
    private final boolean confirmed;
    private final Date created;
    private final Date updated;

    private UserDTO(String username, String firstName, String lastName, String email,
                   String passwordHash, boolean deleted, boolean confirmed,
                   Date created, Date updated) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.deleted = deleted;
        this.confirmed = confirmed;
        this.created = created;
        this.updated = updated;
    }

    public static class Builder{
        private String username = null;
        private String firstName = null;
        private String lastName = null;
        private String email = null;
        private String passwordHash = null;
        private boolean deleted = false;
        private boolean confirmed = false;
        private Date created = null;
        private Date updated = null;

        public Builder(){}

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Builder setDeleted(boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Builder setConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
            return this;
        }

        public Builder setCreated(Date created) {
            this.created = created;
            return this;
        }

        public Builder setUpdated(Date updated) {
            this.updated = updated;
            return this;
        }

        public UserDTO build(){
            return new UserDTO(username, firstName, lastName, email, passwordHash,
                    deleted, confirmed, created, updated);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return deleted == userDTO.deleted &&
                confirmed == userDTO.confirmed &&
                username.equals(userDTO.username) &&
                firstName.equals(userDTO.firstName) &&
                lastName.equals(userDTO.lastName) &&
                email.equals(userDTO.email) &&
                passwordHash.equals(userDTO.passwordHash) &&
                created.equals(userDTO.created) &&
                updated.equals(userDTO.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, email, passwordHash, deleted, confirmed, created, updated);
    }

    @Override
    public String toString() {
        return "User(" + username + ", " + firstName + ", " + lastName +
                ", " + email + ", " + passwordHash + ", " +
                confirmed + ", " + deleted + ", " + created + ", " + updated +")";
    }
}
