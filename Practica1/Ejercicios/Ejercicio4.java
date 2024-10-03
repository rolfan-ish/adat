package Ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Ejercicio4
 * @author Aimar Ibarra
 */
public class Ejercicio4 {
	public static void main(String[] args) {
		new Ejercicio4().run();
	}
	
	private static Scanner sc = new Scanner(System.in);

	private static String ask(String q) {
		System.out.println(q);
		return sc.nextLine();
	}
	
	private ArrayList<Olimpiada> arr;

	private int menu() {
		System.out.println("1. Crear fichero serializable");
		System.out.println("2. Aniadir edición olimpica");
		System.out.println("3. Buscar olimpiadas por sede");
		System.out.println("4. Eliminar edición olímpica");
		return sc.nextInt();
	}

	public void run() {
		switch (menu()) {
		case 1:
			crearFicheroOlimpiadas();
			break;
		case 2:
			aniadirEdicion();
			break;
		case 3:
			buscar();
			break;
		case 4:
			eliminar();
			break;
		}
	}
	public Ejercicio4() {
		var bin = new File("olimpiadas.bin");
		if (bin.exists()) {
			try (var obj = new ObjectInputStream(new FileInputStream(bin))) {
				arr = (ArrayList<Olimpiada>) obj.readObject();
			} catch (Exception e) {
				System.out.println("Error " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			try {
				var doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("olimpiadas.xml");
				var olimpiadas = doc.getElementsByTagName("olimpiada");
				arr = new ArrayList<Olimpiada>();
				for (int i = 0; i < olimpiadas.getLength(); i++) {
					if (olimpiadas.item(i).getNodeType() != Node.ELEMENT_NODE)
						continue;
					arr.add(new Olimpiada((Element) olimpiadas.item(i)));
				}
			} catch (Exception e) {
				System.out.println("Error " + e.getMessage());
				e.printStackTrace();

			}
		}
	}

	private void crearFicheroOlimpiadas() {
		try (var objectFile = new ObjectOutputStream(new FileOutputStream("olimpiadas.bin"))) {
			objectFile.writeObject(arr);
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void aniadirEdicion() {
		var juegos = ask("Juegos:");
		var anio = Integer.parseInt("Año:");
		var temporada = ask("Temporada:");
		var ciudad = ask("Ciudad");
		arr.add(new Olimpiada(juegos, anio, temporada, ciudad));
		crearFicheroOlimpiadas();
	}

	private void buscar() {
		var busqueda = ask("busqueda:");
		arr.stream().filter(o -> o.getCiudad().indexOf(busqueda) != -1)
				.map(o -> "Juegos: " + o.getJuegos() +
						", Año: " + o.getAnio() +
						", Temporada: " + o.getTemporada() +
						", Ciudad: " + o.getCiudad())
				.forEach(System.out::println);
	}

	private void eliminar() {
		var anio = Integer.parseInt("Año:");
		var temporada = ask("Temporada:");
		if (arr.removeIf(o -> o.getAnio() == anio && o.getTemporada().equals(temporada)))
			crearFicheroOlimpiadas();
		else
			System.out.println("No se elimino ninguna entrada");
	}
}
