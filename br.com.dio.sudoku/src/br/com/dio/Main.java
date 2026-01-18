package br.com.dio;

import br.com.dio.model.Board;
import br.com.dio.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static br.com.dio.util.BoardTemplate.BOARD_TEMPLATE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {

		private final static Scanner scanner = new Scanner(System.in);

	    private static Board board;

	    private final static int BOARD_LIMIT = 9;

	    public static void main(String[] args) {
	        final var positions = Stream.of(args)
	                .collect(toMap(
	                        k -> k.split(";")[0],
	                        v -> v.split(";")[1]
	                ));
	        var option = -1;
	        while (true){
	            System.out.println("Selecione uma das opções a seguir");
	            System.out.println("1 - Iniciar um novo Jogo");
	            System.out.println("2 - Colocar um novo número");
	            System.out.println("3 - Remover um número");
	            System.out.println("4 - Visualizar jogo atual");
	            System.out.println("5 - Verificar status do jogo");
	            System.out.println("6 - limpar jogo");
	            System.out.println("7 - Finalizar jogo");
	            System.out.println("8 - Sair");

	            option = scanner.nextInt();

	            switch (option){
	                case 1 -> startGame(positions);
	                case 2 -> inputNumber();
	                case 3 -> removeNumber();
	                case 4 -> showCurrentGame();
	                case 5 -> showGameStatus();
	                case 6 -> clearGame();
	                case 7 -> finishGame();
	                case 8 -> System.exit(0);
	                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
	            }
	        } 
	    }

	    private static void startGame(final Map<String, String> positions) {
	        //VERIFICA se o Board nao é nulo
	    	if (nonNull(board)){
	            System.out.println("O jogo já foi iniciado");
	            return;
	        }

	    	//Montando a estrutura
	        List<List<Space>> spaces = new ArrayList<>();
	        //Iterando sobre o ArrayList
	        for (int i = 0; i < BOARD_LIMIT; i++) {
	        	//criada um ArrayList p/add os valores na coluna
	            spaces.add(new ArrayList<>());
	            //for mais interno
	            for (int j = 0; j < BOARD_LIMIT; j++) {
	            	
	            	//busca no mapa a posição p/jogar na variavel
	                var positionConfig = positions.get("%s,%s".formatted(i, j));
	                
	                //c/a posição recuperada fazer o split
	                var expected = Integer.parseInt(positionConfig.split(",")[0]);
	                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
	                
	                //instanciando nosso espaço e criamos o objeto
	                var currentSpace = new Space(expected, fixed);
	                
	                //pega a coluna e add o espaço instanciado
	                spaces.get(i).add(currentSpace);
	            }
	        }
            //c/nossos espaços criado e montamos a tela
	        board = new Board(spaces);
	        System.out.println("O jogo está pronto para começar");
	    }

        /*   
         * 
         *   Verificar se nao foi digitado letra
         * 
         *   Se foi digitado somente numero
         * 
         */
	    private static void inputNumber() {
	        //Verifica se o jogo ja foi iniciado
	    	if (isNull(board)){
	            System.out.println("O jogo ainda não foi iniciado iniciado");
	            return;
	        }

	        System.out.println("Informe a coluna que em que o número será inserido");
	        var col = runUntilGetValidNumber(0, 8);
	        System.out.println("Informe a linha que em que o número será inserido");
	        var row = runUntilGetValidNumber(0, 8);
	        System.out.printf("Informe o número que vai entrar na posição [%s,%s]\n", col, row);
	        var value = runUntilGetValidNumber(1, 9);
	        
	        //se retornar false nao fez a inserção
	        if (!board.changeValue(col, row, value)){
	            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
	        }
	    }

	    private static void removeNumber() {
	        if (isNull(board)){
	            System.out.println("O jogo ainda não foi iniciado iniciado");
	            return;
	        }

	        System.out.println("Informe a coluna que em que o número será inserido");
	        var col = runUntilGetValidNumber(0, 8);
	        System.out.println("Informe a linha que em que o número será inserido");
	        var row = runUntilGetValidNumber(0, 8);
	        
	        //limpar a posição determinada
	        if (!board.clearValue(col, row)){
	            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
	        }
	    }
//Verifica o Status do jogo
	    private static void showCurrentGame() {
	        //Se board nulo nao foi iniciado
	    	if (isNull(board)){
	            System.out.println("O jogo ainda não foi iniciado iniciado");
	            return;
	        }

	    	//Exibir o jogo atual
	    	//Fazer um array de object
	        var args = new Object[81];
	        var argPos = 0;
	        //vai pegar o template p/imprimir
	        //A iteração qua agente fez até agora de List de List foi de coluna
	        //pega toda 1º coluna imprime, toda 2º imprime
	        //Agora vamos fazer diferente imprimir linha a linha
	        for (int i = 0; i < BOARD_LIMIT; i++) {
	        	//pegando a lista da lista de espaços,esse for pega cada coluna da linha do outro
	        	//for
	            for (var col: board.getSpaces()){
	            	//se ele for nulo inserimos espaço
	            	//espaço colocado pois e gerado so um caracter q/é o numero(formatação)
	                args[argPos ++] = " " + ((isNull(col.get(i)
	                //senao imprimimos o valor
	                		.getActual())) ? " " : col.get(i).getActual());
	            }
	        }
	        System.out.println("Seu jogo se encontra da seguinte forma");
	        //Se fosse ArrayList usaria args.toArray()
	        System.out.printf((BOARD_TEMPLATE) + "\n", args);
	    }
//Verificar o Status do jogo
	    private static void showGameStatus() {
	        if (isNull(board)){
	            System.out.println("O jogo ainda não foi iniciado iniciado");
	            return;
	        }

	        System.out.printf("O jogo atualmente se encontra no status %s\n", board.getStatus()
	        		//exibindo o label do enum
	        		.getLabel());
	        
	        //Verificando se o jogo tem erros
	        if(board.hasErrors()){
	            System.out.println("O jogo contém erros");
	        } else {
	            System.out.println("O jogo não contém erros");
	        }
	    }
	    //Limpando o jogo
	    private static void clearGame() {
	        if (isNull(board)){
	            System.out.println("O jogo ainda não foi iniciado iniciado");
	            return;
	        }

	        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu "
	        		+ "progresso?");
	        var confirm = scanner.next();
	        
	        //Validação p/resposta seja correta
	        while (!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("não")){
	            System.out.println("Informe 'sim' ou 'não'");
	            confirm = scanner.next();
	        }
	        
	        //Limpando o jogo
	        if(confirm.equalsIgnoreCase("sim")){
	            board.reset();
	        }
	    }

	    private static void finishGame() {
	        if (isNull(board)){
	            System.out.println("O jogo ainda não foi iniciado iniciado");
	            return;
	        }

	        if (board.gameIsFinished()){
	            System.out.println("Parabéns você concluiu o jogo");
	            //exibindo o jogo p/nosso usuario
	            showCurrentGame();
	            //limpando o board
	            board = null;
	            
	        //senao conclui o jogo tem alguma coisa Errada
	        } else if (board.hasErrors()) {
	            System.out.println("Seu jogo conté, erros, verifique seu board e ajuste-o");
	        } else {
	            System.out.println("Você ainda precisa preenhcer algum espaço");
	        }
	    }

//Metodo utilitario p/validação garante qu vai rodar ate o usuario digitar valores validos
	    private static int runUntilGetValidNumber(final int min, final int max){
	        var current = scanner.nextInt();
	        
	        while (current < min || current > max){
	            System.out.printf("Informe um número entre %s e %s\n", min, max);
	            current = scanner.nextInt();
	        }
	        return current;
	    }
}
