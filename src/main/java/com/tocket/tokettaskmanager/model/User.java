package com.tocket.tokettaskmanager.model;

public class User {
    private String username;
    private String password;
    private int role;
    /*
    * Role 1 -> admin
    * Role 2 -> user
    * */
    public void setUsername(String newName){
        username = newName;
    }
    public String getUsername(){
        return username;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }
    public String getPassword(){
        return password;
    }

    public int getRole(){
        return role;
    }

    public void setRole(int newRole){
        role = newRole;
    }
}
