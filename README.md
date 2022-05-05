XGB's JSON Lib
==============

> ⚠️ Este documento ainda está em construção. 

**Autor:** Dr.XGB<br>
**Versão:** 1.1.3<br>
**Versão da compilação:** JDK 11.0.12<br>
**Data de criação:** 29 de Abril de 2022<br>
**Data de publicação:** 01 de Maio de 2022<br>
**Última atualização:** 05 de Maio de 2022<br>

Biblioteca feita em JAVA para analisar um conteúdo JSON e converter para objetos onde podem ser usados em suas aplicações JAVA.

***


Requerimentos
-------------

- JDK 11+ instalado. Caso não tenha esta versão ou Java instalado, você pode baixar através do [AdoptJDK](https://adoptopenjdk.net/) ou no próprio site da Oracle.
	- Escolha a versão 11 (LTS) ou uma versão superior.
	- Escolha a JVM Hotspot (se estiver em português aparecerá como  _Ponto de acesso_ .
- Sistemas operacionais: Windows, Linux e Mac.
Em breve esta biblioteca poderá ser usada com Maven.

***

Como utilizar
-------------

Para utilizar a biblioteca, basta chamar `JSON.parse()`para que o sistema faça a análise e converta em um objeto da interface `JSONCollection`, onde serão guardados todos os itens encontrados no arquivo JSON.
Até então, há 2 classes que implementam a interface:
1. `JSONObject`: Um objeto que utiliza a notação JSON, geralmente estão entre chaves `{}`.

**Exemplo:**<br> 
```json
{ "name": "Jubileu" , "age": 16 }
```

2. `JSONArray`: Objeto que representa um array contendo um conjunto de objetos JSON, geralmente estão entre colchetes `[]`.

**Exemplo:**<br>
```json
[{ "name":  "Jubileu", "age": 16 }, { "name": "Jacira", "age": 42 }]
```

O método aceita 3 tipos de entrada para analisar o JSON:

1. `String`		
```java
String str = "{ \"name\": \"John\", \"age\": 30 }";
JSONCollection json = JSON.parse(str);
```
2. `InputStream`
```java
try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("path/to/file.json")))
{
	JSONCollection json = JSON.parse(bis);
}
catch (IOExceprion e)
{
	// Tratamento da exceção
	e.printStackTrace();
}
```
3. `File`
```java
JSONCollection json = JSON.parse(new File("path/to/file.json"));
```
4. `URL` 
```java
JSONCollection json = JSON.parse(new URL("https://yesno.wtf/api"));
System.out.println(json);
```
A saída do código acima ficaria algo parecido com:
```json
{ "answer": "no", "forced": false, "image": "https://yesno.wtf/assets/no/21-05540164de4e3229609f106e468fa8e7.gif" }
```

Feito isso, você consegue consultar os elementos utilizando o método `get()`, fornecendo a chave do elemento como argumento.
```java
JSONCollection json = JSON.parse(new URL("https://yesno.wtf/api"));
System.out.printn(json.get("forced");
```
Resultado:
`false`
