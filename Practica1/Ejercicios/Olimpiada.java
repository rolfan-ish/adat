package Ejercicios;

import java.io.Serializable;

import org.w3c.dom.Element;

import csvutil.CsvEntry;

/**
 * Olimpiada
 * @author Aimar Ibarra
 */
public class Olimpiada implements Comparable<Olimpiada>, Serializable {
	private String juegos, temporada, ciudad;
	private int anio;

	public Olimpiada(Element e) {
		anio = Integer.parseInt(e.getAttribute("year"));
		juegos = e.getElementsByTagName("juegos").item(0).getTextContent();
		temporada = e.getElementsByTagName("temporada").item(0).getTextContent();
		ciudad = e.getElementsByTagName("ciudad").item(0).getTextContent();
	}

	public Olimpiada(String juegos, int anio, String temporada, String ciudad) {
		this.juegos = juegos;
		this.anio = anio;
		this.temporada = temporada;
		this.ciudad = ciudad;
	}
	
	public Olimpiada(CsvEntry entry) {
		juegos = entry.next();
		anio = Integer.parseInt(entry.next());
		temporada = entry.next();
		ciudad = entry.next();
	}
	
	@Override
	public int compareTo(Olimpiada arg0) {
		if (equals(arg0))
			return 0;
		int aniodiff = anio - arg0.anio;
		return aniodiff == 0
			? temporada.equals("Winter") ? 1 : -1
			: aniodiff;
	}

	public String getJuegos() {
		return juegos;
	}

	public int getAnio() {
		return anio;
	}

	public String getTemporada() {
		return temporada;
	}

	public String getCiudad() {
		return ciudad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((juegos == null) ? 0 : juegos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Olimpiada other = (Olimpiada) obj;
		if (juegos == null) {
			if (other.juegos != null)
				return false;
		} else if (!juegos.equals(other.juegos))
			return false;
		return true;
	}
}
