package com.app.security.rest.models;

import java.util.Objects;

public class AppUser {

    private String userName;

    private String password;

    private String role;

    private boolean isActive;

    public AppUser() {
    }

    public AppUser(String userName, String password, String role, boolean isActive) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return isActive == appUser.isActive &&
                userName.equals(appUser.userName) &&
                password.equals(appUser.password) &&
                role.equals(appUser.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, role, isActive);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
