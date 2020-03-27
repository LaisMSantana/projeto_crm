package com.cliente.projetocrm.util;

import java.io.InputStream;

import javax.json.*;

import com.cliente.projetocrm.model.vo.Cliente;

public class ClienteMapper {

	public static Cliente map(InputStream is) {
		try (JsonReader jsonReader = Json.createReader(is)) {
			JsonObject jsonObject = jsonReader.readObject();
			Cliente cliente = new Cliente();
			cliente.setNome(getStringFromJson("nome", jsonObject));
			cliente.setCpf(getStringFromJson("cpf", jsonObject));
			cliente.setEmail(getStringFromJson("email", jsonObject));
			cliente.setDataDeNascimento(getStringFromJson("dataDeNascimento", jsonObject));
			
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
