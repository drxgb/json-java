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
	 * Aqui ser�o aceitos todos os tipos para manter a fidelidade do padr�o JSON,
	 * que foi feito, inicialmente, para representar objetos na linguagem JavaScript.
	 * Portanto, s�o todas as inst�ncias que herdam da classe <code>Object</code> podem
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
	 * Cria um JSON com conte�do.
	 * @param items Os itens do objeto a serem inseridos.
	 */
	public AbstractJSON(T items)
	{
		this.items = items;
	}
	
	
	/*
	 * ===========================================================
	 * 			*** M�TODOS P�BLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Recebe a cole��o de todos os itens do JSON.
	 * @return A cole��o de itens do JSON.
	 */
	public T getItems()
	{
		return items;
	}
}
