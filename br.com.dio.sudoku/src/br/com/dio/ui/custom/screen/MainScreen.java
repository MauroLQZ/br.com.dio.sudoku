package br.com.dio.ui.custom.screen;

import br.com.dio.model.Space;
import br.com.dio.service.BoardService;
import br.com.dio.service.EventEnum;
import br.com.dio.service.NotifierService;
import br.com.dio.ui.custom.button.CheckGameStatusButton;
import br.com.dio.ui.custom.button.FinishGameButton;
import br.com.dio.ui.custom.button.ResetButton;
import br.com.dio.ui.custom.frame.MainFrame;
import br.com.dio.ui.custom.input.NumberText;
import br.com.dio.ui.custom.panel.MainPanel;
import br.com.dio.ui.custom.panel.SudokuSector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.dio.service.EventEnum.CLEAR_SPACE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

/* Classe q/trabalha com a composição da nossa tela 
 * Classe q/implementa nossa interface
 * */
public class MainScreen {
    private final static Dimension dimension = new Dimension(600, 600);

    private final BoardService boardService;
    
    //instanciamos nosso serviço
    private final NotifierService notifierService;

    private JButton checkGameStatusButton;
    private JButton finishGameButton;
    private JButton resetButton;

    //Construtor q/recebe um map como parametro
    public MainScreen(final Map<String, String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }
    
    /** AGORA COMEÇAMOS COMPOR NOSSA TELA */
    
    //Colocar os espaços no Sudoku - Montando as interfaces
    public void buildMainScreen(){
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        
        //for vai atuar nas linhas da lista,fazendo step de 3 em 3(setores 9 nº)
        for (int r = 0; r < 9; r+=3) {
        	//ultima linha q/ele vai atuar +2
            var endRow = r + 2;
            
        //c de coluna seguindo a mesma logica
            for (int c = 0; c < 9; c+=3) {
                var endCol = c + 2;
                var spaces = getSpacesFromSector(boardService.getSpaces(), c, endCol, 
                		                                r, endRow);
                		                                    
                JPanel sector = generateSection(spaces);
                //montamos nosso space
                mainPanel.add(sector);
            }
        }
        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    //Obter nossos espaços,vai retornar uma lista de espaços
    private List<Space> getSpacesFromSector(final List<List<Space>> spaces,
                                            final int initCol, final int endCol,
                                            final int initRow, final int endRow){
    	//Lista de espaços
        List<Space> spaceSector = new ArrayList<>();
        
        //Trabalha com as linhas
        for (int r = initRow; r <= endRow; r++) {
        
        	//trabalha com as colunas
        	for (int c = initCol; c <= endCol; c++) {
        		//add na lista de espaço a coluna e linha
                spaceSector.add(spaces.get(c).get(r));
            }
        }
        //retorna a lista de espaço
        return spaceSector;
    }

    //Gerando a seção c/nosso panel-c/argunento c/todos espaços da seção
    //Metoto q/vai devolver nosso panel-vai receber uma lista(9 elementos nossos espaços)
    private JPanel generateSection(final List<Space> spaces){
        List<NumberText> fields = new ArrayList<>(spaces.stream()
        		.map(NumberText::new).toList());
        
        //faz a inscrição no serviço
        fields.forEach(t -> notifierService.subscribe(CLEAR_SPACE, t));
        
        //retorna o painel nosso q/vai compor as 9 posições
        return new SudokuSector(fields);
    }

    private void addFinishGameButton(final JPanel mainPanel) {
        finishGameButton = new FinishGameButton(e -> {
        	//se ocorreu ok:sem erros aciona o service p/finalizar o jogo
            if (boardService.gameIsFinished()){
                showMessageDialog(null, "Parabéns você concluiu o jogo");
                //desabilitando todos botoes
                resetButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);
            } else {
                var message = "Seu jogo tem alguma inconsistência, ajuste e tente novamente";
                showMessageDialog(null, message);
            }
        });
        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatusButton(final JPanel mainPanel) {
        checkGameStatusButton = new CheckGameStatusButton(e -> {
            var hasErrors = boardService.hasErrors();
            var gameStatus = boardService.getStatus();
            var message = switch (gameStatus){
                case NON_STARTED -> "O jogo não foi iniciado";
                case INCOMPLETE -> "O jogo está imcompleto";
                case COMPLETE -> "O jogo está completo";
            };
            message += hasErrors ? " e contém erros" : " e não contém erros";
            showMessageDialog(null, message);
        });
        mainPanel.add(MainScreen.this.checkGameStatusButton);
    }

    private void addResetButton(final JPanel mainPanel) {
    	
    	//espera a ação do nosso botao
        resetButton = new ResetButton(e ->{
        	
        	//pergunta se realmente quer resetar o jogo c/dialog de confirmação
        	//Exibindo o dialogo na tela
            var dialogResult = showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar o jogo?",//MSG
                    "Limpar o jogo",//TITULO
                    YES_NO_OPTION,//TIPOS DE BOTAO
                    QUESTION_MESSAGE
            );
            //resultado(0=sim/1=nao)
            if (dialogResult == 0){
            	//resetar o jogo
                boardService.reset();
                //disparamos nosso evento de limpeza
                notifierService.notify(CLEAR_SPACE);
            }
        });
        mainPanel.add(resetButton);
    }

}
