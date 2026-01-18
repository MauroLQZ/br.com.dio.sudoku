package br.com.dio.ui.custom.frame;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    public MainFrame(final Dimension dimension, final JPanel mainPanel){
        super("Sudoku");
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        
        //quando clicamos no X
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //nao deixar redimensionavel
        this.setResizable(false);
        //add o panel q/recebemos como parametro
        this.add(mainPanel);
    }

}
