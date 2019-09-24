package SINTAXIS;
import java.io.File;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Analizador {
    public String path;
    private Queue<TKEN> TOKENS;
    public Analizador(String path){
        this.path = path;
        this.TOKENS = new LinkedList<>();
    }

    public String Analizar() throws IOException {
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
                case IDENTIFICADOR:
                    if(lexer.foundLine.length() <= 31) {
                        result.append(lexer.foundLine + " es un: " + token + " linea:" + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                        TOKENS.add(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    } else{
                        result.append(lexer.foundLine + " excede la longitud máxima del identificador,31. El identificador válido sería: "  +
                                lexer.foundLine.substring(0,31)+ " linea: " + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    }

                    break;
                case CADENA:
                    result.append(  lexer.foundLine+ " es una: " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    TOKENS.add(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
                case ASIGNACION: case SUMA: case RESTA: case MULTIPLICACION: case DIFERENCIA: case DIVISION: case MOD: case MAYOR: case MAYOR_IGUAL:
                case MENOR: case MENOR_IGUAL: case IGUALACION: case CONSTANTE_DECIMAL: case AND: case ARROBA: case COMA: case CORCHETEA: case PUNTO_COMA: case CONSTANTE_BOOLEANA:
                case CONSTANTE_ENTERA: case CORCHETEC: case CORCHETES: case LLAVEC: case PARENTESISC: case EXCLAMACION: case LLAVEA: case LLAVES: case NUMERAL: case NUMERALES:
                case PARENTESIS: case PARENTESISA: case PUNTO: case OR:
                    result.append(  lexer.foundLine+ " es un símbolo de : " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    TOKENS.add(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
                    default:
                    result.append(  lexer.foundLine+ " palabra reservada: " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    TOKENS.add(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
            }

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

    public void Sintaxis(){
        TKEN temp = new TKEN(null," ",0,0 ,0);
        Queue<TKEN> query = new LinkedList<>();
        while(!TOKENS.isEmpty()){
            temp = TOKENS.remove();
            while ((!temp.getValue().trim().equals(";") || !temp.getValue().trim().equals("GO")) && !TOKENS.isEmpty()){
                query.add(temp);
                temp = TOKENS.remove();
            }
            if (temp.getValue().trim().equals(";")){
                query.add(temp);
                if (TOKENS.peek().getValue().trim().equals("GO")){
                    query.add(TOKENS.remove());
                }
            }else if (temp.getValue().trim().equals("GO")){
                query.add(temp);
            }
            AnalisisSintactico(query);
            query.clear();
        }
    }

    private void AnalisisSintactico(Queue<TKEN> query){

    }



}
