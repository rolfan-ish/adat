package csvutil;

import java.util.Iterator;

/**
 * CsvEntry
 * 
 * @author Aimar Ibarra
 */
public class CsvEntry implements Iterable<String>, Iterator<String> {
	private final String line;
	private final int delim;
	private int index = 0;

	public CsvEntry(String line, int delim) {
		this.line = line;
		this.delim = delim;
	}

	public CsvEntry(String line) {
		this(line, ',');
	}

	@Override
	public Iterator<String> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return index < line.length();
	}

	private void nextToken() {
		index = line.indexOf(delim, index);
		if (index == -1)
			index = line.length();
		else
			index++;
	}

	private String readQuote() {
		index++;
		int start = index;
		int end = line.indexOf('"', start);
		index = end;
		nextToken();
		return line.substring(start, end);
	}

	@Override
	public String next() {
		if (!hasNext())
			return null;
		if (line.charAt(index) == '"')
			return readQuote();
		int start = index;
		nextToken();
		if (line.charAt(index - 1) == ',')
			return line.substring(start, index - 1);
		return line.substring(start, index);
	}
}
