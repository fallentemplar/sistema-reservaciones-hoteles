package edu.itq.soa.dto;

public class UsuarioDto {

    private String idReservacion;
    private String email;
    private String idHotel;
    private double costo;
    private int codigoRespuesta;

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
    public double getCosto() {
        return costo;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }
    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }
    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    
}
