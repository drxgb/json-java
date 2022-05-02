package com.drxgb.json.exception;

/**
 * Trata as exce��es geradas por formato inv�lido de um JSON.
 * @author Dr.XGB
 * @version 1.0.0
 */
public class InvalidJSONException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Gera uma mensagem de exce��o apenas com texto.
	 * @param msg A mensagem de exce��o a ser exibida.
	 */
	public InvalidJSONException(String msg)
	{
		super(msg);
	}
	
	/**
	 * Gera uma mensagem de exce��o contendo uma posi��o de leitura.
	 * @param msg A mensagem de exce��o a ser exibida.
	 * @param index A posi��o de leitura onde a exce��o ocorreu.
	 */
	public InvalidJSONException(String msg, int index)
	{
		super(msg.replaceAll("\\{n\\}", String.valueOf(index)));
	}

}
