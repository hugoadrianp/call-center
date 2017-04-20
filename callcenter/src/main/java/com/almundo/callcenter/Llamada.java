package com.almundo.callcenter;

import java.util.concurrent.TimeUnit;

/**
 * Esta clase representa la llamada propiamente dicha, con un callID que la identifica,
 * el empleado que la tiene asignada y el callCenter que la recepciono.
 * 
 * Nota: implemente Runnable ya que cada llamada corre como un thread distinto para hacer la
 * atencion concurrente.
 * 
 * 
 * @author Hugo Peralta
 *
 */
public class Llamada implements Runnable{
	
	String callID;
	Empleado empleado;
	CallCenter callCenter;
		
	/**
	 * En el metodo run() se define una duracion aleatoria de entre 5 y 10 segundos, lo cual lo representados
	 * invocando a sleep.
	 * Luego de que termina la atencion de la llamada, se debe reingresar al empleado a su cola de disponibles correspondiente
	 * para luego poder ser utilizado nuevamente en la atencion de alguna otra llamada en espera.
	 * 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
        {
            long duration = (long) (Math.random() * 5 + 5);//timeout de la llamada entre 5 y 10 segundos
           
            System.out.println("Atendiendo llamada nro: " + callID + " por el empleado nro: " + empleado.getId());
            TimeUnit.SECONDS.sleep(duration);
            System.out.println("Llamada nro "+ callID +" Finalizada --> thread name: "+ Thread.currentThread().getName());
            
            if(empleado instanceof Operador) {
            	this.callCenter.addOperadorDisponible((Operador)empleado);
            }else if(empleado instanceof Supervisor) {
            	this.callCenter.addSupervisorDisponible((Supervisor)empleado);
            }else {// es director
            	this.callCenter.addDirectorDisponible((Director)empleado);
            }
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
		
	}
	
	//-------------------- GETTERS Y SETTERS ------------------------
	
	public String getCallID() {
		return callID;
	}

	public void setCallID(String callID) {
		this.callID = callID;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public CallCenter getCallCenter() {
		return callCenter;
	}

	public void setCallCenter(CallCenter callCenter) {
		this.callCenter = callCenter;
	}
	
}
