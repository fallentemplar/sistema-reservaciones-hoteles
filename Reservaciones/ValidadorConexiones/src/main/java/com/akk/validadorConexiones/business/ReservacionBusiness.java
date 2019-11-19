package com.akk.validadorConexiones.business;

import java.rmi.RemoteException;
import java.sql.Time;

import org.apache.axis2.AxisFault;
import org.apache.xmlbeans.XmlException;

import com.akk.receptorValidadorConexiones.*;
import com.akk.validadorConexiones.dao.ReservacionDao;
import com.akk.validadorConexiones.dao.UsuarioDao;
import com.akk.validadorConexiones.dto.ReservacionDto;
import com.akk.validadorConexiones.dto.UsuarioDto;
import com.akk.validadorConexiones.jms.JmsSender;

import com.akk.validarhabitacion.*;
import com.karla.receptorRealizarPagos.RequestRealizarPagosDocument;
import com.karla.receptorRealizarPagos.RequestRealizarPagosDocument.RequestRealizarPagos;
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
            
            System.out.println("-----------------------\n\n");
            System.out.println(xml);
            System.out.println("-----------------------\n\n");
            
            AgendarReservacion(reservacionDto,doc);
            System.out.println("A");
            reservacionDto.setIDReservacion(reservacionDao.ObtenerIDReservacion(reservacionDto));
            System.out.println("B");
            ValidarHabitacionServiceStub stubValidarHabitacion=null;
            try {
                /*stubValidarHabitacion = new ValidarHabitacionServiceStub("http://192.168.43.35:8082/axis2/services/ValidarHabitacionService/");
                
                ValidarHabitacionServiceStub.RequestValidar request = new ValidarHabitacionServiceStub.RequestValidar(); 
                request.setIdReservacion(reservacionDto.getIDReservacion().toString());
                request.setIdHotel(reservacionDto.getIDHotel().toString());
                request.setFechaReservacion(reservacionDto.getFECHA());
                
                ValidarHabitacionServiceStub.ResponseValidar response = stubValidarHabitacion.validarHabitacionOperation(request);
                
                System.out.println(response.getCodigoRespuesta()+" | "+response.getIdReservacion()+" | "+ response.getIdHabitacion()+" | "+response.getCosto()); 
                */
                RequestRealizarPagosDocument docBanco = RequestRealizarPagosDocument.Factory.newInstance();  
                RequestRealizarPagos requestBanco = docBanco.addNewRequestRealizarPagos();
                
                requestBanco.setIdReservacion(reservacionDto.getIDReservacion().toString());
                requestBanco.setEmailUsuario(reservacionDto.getEmail());
                
                //requestBanco.setCosto(reservacionDto.getMONTO());
                requestBanco.setCosto(66);
                requestBanco.setCodigoRespuesta(0);
                
                System.out.println("*****************************************");
                System.out.println(docBanco.xmlText());
                System.out.println("\n*****************************************");
                jmsSender.sendMessage("queue/C", docBanco.xmlText());
                
            } catch(Exception e) {
            }/*catch (AxisFault e) {
            }
                // TODO Auto-generated catch block
                System.out.println("No jaló :'c");
                e.printStackTrace();
            } catch (RemoteException e) {
                System.out.println("Otra vez no jaló :'c");
                e.printStackTrace();
            }*/
            //doc.xmlText() + " " + doc.getUsuario().getLogin());
            //jmsSender.sendMessage("queue/C", xml);
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
    public final void setReservacionDao(ReservacionDao reservacionDao) {
        this.reservacionDao = reservacionDao;
    }

    private void AgendarReservacion(ReservacionDto reservacionDto, RequestValidadorConexionesDocument doc) {
        System.out.println("1");
        
        System.out.println("2");
        reservacionDto.setIDReservacion(null);
        System.out.println("3");
        reservacionDto.setEmail(doc.getRequestValidadorConexiones().getEmailUsuario());
        System.out.println("4");
        reservacionDto.setFECHA(doc.getRequestValidadorConexiones().getFechaReservacion());
        System.out.println("5");
        reservacionDto.setIDHotel(Integer.parseInt(doc.getRequestValidadorConexiones().getIdHotel()));
        System.out.println("6");
        reservacionDto.InicializarReservacion();
        System.out.println("7");
        reservacionDto.setIDUsuario(reservacionDao.ObtenerIDUsuario(reservacionDto));
        System.out.println("ID Usuario: "+reservacionDto.getIDUsuario());
        System.out.println("8");
        reservacionDao.AgregarReservacion(reservacionDto);
        System.out.println("9");
    }
}
