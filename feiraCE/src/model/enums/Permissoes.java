package model.enums;

public enum Permissoes {

	PADRAO(1),
	ADMINISTRADOR(2);
	
	private int id;
	
	Permissoes(int id) {
		this.id = id;
	}
	
	public int getIndice() {
		return id;
	}
	
}
