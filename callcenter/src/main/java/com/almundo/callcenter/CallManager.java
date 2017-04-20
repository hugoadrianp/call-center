package com.almundo.callcenter;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Esta clase se encarga de administrar la atencion de las llamadas concurrentemente mediante 
 * el manejo del pattern ThreadPool 
 *  
 * @author Hugo Peralta
 *
 */
public class CallManager {
	
	private static final int MAX_THREADS= 10; //nro max de llamadas que se pueden atender concurrentemente
	
	private ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREADS);
	
	/**
	 * Este metodo realiza la ejecucion concurrente de la atencion de las llamadas, 
	 * previo asociar la llamada al empleado que la toma.
	 * 
	 * @param call - llamada para atender
	 * @param emp - empleado que la toma
	 */
	public void atenderLLamada(Llamada call, Empleado emp) {
		
		call.setEmpleado(emp);
	    System.out.println("Llamada despachada nro: >>>>" + call.getCallID());
        executor.execute(call);

	}

	
	//---------------------- GETTERS Y SETTERS -------------------------------
	
	public ThreadPoolExecutor getExecutor() {
		return executor;
	}

	public void setExecutor(ThreadPoolExecutor executor) {
		this.executor = executor;
	}
	

}
