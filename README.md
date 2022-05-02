XGB's JSON Reader
=================

**Autor:** Dr.XGB<br>
**Vers�o:** 1.0.0<br>
**Vers�o da compila��o:** JDK 11.0.12<br>
**Data de cria��o:** 29 de Abril de 2022<br>
**Data de publica��o:** 01 de Maio de 2022<br>

Biblioteca feita em JAVA para analisar um conte�do JSON e converter para objetos JAVA.

***


Requerimentos
-------------

- Java 11 instalado. Caso n�o tenha esta vers�o ou Java instalado, voc� pode baixar atrav�s do [AdoptJDK](https://adoptopenjdk.net/).
	- Escolha a vers�o 11 (LTS).
	- Escolha a JVM Hotspot (se estiver em portugu�s aparecer� como  _Ponto de acesso_ .

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