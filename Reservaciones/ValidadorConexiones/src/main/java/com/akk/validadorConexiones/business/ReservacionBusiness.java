package com.akk.validadorConexiones.business;

import org.apache.xmlbeans.XmlException;

import com.akk.receptorValidadorConexiones.*;
import com.akk.validadorConexiones.dao.ReservacionDao;
import com.akk.validadorConexiones.dao.UsuarioDao;
import com.akk.validadorConexiones.dto.ReservacionDto;
import com.akk.validadorConexiones.jms.JmsSender;

/**
 * Clase de negocio.
 * @author tlopez.
 *
 */
public class ReservacionBusiness implements Business {
    /** Emisor de mensajes. */
    private JmsSender jmsSender;

    
    private ReservacionDao reservacionDao;
    

    @Override
    public void agregarReservacion(String xml) {
        
        try {
            System.out.println("Super instancia de negocio: " + this);
            
            RequestValidadorConexionesDocument doc = RequestValidadorConexionesDocument.Factory.parse(xml); 
            ReservacionDto reservacionDto = new ReservacionDto();
            reservacionDto.setIDHotel(Integer.parseInt(doc.getRequestValidadorConexiones().getIdHotel()));
            reservacionDto.setFECHA(doc.getRequestValidadorConexiones().getFechaReservacion());
            reservacionDto.setEmail(doc.getRequestValidadorConexiones().getEmailUsuario());
            //reservacionDto.setIDUsuario(reservacionDao.ObtenerIDUsuario(reservacionDto);
            System.out.println(reservacionDto.getEmail()+"|"+reservacionDto.getIDHotel());
            System.out.println("-----------------------\n\n");
            System.out.println(xml);
            System.out.println("-----------------------\n\n");
            /*UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setLogin(doc.getUsuario().getLogin());
            usuarioDto.setPassword(doc.getUsuario().getPassword());
            usuarioDao.add(usuarioDto);
            System.out.println(
                    doc.xmlText() + " " + doc.getUsuario().getLogin());*/
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
    public final void setReservacionDao(ReservacionDao ReservacionDao) {
        this.reservacionDao = reservacionDao;
    }

    
}
