package com.almundo.callcenter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * La clase CallCenter consta de los empleados: operadores, supervisores y directores,
 * de la clase Dispatcher que es la encargada de atender las llamadas entrantes de manera concurrente,
 * y de la lista de llamadas que llegan para su atencion.
 * 
 * Por una cuestion practica, tanto los empleados como las llamadas, se implementaron como colas representando mejor
 * la realidad y simplificando su utilizacion.
 * 
 * Nota: por una cuestion de tamaño y tiempo se decidio no utilizar Spring en la resolucion de este test.
 * 
 * @author Hugo Peralta
 *
 */
public class CallCenter {
	
	private BlockingQueue<Operador> operadores;
	private BlockingQueue<Supervisor> supervisores;
	private BlockingQueue<Director> directores;
	
	private Dispatcher dispatcher;
	
	private BlockingQueue<Llamada> llamadasEntrantes;

	/**
	 * constructor
	 */
	public CallCenter() {
		super();
		this.operadores = new LinkedBlockingQueue<Operador>();;
		this.supervisores = new LinkedBlockingQueue<Supervisor>();
		this.directores = new LinkedBlockingQueue<Director>();
		this.dispatcher = new Dispatcher(this);
		this.setLlamadasEntrantes(new LinkedBlockingQueue<Llamada>());
	}

	/**
	 * Checkea si hay operadores disponibles para atender llamados
	 * 
	 * @return true|false
	 */
	public boolean hayOperadoresDisponibles() {
		
		return (!operadores.isEmpty());
	}
	
	/**
	 * Checkea si hay supervisores disponibles para atender llamados
	 * 
	 * @return true|false
	 */
	public boolean haySupervisoresDisponibles() {
		
		return (!supervisores.isEmpty());
	}

	/**
	 * Checkea si hay directores disponibles para atender llamados
	 * 
	 * @return true|false
	 */
	public boolean hayDirectoresDisponibles() {
		
		return (!directores.isEmpty());
	}
	
	/**
	 * Devuelve el primer operador disponible para atender llamados
	 * 
	 * @return Operador
	 */
	public Operador getOperadorDisponible() {
		
		Operador op = null;
		try {
			
			op= this.operadores.take();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return op;
	}
	
	/**
	 * Devuelve el primer supervisor disponible para atender llamados
	 * 
	 * @return Supervisor
	 */
	public Supervisor getSupervisorDisponible() {
		
		Supervisor sup=null;
		try {
			
			sup= this.supervisores.take();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sup;
	}
	
	/**
	 * Devuelve el primer director disponible para atender llamados
	 * 
	 * @return Director
	 */
	public Director getDirectorDisponible() {
		
		Director dir=null;
		try {
			
			dir= this.directores.take();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dir;
	}
	
	/**
	 * Agrega un operador a la cola de disponibles para atender llamados
	 * 
	 * @return void
	 */
	public void addOperadorDisponible(Operador op) {
		try {
			this.getOperadores().put(op);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Agrega un supervisor a la cola de disponibles para atender llamados
	 * 
	 * @return void
	 */
	public void addSupervisorDisponible(Supervisor sup) {
		try {
			this.getSupervisores().put(sup);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Agrega un director a la cola de disponibles para atender llamados
	 * 
	 * @return void
	 */
	public void addDirectorDisponible(Director dir) {
		try {
			this.getDirectores().put(dir);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//-------------------------- GETTERS Y SETTERS ----------------------------------
	
	public BlockingQueue<Operador> getOperadores() {
		return operadores;
	}

	public void setOperadores(BlockingQueue<Operador> operadores) {
		this.operadores = operadores;
	}

	public BlockingQueue<Supervisor> getSupervisores() {
		return supervisores;
	}

	public void setSupervisores(BlockingQueue<Supervisor> supervisores) {
		this.supervisores = supervisores;
	}

	public BlockingQueue<Director> getDirectores() {
		return directores;
	}

	public void setDirectores(BlockingQueue<Director> directores) {
		this.directores = directores;
	}

	public Dispatcher getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public BlockingQueue<Llamada> getLlamadasEntrantes() {
		return llamadasEntrantes;
	}

	public void setLlamadasEntrantes(BlockingQueue<Llamada> llamadasEntrantes) {
		this.llamadasEntrantes = llamadasEntrantes;
	}
	
	

}
