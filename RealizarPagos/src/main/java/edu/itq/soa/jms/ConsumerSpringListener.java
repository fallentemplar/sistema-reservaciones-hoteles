package edu.itq.soa.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import edu.itq.soa.business.UsuarioBusiness;
import edu.itq.soa.factory.UsuarioBusinessFactory;


public class ConsumerSpringListener implements MessageListener /*, ApplicationContextAware*/{
    
    private UsuarioBusiness usuarioBusiness;
    private UsuarioBusinessFactory usuarioBusinessFactory;

	@Override
	public void onMessage(Message arg0) {
	    String mensaje = "";

        System.out.println(this);
        try {
            TextMessage txtMsg = (TextMessage) arg0;
            mensaje = txtMsg.getText();
           usuarioBusiness = (UsuarioBusiness) usuarioBusinessFactory.getBusiness("usuarioBusiness");
            usuarioBusiness.agregarUsuarios(mensaje);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param usuarioBusiness the usuarioBusiness to set
     */
    public void setUsuarioBusiness(UsuarioBusiness usuarioBusiness) {
        this.usuarioBusiness = usuarioBusiness;
    }

    public void setUsuarioBusinessFactory(UsuarioBusinessFactory usuarioBusinessFactory) {
        this.usuarioBusinessFactory = usuarioBusinessFactory;
    }

}
