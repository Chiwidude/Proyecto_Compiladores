Proyecto_Compiladores
Francisco Alonzo 1197517

FUNCIONAMIENTO (MANEJO DE ERRORES)
-------------------------------------------------------------------------------------------------------------------------------------
El manejo de errores del analizador sintáctico, es de la siguiente forma:
Al analizar un token, y este no es el token que se esperaba, el error se maneja através del método raiseERROR, el cual crea un mensaje, donde se indica, el token que se recibió y el o los tokens que se esperaban que fueran. Se agrega a un StringBuilder, se desencola todos los tokens siguientes, hasta encontrar un punto y coma. Luego regresa a la producción Inicial, y continúa el análisis. Al final del análisis se muestra un cuadro de diálogo, con todos los errores que se encontraron de haber existido o muestra el mensaje "ARCHIVO SINTÁCTICAMENTE CORRECTO#


Explicación
------------------------------------------------------------------------------------------------------------------------------------
El programa funciona correctamente, pues se tomaron en cuenta todas las restricciones que se presentaban en el enunciado del mismo,
tanto la validación de errores y el análisis léxico están calcadas en base a los requerimientos dados, así programa cumple su función,
sin problemas. Es robusto, pues al momento que se presente una condición erronea para el analizador, este mostrará un mensaje con una des-
cripción del error, y continuará con el análisis.



