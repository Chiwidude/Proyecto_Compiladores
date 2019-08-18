package SINTAXIS;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class minisql {
    private JPanel minisqlPanel;
    private JButton btn_rutain;
    private String ruta_SQL;

    public minisql() {
        btn_rutain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Seleccione el archivo sql");
                jfc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos sql","sql");
                jfc.addChoosableFileFilter(filter);
                int retvalue = jfc.showOpenDialog(null);
                if (retvalue == JFileChooser.APPROVE_OPTION){
                    File selectFile = jfc.getSelectedFile();
                    ruta_SQL = selectFile.getPath();
                }
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
}
