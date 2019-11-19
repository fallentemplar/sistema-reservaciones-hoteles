package edu.itq.soa.factory;

import edu.itq.soa.business.Business;

public interface UsuarioBusinessFactory {

    Business getBusiness(String name);

}
