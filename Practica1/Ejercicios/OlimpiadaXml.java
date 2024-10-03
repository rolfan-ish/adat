package Ejercicios;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import csvutil.CsvReader;

/**
 * OlimpiadaXml
 * @author Aimar Ibarra
 */
public class OlimpiadaXml {
	private XmlBuilder b;
	private Element elem;
	private Document doc;
	public OlimpiadaXml() {
		try {
		doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		b = new XmlBuilder(doc);
		elem = b.add(doc, "olimpiadas");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parseCsv(File in) {
		
		try (var reader = new CsvReader(in)) {
			reader.stream().map(Olimpiada::new)
				.sorted().forEach(o -> {
						var ol = b.add(elem, "olimpiada", "year", Integer.toString(o.getAnio()));
						b.add(ol, "juegos", o.getJuegos());
						b.add(ol, "temporada", o.getTemporada());
						b.add(ol, "ciudad", o.getCiudad());
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void dump(File out) {
		try {
			var transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(doc), new StreamResult(out));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
