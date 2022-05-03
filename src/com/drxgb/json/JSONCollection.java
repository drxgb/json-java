package com.drxgb.json;

import java.io.Serializable;

/**
 * Representa um objeto JSON onde dados devem ser coletados.
 * @author Dr.XGB
 * @version 1.0.2
 * @see Serializable
 */
public interface JSONCollection extends Serializable
{
	/**
	 * Insere um elemento à coleção.
	 * @param key A chave do elemento a ser inserido.
	 * @param obj Elemento a ser inserido ao JSON.
	 */
	void insert(String key, Object obj);
	
	
	/**
	 * Remove um elemento de uma coleção.
	 * @param key Chave do elemento a ser removido do JSON.
	 */
	void remove(Object key);
	
	
	/**
	 * Recebe um elemento JSON.
	 * @param key A chave do elemento a ser encontrado.
	 * @return Elemento do JSON encontrado.
	 */
	Object get(Object key);
	
	
	/**
	 * Insere um elemento à coleção.
	 * @param obj Elemento a ser inserido ao JSON.
	 */
	default void insert(Object obj)
	{
		insert(null, obj);
	}
}
