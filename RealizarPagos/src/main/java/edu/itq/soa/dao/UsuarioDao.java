package edu.itq.soa.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import edu.itq.soa.dto.UsuarioDto;

public class UsuarioDao {

    /**
     * Template para manejo de BD de Spring.
     */
    protected JdbcTemplate jdbcTemplate;

    /**
     * Establece el template.
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public void update(UsuarioDto usuarioDto) {
        jdbcTemplate.execute("UPDATE banco SET saldo= saldo - "+usuarioDto.getCosto()+"WHERE id =1");
    }
    
    /**
     * Agrega un usuario en la BD.
     * @param usuarioDto Usuario a agregar.
     */
    public List <UsuarioDto> queryRowMapper(UsuarioDto usuarioDto) {
        List <UsuarioDto> resultList = jdbcTemplate.query("SELECT id,saldo from banco"
                + " WHERE id ='"+ usuarioDto.getLogin() + "'", new BeanPropertyRowMapper(UsuarioDto.class));
        return resultList;
    }

}
