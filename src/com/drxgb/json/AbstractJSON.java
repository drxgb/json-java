package com.drxgb.json;

public abstract class AbstractJSON<T> implements JSONCollection
{
	/*
	 * ===========================================================
	 * 			*** CONSTANTES ***
	 * ===========================================================
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	/**
	 * Os itens do objeto JSON.
	 * Aqui serão aceitos todos os tipos para manter a fidelidade do padrão JSON,
	 * que foi feito, inicialmente, para representar objetos na linguagem JavaScript.
	 * Portanto, são todas as instâncias que herdam da classe <code>Object</code> podem
	 * ser inseridas neste conjunto, contendo uma chave representada por uma <code>String</code>.
	 */
	protected T items;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	/**
	 * Cria um JSON vazio.
	 */
	public AbstractJSON()
	{}
	
	/**
	 * Cria um JSON com conteúdo.
	 * @param items Os itens do objeto a serem inseridos.
	 */
	public AbstractJSON(T items)
	{
		this.items = items;
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Recebe a coleção de todos os itens do JSON.
	 * @return A coleção de itens do JSON.
	 */
	public T getItems()
	{
		return items;
	}
}
