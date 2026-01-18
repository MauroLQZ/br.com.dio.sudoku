package br.com.dio.ui.custom.input;

import br.com.dio.model.Space;
import br.com.dio.service.EventEnum;
import br.com.dio.service.EventListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;
import java.awt.Font;

import static br.com.dio.service.EventEnum.CLEAR_SPACE;
import static java.awt.Font.PLAIN;

/* Definimos nosso componente de texto q esteja pronto p/ouvir nosso evento
 * Implementando EventListener
 * */
public class NumberText extends JTextField implements EventListener {
   
	//Espaço q/utilizamos na lista de lista
    private final Space space;
    
    //Construtor c/instancia de espaço
    public NumberText(final Space space) {
        this.space = space;
        var dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        
        //passamos a configuração TextLimit
        this.setDocument(new NumberTextLimit());
        //permite edição de numero nao fixo
        this.setEnabled(!space.isFixed());
        
        //se espaço for fixo escreve o numero fixo
        if (space.isFixed()){
            this.setText(space.getActual().toString());
        }
        
        //Qdo agente colocar o campo de texto na tela e digitarmos, esse campo pegar
        //e propagar essa mudança
        //
        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(final DocumentEvent e) {
            	//valida o componente
            	changeSpace();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
            	//valida o componente
            	changeSpace();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
            	//valida o componente
                changeSpace();
            }

            //Para todos os casos vai alterar o nosso espaço q/recebemos p/manipular
            //
            private void changeSpace(){
            	//o text q/esta no nosso componente estiver vazio
                if (getText().isEmpty()){
                	//limpamos o espaço
                    space.clearSpace();
                    return;
                }
                //senao convertemos o texto em Integer
                space.setActual(Integer.parseInt(getText()));
            }

        });
    }

    //Implementação do EventListener, pronto p/fazer a limpeza
    @Override
    public void update(final EventEnum eventType) {
    	//verifica se é o evento q/estamos esperando e se o componente esta habilitado
        if (eventType.equals(CLEAR_SPACE) && (this.isEnabled())){
        	//se as duas condiçoes forem atendidas limpa todos os componentes
            this.setText("");
        }
    }
}

