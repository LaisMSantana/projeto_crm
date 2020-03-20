package com.cliente.projetocrm.util;

import java.io.InputStream;

import javax.json.*;

import com.cliente.projetocrm.model.vo.Produto;

public class ProdutoMapper {

	public static Produto map(InputStream is) {
		try (JsonReader jsonReader = Json.createReader(is)) {
			JsonObject jsonObject = jsonReader.readObject();
			Produto produto = new Produto();
			produto.setNome(getStringFromJson("NOME", jsonObject));
			produto.setCodigo(getStringFromJson("CODIGO", jsonObject));
			produto.setMarca(getStringFromJson("MARCA", jsonObject));
			produto.setTipo(getStringFromJson("TIPO", jsonObject));
			
			return produto;
		}
	}
	
	 private static String getStringFromJson(String key, JsonObject json) {
	        String returnedString = null;
	        if (json.containsKey(key)) {
	            JsonString value = json.getJsonString(key);
	            if (value != null) {
	                returnedString = value.getString();
	            }
	        }
	        return returnedString;
	    }

}
