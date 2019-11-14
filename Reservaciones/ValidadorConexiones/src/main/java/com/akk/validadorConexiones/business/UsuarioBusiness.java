package com.akk.validadorConexiones.business;

import org.apache.xmlbeans.XmlException;

import com.soa.UsuarioDocument;

import com.akk.validadorConexiones.dao.UsuarioDao;
import com.akk.validadorConexiones.dto.UsuarioDto;
import com.akk.validadorConexiones.jms.JmsSender;

/**
 * Clase de negocio.
 * @author Alexis Aguirre.
 *
 */
public class UsuarioBusiness {
    /** Emisor de mensajes. */
    private JmsSender jmsSender;

    /** DAO para manipuación de usuarios. */
    private UsuarioDao usuarioDao;
    
    /**
     * Inserta un usuario con la informaci�n recibida.
     * @param xml XML con la informaci�n.
     */
    public void agregarUsuarios(String xml) {
        try {
            System.out.println("Instancia de negocio: " + this);
            UsuarioDocument doc = UsuarioDocument.Factory.parse(xml);
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setLogin(doc.getUsuario().getLogin());
            usuarioDto.setPassword(doc.getUsuario().getPassword());
            usuarioDao.add(usuarioDto);
            System.out.println(
                    doc.xmlText() + " " + doc.getUsuario().getLogin());
            jmsSender.sendMessage("queue/C", xml);
        } catch (XmlException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param jmsSender the jmsSender to set
     */
    public final void setJmsSender(JmsSender jmsSender) {
        this.jmsSender = jmsSender;
    }

    /**
     * @param usuarioDao the usuarioDao to set
     */
    public final void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
}
