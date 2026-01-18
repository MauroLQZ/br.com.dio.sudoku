package br.com.dio.model;

/*Verifica o status do jogo */
public enum GameStatusEnum {
	
	//Labels no construtor do enum
	NON_STARTED("não iniciado"),//sem erros
    INCOMPLETE("incompleto"),//pode ter erros
    COMPLETE("completo");//pode ter erros

	//colocando um label p/o enum
    private String label;

    GameStatusEnum(final String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
