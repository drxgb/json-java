package com.drxgb.json;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

import com.drxgb.json.exception.InvalidJSONException;
import com.drxgb.json.io.JSONStream;

/**
 * <p>Utilizada para interpretar um conteúdo de texto para ser codificado a um objeto JSON.</p>
 * @author Dr.XGB
 * @version 1.1.3
 */
public class Parser
{
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */

	/**
	 * Cadeia de caracteres do conteúdo do JSON.
	 */
	private JSONStream stream;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTANTES ***
	 * ===========================================================
	 */
	
	/**
	 * Representa o valor <code>null</code>.
	 */
	public static final String NULL = "null";
	
	/**
	 * Representa o tipo NaN (Not a Number).
	 */
	public static final String NAN = "NaN";
	
	/**
	 * Expressão regular para comparar strings.
	 */
	private static final String STRING = "^\"(.*)\"$";
	
	/**
	 * Expressão regular para comparar valores inteiros;
	 */
	private static final String INTEGER = "-?\\d+";
	
	/**
	 * Expressão regular para comparar valores reais.
	 */
	private static final String FLOAT = "-?\\d*.?\\d+";
	
	/**
	 * Expressão regular para comparar valores lógicos.
	 */
	private static final String BOOLEAN = "true|false";
	
	/**
	 * Expressão regular para comparar objetos.
	 */
	private static final String OBJECT = "^\\{(.*)\\}$";
	
	/**
	 * Expressão regular para comparar arrays
	 */
	private static final String ARRAY = "^\\[(.*)\\]$";
	
	/**
	 * Expressão regular para detectar um array de JSON.
	 */
	private static final String JSON_ARRAY = "^\\[\\s*\\{(.*)\\}\\s*\\]$";
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */

	/**
	 * Prepara o analisador.
	 * @param content O conteúdo do JSON em forma de <code>String</code>.
	 */
	public Parser(String content)
	{	
		this.stream = new JSONStream(content.replaceAll("\r", ""));
	}
	
	/*
	 * ===========================================================
	 * 			*** GETTERS ***
	 * ===========================================================
	 */
	
