package edu.itq.soa.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xmlbeans.XmlException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.karla.receptorRealizarPagos.RequestRealizarPagosDocument;

import edu.itq.soa.dao.UsuarioDao;
import edu.itq.soa.dto.UsuarioDto;
import edu.itq.soa.jms.JmsSender;

/**
 * Clase de negocio
 * @author Notebook Dell
 *
 */
public class UsuarioBusiness implements Business {
    private static JmsSender jmsSender;
    private UsuarioDao usuarioDao;
        /**
         * Inserta un usuario con la información recibida
         * @param xml XML con la información.
         */
        public void agregarUsuarios(String xml) {
            try {
                System.out.println("Instancia de negocio: "+this);
                RequestRealizarPagosDocument docRequest = RequestRealizarPagosDocument.Factory.parse(xml);
                UsuarioDto usuarioDto = new UsuarioDto();                
                usuarioDto.setCosto(docRequest.getRequestRealizarPagos().getCosto());
                usuarioDto.setEmail(docRequest.getRequestRealizarPagos().getEmailUsuario());
                usuarioDao.update(usuarioDto);
                System.out.println("************Documento del request***************");
                System.out.println(docRequest.xmlText());
                setCodigoRespuesta(xml);
            } catch (XmlException e) {
                e.printStackTrace();
            }
        }
        
        public static void setCodigoRespuesta(String xml) {
            try {
                String sourceFilepath = "RequestRealizarPagos.xml";
                String destinationFilepath = "ResponseRealizarPagos.xml";
                stringToXml(xml);
                
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(sourceFilepath);
                // Establece el código de respuesta = 200
                Node staff = doc.getElementsByTagName("tns:codigoRespuesta").item(0);
                staff.setTextContent("200");
                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(destinationFilepath));
                transformer.transform(source, result);
                xmlToString(destinationFilepath);
                System.out.println("Done!");
            } catch (ParserConfigurationException | SAXException | IOException  | DOMException | TransformerFactoryConfigurationError | IllegalArgumentException | TransformerException exp) {
                exp.printStackTrace();
            }
        }
        
        public static void stringToXml(String xmlSource) throws IOException {
            java.io.FileWriter fw = new java.io.FileWriter("RequestRealizarPagos.xml");
            fw.write(xmlSource);
            fw.close();
        }
        
        public static void xmlToString(String xmlFile) throws IOException  {
            Reader fileReader = new FileReader(xmlFile); 
            BufferedReader bufReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder(); 
            String line = bufReader.readLine();
            
            while( line != null){ 
                sb.append(line).append("\n"); 
                line = bufReader.readLine(); 
            } 
            String xmlResponse = sb.toString(); 
            System.out.println("************Documento del response***************");
            System.out.println(xmlResponse); 
            bufReader.close();
            jmsSender.sendMessage("queue/D", xmlResponse);

        }
        /**
         * @param jmsSender the jmsSender to set
         */
        public void setJmsSender(JmsSender jmsSender) {
            UsuarioBusiness.jmsSender = jmsSender;
        }
        /**
         * @param usuarioDao the usuarioDao to set
         */
        public void setUsuarioDao(UsuarioDao usuarioDao) {
            this.usuarioDao = usuarioDao;
        }
}
