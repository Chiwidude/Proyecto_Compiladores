Proyecto_Compiladores
Francisco Alonzo 1197517

FUNCIONAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
El programa al iniciarlo, habilitar� un bot�n donde al presionarlo, mostrar� una ventana emergente, donde se debe seleccionar
el archivo .sql, el cual ser� analizado por el analizador l�xico. Una vez seleccionado, se habilitar� el bot�n Analizar, que al 
presionarlo, comenzar� con el proceso de generaci�n y compilaci�n de la clase Lexer, basado en el archivo .flex con las reglas
l�xicas y analiza el archivo .sql con la clase generada. As� mismo, genera un archivo .out, en la ruta donde se encuentra el archivo
.sql, y muestra en pantalla el resultado del an�lisis l�xico.


Explicaci�n
------------------------------------------------------------------------------------------------------------------------------------
El programa funciona correctamente, pues se tomaron en cuenta todas las restricciones que se presentaban en el enunciado del mismo,
tanto la validaci�n de errores y el an�lisis l�xico est�n calcadas en base a los requerimientos dados, as� programa cumple su funci�n,
sin problemas. Es robusto, pues al momento que se presente una condici�n erronea para el analizador, este mostrar� un mensaje con una des-
cripci�n del error, y continuar� con el an�lisis.



