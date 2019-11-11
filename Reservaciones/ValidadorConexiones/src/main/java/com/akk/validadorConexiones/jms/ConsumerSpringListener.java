package com.akk.validadorConexiones.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.xmlbeans.XmlException;

import com.akk.validadorConexiones.dao.UsuarioDao;

public class ConsumerSpringListener implements MessageListener{
    private JmsSender jmsSender;
    private UsuarioDao usuarioDao;

	@Override
	public void onMessage(Message arg0) {
		String mensaje = "";

		System.out.println(this);
		try {
		    TextMessage txtMsg = (TextMessage) arg0;
			mensaje = txtMsg.getText();
			try {
                UsuarioDocument doc = UsuarioDocument.Factory.parse(mensaje);
                System.out.println(doc.xmlText()+ " "+ doc.getUsuario().getLogin());
            } catch (XmlException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jmsSender.sendMessage("queue/C", mensaje);
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

    public void setJmsSender(JmsSender jmsSender) {
        this.jmsSender = jmsSender;
    }
    
    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

}
