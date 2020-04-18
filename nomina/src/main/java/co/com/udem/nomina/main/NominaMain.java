package co.com.udem.nomina.main;



import co.com.udem.nomina.hilo.ProcesadorArchivo;

public class NominaMain {

	public static void main(String[] args) {

		ProcesadorArchivo procesadorArchivo = new ProcesadorArchivo();
		
		procesadorArchivo.iniciarHilo();

	}

}
