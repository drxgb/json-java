package com.drxgb.json;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.drxgb.json.exception.InvalidJSONException;

/**
 * Representa ações relacionadas a um JSON.
 * @author Dr.XGB
 * @version 1.0.0
 */
public abstract class JSON
{
	/**
	 * Analisa uma <code>String</code> e transforma em um objeto JSON.
	 * @param content Conteúdo JSON em forma de texto.
	 * @return Um objeto JSON codificado.
	 */
	public static JSONCollection parse(String content)
	{
		Parser parser = new Parser(content);
		try
		{
			return parser.read();
		}
		catch (Exception e)
		{
			String msg = (e instanceof InvalidJSONException) ?
					e.getMessage() + " near at index {n}." :
					"Failed to read JSON: " + e.getMessage();
			throw new InvalidJSONException(msg, parser.getStream().getPosition());
		}
	}
	
	
	/**
	 * Analisa uma <code>String</code> e transforma em um objeto JSON.
	 * Mas antes, o sistema deve ler todo o conteúdo de um arquivo JSON.
	 * @param is O arquivo <code>.json</code>.
	 * @return Um objeto JSON codificado.
	 * @throws IOException Caso ocorra algum erro na leitura do arquivo.
	 */
	public static JSONCollection parse(InputStream is) throws IOException
	{
		String content = "";
		while (is.available() > 0)
		{
			content += (char) is.read();
		}
		return parse(content);
	}
	
	
	/**
	 * Analisa uma <code>String</code> e transforma em um objeto JSON.
	 * Mas antes, o sistema deve ler todo o conteúdo de um arquivo JSON.
	 * @param file O caminho do arquivo <code>.json</code>.
	 * @return Um objeto JSON codificado.
	 * @throws IOException Caso ocorra algum erro na leitura do arquivo.
	 */
	public static JSONCollection parse(File file) throws IOException
	{
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)))
		{
			return parse(bis);
		}
		catch (IOException e)
		{
			throw e;
		}
	}
}
