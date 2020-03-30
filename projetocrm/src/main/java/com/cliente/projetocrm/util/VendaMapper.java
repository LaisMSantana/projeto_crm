package com.cliente.projetocrm.util;

import java.io.InputStream;

import javax.json.*;

import com.cliente.projetocrm.model.vo.Venda;

public class VendaMapper {

	public static Venda map(InputStream is) {
		try (JsonReader jsonReader = Json.createReader(is)) {
			JsonObject jsonObject = jsonReader.readObject();
			Venda venda = new Venda();
			venda.setIdCliente(getStringFromJson("idCliente", jsonObject));
			venda.setValor(getStringFromJson("valor", jsonObject));
			venda.setFormaDePagamento(getStringFromJson("formaDePagamento", jsonObject));
			
			return venda;
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
