package model.enums;

public enum Niveis {
	
	ADMINISTRADOR(1),
	GESTOR(2),
	GERENTE(3),
	FUNCIONARIO(4);

	Niveis(int i) {
		this.i = i;
	}
	
	private int i;
	
	public int getIndice() {
		return i;
	}
}
