package com.akk.validadorConexiones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.akk.validadorConexiones.dto.UsuarioDto;

public class UsuarioDao {

    private static final Object ID = "idUsuario";
    private static final Object PASSWORD = "Password";
    private static final Object EMAIL = "Email";
    protected JdbcTemplate jdbcTemplate;

    /*
     * Establece el template
     * 
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * Agrega un usuario en la BD
     * 
     * @param usuarioDTO Usuario a agregar
     */
    public void add(UsuarioDto usuarioDTO) {
        jdbcTemplate.execute("Insert into usuario(login,password) values ('" + usuarioDTO.getEmail() + "','"
                + usuarioDTO.getPassword() + "')");
    }

    public void delete(UsuarioDto usuarioDTO) {
        jdbcTemplate.execute("delete from usuario where login='" + usuarioDTO.getEmail() + "'");
    }

    public void update(UsuarioDto usuarioDTO) {
        jdbcTemplate.execute("update usuario set password='" + usuarioDTO.getPassword() + "' where login='"
                + usuarioDTO.getEmail() + "'");
    }

    /*
     * public List<UsuarioDto> selectUsuario(UsuarioDto usuarioDTO) { String
     * consulta =
     * "SELECT id, name, age from EMPLOYEE where id = '"+usuarioDTO.getId()+"'";
     * return jdbcTemplate.queryForObject(consulta, new UsuarioMapper()); }
     * 
     * public List<UsuarioDto> selectUsuarios() { return
     * jdbcTemplate.query("select * from usuario", new UsuarioMapper()); }
     */

    public UsuarioDto qry(UsuarioDto usuarioDto) {
        List<UsuarioDto> resultado = null;

        List<Map<String, Object>> list = jdbcTemplate.queryForList(
                "" + "Select id,login,password from usuario where login = '" + usuarioDto.getEmail() + "'");

        if (list.size() > 0) {
            resultado = new ArrayList<UsuarioDto>();
            for (Map<String, Object> map : list) {
                UsuarioDto dto = new UsuarioDto();
                dto.setId((int) map.get(ID));
                dto.setEmail((String) map.get(EMAIL));
                dto.setPassword((String) map.get(PASSWORD));
                resultado.add(dto);
            }
        }
        return resultado;
    }

    public boolean search(UsuarioDto usuarioDTO) {
        String sql = "select count(*) from Usuarios where Email=? AND Password=?";
        UsuarioDto usuarioEncontrado = (UsuarioDto) jdbcTemplate.queryForObject(sql,
                new Object[] { usuarioDTO.getEmail(), usuarioDTO.getPassword()}, UsuarioDto.class);
        return usuarioEncontrado.
    }
    /*
     * public List<UsuarioDto> queryRowMapper(UsuarioDto usuarioDto) {
     * List<UsuarioDto> resultList = jdbcTemplate.query( "Select id,login," +
     * "password from usuario where login = '" + usuarioDto.getLogin() + "'", new
     * BeanPropertyRowMapper(UsuarioDto.class)); return resultList; } }
     */

final class UsuarioMapper implements RowMapper {
    public UsuarioDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsuarioDto usuario = new UsuarioDto();
        usuario.setId(rs.getInt("id"));
        usuario.setLogin(rs.getString("login"));
        usuario.setPassword(rs.getString("password"));
        return usuario;
    }
}