package com.drxgb.json.io;

/**
 * Responsável por tratar uma leitura de um conteúdo JSON.
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
	 * Conteúdo do JSON em forma de <code>String</code>.
	 */
	private String content;
	
	/**
	 * Posição do cursor de leitura do analisador.
	 */
	private Integer position;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */

	/**
	 * Cria uma cadeia de caracteres de um conteúdo JSON.
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
	 * Recebe a posição atual de leitura do conteúdo.
	 * @return A posição de leitura.
	 */
	public Integer getPosition()
	{
		return position;
	}

	/**
	 * Recebe o conteúdo sendo lido.
	 * @return O conteúdo.
	 */
	public String getContent()
	{
		return content;
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Verifica se chegou ao final do texto.
	 * @return <code>true</code> se chegou ao fim ou <code>false</code> caso tenha mais conteúdo a ser lido.
	 */
	public boolean finished()
	{
		return position >= content.length();
	}
	
	/**
	 * Avança a posição de leitura para o próximo caractere.
	 */
	public void nextPosition()
	{
		++position;
	}
	
	/**
	 * Lê o caractere da posição de leitura.
	 * @return O caracter da posição atual.
	 */
	public char current() throws IndexOutOfBoundsException
	{
		return content.charAt(position);
	}
}
