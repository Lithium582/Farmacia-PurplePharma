package Clases;

import java.io.*;
import java.util.ArrayList;

public class ManejadorArchivosGenerico {
	/**
	 * @param nombreCompletoArchivo
	 * @param listaLineasArchivo
	 *            lista con las lineas del archivo
	 * @throws IOException
	 */
	public static void escribirArchivo(String nombreCompletoArchivo,
			String[] listaLineasArchivo) {
		FileWriter fw;
		try {
			fw = new FileWriter(nombreCompletoArchivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < listaLineasArchivo.length; i++) {
				String lineaActual = listaLineasArchivo[i];
				bw.write(lineaActual);
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Error al escribir el archivo "
					+ nombreCompletoArchivo);
			e.printStackTrace();
		}
	}

	public static ArrayList<String> leerArchivo(String nombreCompletoArchivo) {
		FileInputStream fr;
		ArrayList<String> listaLineasArchivo = new ArrayList<String>();
		try {
			fr = new FileInputStream(nombreCompletoArchivo);
                        
			BufferedReader br = new BufferedReader(new InputStreamReader (fr, "cp1252"));
			String lineaActual = br.readLine();
			while (lineaActual != null) {
				listaLineasArchivo.add(lineaActual);
				lineaActual = br.readLine();
			}
			br.close();
            fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error al leer el archivo " + nombreCompletoArchivo);
		} catch (IOException e) {
			System.out.println("Error al leer el archivo " + nombreCompletoArchivo);
		}
		//System.out.println("Archivo leido satisfactoriamente");

		return listaLineasArchivo;
	}
}
