package SINTAXIS;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class minisql extends javax.swing.JFrame{
    private JPanel minisqlPanel;
    private JButton btn_rutain;
    private JButton btn_Analizar;
    private JTextArea showArea;
    private JTextArea textArea1;
    private String ruta_SQL;
    private String path = "C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/lexer.flex";
    private String path_lcup = "C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/LexerJcup.flex";
    private String [] Sint_path = {
            "-parser",
            "Sintactic",
            "C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/Sintactic.cup"
    };
    public minisql() {
        btn_Analizar.setEnabled(false);
        btn_rutain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Btn_rutainAction(e);
            }

        });
        btn_Analizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Btn_analizar(e);
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("minisql");
        frame.setContentPane(new minisql().minisqlPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    private void Btn_rutainAction (ActionEvent evt) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Seleccione el archivo sql");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos sql", "sql");
        jfc.addChoosableFileFilter(filter);
        int retvalue = jfc.showOpenDialog(null);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
            File selectFile = jfc.getSelectedFile();
            ruta_SQL = selectFile.getPath();
            btn_Analizar.setEnabled(true);
            showArea.setText("");
        }
    }
    private void Btn_analizar(ActionEvent evt){
        try {
            GenerarAnalizadores(path,path_lcup,Sint_path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizador analizador = new Analizador(ruta_SQL);
        try {
           showArea.setText(analizador.Analizar_Lexico());
           analizador.Analizar_Sintactico();
            textArea1.setText(analizador.getErrors_St());
           btn_Analizar.setEnabled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void GenerarAnalizadores(String path, String path1, String[] paths) throws Exception {
        File lexRules = new File(path);
         String temp_path = "C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/Lexer.java";
         String temp_pathCup = "C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/LexerJcup.java";
         String symPath = "C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/sym.java";
         String sintPath = "C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/Sintactic.java";
         File temp = new File(temp_path);
         if(temp.exists()){
             if(temp.delete()){
                 jflex.Main.generate(lexRules);
             }
         }else{
             jflex.Main.generate(lexRules);
         }
         lexRules = new File(path1);
         temp = new File(temp_pathCup);
         if(temp.exists()){
             if(temp.delete()){
                 jflex.Main.generate(lexRules);
             }
         }else{
                jflex.Main.generate(lexRules);
         }

         java_cup.Main.main(paths);
         temp = new File(symPath);
         if(temp.exists()) {
            if(temp.delete()) {
                Files.move(
                        Paths.get("C:/PROYECTO_COMPILADORES/PROYECTO/sym.java"),
                        Paths.get("C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/sym.java")
                );
            }
         }else{
             Files.move(
                     Paths.get("C:/PROYECTO_COMPILADORES/PROYECTO/sym.java"),
                     Paths.get("C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/sym.java")
             );
         }
         temp = new File(sintPath);
         if(temp.exists()) {
             if(temp.delete()) {
                 Files.move(
                         Paths.get("C:/PROYECTO_COMPILADORES/PROYECTO/Sintactic.java"),
                         Paths.get("C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/Sintactic.java")
                 );
             }
         }else{
             Files.move(
                     Paths.get("C:/PROYECTO_COMPILADORES/PROYECTO/sym.java"),
                     Paths.get("C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/sym.java")
             );
         }

    }
}
