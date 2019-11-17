package com.akk.validadorConexiones.dao;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.akk.validadorConexiones.dto.ReservacionDto;

/**
 * @author tlopez
 */
public class ReservacionDao {

	private static final String IDReservacion = "idReservacion";

    private static final String IDUsuario = "idUsuario";

    private static final String IDHotel = "idHotel";
    
    private static final String IDHabitacion = "idHabitacion";
    
    private static final String FECHA="Fecha";
    
    private static final String MONTO="Monto";
    
    private static final String Estatus="Estatus";

	
    protected JdbcTemplate jdbcTemplate;

    /**
     * Establece el template.
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Aquí van los métodos de BD
    
}






