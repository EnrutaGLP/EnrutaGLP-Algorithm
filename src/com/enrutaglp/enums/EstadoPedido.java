package com.enrutaglp.enums;

public enum EstadoPedido {
	INICIAL((byte) 1) , 
	EN_PROCESO ((byte) 2), 
	COMPLETADO ((byte) 3);

	private byte value; 
	
	private EstadoPedido(byte value) {
		this.value = value;
	}
	
	@SuppressWarnings("unused")
	private byte getValue() {
		return this.value; 
	}
	
}
