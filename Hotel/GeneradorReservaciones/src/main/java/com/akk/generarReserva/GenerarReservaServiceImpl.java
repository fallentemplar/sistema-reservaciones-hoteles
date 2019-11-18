
/**
 * GenerarReservaServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package com.akk.generarReserva;

import java.util.List;

import com.springsample.dao.UsuarioDao;
import com.springsample.dto.UsuarioDto;

import edu.itq.soa.generarreserva.RequestGenerar;
import edu.itq.soa.generarreserva.ResponseGenerar;


/**
     *  GenerarReservaServiceSkeleton java skeleton for the axisService
     */
    public class GenerarReservaServiceImpl extends GenerarReservaServiceSkeleton{
        /** Objeto para acceso a datos. */
        private UsuarioDao usuarioDao;
         
        /**
         * Auto generated method signature
         * 
                                     * @param requestGenerar 
             * @return responseGenerar 
         */
        
                 public ResponseGenerar generarReservaOperation(RequestGenerar request) {
                     ResponseGenerar response = new ResponseGenerar();
                     UsuarioDto usuarioDto = new UsuarioDto();
                     response.setIdReservacion(request.getIdReservacion());
                     
                     try {
                         usuarioDto.setIdReservacion(request.getIdReservacion());
                         usuarioDto.setIdHabitacion(request.getIdHabitacion());
                         usuarioDto.setIdHotel(request.getIdHotel());
                         usuarioDto.setFechaReservacion(request.getFechaReservacion());
                         usuarioDto.setEmailUsuario(request.getEmailUsuario());
                     List<UsuarioDto> list = usuarioDao.consultaDisp(usuarioDto);
                     for (UsuarioDto usuarioDto3 : list) {
                         System.out.println(usuarioDto3.getIdHabitacion());
                         if (usuarioDto3.getIdReservacion() == null ) {
                             response.setCodigoRespuesta(200);
                             usuarioDao.add(usuarioDto);
                         }else {
                             response.setCodigoRespuesta(204);               
                         }
                        }
                     } catch (Exception e) {
                         
                     }
                     
                     
                     return response;
                 }

        /**
         * @param usuarioDao the usuarioDao to set
         */
        public void setUsuarioDao(UsuarioDao usuarioDao) {
            this.usuarioDao = usuarioDao;
        }
     
    }
    