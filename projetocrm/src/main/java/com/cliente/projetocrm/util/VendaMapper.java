package com.cliente.projetocrm.util;

import java.io.InputStream;
import java.util.ArrayList;

import javax.json.*;

import com.cliente.projetocrm.model.vo.ItemProduto;
import com.cliente.projetocrm.model.vo.Venda;

public class VendaMapper {

	public static Venda map(InputStream is) {
		try (JsonReader jsonReader = Json.createReader(is)) {
			JsonObject jsonObject = jsonReader.readObject();
			Venda venda = new Venda();
			venda.setIdCliente(getStringFromJson("idCliente", jsonObject));
			venda.setValor(getStringFromJson("valor", jsonObject));
			venda.setFormaDePagamento(getStringFromJson("formaDePagamento", jsonObject));
			venda.setProdutos(getListaFromJson("produtos", jsonObject));
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
	 
	 private static ArrayList<ItemProduto> getListaFromJson(String key, JsonObject json) {
		 ArrayList<ItemProduto> lista  = new ArrayList<ItemProduto>();
	        if (json.containsKey(key)) {
	            JsonArray value = json.getJsonArray(key);
	            if (value != null) {
	            	for(int i = 0; i < value.size(); i++) {
	            		ItemProduto itemProduto = new ItemProduto();
	    				itemProduto.setIdProduto(getIntFromJson("idProduto", value.getJsonObject(i)));
	    				itemProduto.setIdVenda(getIntFromJson("idVenda", value.getJsonObject(i)));
	    				itemProduto.setQuantidade(getIntFromJson("quantidade", value.getJsonObject(i)));
	            		lista.add(itemProduto);
	            	}
	            }
	        }
	        return lista;
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
