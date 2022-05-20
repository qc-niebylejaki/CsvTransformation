package qc.tutorial.components;

import qc.tutorial.dto.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

/**
 * @author Daniel Kąckowski
 * @copyright Blue Media SA
 */
public class CsvReader implements AutoCloseable {

    private static final long HEADER_INDEX = 1;

    private BufferedReader reader;

    public CsvReader(String inFileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(inFileName));
    }

    public Stream<Person> getTransactions() {
        return reader.lines()
                .skip(HEADER_INDEX)
                .map(this::parseLine)
                .map(this::parsePerson);
    }

    private String[] parseLine(String line) {
        return line.split(",");
    }

    private Person parsePerson(String[] personRecord) {
        return Person.builder()
                .firstName(personRecord[0])
                .lastName(personRecord[1])
                .build();
    }

    @Override
    public void close() throws Exception {
        if (reader != null) {
            reader.close();
        }
    }
}
