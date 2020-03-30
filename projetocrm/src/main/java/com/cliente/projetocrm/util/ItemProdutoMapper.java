package com.cliente.projetocrm.util;

import java.io.InputStream;

import javax.json.*;

import com.cliente.projetocrm.model.vo.ItemProduto;

public class ItemProdutoMapper {

	public static ItemProduto map(InputStream is) {
		try (JsonReader jsonReader = Json.createReader(is)) {
			JsonObject jsonObject = jsonReader.readObject();
			ItemProduto itemProduto = new ItemProduto();
			
			return itemProduto;
		}
	}
	 
	 private static int getIntFromJson(String key, JsonObject json) {
	        Integer returnedValue = 0;
	        if (json.containsKey(key)) {
	            JsonNumber value = json.getJsonNumber(key);
	            if (value != null) {
	                returnedValue = value.intValue();
	            }
	        }
	        return returnedValue;
	    }
}
