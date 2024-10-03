package Ejercicios;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import csvutil.CsvReader;

/**
 * TablaDeportistaXml
 * @author Aimar Ibarra
 */
public class TablaDeportistaXml {
	private Document document;

	public TablaDeportistaXml() {
	    try {
		var factory = DocumentBuilderFactory.newInstance();
		var builder = factory.newDocumentBuilder();
		document = builder.newDocument();
	    } catch (ParserConfigurationException e) {
		e.printStackTrace();
	    }
	}

	private Element createElement(Node parent, String name) {
		var e = document.createElement(name);
		parent.appendChild(e);
		return e;
	}

	public void parseCsv(File input) {
		try (var reader = new CsvReader(input)) {
            var tabla = new Hashtable<String, DeportistaXml>();
            var root = createElement(document, "deportistas");
            reader.stream().skip(1).forEach(entry -> {
            	var ent = new Entrada(entry);
            	var dep = tabla.get(ent.getId());
            	if (dep == null) {
            		dep = new DeportistaXml(document, ent);
            		tabla.put(ent.getId(), dep);
            		dep.dumpInto(root);
            	}
            	dep.add(ent);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void dump(File out) {
		try {
			var transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult(out));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
