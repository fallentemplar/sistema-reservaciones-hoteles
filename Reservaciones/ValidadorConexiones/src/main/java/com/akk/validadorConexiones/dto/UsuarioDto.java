package com.akk.validadorConexiones.dto;

public class UsuarioDto {
    
    private int id;
    private String email;
    private String password;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the login
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param login the login to set
     */
    public void setEmail(String login) {
        this.email = login;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
