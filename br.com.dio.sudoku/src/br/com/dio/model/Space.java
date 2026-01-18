package br.com.dio.model;

public class Space {
	//valor digitado
	private Integer actual;
	//valor fixo esperado
	private final int expected;
	//posicao pode ser alterada ou nao
	private final boolean fixed;
	
	public Space(final int expected, final boolean fixed) {
		this.expected = expected;
		this.fixed = fixed;
		//se posicao fixa nao pode ser alterada
		if(fixed) {
			//actual recebe expected e nao pode ser alterado
			actual = expected;
		}
	}
	public Integer getActual() {
		return actual;
	}

	public void setActual(Integer actual) {
		//se for fixo nao editar
		if(fixed) return;
		
		this.actual = actual;
	}
	//limpando a posição atual
	public void clearSpace() {
		setActual(null);
	}

	public int getExpected() {
		return expected;
	}

	public boolean isFixed() {
		return fixed;
	}

}
