package br.com.dio.model;

import java.util.Collection;
import java.util.List;

import static br.com.dio.model.GameStatusEnum.COMPLETE;
import static br.com.dio.model.GameStatusEnum.INCOMPLETE;
import static br.com.dio.model.GameStatusEnum.NON_STARTED;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {

	private final List<List<Space>> spaces;
	
	public Board(final List<List<Space>> spaces) {
		this.spaces = spaces;
	}
	//Liberar acesso p/leitura da lista
	public List<List<Space>> getSpaces() {
		return spaces;
	}
	//metodo p/fazer a verificação do Status
	public GameStatusEnum getStatus(){
        if (spaces.stream().flatMap(Collection::stream)
        //s é uma lista de espaço- noneMatch verifica se nenhum situaçao ocorre
        //verificar se nenhum valor vai fazer match c/essa situação aqui
        //se a posição nao é fixa e nao for nula o espaço ja foi preenchido p/usuario
        		.noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))){
        	//se essa situação estiver ocorrendo retorna nao iniciado
            return NON_STARTED;
        }
//senao so pode ser duas opçoes:incomplete ou complete
        return spaces.stream().flatMap(Collection::stream)
//verifica se tem algum correspondente com nossa verificação.
        		.anyMatch(s -> isNull(s.getActual())) ? INCOMPLETE : COMPLETE;
    }
	//Verifica se jogo tem algum erro
	public boolean hasErrors(){
		//se o jogo nao foi iniciado
        if(getStatus() == NON_STARTED){
            return false;
        }
//verificar se o espaço atual nao seja nulo(preenchido) e o 
//valor diferente do expected(ele tem erro)
        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getActual()) && 
                		!s.getActual().equals(s.getExpected()));
    }
//mostra o encapsulamento do acesso as nossa posições da lista
//colunas nossa listas(List externo)-linhas(List interno)
//int value pq nao faz sentido ter um valor nulo,pois usa clearValue
	public boolean changeValue(final int col, final int row, final int value){
		//pega a coluna e linha
        var space = spaces.get(col).get(row);
        //se posiçao fixa nao pode ser editado
        if (space.isFixed()){
            return false;
        }
        //faz a alteração q agente esprava
        space.setActual(value);
        return true;
    }
	//metodo especifico p limpeza
	public boolean clearValue(final int col, final int row){
        var space = spaces.get(col).get(row);
      //se posiçao fixa nao pode ser editado
        if (space.isFixed()){
            return false;
        }
        //limpa o espaço
        space.clearSpace();
        return true;
    }
	//fazer um reset dos nossos valores
	public void reset(){
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }
	//Verifica se o jogo acabou
	public boolean gameIsFinished(){
		//se nao tem erro e status complete
        return !hasErrors() && getStatus().equals(COMPLETE);
    }
}
