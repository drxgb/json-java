XGB's JSON Reader
=================

**Autor:** Dr.XGB<br>
**Versão:** 1.0.0<br>
**Versão da compilação:** JDK 11.0.12<br>
**Data de criação:** 29 de Abril de 2022<br>
**Data de publicação:** 01 de Maio de 2022<br>

Biblioteca feita em JAVA para analisar um conteúdo JSON e converter para objetos JAVA.

***


Requerimentos
-------------

- JDK 11 instalado. Caso não tenha esta versão ou Java instalado, você pode baixar através do [AdoptJDK](https://adoptopenjdk.net/).
	- Escolha a versão 11 (LTS).
	- Escolha a JVM Hotspot (se estiver em português aparecerá como  _Ponto de acesso_ .
- Sistemas operacionais: Windows, Linux e Mac.

***

Como utilizar
-------------

Para utilizar a biblioteca, basta chamar `JSON.parse()`para que o sistema faça a análise e converta em um objeto da interface `JSONCollection`, onde serão guardados todos os itens encontrados no arquivo JSON.
Até então, há 2 classes que implementam a interface:
1. `JSONObject`: Um objeto que utiliza a notação JSON, geralmente estão entre chaves `{}`.
**Exemplo:**<br> 
`{ "name": "Jubileu , "age": 16 }`

2. `JSONArray`: Objeto que representa um array contendo um conjunto de objetos JSON, geralmente estão entre colchetes `[]`.
**Exemplo:**<br>
`[{ "name":  "Jubileu", "age": 16 }, { "name": "Jacira", "age": 42 }]`

O método aceita 3 tipos de entrada para analisar o JSON:

1. `String`<br>
**Exemplo:**			
```java
String str = "{ \"name\": \"John\", \"age\": 30 }";
JSONCollection json = JSON.parse(str);
```
2. `InputStream`<br>
**Exemplo:**
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
3. `File`<br>
**Exemplo:**
```java
JSONCollection  json = JSON.parse(new File("path/to/file.json"));
```

Na próxima versão será adicionada entrada do tipo `URL` para recuperar o conteúdo JSON através de uma requisição via REST.
