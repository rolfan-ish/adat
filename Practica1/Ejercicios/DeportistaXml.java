package Ejercicios;

import java.util.Hashtable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * DeportistaXml
 * @author Aimar Ibarra
 */
public class DeportistaXml extends XmlBuilder {
	private Element elem;
	private Element participaciones;

	private Hashtable<String, Element> deportes = new Hashtable<>();

	public DeportistaXml(Document doc, Entrada ent) {
		super(doc);
		elem = add("deportista", "id", ent.getId());
		add(elem, "nombre", ent.getNombre());
		add(elem, "sexo", ent.getSexo());
		add(elem, "altura", ent.getAltura());
		add(elem, "peso", ent.getPeso());
		participaciones = add(elem, "participaciones");
	}

	public void add(Entrada ent) {
		var edep = deportes.get(ent.getDeporte());
		if (edep == null) {
			edep = add(participaciones, "deporte", "nombre", ent.getDeporte());
			deportes.put(ent.getDeporte(), edep);
		}
		var part = add(edep, "participacion");
		add(part, "edad", ent.getEdad());
		add(part, "equipo", ent.getEquipo(), "noc", ent.getNoc());
		add(part, "juegos", ent.getJuegos());
		add(part, "evento", ent.getEvento());
		add(part, "medalla", ent.getMedalla());
	}

	public void dumpInto(Node root) {
		root.appendChild(elem);
	}
}
