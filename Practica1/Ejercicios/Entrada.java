package Ejercicios;

import java.util.Iterator;

import csvutil.CsvEntry;

class Entrada {
	final String id, nombre, sexo, edad, altura, peso, equipo, noc, juegos, anio, temporada, ciudad, deporte, evento,
			medalla;

	public Entrada(final CsvEntry entry) {
		final Iterator<String> it = entry.iterator();
		id = it.next();
		nombre = it.next();
		sexo = it.next();
		edad = it.next();
		altura = it.next();
		peso = it.next();
		equipo = it.next();
		noc = it.next();
		juegos = it.next();
		anio = it.next();
		temporada = it.next();
		ciudad = it.next();
		deporte = it.next();
		evento = it.next();
		medalla = it.next();
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public String getEdad() {
		return edad;
	}

	public String getAltura() {
		return altura;
	}

	public String getPeso() {
		return peso;
	}

	public String getEquipo() {
		return equipo;
	}

	public String getNoc() {
		return noc;
	}

	public String getJuegos() {
		return juegos;
	}

	public String getAnio() {
		return anio;
	}

	public String getTemporada() {
		return temporada;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getDeporte() {
		return deporte;
	}

	public String getEvento() {
		return evento;
	}

	public String getMedalla() {
		return medalla;
	}

}