	/**
	 * Recebe o conteúdo JSON que está sendo analisado.
	 * @return O conteúdo do JSON.
	 */
	public JSONStream getStream()
	{
		return stream;
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */	
	
	/**
	 * Lê o conteúdo recebido pelo analisador, transformando em um objeto JSON.
	 * @return O objeto JSON codificado. Se o texto não for reconhecido, será retornado <code>null</code>.
	 * @throws Exception Caso ocorra algum erro na interpretação do JSON.
	 */
	public JSONCollection read() throws Exception
	{
		String content = stream.getContent();
		if (checkType(content, OBJECT))
		{
			JSONCollection obj = new JSONObject();
			String key, value;
			stream.nextPosition();
			while (!stream.finished())
			{
				skipSpaces(stream);
				key = findKey(stream);
				if (key != null)
				{
					stream.nextPosition();
					value = findValue(stream);
					if (value == null)
						throw new InvalidJSONException("Value not found at index {n}.", stream.getPosition());
					insertValue(key, value, (k, v) -> obj.insert(k, v));
				}
				else
				{
					stream.nextPosition();
				}
			}
			return obj;
		}
		else if (checkType(content, JSON_ARRAY))
		{
			return new JSONArray(stringArrayToList(content));
		}
		return null;
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ESTÁTICOS ***
	 * ===========================================================
	 */

	/**
	 * Interpreta um string em sua forma literal
	 * @param stream Conteúdo do texto a ser verificado.
	 * @return O valor literal da <code>String</code>.
	 * @throws InvalidJSONException Caso o valor não estaja separado adequadamente no JSON.
	 */
	public static String parseString(JSONStream stream) throws InvalidJSONException
	{
		String out = "";
		try
		{
			char ch;
			int quoteCount = 0;
			while (!stream.finished())
			{
				ch = stream.current();
				stream.nextPosition();
				out += ch;
				if (ch == '\"')
				{
					if (++quoteCount >= 2)
					{
						skipSpaces(stream);
						if (isSeparator(stream.current()))
							break;
						throw new InvalidJSONException("Comma or closed Bracket expected");
					}
				}
			}
		}
		catch (IndexOutOfBoundsException e)	{}
		return out;
	}
	
	/**
	 * Interpreta um valor numérico durante a leitura.
	 * Neste caso, incluem-se os <code>Integer</code>s e os <code>Float</code>s ou <code>Double</code>s.
	 * @param stream Conteúdo do texto a ser verificado.
	 * @return O valor numérico representado em uma <code>String</code>.
	 */
	public static String parseNumber(JSONStream stream)
	{
		String num = "";
		try
		{
			char ch;
			int decimalCount = 0;
			while (!stream.finished())
			{
				ch = stream.current();
				stream.nextPosition();
				if (isBlankSpace(ch))
				{
					skipSpaces(stream);
					continue;
				}
				if (isSeparator(ch))
					return num;
				num += ch;
				if (ch == '.')
					++decimalCount;
				if (!(ch >= '0' && ch <= '9' || (ch == '.' && decimalCount < 2)))
					return NAN;
			}
		}
		catch (IndexOutOfBoundsException e) {}
		return num;
	}
	
	/**
	 * Interpreta um valor booleano durante a leitura.
	 * @param stream Conteúdo do texto a ser verificado.
	 * @return O valor booleano representado em uma <code>String</code>.
	 */
	public static String parseBoolean(JSONStream stream)
	{
		String val = "";
		try
		{
			char ch;
			while (!stream.finished())
			{
				ch = stream.current();
				stream.nextPosition();
				if (isBlankSpace(ch))
				{
					skipSpaces(stream);
					continue;
				}
				if (isSeparator(ch))
					break;
				val += ch;
			}			
		}
		catch (IndexOutOfBoundsException e) {}
		if (val.matches(BOOLEAN))
				return val;
			return null;
	}
	
	/**
	 * Interpreta um objeto durante a leitura. Este método também serve encontrar arrays ao
	 * fornecer o segundo argumento (<code>isArray</code>) com o valor <code>true</code>.
	 * @param stream Conteúdo do texto a ser verificado.
	 * @param isArray Se o objeto será um array ou, literalmente, um objeto.
	 * @return O objeto representado em um <code>String</code>.
	 */
	public static String parseObject(JSONStream stream, boolean isArray)
	{
		String obj = "";
		try
		{
			char ch;
			int depth = 0;
			boolean quoteOpened = false;
			final char blockOpen = isArray ? '[' : '{',
				blockClose = isArray ? ']' : '}';
			while (!stream.finished())
			{
				ch = stream.current();
				stream.nextPosition();
				obj += ch;
				if (ch == blockOpen && !quoteOpened)
					++depth;
				else if (ch == blockClose && !quoteOpened)
				{
					if (--depth < 1)
					{
						skipSpaces(stream);
						if (isSeparator(stream.current()))
							return obj;
						break;
					}
				}
				else if (ch == '\"')
					quoteOpened = !quoteOpened;
			}
			return null;
		}
		catch (IndexOutOfBoundsException e)
		{
			return obj;
		}
	}
	
	/**
	 * Interpreta um objeto durante a leitura.
	 * @param stream Conteúdo do texto a ser verificado.
	 * @return O objeto representado em um <code>String</code>.
	 */
	public static String parseObject(JSONStream stream)
	{
		return parseObject(stream, false);
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PRIVADOS ESTÁTICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Verifica se a string representa um valor booleano.
	 * @param value A expressão em forma de <cdoe>String</code>.
	 * @return Se o valor representa um booleano.
	 */
	private static boolean isBoolean(String value)
	{
		try
		{
			return value.substring(0, 4).equals("true") || value.substring(0, 5).equals("false");
		}
		catch (StringIndexOutOfBoundsException e)
		{
			return false;
		}
	}
	
	/**
	 * Detecta a chave do par de um objeto JSON.
	 * @param stream Conteúdo do texto a ser verificado.
	 * @return O nome da chave ou <code>null</code> se a mesma não foi encontrada.
	 * @throws InvalidJSONException Caso encontre alguma falha na gerção da chave.
	 */
	private static String findKey(JSONStream stream) throws InvalidJSONException
	{
		String key = "";
		skipSpaces(stream);
		if (!stream.finished())
		{
			if (stream.current() == '\"')
			{
				char ch;
				while (!stream.finished())
				{
					stream.nextPosition();
					ch = stream.current();
					if (ch != '\"')
					{
						key += ch;
					}
					else
						break;
				}
				stream.nextPosition();
				skipSpaces(stream);
				if (stream.current() == ':')
					return key;
				else
					throw new InvalidJSONException("Missing \":\" after key name");
			}
		}
		return null;
	}
	
	/**
	 * Detecta o valor de um par do objeto JSON.
	 * @param stream Conteúdo do texto a ser verificado.
	 * @return O conteúdo do valor ou <code>null</code> se o mesmo não foi encontrado.
	 */
	private static String findValue(JSONStream stream)
	{
		skipSpaces(stream);
		if (!stream.finished())
		{
			int pos = stream.getPosition();
			char ch = stream.current();
			if (ch == '\"')
				return parseString(stream);
			if ((ch >= '0' && ch <= '9') || ch == '.')
				return parseNumber(stream);
			if (isBoolean(stream.getContent().substring(pos)))
				return parseBoolean(stream);
			if (ch == '{')
				return parseObject(stream);
			if (ch == '[')
				return parseObject(stream, true);
			if (stream.getContent().substring(pos, pos + 4).equals("null"))
				return "null";
		}
		return null;
	}
	
	/**
	 * Analisa um texto que representa um array, entregando uma list de objetos.
	 * @param arr Um array só que representado como texto.
	 * @return Uma lista de objetos extraídos do texto.
	 * @throws Exception Caso ocorra algum erro na análise.
	 */
	private static List<Object> stringArrayToList(String arr) throws Exception
	{
		arr = arr.trim();
		arr = arr.substring(1, arr.length() - 1);

		List<Object> list = new ArrayList<Object>();
		JSONStream stream = new JSONStream(arr);
		
		while (!stream.finished())
		{
			insertValue(findValue(stream), (k, v) -> list.add(v));
			stream.nextPosition();
		}

		return list;
	}
	
	/**
	 * Verifica se o caractere é um separador de pares de um objeto JSON (<code>,</code>) ou o fecho do objeto (<code>}</code>).
	 * @param ch Caractere a ser verificado.
	 * @return Se o separador foi encontrado ou um fecho.
	 */
	private static boolean isSeparator(char ch)
	{
		return ch == ',' || ch == '}';
	}
	
	/**
	 * Percorre pelo texto pulando todos os espaços vazios.
	 * @param stream Conteúdo do texto a ser verificado.
	 * @param pos Posição do texto a ser verificado.
	 * Isso inclui as tabulações (<code>\t</code>) e quebras de linha (<code>\r</code> ou <code>\n</code>).
	 */
	private static void skipSpaces(JSONStream stream)
	{
		while (!stream.finished())
		{
			if (!isBlankSpace(stream.current()))
				break;
			stream.nextPosition();
		}
	}

	/**
	 * Verificar se o caractere é um espaço vazio.
	 * @param ch Caractere a ser analisado.
	 * @return <code>true</code> se há um espaço em branco.
	 * Isso inclui as tabulações (<code>\t</code>) e quebras de linha (<code>\r</code> ou <code>\n</code>).
	 */
	private static boolean isBlankSpace(char ch)
	{
		return ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n';
	}
	
	/**
	 * Verificar se o valor é um tipo dado por argumento.
	 * @param value O valor a ser verificado.
	 * @param type O tipo que deve ser verificado.
	 * @return Se o valor realmente é o tipo do mesmo solicitado.
	 */
	private static boolean checkType(String value, String type)
	{
		try
		{
			if (
					type == STRING	||
					type == OBJECT	|| 
					type == ARRAY	||
					type == JSON_ARRAY
				)
				return Pattern.compile(type, Pattern.DOTALL).matcher(value).matches();
			else if (
						type == INTEGER	||
						type == FLOAT	||
						type == BOOLEAN
					)
				return value.matches(type);
			else if (
						type == NULL ||
						type == NAN
					)
				return value.equals(type);
		}
		catch (Exception e)	{}
		return false;
	}
	
	/**
	 * Realiza a inserção de uma elemento de tipo chave/valor.
	 * @param key A chave a ser inserida.
	 * @param value O valor do elemento.
	 * @param insert Ação para inserção do elemento.
	 * @throws Exception Caso ocorra alguma falha na inserção do valor.
	 */
	private static void insertValue(
			String key,
			String value,
			BiConsumer<String, Object> insert
	)
			throws Exception
	{
		if (checkType(value, STRING))
			insert.accept(key, value.substring(1, value.length() - 1));
		else if (checkType(value, INTEGER))
			insert.accept(key, Integer.valueOf(value));
		else if (checkType(value, FLOAT))
			insert.accept(key, Double.valueOf(value));
		else if (checkType(value, BOOLEAN))
			insert.accept(key, Boolean.valueOf(value));
		else if (checkType(value, NULL))
			insert.accept(key, null);
		else if (checkType(value, NAN))
			insert.accept(key, NAN);
		else if (checkType(value, ARRAY))
			insert.accept(key, stringArrayToList(value));
		else if (checkType(value, OBJECT))
		{
			Parser parser = new Parser(value);
			insert.accept(key, parser.read());
		}
	}
	
	/**
	 * Realiza uma inserção de um elemento com apenas um valor.
	 * @param value O valor do elemento.
	 * @param insert Ação para inserção do elemento.
	 * @throws Exception Caso ocorra alguma falha na inserção do valor.
	 */
	private static void insertValue(String value, BiConsumer<String, Object> insert) throws Exception
	{
		insertValue(null, value, insert);
	}
}
