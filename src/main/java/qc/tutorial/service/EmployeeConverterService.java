package qc.tutorial.service;

import qc.tutorial.dto.Employee;
import qc.tutorial.dto.Person;

import java.util.Locale;

/**
 * @author Daniel Kąckowski
 * @copyright Blue Media SA
 */
public class EmployeeConverterService {

    public Employee convert(Person person) {
        return Employee.builder()
                .name(person.getFirstName() + " " + person.getLastName())
                .position("EMPLOYEE")
                .email((person.getFirstName() + "." + person.getLastName() + "@work.pl").toLowerCase(Locale.ROOT))
                .phoneNumber("48999888777")
                .build();
    }
}
