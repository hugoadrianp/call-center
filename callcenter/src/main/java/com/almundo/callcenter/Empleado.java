package com.almundo.callcenter;

/**
 * Representa una entidad empleado.
 * 
 * Nota: Se colocar solo atributos basicos los cuales van a ser comunes a operador, supervisor y director.
 * 
 * @author Hugo Peralta
 *
 */
public abstract class Empleado {

	public String id;
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
