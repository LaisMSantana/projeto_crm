package com.cliente.projetocrm.util;

import java.io.InputStream;

import javax.json.*;

import com.cliente.projetocrm.model.vo.Produto;

public class ProdutoMapper {

	public static Produto map(InputStream is) {
		try (JsonReader jsonReader = Json.createReader(is)) {
			JsonObject jsonObject = jsonReader.readObject();
			Produto produto = new Produto();
			produto.setNome(getStringFromJson("nome", jsonObject));
			produto.setCodigo(getStringFromJson("codigo", jsonObject));
			produto.setMarca(getStringFromJson("marca", jsonObject));
			produto.setTipo(getStringFromJson("tipo", jsonObject));
			
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
