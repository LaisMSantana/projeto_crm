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

import com.cliente.projetocrm.model.vo.ItemProduto;
import com.cliente.projetocrm.util.ItemProdutoMapper;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class ItemProdutoMessageBodyReader implements MessageBodyReader<ItemProduto>{

	@Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type.equals(ItemProduto.class);
    }

	@Override
	public ItemProduto readFrom(Class<ItemProduto> type, Type genericType, Annotation[] annotations, MediaType mediaType, 
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
		return ItemProdutoMapper.map(entityStream);
	}
}
