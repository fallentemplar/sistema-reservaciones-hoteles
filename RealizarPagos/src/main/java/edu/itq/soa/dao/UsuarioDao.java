package edu.itq.soa.dao;

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
        jdbcTemplate.execute("UPDATE banco SET saldo= saldo - "+usuarioDto.getCosto()+
                "WHERE email ='"+usuarioDto.getEmail()+"'");
    }

}
