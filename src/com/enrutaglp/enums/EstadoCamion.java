package com.enrutaglp.enums;

public enum EstadoCamion {
	INICIAL((byte) 1) , 
	EN_PROCESO ((byte) 2), 
	COMPLETADO ((byte) 3);

	private byte value; 
	
	private EstadoCamion(byte value) {
		this.value = value;
	}
	
	private byte getValue() {
		return this.value; 
	}
	
}
