package com.drxgb.json;

import java.util.SortedMap;
import java.util.TreeMap;


/**
 * <p>Representa um objeto JSON, isto é, quando o mesmo é envolvidos entre chaves
 * (<code>{}</code>).</p>
 * <p>Exemplo:</p>
 * <pre><code>
 * {
 * 		"name": "John Doe",
 * 		"value": 10,
 * 		"items": [
 * 			{
 * 				"title": "Foo",
 * 				"cost": 2.79
 * 			},
 * 			{
 * 				"title": "Bar",
 * 				"cost": 5.29
 * 			}
 * 		]
 * }
 * </code></pre>
 * @author Dr.XGB
 * @version 1.0.2
 * @see JSONCollection
 * @see SortedMap
 */
public class JSONObject extends AbstractJSON<SortedMap<String, Object>>
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
	 * Cria um objeto JSON vazio.
	 */
	public JSONObject()
	{
		items = new TreeMap<String, Object>();
	}
	
	/**
	 * Cria um objeto JSON com o seu conteúdo.
	 * @param items A coleçao de itens do JSON.
	 */
	public JSONObject(SortedMap<String, Object> items)
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
		items.put(key, obj);
	}
	
	@Override
	public void remove(Object key)
	{
		items.remove((String) key);
	}
	
	@Override
	public Object get(Object key)
	{
		return items.get(key);
	}
	
	
	/*
	 * ===========================================================
	 * 			*** TO STRING ***
	 * ===========================================================
	 */
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{ ");
		items.forEach((k, v) -> {
			builder.append('\"')
				.append(k)
				.append("\": ")
				.append(v);
			if (!k.equals(items.lastKey()))
				builder.append(',');
			builder.append(' ');
		});
		builder.append('}');
		return builder.toString();
	}
}
