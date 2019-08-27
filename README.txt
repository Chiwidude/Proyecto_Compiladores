Proyecto_Compiladores
Francisco Alonzo 1197517

FUNCIONAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
El programa al iniciarlo, habilitará un botón donde al presionarlo, mostrará una ventana emergente, donde se debe seleccionar
el archivo .sql, el cual será analizado por el analizador léxico. Una vez seleccionado, se habilitará el botón Analizar, que al 
presionarlo, comenzará con el proceso de generación y compilación de la clase Lexer, basado en el archivo .flex con las reglas
léxicas y analiza el archivo .sql con la clase generada. Así mismo, genera un archivo .out, en la ruta donde se encuentra el archivo
.sql, y muestra en pantalla el resultado del análisis léxico.


Explicación
------------------------------------------------------------------------------------------------------------------------------------
El programa funciona correctamente, pues se tomaron en cuenta todas las restricciones que se presentaban en el enunciado del mismo,
tanto la validación de errores y el análisis léxico están calcadas en base a los requerimientos dados, así programa cumple su función,
sin problemas. Es robusto, pues al momento que se presente una condición erronea para el analizador, este mostrará un mensaje con una des-
cripción del error, y continuará con el análisis.



