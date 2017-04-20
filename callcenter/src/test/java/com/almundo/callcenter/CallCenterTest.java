package com.almundo.callcenter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 * 
 * @author Hugo Peralta
 * 
 */
public class CallCenterTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CallCenterTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CallCenterTest.class );
    }

    /**
     * Rigourous Test :-)
     * 
     * Este test cubre todas las posibilidades como fueron explicadas en la clase Dispatcher.
     * Por esta razon solo se construyo uno solo.
     * 
     */
    public void testCallCenter() {
       
    	CallCenter callCenter= new CallCenter();
    	
    	cargarEmpleados(callCenter);
    	cargarLlamadasEntrantes(callCenter);
    	callCenter.getDispatcher().dispatchCall();
    	
    	assertTrue( true );
    }
    
    //--------------------------------- AUXILIARES -------------------------------------
    private void cargarEmpleados(CallCenter callCenter) {
    	
    	for (int i=1; i<=5; i++) {
    		Operador op= new Operador();
    		op.setId(Integer.toString(i)+"_Op");
    		op.setName("Op"+i);
			try {
				callCenter.getOperadores().put(op);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	for (int i=1; i<=4; i++) {
    		Supervisor sup= new Supervisor();
    		sup.setId(Integer.toString(i)+"_Sup");
    		sup.setName("Sup"+i);
			try {
				callCenter.getSupervisores().put(sup);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	Director dir= new Director();
    		dir.setId(Integer.toString(1)+"_Dir");
    		dir.setName("Dir1");
			try {
				callCenter.getDirectores().put(dir);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
    
    
    private void cargarLlamadasEntrantes(CallCenter callCenter) {
    	
    	for (int i=1; i<=12; i++) {
    		Llamada call= new Llamada();
    		call.setCallID(Integer.toString(i));
    		call.setCallCenter(callCenter);
    		
			try {
				callCenter.getLlamadasEntrantes().put(call);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
