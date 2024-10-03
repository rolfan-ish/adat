package Ejercicios;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class Handler extends DefaultHandler {
}

/**
 * Ejercicio3
 * 
 * @author Aimar Ibarra
 */
public class Ejercicio3 {
	final private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		new Ejercicio3().run();
	}

	private int menu() {
		System.out.println("1. Crear fichero XML de olimpiadas");
		System.out.println("2. Crear un fichero XML de deportistas");
		System.out.println("3. Listado de olimpiadas");
		return sc.nextInt();
	}

	public void run() {
		switch (menu()) {
			case 1:
				crearOlimpiadaXml(new File("olimpiads.csv"), new File("olimpiadas.xml"));
				break;
			case 2:
				crearEventosXml(new File("athlete_events.csv"), new File("deportistas.xml"));
				break;
			case 3:
				listado();
				break;
		}
	}

	private void crearOlimpiadaXml(final File input, final File output) {
		var olim = new OlimpiadaXml();
		olim.parseCsv(input);
		olim.dump(output);
	}

	private void crearEventosXml(final File input, final File output) {
		var tabla = new TablaDeportistaXml();
		tabla.parseCsv(input);
		tabla.dump(output);
	}

	private void listado() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();

			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {
				boolean isGames = false;
				String year = "";

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					if (qName.equalsIgnoreCase("olimpiada")) {
						year = attributes.getValue("year");
					}
					if (qName.equalsIgnoreCase("juegos")) {
						isGames = true;
					}
				}

				public void characters(char ch[], int start, int length) throws SAXException {
					if (isGames) {
						System.out.println("Games: " + new String(ch, start, length) + ", Year: " + year);
						isGames = false;
					}
				}

				public void endElement(String uri, String localName, String qName) throws SAXException {
				}
			};

			saxParser.parse("olimpiadas.xml", handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
