package com.drxgb.json.exception;

/**
 * Trata as exceções geradas por formato inválido de um JSON.
 * @author Dr.XGB
 * @version 1.0.0
 */
public class InvalidJSONException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Gera uma mensagem de exceção apenas com texto.
	 * @param msg A mensagem de exceção a ser exibida.
	 */
	public InvalidJSONException(String msg)
	{
		super(msg);
	}
	
	/**
	 * Gera uma mensagem de exceção contendo uma posição de leitura.
	 * @param msg A mensagem de exceção a ser exibida.
	 * @param index A posição de leitura onde a exceção ocorreu.
	 */
	public InvalidJSONException(String msg, int index)
	{
		super(msg.replaceAll("\\{n\\}", String.valueOf(index)));
	}

}
