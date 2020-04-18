package co.com.udem.nomina.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import co.com.udem.nomina.dto.EmpleadoDTO;

public class LecturaArchivo {

	private static final Logger logger = LogManager.getLogger(LecturaArchivo.class);
	private HashMap<String, EmpleadoDTO> listaEmpleTabla = new HashMap<String, EmpleadoDTO>();
	private int cantidadRegistros = 0;

	public String leerArchivo() {
		BasicConfigurator.configure();
		File archivoNomina = new File("D:\\Ejercicio_Nomina_Empleados\\nominaEmpleados.txt");
		Scanner scanner = null;
		String mensaje = "";

		try {
			scanner = new Scanner(archivoNomina);

			while (scanner.hasNextLine()) {
				String infoEmpleado = scanner.nextLine();
				leerRegistro(infoEmpleado);
				cantidadRegistros++;
			}

		} catch (FileNotFoundException ex) {
			mensaje = "El archivo no está en la ruta especificada";
			logger.error(ex.getMessage());

		} finally {

			if (scanner != null) {
				scanner.close();
			}

		}

		return mensaje;

	}

	private void leerRegistro(String infoEmpleado) {

		Scanner scanner = new Scanner(infoEmpleado);
		scanner.useDelimiter(",");

		while (scanner.hasNext()) {
			EmpleadoDTO empleadoDTO = new EmpleadoDTO();

			empleadoDTO.setNombres(scanner.next());
			empleadoDTO.setApellidos(scanner.next());
			String cedula = scanner.next();
			empleadoDTO.setCedula(cedula);
			empleadoDTO.setDepartamento(scanner.next());
			empleadoDTO.setSalario(Double.parseDouble(scanner.next()));

			if (!existeEmpleado(cedula)) {
				listaEmpleTabla.put(cedula, empleadoDTO);
			}
		}
		imprimirEmpleadoArchivo(listaEmpleTabla);
		scanner.close();

	}

	private boolean existeEmpleado(String cedula) {

		boolean existe = false;

		if (listaEmpleTabla.containsKey(cedula)) {
			existe = true;
		}
		return existe;
	}
	
	private void imprimirEmpleadoArchivo(HashMap<String, EmpleadoDTO> listaEmpleTabla) {
		
		BasicConfigurator.configure();
		logger.info(listaEmpleTabla);
		
		/*for(Entry<String, EmpleadoDTO> entry: listaEmpleTabla.entrySet()) {
			
		}*/
		
	}
	
	public int cantidadRegistros() {
		return cantidadRegistros;
	}

}
