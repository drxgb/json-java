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

- Java 11 instalado. Caso não tenha esta versão ou Java instalado, você pode baixar através do [AdoptJDK](https://adoptopenjdk.net/).
	- Escolha a versão 11 (LTS).
	- Escolha a JVM Hotspot (se estiver em português aparecerá como  _Ponto de acesso_ .

***

Como utilizar
-------------

O sistema aceita 3 tipos de dados de entrada para analisar o JSON:

1. String<br>
	**Exemplo:**
			
	`
	String str = "{ "name": "John", "age": 30 }";
	JSONCollection json = JSON.parse(str);
	`