package csvutil;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * CsvReader
 * @author Aimar Ibarra
 */
public class CsvReader implements Closeable, Iterable<CsvEntry> {
	private final Stream<CsvEntry> entries;

	public CsvReader(File inputFile, int delim) throws IOException {
		entries = Files.lines(inputFile.toPath())
				.map(l -> new CsvEntry(l, delim));

	}

	public CsvReader(File inputFile) throws IOException {
		this(inputFile, ',');
	}

	public Stream<CsvEntry> stream() {
		return entries;
	}

    @Override
    public Iterator<CsvEntry> iterator() {
	return entries.iterator();
    }

	@Override
	public void close() {
		entries.close();
	}

}
