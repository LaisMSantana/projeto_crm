package com.cliente.projetocrm.providers;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import com.cliente.projetocrm.model.vo.Cliente;
import com.cliente.projetocrm.util.ClienteMapper;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteMessageBodyReader implements MessageBodyReader<Cliente>{

	@Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type.equals(Cliente.class);
    }

	@Override
	public Cliente readFrom(Class<Cliente> type, Type genericType, Annotation[] annotations, MediaType mediaType, 
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
		return ClienteMapper.map(entityStream);
	}
}
