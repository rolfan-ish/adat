package Ejercicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

/**
 * Ejercicio1
 * @author Aimar Ibarra
 */
public class Ejercicio1 {
	private static Scanner sc = new Scanner(System.in);

	private static String ask(String q) {
		System.out.println(q);
		return sc.nextLine();
	}

	private void crearDirectorio() {
		var ruta = ask("Introduce la ruta:");
		var dir = ask("Introduce el directorio:");
		new File(ruta, dir).mkdir();
	}

	private void listarDir() {
		var dirnam = ask("Introduce el directorio:");
		var dir = new File(dirnam);
		for (var f : dir.listFiles()) {
			if (f.isDirectory())
				System.out.println(f.getName() + "/");
			else
				System.out.println(f.getName());
		}
	}

	private void copiarArchivo() {
		var o = ask("Archivo fuente:");
		var n = ask("Archivo destino:");
		try {
			Files.copy(new File(o).toPath(), new File(n).toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void moverArchivo() {
		var o = ask("Archivo original:");
		var n = ask("Nombre nuevo:");
		new File(o).renameTo(new File(n));
	}

	private void eliminarArchivo(File a) {
		if (a.isDirectory()) {
			for (var f : a.listFiles())
				eliminarArchivo(f);
		}
		a.delete();
	}

	private void eliminarArchivo() {
		var a = new File(ask("Introduce el archivo/directorio:"));
		eliminarArchivo(a);
	}

	private int menu() {
		System.out.println("1. Crea un directorio");
		System.out.println("2. Listar un directorio");
		System.out.println("3. Copiar un archivo");
		System.out.println("4. Mover un archivo");
		System.out.println("5. Eliminar un archivo/directorio");
		System.out.println("6. Salir del programa.");
		int n = sc.nextInt();
		while (n < 1 || n > 6)
		    n = sc.nextInt();
		return n;
	}

	public void run() {
	    boolean run = true;
	    while (run) {
		switch (menu()) {
		case 1: crearDirectorio(); break;
		case 2: listarDir(); break;
		case 3: copiarArchivo(); break;
		case 4: moverArchivo(); break;
		case 5: eliminarArchivo(); break;
		case 6: run = false; break;
		}
	    }
	}

	public static void main(String[] args) {
	    new Ejercicio1().run();
	}
}
