package br.com.dio.ui.custom.panel;

import java.awt.Dimension;

import javax.swing.JPanel;

/* Painel principal onde guarda todos os nossos componentes */
public class MainPanel extends JPanel {

    public MainPanel(final Dimension dimension){
        this.setSize(dimension);
        this.setPreferredSize(dimension);
    }
}
