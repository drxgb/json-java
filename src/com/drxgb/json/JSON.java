package com.drxgb.json;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.drxgb.json.exception.InvalidJSONException;

/**
 * Representa a??es relacionadas a um JSON.
 * @author Dr.XGB
 * @version 1.1.3
 */
public abstract class JSON
{
	/**
	 * Analisa uma <code>String</code> e transforma em um objeto JSON.
	 * @param content Conte?do JSON em forma de texto.
	 * @return Um objeto JSON codificado.
	 * @throws InvalidJSONException Quando o formato JSON n?o ? v?lido.
	 */
	public static JSONCollection parse(String content) throws InvalidJSONException
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
	 * Mas antes, o sistema deve ler todo o conte?do de um arquivo JSON.
	 * @param is O arquivo <code>.json</code>.
	 * @return Um objeto JSON codificado.
	 * @throws IOException Caso ocorra algum erro na leitura do arquivo.
	 * @throws InvalidJSONException Quando o formato JSON n?o ? v?lido.
	 */
	public static JSONCollection parse(InputStream is) throws IOException, InvalidJSONException
	{
		String content = "";
		int i;
		while ((i = is.read()) != -1)
		{
			content += (char) i;
		}
		return parse(content);
	}
	
	
	/**
	 * Analisa uma <code>String</code> e transforma em um objeto JSON.
	 * Mas antes, o sistema deve ler todo o conte?do de um arquivo JSON.
	 * @param file O caminho do arquivo <code>.json</code>.
	 * @return Um objeto JSON codificado.
	 * @throws IOException Caso ocorra algum erro na leitura do arquivo.
	 * @throws InvalidJSONException Quando o formato JSON n?o ? v?lido.
	 */
	public static JSONCollection parse(File file) throws IOException, InvalidJSONException
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
	
	
	/**
	 * Faz uma requisi??o HTTP, recebendo do servidor na qual
	 * ? solicitada um conte?do JSON.
	 * @param url Caminho para qual a requisi??o deve solicitar um conte?do JSON.
	 * @return O conte?do JSON lido do corpo da resposta.
	 * @throws IOException Caso ocorra um erro na leitura do conte?do JSON.
	 * @throws InvalidJSONException Quando o formato JSON n?o ? v?lido.
	 */
	public static JSONCollection parse(URL url) throws IOException, InvalidJSONException
	{
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		if (connection != null)
		{
			String status = connection.getHeaderField("Status");
			if (status != null && status.matches("^[45]\\d{2}(.*)"))
				throw new InvalidJSONException("The response header Content-Type is not a JSON application.");
		}
		JSONCollection json = parse(connection.getInputStream());
		connection.disconnect();
		return json;
	}
}
