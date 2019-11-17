/**
 * 
 */
package com.springsample.dto;

/**
 * @author tlopez
 *
 */
public class UsuarioDto {
    private String id_usuario;
    private String id_hotel;
    private String fecha;
    private Integer monto;
    /**
     * @return the id_hotel
     */
    public String getId_hotel() {
        return id_hotel;
    }
    /**
     * @param id_hotel the id_hotel to set
     */
    public void setId_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }
    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * @return the monto
     */
    public Integer getMonto() {
        return monto;
    }
    /**
     * @param monto the monto to set
     */
    public void setMonto(Integer monto) {
        this.monto = monto;
    }
    /**
     * @return the id_usuario
     */
    public String getId_usuario() {
        return id_usuario;
    }
    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
}