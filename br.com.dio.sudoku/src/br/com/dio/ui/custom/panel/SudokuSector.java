package br.com.dio.ui.custom.panel;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import br.com.dio.ui.custom.input.NumberText;

import static java.awt.Color.black;

/* Setor onde agente coloca quadradinhos de 9 em 9- vai representar os 9 quadrados da tela
 * 
 * */
public class SudokuSector extends JPanel {

    public SudokuSector(final List<NumberText> textFields){
    	//dimensao do painel
        var dimension = new Dimension(170, 170);
        this.setSize(dimension);
        //renderiza o nosso componente
        this.setPreferredSize(dimension);
        
        //borda = cor - grossura da linha - roundedCorners(destacar bem dividido seções
        this.setBorder(new LineBorder(black, 2, true));
        this.setVisible(true);
        
        //ADD os campos de texto q/passamos no parametro p/o SudokuSector 
        //JPanel(aparecerem dentro dele)
        textFields.forEach(this::add);
    }

}
