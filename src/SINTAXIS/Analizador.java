package SINTAXIS;
import java.io.File;
import java.io.*;

public class Analizador {
    public String path;
    public Analizador(String path){
        this.path = path;
    }

    public String Analizar() throws IOException {
        Reader reader   = new BufferedReader(new FileReader(this.path));
        Lexer lexer = new Lexer(reader);
        StringBuilder result = new StringBuilder();

        while(true){
            Token token = lexer.yylex();
            if(token == null){
                result.append("FIN LECTURA");
                return result.toString();
            }
            switch (token){
                case ERROR:
                    result.append( token + ": " + lexer.foundLine + " Regla no definida linea: " + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    break;
                case RESERVADA:
                    result.append(  lexer.foundLine+ " es una palabra: " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    break;
                case IDENTIFICADOR:
                    result.append(  lexer.foundLine+ " es un: " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    break;

            }
        }


    }
}
