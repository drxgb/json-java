package com.drxgb.json.io;

/**
 * Respons�vel por tratar uma leitura de um conte�do JSON.
 * @author Dr.XGB
 * @version 1.0.0
 */
public class JSONStream
{
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	/**
	 * Conte�do do JSON em forma de <code>String</code>.
	 */
	private String content;
	
	/**
	 * Posi��o do cursor de leitura do analisador.
	 */
	private Integer position;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */

	/**
	 * Cria uma cadeia de caracteres de um conte�do JSON.
	 * @param content
	 */
	public JSONStream(String content)
	{
		this.content = content;
		this.position = 0;
	}
	
	
	/*
	 * ===========================================================
	 * 			*** GETTERS ***
	 * ===========================================================
	 */

	/**
	 * Recebe a posi��o atual de leitura do conte�do.
	 * @return A posi��o de leitura.
	 */
	public Integer getPosition()
	{
		return position;
	}

	/**
	 * Recebe o conte�do sendo lido.
	 * @return O conte�do.
	 */
	public String getContent()
	{
		return content;
	}
	
	
	/*
	 * ===========================================================
	 * 			*** M�TODOS P�BLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Verifica se chegou ao final do texto.
	 * @return <code>true</code> se chegou ao fim ou <code>false</code> caso tenha mais conte�do a ser lido.
	 */
	public boolean finished()
	{
		return position >= content.length();
	}
	
	/**
	 * Avan�a a posi��o de leitura para o pr�ximo caractere.
	 */
	public void nextPosition()
	{
		++position;
	}
	
	/**
	 * L� o caractere da posi��o de leitura.
	 * @return O caracter da posi��o atual.
	 */
	public char current() throws IndexOutOfBoundsException
	{
		return content.charAt(position);
	}
}
