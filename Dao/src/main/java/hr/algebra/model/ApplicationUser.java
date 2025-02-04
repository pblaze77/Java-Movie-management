/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import hr.algebra.enums.Users;

/**
 *
 * @author Paulo
 */
public class ApplicationUser {
    private int id;
    private String username;
    private String password;
    private Users role;

    
    
     public ApplicationUser(int id, Users role) {
        setId(id);
        setRole(role);
    }

    public ApplicationUser(int id, String username, String password, Users role) {
         this(username, password, role);
         setId(id);
    }

    public ApplicationUser(String username, String password, Users role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users getRole() {
        return role;
    }

    public void setRole(Users role) {
        this.role = role;
    }

    public ApplicationUser() {
    }

   
    
    
    @Override
    public String toString() {
        return "ApplicationUser{" + "id=" + id + ", username=" + username + ", role=" + role + '}';
    }
}
