package model.enums;

public enum Tipos {
	BEBIDAS(1),
	COMIDAS(2),
	HIGIENE(3),
	LIMPEZA(4),
	ROUPAS(5),
	EQUIPAMENTOS(6),
	DESCARTAVEIS(7),
	OUTROS(8);

	Tipos(final int i) {
		this.i = i;
	}
	
	private int i;
	
	public int getIndice() {
		return i;
	}
	
}
