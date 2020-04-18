package co.com.udem.nomina.hilo;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import co.com.udem.nomina.util.LecturaArchivo;

public class ProcesadorArchivo implements Runnable {
	
	private static final Logger logger = LogManager.getLogger(ProcesadorArchivo.class);
	LecturaArchivo lecturaArchivo = new LecturaArchivo();
	String mensaje = "";
	Thread t;
	
	public void iniciarHilo() {
		t = new Thread(this);
		t.start();
	}
	
	public void run() {
		BasicConfigurator.configure();
		
		while(true) {
			try {
				Thread.sleep(30000);
				mensaje = lecturaArchivo.leerArchivo();
				
				if(lecturaArchivo.cantidadRegistros() == 2) {
					break;
				}
				logger.info("Mensaje: " + mensaje);
				
			} catch (InterruptedException e) {				
				logger.error(e.getMessage());
				Thread.currentThread().interrupt();
			}
			
		}
		

	}

}
