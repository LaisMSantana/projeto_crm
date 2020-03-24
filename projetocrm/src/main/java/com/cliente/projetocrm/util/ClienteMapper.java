package com.cliente.projetocrm.util;

import java.io.InputStream;

import javax.json.*;

import com.cliente.projetocrm.model.vo.Cliente;

public class ClienteMapper {

	public static Cliente map(InputStream is) {
		try (JsonReader jsonReader = Json.createReader(is)) {
			JsonObject jsonObject = jsonReader.readObject();
			Cliente cliente = new Cliente();
			cliente.setNome(getStringFromJson("NOME", jsonObject));
			cliente.setCpf(getStringFromJson("CPF", jsonObject));
			cliente.setEmail(getStringFromJson("EMAIL", jsonObject));
			cliente.setDataDeNascimento(getStringFromJson("DATA_NASCIMENTO", jsonObject));
			
			return cliente;
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
