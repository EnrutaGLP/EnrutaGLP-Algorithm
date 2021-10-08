package com.enrutaglp.enums;

public enum TipoBloqueo {
	HORIZONTAL((byte) 1) , 
	VERTICAL ((byte) 2), 
	EN_L ((byte) 3);

	private byte value; 
	
	private TipoBloqueo(byte value) {
		this.value = value;
	}
	
	@SuppressWarnings("unused")
	private byte getValue() {
		return this.value; 
	}
}
