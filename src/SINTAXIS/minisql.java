package SINTAXIS;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class minisql extends javax.swing.JFrame{
    private JPanel minisqlPanel;
    private JButton btn_rutain;
    private JButton btn_Analizar;
    private JTextArea showArea;
    private String ruta_SQL;
    private String path = "C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/lexer.flex";

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
        }
    }
    private void Btn_analizar(ActionEvent evt){
        GenerarLexer(path);
        Analizador analizador = new Analizador(ruta_SQL);
        try {
           showArea.setText(analizador.Analizar());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void GenerarLexer(String path){
        File lexRules = new File(path);
         String temp_path = "C:/PROYECTO_COMPILADORES/PROYECTO/src/SINTAXIS/Lexer.java";
         File temp = new File(temp_path);
         if(temp.exists()){
             if(temp.delete()){
                 jflex.Main.generate(lexRules);
             }
         }else{
             jflex.Main.generate(lexRules);
         }
    }
}
