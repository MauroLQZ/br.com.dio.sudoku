package br.com.dio.service;

import br.com.dio.model.Board;
import br.com.dio.model.GameStatusEnum;
import br.com.dio.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* Classe Service é bom p/quebrar em camadas o projeto,um serviço q/vai disponibilizar as
 * interfaces(Interação c/nosso codigo) p/nossas funcionalidades */
public class BoardService {
	
	private final static int BOARD_LIMIT = 9;

	//Board q/vai ser utilizado p/nosso serviço
    private final Board board;

    //Construtor q/recebe uma instancia de um map
    public BoardService(final Map<String, String> gameConfig) {
    	//inicia o board c/metodo initBoard passando a instancia do map como argumento
        this.board = new Board(initBoard(gameConfig));
    }
    //Metodo do board utilizado no serviço
    public List<List<Space>> getSpaces(){
        return board.getSpaces();
    }
  //Metodo do board utilizado no serviço
    public void reset(){
        board.reset();
    }
  //Metodo do board utilizado no serviço
    public boolean hasErrors(){
        return board.hasErrors();
    }
  //Metodo do board utilizado no serviço
    public GameStatusEnum getStatus(){
        return board.getStatus();
    }
  //Metodo do board utilizado no serviço
    public boolean gameIsFinished(){
        return board.gameIsFinished();
    }
    
    //Montando a estrutura 
    private List<List<Space>> initBoard(final Map<String, String> gameConfig) {
        List<List<Space>> spaces = new ArrayList<>();
      //Iterando sobre o ArrayList
        for (int i = 0; i < BOARD_LIMIT; i++) {
        	//criada um ArrayList p/add os valores na coluna
            spaces.add(new ArrayList<>());

          //for mais interno
            for (int j = 0; j < BOARD_LIMIT; j++) {
            	//busca no mapa a posição p/jogar na variavel
                var positionConfig = gameConfig.get("%s,%s".formatted(i, j));
                
              //c/a posição recuperada fazer o split
				var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                
              //instanciando nosso espaço e criamos o objeto
                var currentSpace = new Space(expected, fixed);
              //pega a coluna e add o espaço instanciado
                spaces.get(i).add(currentSpace);
            }
        }
      //c/nossos espaços criado retornamos a tela
        return spaces;
    }

}
