package qc.tutorial;

import lombok.extern.slf4j.Slf4j;
import qc.tutorial.components.CsvReader;
import qc.tutorial.components.CsvWriter;
import qc.tutorial.dto.Employee;
import qc.tutorial.dto.Person;
import qc.tutorial.service.EmployeeConverterService;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class CsvTransformation {

    private static final String CSV_IN_FILE_NAME = "src/main/resources/input.csv";
    private static final String CSV_OUT_FILE_NAME = "src/main/resources/output.csv";

    private static EmployeeConverterService employeeConverterService = new EmployeeConverterService();

    public static void main(String[] args) throws Exception {
        try (CsvReader csvReader = new CsvReader(CSV_IN_FILE_NAME);
             CsvWriter csvWriter = new CsvWriter(CSV_OUT_FILE_NAME)) {
            csvWriter.writeHeader();
            final AtomicLong lineCounter = new AtomicLong(0);
            csvReader.getTransactions()
                    .forEach(person -> CsvTransformation.processLine(csvWriter, person, lineCounter.incrementAndGet()));
        }
    }

    public static void processLine(CsvWriter csvWriter, Person person, long lineCounter) {
        try {
            log.info("{}: {}", lineCounter, person);
            Employee employee = employeeConverterService.convert(person);
            csvWriter.writeLine(employee);
        } catch (Exception e) {
            log.error("Exception {} with person {}", e.getMessage(), person);
        }
    }
}
