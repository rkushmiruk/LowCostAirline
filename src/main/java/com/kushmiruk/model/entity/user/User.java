package com.kushmiruk.model.entity.user;

import com.kushmiruk.model.entity.Entity;

/**
 * Entity to table User
 */
public class User extends Entity {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserAuthentication userAuthentication;
    private UserRole userRole;

    private User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.userAuthentication = builder.userAuthentication;
        this.userRole = builder.userRole;
    }

    public User(User user) {
        this.id = user.id;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.userAuthentication = user.userAuthentication;
        this.userRole = user.userRole;
    }

    /**
     * Builder for User entities
     */
    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private UserAuthentication userAuthentication;
        private UserRole userRole;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder userAuthentication(UserAuthentication userAuthentication) {
            this.userAuthentication = userAuthentication;
            return this;
        }

        public Builder userRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (userAuthentication != null ? !userAuthentication.equals(user.userAuthentication) : user.userAuthentication != null)
            return false;
        return userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 67 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 67 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 67 * result + (email != null ? email.hashCode() : 0);
        result = 67 * result + (userAuthentication != null ? userAuthentication.hashCode() : 0);
        result = 67 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("User{")
                .append("id=").append(id)
                .append(", FIRST_NAME='").append(firstName)
                .append(", LAST_NAME='").append(lastName)
                .append(", EMAIL='").append(email)
                .append(", userAuthentication=").append(userAuthentication)
                .append(", userRole=").append(userRole)
                .append('}').toString();
    }
}
