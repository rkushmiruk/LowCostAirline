package com.kushmiruk.model.entity.user;

import com.kushmiruk.model.entity.Entity;

/**
 * Entity to table UserAuthentication
 */
public class UserAuthentication extends Entity implements Cloneable {
    private Long id;
    private String login;
    private String password;
    
    public UserAuthentication(String login,String password){
        this.login = login;
        this.password = password;
    }

    public UserAuthentication(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UserAuthentication(UserAuthentication userAuthentication) {
        this.id = userAuthentication.id;
        this.login = userAuthentication.login;
        this.password = userAuthentication.password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected UserAuthentication clone() throws CloneNotSupportedException {
        return new UserAuthentication(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthentication that = (UserAuthentication) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 91 * result + (login != null ? login.hashCode() : 0);
        result = 91 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("UserAuthentication {")
                .append("id=").append(id)
                .append(", LOGIN='").append(login)
                .append(", PASSWORD='").append(password)
                .append('}').toString();

    }
}
