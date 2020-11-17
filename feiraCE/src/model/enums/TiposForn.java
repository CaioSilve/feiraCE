package model.enums;

public enum TiposForn {
		
	PRIVADO(1), 
	MERCADO(2), 
	FEIRA(3);
	
	TiposForn(int i) {
		this.i = i;
	}
	
	private int i;
	
	public int getIndice() {
		return i;
	}
}

