package qc.tutorial.components;

import qc.tutorial.dto.Employee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter implements AutoCloseable {

    private static final String COLUMN_SEPARATOR = ",";
    private static final String LINE_SEPARATOR = "\n";

    private final BufferedWriter writer;

    public CsvWriter(String outFileName) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(outFileName, false));
    }

    public void writeHeader() throws IOException {
        writer.append("name")
                .append(COLUMN_SEPARATOR)
                .append("position")
                .append(COLUMN_SEPARATOR)
                .append("phone number")
                .append(COLUMN_SEPARATOR)
                .append("email");
    }

    public void writeLine(Employee employee) throws IOException {
        writer.append(LINE_SEPARATOR)
                .append(employee.getName())
                .append(COLUMN_SEPARATOR)
                .append(employee.getPosition())
                .append(COLUMN_SEPARATOR)
                .append(employee.getPhoneNumber())
                .append(COLUMN_SEPARATOR)
                .append(employee.getEmail());
    }

    @Override
    public void close() throws Exception {
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }
}
