package com.springsample.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.springsample.dto.UsuarioDto;

/**
 * @author tlopez
 */
public class UsuarioDao {
    
    private String idReservacion;
    private String idHotel;
    private String idHabitacion;
    private String fechaReservacion;
    private String codigoRespuesta;
    private String emailUsuario;
    protected JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
   /* public UsuarioDao() {

    }
    UsuarioDao (String a, String x, String b,String c,  int d)
    {
        codigoRespuesta=a;
        idHotel = x;
        idReservacion=b;
        idHabitacion=c;
        costoS=String.valueOf(d);
    } */
    
public List<UsuarioDto> consultaDisp(UsuarioDto usuarioDto){
        
        List<UsuarioDto> resultlist = jdbcTemplate.query("SELECT idReservacion"
                + " FROM reservaciones"
                + " WHERE idHotel=" +usuarioDto.getIdHotel() 
                + " AND FechaReservacion = '" + usuarioDto.getFechaReservacion() 
                + "' AND idHabitacion = " + usuarioDto.getIdHabitacion() 
                + " UNION SELECT NULL FROM DUAL WHERE NOT EXISTS "
                + "(SELECT idReservacion"
                + " FROM reservaciones"
                + " WHERE idHotel= " +usuarioDto.getIdHotel()
                + " AND FechaReservacion = '" + usuarioDto.getFechaReservacion()
                + "' AND idHabitacion = " + usuarioDto.getIdHabitacion() +")",
            new BeanPropertyRowMapper(UsuarioDto.class));
        return resultlist;
    }

public void add(UsuarioDto usuarioDto) {
    jdbcTemplate.execute("INSERT INTO reservaciones (idReservacion, idHotel, idHabitacion, EmailUsuario, FechaReservacion) VALUES ("
            + usuarioDto.getIdReservacion() + ", " + usuarioDto.getIdHotel()
            + ", " + usuarioDto.getIdHabitacion()
            + ", '" + usuarioDto.getEmailUsuario()
            + "', '" + usuarioDto.getFechaReservacion()
            + "')");
}
    
}







