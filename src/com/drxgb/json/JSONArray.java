package com.drxgb.json;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Representa um array JSON, isto é, quando o conteúdo está entre colchetes
 * (<code>[]</code>).</p>
 * <p>Exemplo:</p>
 * <pre><code>
 * [
 * 		{
 * 			"name": "John",
 * 			"age": 26
 * 		},
 * 		{
 * 			"name": "Doe",
 * 			"age": 19
 * 		},
 * ]
 * </code></pre>
 * @author Dr.XGB
 * @version 1.0.2
 * @see AbstractJSON
 */
public class JSONArray extends AbstractJSON<List<Object>>
{
	/*
	 * ===========================================================
	 * 			*** CONSTANTES ***
	 * ===========================================================
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	/**
	 * Cria um array JSON vazio.
	 */
	public JSONArray()
	{
		super();
		items = new ArrayList<Object>();
	}
	
	/**
	 * Cria um array JSON com o seu conteúdo.
	 * @param items A coleção de itens JSON.
	 */
	public JSONArray(List<Object> items)
	{
		super(items);
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS IMPLEMENTADOS ***
	 * ===========================================================
	 */		

	@Override
	public void insert(String key, Object obj)
	{
		insert(obj);		
	}
	
	@Override
	public void insert(Object obj)
	{
		items.add(obj);
	}

	@Override
	public void remove(Object obj)
	{
		items.remove(obj);
	}
	
	@Override
	public Object get(Object index)
	{
		return items.get((Integer) index);
	}
	
	
	/*
	 * ===========================================================
	 * 			*** TO STRING ***
	 * ===========================================================
	 */
	
	@Override
	public String toString()
	{
		return items.toString();
	}
}
