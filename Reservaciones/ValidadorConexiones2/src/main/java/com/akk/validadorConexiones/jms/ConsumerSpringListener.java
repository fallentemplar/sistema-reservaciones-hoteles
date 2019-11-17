package com.akk.validadorConexiones.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.akk.validadorConexiones.business.UsuarioBusiness;
import com.akk.validadorConexiones.factory.UsuarioBusinessFactory;

public class ConsumerSpringListener implements MessageListener {
    /**
     * Objeto de ejecución de negocio.
     */
    private UsuarioBusiness usuarioBusiness;

    private UsuarioBusinessFactory usuarioBusinessFactory;

    // private ApplicationContext appContext;

    @Override
    public void onMessage(Message arg0) {
        String mensaje = "";

        System.out.println(this);
        try {
            TextMessage txtMsg = (TextMessage) arg0;
            mensaje = txtMsg.getText();
            // System.out.println("appContext: " + appContext);
            usuarioBusiness = (UsuarioBusiness) usuarioBusinessFactory.getBusiness("usuarioBusiness2");
            // usuarioBusiness = (UsuarioBusiness)
            // appContext.getBean("usuarioBusiness");
            usuarioBusiness.agregarUsuarios(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param usuarioBusiness the usuarioBusiness to set
     */
    public final void setUsuarioBusiness(UsuarioBusiness usuarioBusiness) {
        this.usuarioBusiness = usuarioBusiness;
    }

    // @Override
    // public void setApplicationContext(ApplicationContext applicationContext)
    // throws BeansException {
    // this.appContext = applicationContext;
    // }

    /**
     * @param usuarioBusinessFactory the usuarioBusinessFactory to set
     */
    public final void setUsuarioBusinessFactory(UsuarioBusinessFactory usuarioBusinessFactory) {
        this.usuarioBusinessFactory = usuarioBusinessFactory;
    }
}
