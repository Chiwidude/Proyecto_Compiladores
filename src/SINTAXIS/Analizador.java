package SINTAXIS;
import java_cup.runtime.Symbol;

import java.io.File;
import java.io.*;
import java.nio.file.Files;

public class Analizador {
    public String path;
    public Analizador(String path){
        this.path = path;
    }

    public String Analizar_Lexico() throws IOException {
        Reader reader   = new BufferedReader(new FileReader(this.path));
        Lexer lexer = new Lexer(reader);
        StringBuilder result = new StringBuilder();

        while(true){
            Token token = lexer.yylex();
            if(token == null){
                result.append("FIN LECTURA");
                GenerarOut(result.toString());
                return result.toString();
            }
            switch (token){
                case ERROR:
                    if(lexer.foundLine.contains("'")){
                        result.append( token + ": " + lexer.foundLine + " <- Cadena mal definida linea: " + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    }else if(lexer.foundLine.contains(("/*")))
                    {
                        result.append( token + ": " + lexer.foundLine + " <- Comentario con */ faltante linea: " + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    }
                    else{
                        result.append( token + ": " + lexer.foundLine + " <- Regla no definida linea: " + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    }

                    break;
                case ERROR_DECIMAL:
                    result.append( token + ": " + lexer.foundLine + " número decimal mal escrito linea: " + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    break;
                default:
                    result.append(  lexer.foundLine+ " es una palabra: " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    break;
                case IDENTIFICADOR:
                    if(lexer.foundLine.length() <= 31) {
                        result.append(lexer.foundLine + " es un: " + token + " linea:" + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    } else{
                        result.append(lexer.foundLine + " excede la longitud máxima del identificador,31. El identificador válido sería: "  +
                                lexer.foundLine.substring(0,31)+ " linea: " + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    }
                    break;
                case CADENA:
                    result.append(  lexer.foundLine+ " es una: " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    break;
                case ASIGNACION: case SUMA: case RESTA: case MULTIPLICACION: case DIFERENCIA: case DIVISION: case MOD: case MAYOR: case MAYOR_IGUAL:
                case MENOR: case MENOR_IGUAL: case IGUALACION: case CONSTANTE_DECIMAL: case AND: case ARROBA: case COMA: case CORCHETEA: case PUNTO_COMA: case CONSTANTE_BOOLEANA:
                case CONSTANTE_ENTERA: case CORCHETEC: case CORCHETES: case LLAVEC: case PARENTESISC: case EXCLAMACION: case LLAVEA: case LLAVES: case NUMERAL: case NUMERALES:
                case PARENTESIS: case PARENTESISA: case PUNTO: case OR:
                    result.append(  lexer.foundLine+ " es un símbolo de : " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    break;
            }

        }


    }
    public void Analizar_Sintactico () throws IOException {
        File file = new File(path);
        String st = new String(Files.readAllBytes(file.toPath()));
        Sintactic sintactic = new Sintactic(new LexerJcup(new StringReader(st)));
        try {
          sintactic.parse();
        } catch (Exception e) {
            Symbol s = sintactic.getSymbol();
        }
    }
    private void GenerarOut(String result){
        StringBuilder newpath = new StringBuilder(path.replace(".sql",".out"));
        File out = new File(newpath.toString());
        try {
            FileWriter fileWriter = new FileWriter(out);
            fileWriter.write(result);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
