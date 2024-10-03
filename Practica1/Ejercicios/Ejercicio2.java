package Ejercicios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import csvutil.CsvReader;

/**
 * Ejercicio2
 * 
 * @author Aimar Ibarra
 */
public class Ejercicio2 {
	private static Scanner sc = new Scanner(System.in);
	private ArrayList<Entrada> deportistas;

	public static void main(String[] args) {
		new Ejercicio2().run();
	}

	public Ejercicio2() {
		try (var rd = new CsvReader(new File("athlete_events.csv"))) {
			rd.stream().skip(1).map(Entrada::new).forEach(deportistas::add);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		switch (menu()) {
			case 1:
				generarCsv();
				break;
			case 2:
				buscarDeportista();
				break;
			case 3:
				buscarPor();
				break;
			case 4:
				aniadir();
				break;
		}
	}

	private static int menu() {
		System.out.println("1. Generar fichero csv de olimpiadas");
		System.out.println("2. Buscar deportista");
		System.out.println("3. Buscar deportistas por deporte y olimpiada");
		System.out.println("4. Añadir deportista");
		return sc.nextInt();
	}

	private void generarCsv() {
		try (var out = new PrintStream(new FileOutputStream("olimpiadas.csv"))) {
			var set = new HashSet<String>();
			deportistas.stream()
					.forEach(ent -> {
						if (set.contains(ent.getJuegos()))
							return;
						set.add(ent.getJuegos());
						out.printf("\"%s\",%s,%s,\"%s\"\n", ent.getJuegos(), ent.getAnio(), ent.getTemporada(),
								ent.getCiudad());
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buscarDeportista() {
		System.out.println("Nombre deportista:");
		var nom = sc.nextLine();
		var res = new ArrayList<Entrada>();
		deportistas.stream()
				.filter(e -> e.getNombre().indexOf(nom) != -1)
				.forEach(res::add);
		if (res.isEmpty())
			System.out.println("No hubo coincidencias");
		res.forEach(e -> {
			System.out.println("Nombre: " + e.getNombre());
			System.out.println("Sexo  : " + e.getSexo());
			System.out.println("Altura: " + e.getAltura());
			System.out.println("Peso  : " + e.getPeso());
			System.out.println("Juegos: " + e.getJuegos());
			System.out.println();
		});
	}

	private static String ask(String q) {
		System.out.println(q);
		return sc.nextLine();
	}

	private void buscarPor() {
		var deporte = ask("Deporte:");
		var anio = ask("Año:");
		var temporada = ask("Temporada:");
		var res = new ArrayList<Entrada>();
		deportistas.stream()
				.filter(e -> e.getDeporte().equals(deporte) &&
						e.getAnio().equals(anio) &&
						e.getTemporada().equals(temporada))
				.forEach(res::add);

		if (res.isEmpty()) {
			System.out.println("No se encontraron deportistas.");
		} else {
			var e0 = res.get(0);
			System.out.println("Juegos Olímpicos: " + e0.getJuegos() + " (" + e0.getCiudad() + ")");
			System.out.println("Participantes:");
			for (var e : res) {
				System.out.println("Nombre: " + e.getNombre() + ", Evento: " + e.getEvento() + ", Medalla: "
						+ e.getMedalla());
			}
		}
	}

	private void aniadir() {
		var id = ask("id:");
		var nom = ask("nombre:");
		var sex = ask("sexo:");
		var eda = ask("edad:");
		var alt = ask("altura:");
		var pes = ask("peso:");
		var equipo = ask("equipo:");
		var noc = ask("noc:");
		var juegos = ask("juegos:");
		var anio = ask("año:");
		var temporada = ask("temporada:");
		var ciud = ask("ciudad:");
		var depo = ask("deporte:");
		var evento = ask("evento:");
		var medalla = ask("medalla:");

		try (var wr = new FileWriter("athlete_events.csv", true)) {
			var d = "\",\"";

			wr.write("\"" + id + d + nom + d + sex + d + eda + d + alt + d + pes + d + equipo + d + noc + d + juegos + d
					+ anio + d + temporada + d + ciud + d + depo + d + evento + d + medalla + "\"\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
