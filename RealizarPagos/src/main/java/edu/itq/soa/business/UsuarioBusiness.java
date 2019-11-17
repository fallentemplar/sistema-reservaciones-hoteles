package edu.itq.soa.business;

import org.apache.xmlbeans.XmlException;

import com.soa.UsuarioDocument;

import edu.itq.soa.dao.UsuarioDao;
import edu.itq.soa.dto.UsuarioDto;
import edu.itq.soa.jms.JmsSender;

/**
 * Clase de negocio
 * @author Notebook Dell
 *
 */
public class UsuarioBusiness implements Business {
    private JmsSender jmsSender;
    private UsuarioDao usuarioDao;
        /**
         * Inserta un usuario con la información recibida
         * @param xml XML con la información.
         */
        public void agregarUsuarios(String xml) {
            try {
                System.out.println("Instancia de negocio: "+this);
                UsuarioDocument doc = UsuarioDocument.Factory.parse(xml);
                UsuarioDto usuarioDto = new UsuarioDto();
//                usuarioDto.setLogin(doc.getUsuario().getLogin());
//                usuarioDto.setPassword(doc.getUsuario().getPassword());
                
                usuarioDto.setCosto(doc.getUsuario().getCosto());

                usuarioDao.update(usuarioDto);
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
        public void setJmsSender(JmsSender jmsSender) {
            this.jmsSender = jmsSender;
        }
        /**
         * @param usuarioDao the usuarioDao to set
         */
        public void setUsuarioDao(UsuarioDao usuarioDao) {
            this.usuarioDao = usuarioDao;
        }
}
