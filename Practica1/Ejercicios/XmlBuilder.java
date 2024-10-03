package Ejercicios;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * XmlBuilder
 * @author Aimar Ibarra
 */
public class XmlBuilder {
	private Document doc;

	public XmlBuilder(Document doc) {
		this.doc = doc;
	}

	public Element add(String name) {
		return doc.createElement(name);
	}
	
	public  Element add(Node root, String name) {
		var e = add(name);
		root.appendChild(e);
		return e;
	}

	public Element add(String name, String content) {
		var e = add(name);
		e.setTextContent(content);
		return e;
	}

	public Element add(Node root, String name, String content) {
		var e = add(name, content);
		root.appendChild(e);
		return e;
	}
	
	public Element add(String name, String content, String attr, String attrcontent) {
		var e = add(name, content);
		e.setAttribute(attr, attrcontent);
		return e;
	}

	public Element add(Node root, String name, String content, String attr, String attrcontent) {
		var e = add(name, content, attr, attrcontent);
		root.appendChild(e);
		return e;
	}
	
	public Element add(String name, String attr, String attrcontent) {
		var e = add(name);
		e.setAttribute(attr, attrcontent);
		return e;
	}

	public Element add(Node root, String name, String attr, String attrcontent) {
		var e = add(name, attr, attrcontent);
		root.appendChild(e);
		return e;
	}
}
