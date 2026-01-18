package br.com.dio.ui.custom.input;

import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import static java.util.Objects.isNull;

import javax.swing.text.PlainDocument;

/* Só permitir alguns caracteres no input(1 a 9)garantindo a validação
 * PlainDocument  */

public class NumberTextLimit extends PlainDocument {

    private final List<String> NUMBERS = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
    
    //Insere os valores no textfield
    @Override
    public void insertString(final int offs, final String str, final AttributeSet a) 
    		                                                  throws BadLocationException {
        
    	//Verifica se é nulo. ou nao esta contido em NUMBERS retorna
    	if (isNull(str) || (!NUMBERS.contains(str))) return;

    	//Verifica o tamanho da nossa entrada:Tamanho atual+ 
        if (getLength() + str.length() <= 1){
            super.insertString(offs, str, a);
        }
    }

}
