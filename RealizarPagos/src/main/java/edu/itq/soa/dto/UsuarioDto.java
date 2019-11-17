package edu.itq.soa.dto;

public class UsuarioDto {

    private Integer id;
    private String login;
    private String password;
    private String idReservacion;
    private String email;
    private String idHotel;
    private String costo;
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }
    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
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
    public String getIdReservacion() {
        return idReservacion;
    }
    public void setIdReservacion(String idReservacion) {
        this.idReservacion = idReservacion;
    }
    public String getIdHotel() {
        return idHotel;
    }
    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCosto() {
        return costo;
    }
    public void setCosto(String costo) {
        this.costo = costo;
    }

    
}
