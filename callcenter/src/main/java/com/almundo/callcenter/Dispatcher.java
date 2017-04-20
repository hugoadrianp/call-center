package com.almundo.callcenter;

import java.util.concurrent.TimeUnit;

/**
 * Esta clase es la encargada de manejar las llamadas y asignarlas a los empleados que corresponda.
 * Seria un Async Dispatcher para manejar concurrencia.
 * 
 * @author Hugo Peralta
 *
 */
public class Dispatcher {
	
	private CallManager callManager;
	private CallCenter callCenter;
	
	/**
	 * Constructor
	 * 
	 * @param callCenter
	 */
	public Dispatcher(CallCenter callCenter) {
		super();
		this.callManager = new CallManager();
		this.setCallCenter(callCenter);
	}


	/**
	 * Este metodo se encarga de asignar las llamadas a los empleados correspondientes siguiendo 
	 * el orden definido, a saber:
	 * 
	 * Primero se asigna la llamada a un operador, si este no esta disponible
	 * se asigna a un supervisor y si este no esta disponible se asigna a un director.
	 * 
	 * En caso de que no haya ningun empleado disponible para atender la llamada, esta ultima queda 
	 * en espera hasta que se desocupe alguno.
	 * 
	 * En caso de que entraran mas llamadas que las que puede manejar el Dispatcher, por mas que haya empleados
	 * disponibles, la llamada quedara encolada por el ThreadPoolExecutor hasta que se libere un thread para
	 * su ejecucion.
	 * 
	 */
	public void dispatchCall() {
		
		 while(hayLlamadasEncoladas()) {
			
			if(this.getCallCenter().hayOperadoresDisponibles()) {
				
				try {
					
					Operador operador= this.getCallCenter().getOperadorDisponible();
					this.getCallManager().atenderLLamada(this.callCenter.getLlamadasEntrantes().take(), operador);
															
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if (this.getCallCenter().haySupervisoresDisponibles()) {
				
				try {
					
					Supervisor supervisor= this.getCallCenter().getSupervisorDisponible();
					this.getCallManager().atenderLLamada(this.callCenter.getLlamadasEntrantes().take(), supervisor);
															
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(this.getCallCenter().hayDirectoresDisponibles()) {
				
				try {
					
					Director director= this.getCallCenter().getDirectorDisponible();
					this.getCallManager().atenderLLamada(this.callCenter.getLlamadasEntrantes().take(), director);
															
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				System.out.println("En este momento todos los operadores estan ocupados. Por favor aguarde en linea...");
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 }
		 
		 // Una vez que no hay mas llamadas se eliminan todos los threads del liberando el thread pool 
		 //pero previo a esto se espera por la terminacion de las tareas pendientes.
		 this.callManager.getExecutor().shutdown();
		 try {
			this.callManager.getExecutor().awaitTermination(25, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Checkear si hay llamadas en espera de ser atendidas en el call center
	 * 
	 * @return true|false
	 */
	private boolean hayLlamadasEncoladas() {
		// TODO Auto-generated method stub
		return (!this.callCenter.getLlamadasEntrantes().isEmpty());
	}
	
	//----------------- GETTERS Y SETTERS ---------------------
	
	public CallManager getCallManager() {
		return callManager;
	}

	public void setCallManager(CallManager callManager) {
		this.callManager = callManager;
	}


	public CallCenter getCallCenter() {
		return callCenter;
	}


	public void setCallCenter(CallCenter callCenter) {
		this.callCenter = callCenter;
	}

}
